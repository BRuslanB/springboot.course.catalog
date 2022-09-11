package kz.bitlab.springboot.course.catalog.services.impl;

import kz.bitlab.springboot.course.catalog.model.Course;
import kz.bitlab.springboot.course.catalog.services.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${targetURL}")
    private String targetURL;

    private final CourseServiceImpl courseService;

    public boolean uploadContent(MultipartFile file, Long courseId) {
        try {
            String fileName = DigestUtils.sha1Hex(courseId + "_springSecurity");
            byte bytes[] = file.getBytes();
            Path path = Paths.get(targetURL + "/" + fileName + ".pdf");
            Files.write(path, bytes);

            Course course = courseService.getCourse(courseId);
            if (course != null) {
                course.setContentUrl(fileName);
                courseService.saveCourse(course);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}