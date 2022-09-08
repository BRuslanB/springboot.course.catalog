package kz.bitlab.springboot.course.catalog.api;

import kz.bitlab.springboot.course.catalog.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class SecurityController {

    @Autowired
    private UserServiceImpl userService;

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

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/updatepassword")
    public String updatePassword(@RequestParam(name = "user_old_password") String oldPassword,
                                 @RequestParam(name = "user_new_password") String newPassword,
                                 @RequestParam(name = "user_re_new_password") String repeatNewPassword) {

        if (newPassword.equals(repeatNewPassword)) {
            User updatePassword = userService.updatePassword(getCurrentUser(), oldPassword, newPassword);
            if (updatePassword != null) {
                return "redirect:/profile?success";
            }
        }
        return "redirect:/profile?error";
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