package kz.bitlab.springboot.course.catalog.api;

import kz.bitlab.springboot.course.catalog.services.impl.FileUploadServiceImpl;
import kz.bitlab.springboot.course.catalog.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import kz.bitlab.springboot.course.catalog.model.User;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class SecurityController {

    private final UserServiceImpl userService;
    private final FileUploadServiceImpl fileUploadService;

    @GetMapping("/enter")
    public String enterPage(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        return "register";
    }

    @PostMapping("/toregister")
    public String toRegister(@RequestParam(name = "user_email") String userEmail,
                             @RequestParam(name = "user_password") String password,
                             @RequestParam(name = "user_re_password") String rePassword,
                             @RequestParam(name = "user_full_name") String fullName) {
        if (password.equals(rePassword)) {
            User newUser = new User();
            newUser.setEmail(userEmail);
            newUser.setPassword(password);
            newUser.setFullName(fullName);
            newUser = userService.registerUser(newUser);
            if (newUser != null) {
                return "redirect:/register?success";
            }
        }
        return "redirect:/register?error";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/resetuserpassword")
    public String resetUserPassword(@RequestParam(name = "user_id") Long userId,
                                 @RequestParam(name = "user_new_password") String newPassword) {

        User user = userService.resetPassword(userId, newPassword);
        if (user != null) {
            return "redirect:/users?success";
        }
        return "redirect:/users?error";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/updatepassword")
    public String updatePassword(@RequestParam(name = "user_old_password") String oldPassword,
                                 @RequestParam(name = "user_new_password") String newPassword,
                                 @RequestParam(name = "user_re_new_password") String repeatNewPassword) {

        if (newPassword.equals(repeatNewPassword)) {
            User user = userService.updatePassword(getCurrentUser(), oldPassword, newPassword);
            if (user != null) {
                return "redirect:/profile?success";
            }
        }
        return "redirect:/profile?error";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/updateprofile")
    public String updateProfile(@RequestParam(name = "user_full_name") String fullName) {

        User user = userService.updateProfile(getCurrentUser(), fullName);
        if (user != null) {
            return "redirect:/profile?success";
        }
        return "redirect:/profile?error";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/uploadavatar")
    public String uploadAvatar(@RequestParam(name = "user_avatar") MultipartFile file){
        if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")){
            fileUploadService.uploadUserAvatar(file, getCurrentUser());
        }
        return "redirect:/profile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String profilePage(Model model) {
        return "profile";
    }

    @GetMapping("/forbidden")
    public String forbiddenPage(Model model) {
        return "403";
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }
}