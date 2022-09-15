package kz.bitlab.springboot.course.catalog.services;

import kz.bitlab.springboot.course.catalog.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    boolean uploadCourseContent(MultipartFile file, Long courseId);
    boolean uploadUserAvatar(MultipartFile file, User user);
}
