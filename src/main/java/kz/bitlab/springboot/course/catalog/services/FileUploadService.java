package kz.bitlab.springboot.course.catalog.services;

import kz.bitlab.springboot.course.catalog.model.Course;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    boolean uploadContent(MultipartFile file, Long courseId);
}
