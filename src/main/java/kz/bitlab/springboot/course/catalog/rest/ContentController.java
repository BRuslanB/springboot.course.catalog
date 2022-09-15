package kz.bitlab.springboot.course.catalog.rest;

import kz.bitlab.springboot.course.catalog.dto.CourseDTO;
import kz.bitlab.springboot.course.catalog.services.impl.CourseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.codehaus.groovy.control.io.InputStreamReaderSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
@RequiredArgsConstructor
public class ContentController {

    @Value("${uploadContentURL}")
    private String fileUploadContentURL;

    @Value("${uploadAvatarURL}")
    private String fileUploadAvatarURL;

    @GetMapping(value = "/contents/{url}", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody byte[] content(@PathVariable(name = "url", required = false) String url) throws IOException {
        String pdfURL = fileUploadContentURL + "default_content.pdf";
        if (!url.isEmpty()){
            pdfURL = fileUploadContentURL +  url + ".pdf";
        }
        InputStream in;
        try{
            ClassPathResource classPathResource = new ClassPathResource(pdfURL);
            in = classPathResource.getInputStream();
        }catch(Exception e){
            pdfURL = fileUploadContentURL + "default_content.pdf";
            ClassPathResource classPathResource = new ClassPathResource(pdfURL);
            in = classPathResource.getInputStream();
        }
        return IOUtils.toByteArray(in);
    }

    @GetMapping(value = "/avatars/{url}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] avatar(@PathVariable(name = "url", required = false) String url) throws IOException{
        String picURL = fileUploadAvatarURL + "default_avatar.jpg";
        if (!url.isEmpty()) {
            picURL = fileUploadAvatarURL +  url + ".jpg";
        }
        InputStream in;
        try{
            ClassPathResource classPathResource = new ClassPathResource(picURL);
            in = classPathResource.getInputStream();
        }catch(Exception e){
            picURL = fileUploadAvatarURL + "default_avatar.jpg";
            ClassPathResource classPathResource = new ClassPathResource(picURL);
            in = classPathResource.getInputStream();
        }
        return IOUtils.toByteArray(in);
    }

//    @PostMapping(value = "{name}",
//                 headers = "Accept=application/json",
//                 produces = "application/pdf")
//    public ResponseEntity<Resource> downloadCourseContent(@PathVariable(name="name") String file_name,
//                                                          HttpServletRequest request,
//                                                          @RequestBody Locale locale) throws IOException {
//        String path_name = "C:/Users/rbaid/IdeaProjects/JavaSpringBoot/springboot.course.catalog/src/main/resources/static/contents/";
//        String full_name = path_name + "default_content.pdf";
//        if (!file_name.isEmpty()){
//            full_name = path_name + file_name + ".pdf";
//        }
//        File filePDF = new File(full_name);
//        HttpHeaders header = new HttpHeaders();
//        header.setContentType(MediaType.parseMediaType("application/pdf"));
//
//        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + full_name);
//        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        header.add("Pragma", "no-cache");
//        header.add("Expires", "0");
//
//        InputStream targetStream = new FileInputStream(filePDF);
//        Resource resource = new InputStreamResource(targetStream);
//        return ResponseEntity.ok()
//                .headers(header)
//                .contentLength(filePDF.length())
//                .contentType(MediaType.parseMediaType("application/pdf"))
//                .body(resource);
//    }
//
//    @GetMapping(value = "{name}")
//    public ResponseEntity<?> getCourseContent(@PathVariable(name="name") String file_name) throws IOException {
//        String path_name = "C:/Users/rbaid/IdeaProjects/JavaSpringBoot/springboot.course.catalog/src/main/resources/static/contents/";
//        String full_name = path_name + "default_content.pdf";
//        if (!file_name.isEmpty()){
//            full_name = path_name + file_name + ".pdf";
//        }
//        File filePDF = new File(full_name);
//        return ResponseEntity.ok()
//                .header("fileName",full_name)
//                .contentType(MediaType.parseMediaType("application/pdf"))
//                .contentLength(filePDF.length())
//                .body(new InputStreamResource(new ByteArrayInputStream(full_name.getBytes())));
//    }
}