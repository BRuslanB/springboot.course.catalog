package kz.bitlab.springboot.course.catalog.api;

import kz.bitlab.springboot.course.catalog.model.*;
import kz.bitlab.springboot.course.catalog.services.impl.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CategoryServiceImpl categoryService;
    private final CourseServiceImpl courseService;
    private final FileUploadServiceImpl fileUploadService;
    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;
    private final EnrollCardServiceImpl enrollCardService;

    @GetMapping("/")
    public String indexPage(Model model) {
        return "index";
    }

    @GetMapping("/categorydetails/{categoryId}")
    public String categoryDetailsPage(@PathVariable(name="categoryId") Long categoryId,
                              Model model){
        Category oneCategory = categoryService.getCategory(categoryId);
        model.addAttribute("oneCategory", oneCategory);
        return "categorydetails";
    }

    @GetMapping("/coursedetails/{courseId}")
    public String courseDetailsPage(@PathVariable(name="courseId") Long courseId,
                              Model model){
        Course oneCourse = courseService.getCourse(courseId);
        model.addAttribute("oneCourse", oneCourse);
        return "coursedetails";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    @GetMapping("/categories")
    public String categoriesPage(Model model) {

        List<Category> allCategories= categoryService.getAllCategories();
        model.addAttribute("allCategories", allCategories);

        return "categories";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    @GetMapping("/courses")
    public String coursesPage(Model model) {
        //получить все уникальные категории Автора (тек.пользователя)
        //List<Category> allCategoriesByAuthor= courseService.getAllCategoriesByAuthor(getCurrentUser().getId());
        //model.addAttribute("allCategoriesByAuthor", allCategoriesByAuthor);

        List<Category> allCategories= categoryService.getAllCategories();
        model.addAttribute("allCategories", allCategories);

        List<Course> allCourses= courseService.getAllCourses();
        model.addAttribute("allCourses", allCourses);

        return "courses";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/addcategory")
    public String addCategory(@RequestParam(name = "category_name") String categoryName) {

        Category category = new Category();
        category.setName(categoryName);
        categoryService.addCategory(category);
        return "redirect:/categories";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/editcategory")
    public String saveCategory(@RequestParam(name = "category_id") Long categoryId,
                               @RequestParam(name = "category_name") String categoryName) {

        Category category = categoryService.getCategory(categoryId);
        if (category != null) {
            category.setName(categoryName);
            categoryService.saveCategory(category);
        }
        return "redirect:/categories";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/deletecategory")
    public String deleteCategory(@RequestParam(name = "category_id") Long categoryId) {

        Category category = categoryService.getCategory(categoryId);
        if (category != null)
            categoryService.deleteCategory(category);
        return "redirect:/categories";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/addcourse")
    public String addCourse(@RequestParam(name = "course_name") String courseName,
                            @RequestParam(name = "course_description") String courseDescription,
                            @RequestParam(name = "course_price") double coursePrice,
                            @RequestParam(name = "course_category_id") Long categoryId){

        Course course = new Course();
        Category category = categoryService.getCategory(categoryId);
        if (category != null) {
            course.setCategory(category);
            course.setName(courseName);
            course.setDescription(courseDescription);
            course.setPrice(coursePrice);
            courseService.addCourse(course);
        }
        return "redirect:/courses";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/editcourse")
    public String saveCourse(@RequestParam(name = "course_id") Long courseId,
                             @RequestParam(name = "course_name") String courseName,
                             @RequestParam(name = "course_description") String courseDescription,
                             @RequestParam(name = "course_price") double coursePrice,
                             @RequestParam(name = "course_category_id") Long categoryId) {

        Course course = courseService.getCourse(courseId);
        if (course != null) {
            Category category = categoryService.getCategory(categoryId);
            if (category != null) {
                course.setCategory(category);
                course.setName(courseName);
                course.setDescription(courseDescription);
                course.setPrice(coursePrice);
                courseService.saveCourse(course);
            }
        }
        return "redirect:/courses";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/deletecourse")
    public String deleteCourse(@RequestParam(name = "course_id") Long courseId) {
        Course course = courseService.getCourse(courseId);
        if (course != null)
            courseService.deleteCourse(course);
        return "redirect:/courses";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public String usersPage(Model model) {
        List<User> allUsers= userService.getAllUsers();
        List<Role> allRoles= roleService.getAllRoles();
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("allRoles", allRoles);
        return "users";
    }

    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    @GetMapping("/students")
    public String studentsPage(Model model) {
        //получить все карточки юзеров
        List<EnrollCard> allEnrollCards = enrollCardService.getAllEnrollCards();
        // получить все курсы Автора текущего пользователя (Teacher) из каталога курсов
        List<Course> allCoursesByAuthor = courseService.getAllCoursesByAuthor(getCurrentUser().getId());

        model.addAttribute("allEnrollCards", allEnrollCards);
        model.addAttribute("allCoursesByAuthor", allCoursesByAuthor);
        return "students";
    }


    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    @PostMapping("/setpointstudent")
    public String saveUserProfile(@RequestParam(name = "enroll_card_id") Long enrollCardId,
                                  @RequestParam(name = "enroll_card_point") int studentPoint) {

        EnrollCard enrollCard = enrollCardService.getEnrollCard(enrollCardId);
        if (enrollCard != null) {
            enrollCard.setPoint(studentPoint);
            enrollCardService.saveEnrollCard(enrollCard);
        }
        return "redirect:/students";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/edituserprofile")
    public String saveUserProfile(@RequestParam(name = "user_id") Long userId,
                                  @RequestParam(name = "user_full_name") String fullName,
                                  @RequestParam(name = "user_role1", required = false) Long roleId1,
                                  @RequestParam(name = "user_role2", required = false) Long roleId2,
                                  @RequestParam(name = "user_role3", required = false) Long roleId3) {

        User user = userService.getUser(userId);
        if (user != null) {
            ArrayList<Role> roles = new ArrayList<>();
            if (roleId1 != null) {
                Role userRole1 = roleService.getRole(roleId1);
                roles.add(userRole1);
            }
            if (roleId2 != null) {
                Role userRole2 = roleService.getRole(roleId2);
                roles.add(userRole2);
            }
            if (roleId3 != null) {
                Role userRole3 = roleService.getRole(roleId3);
                roles.add(userRole3);
            }
            user.setRoles(roles);
            user.setFullName(fullName);
            userService.saveUser(user);
        }
        return "redirect:/users";
    }

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
//    @PostMapping("/edituserprofile")
//    public String saveUserProfile(@RequestParam(name = "user_id") Long userId,
//                                  @RequestParam(name = "user_full_name") String fullName,
//                                  @RequestParam(value = "user_roles", required = false) List<Long> rolesList) {
//
//        User user = userService.getUser(userId);
//        if (user != null) {
//            ArrayList<Role> roles = new ArrayList<>();
//            for (roleId : rolesList) {
//                if (roleId != null) {
//                    Role userRole = roleService.getRole(roleId);
//                    roles.add(userRole);
//                }
//            }
//            user.setRoles(roles);
//            user.setFullName(fullName);
//            userService.saveUser(user);
//        }
//        return "redirect:/users";
//    }

    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    @PostMapping(value = "/uploadcontent")
    public String uploadContent(@RequestParam(name = "course_content") MultipartFile file,
                                @RequestParam(name = "course_id") Long courseId) {
        if (file.getContentType().equals("application/pdf")){
            fileUploadService.uploadCourseContent(file, courseId);
        }
        return "redirect:/courses";
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }
}