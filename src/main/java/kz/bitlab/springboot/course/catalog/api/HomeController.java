package kz.bitlab.springboot.course.catalog.api;

import kz.bitlab.springboot.course.catalog.services.impl.CourseServiceImpl;
import kz.bitlab.springboot.course.catalog.services.impl.FileUploadServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import kz.bitlab.springboot.course.catalog.model.Category;
import kz.bitlab.springboot.course.catalog.model.Course;
import kz.bitlab.springboot.course.catalog.model.User;
import kz.bitlab.springboot.course.catalog.services.impl.CategoryServiceImpl;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @Value("${uploadURL}")
    private String fileUploadURL;

    private final CategoryServiceImpl categoryService;
    private final CourseServiceImpl courseService;

    private final FileUploadServiceImpl fileUploadService;

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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/categories")
    public String categoriesPage(Model model) {

        List<Category> allCategories= categoryService.getAllCategories();
        model.addAttribute("allCategories", allCategories);

        return "categories";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/courses")
    public String coursesPage(Model model) {

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
                            @RequestParam(name = "course_rating") int courseRating,
                            @RequestParam(name = "course_category_id") Long categoryId){

        Course course = new Course();
        Category category = categoryService.getCategory(categoryId);
        if (category != null) {
            course.setCategory(category);
            course.setName(courseName);
            course.setDescription(courseDescription);
            course.setPrice(coursePrice);
            course.setRating(courseRating);
            course.setAuthor(getCurrentUser());
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
                             @RequestParam(name = "course_rating") int courseRating,
                             @RequestParam(name = "course_category_id") Long categoryId) {

        Course course = courseService.getCourse(courseId);
        if (course != null) {
            Category category = categoryService.getCategory(categoryId);
            if (category != null) {
                course.setCategory(category);
                course.setName(courseName);
                course.setDescription(courseDescription);
                course.setPrice(coursePrice);
                course.setRating(courseRating);
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

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/uploadcontent")
    public String uploadContent(@RequestParam(name = "course_content") MultipartFile file,
                                @RequestParam(name = "course_id") Long courseId) {
        if (file.getContentType().equals("application/pdf")){
            fileUploadService.uploadContent(file, courseId);
        }
        return "redirect:/courses";
    }

    @GetMapping(value = "/content/{url}", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody byte[] content(@PathVariable(name = "url", required = false) String url) throws IOException {
        String pdfURL = fileUploadURL + "default.pdf";
        if (url != null){
            pdfURL = fileUploadURL +  url + ".pdf";
        }
        InputStream in;
        try{
            ClassPathResource classPathResource = new ClassPathResource(pdfURL);
            in = classPathResource.getInputStream();
        }catch(Exception e){
            pdfURL = fileUploadURL + "default.pdf";
            ClassPathResource classPathResource = new ClassPathResource(pdfURL);
            in = classPathResource.getInputStream();
        }
        return IOUtils.toByteArray(in);
    }
}