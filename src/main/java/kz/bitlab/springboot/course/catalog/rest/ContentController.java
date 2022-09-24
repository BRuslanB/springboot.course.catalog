package kz.bitlab.springboot.course.catalog.rest;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.*;

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
//            in = new FileInputStream(pdfURL);
        }catch(Exception e){
            pdfURL = fileUploadContentURL + "default_content.pdf";
            ClassPathResource classPathResource = new ClassPathResource(pdfURL);
            in = classPathResource.getInputStream();
//            in = new FileInputStream(pdfURL);
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
//            in = new FileInputStream(picURL);
        }catch(Exception e){
            picURL = fileUploadAvatarURL + "default_avatar.jpg";
            ClassPathResource classPathResource = new ClassPathResource(picURL);
            in = classPathResource.getInputStream();
//            in = new FileInputStream(picURL);
        }
        return IOUtils.toByteArray(in);
    }
}