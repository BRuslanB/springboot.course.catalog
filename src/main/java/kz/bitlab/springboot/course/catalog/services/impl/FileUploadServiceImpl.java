package kz.bitlab.springboot.course.catalog.services.impl;

import kz.bitlab.springboot.course.catalog.model.Course;
import kz.bitlab.springboot.course.catalog.model.User;
import kz.bitlab.springboot.course.catalog.services.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${targetContentURL}")
    private String targetContentURL;

    @Value("${targetAvatarURL}")
    private String targetAvatarURL;

    private final CourseServiceImpl courseService;
    private final UserServiceImpl userService;

    public boolean uploadCourseContent(MultipartFile file, Long courseId) {
        try {
            String fileName = DigestUtils.sha1Hex(courseId + "_springSecurity");
            byte bytes[] = file.getBytes();
            Path path = Paths.get(targetContentURL + "/" + fileName + ".pdf");
            Files.write(path, bytes);

            Course course = courseService.getCourse(courseId);
            if (course != null) {
                course.setContentUrl(fileName);
                course.setAuthor(getCurrentUser());
                courseService.saveCourse(course);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean uploadUserAvatar(MultipartFile file, User user) {
        try {
            String fileName = DigestUtils.sha1Hex(user.getId() + "_springSecurity");
            byte bytes[] = file.getBytes();
            Path path = Paths.get(targetAvatarURL + "/" + fileName + ".jpg");
            Files.write(path, bytes);

            user.setAvatarUrl(fileName);
            userService.saveUser(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }
}