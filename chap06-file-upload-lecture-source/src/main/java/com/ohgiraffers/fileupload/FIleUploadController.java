package com.ohgiraffers.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FIleUploadController {

    @PostMapping("/single-file")
    public String singleFileUpload(@RequestParam MultipartFile singleFile,
        @RequestParam String singleFileDescription, Model model) {
        System.out.println("singleFile: " + singleFile);
        System.out.println("singleFileDescription: " + singleFileDescription);

        String root = "src/main/resources/static";
        String filePath = root + "/uploadFiles";
        File dir = new File(filePath);
        System.out.println(dir.getAbsolutePath());

        if (!dir.exists()) {
            dir.mkdirs();
        }

        String originalFileName = singleFile.getOriginalFilename();
        String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
        System.out.println("ext: " + ext);

        String savedName = UUID.randomUUID() + ext;
        System.out.println("savedName: " + savedName);

        try {
            singleFile.transferTo(new File(filePath + "/" + savedName));
            model.addAttribute("message", "파일 업로드 완료!");
        } catch (IOException e) {
            model.addAttribute("message", "파일 업로드 실패!");
            System.out.println(e.getMessage());
        }

        return "result";
    }

    @PostMapping("/multi-file")
    public String multiFileUpload(@RequestParam String multiFileDescription,
        @RequestParam List<MultipartFile> multiFile, Model model) {

        System.out.println("multiFileDescription: " + multiFileDescription);
        System.out.println("multiFiles: " + multiFile);

        String root = "src/main/resources/static";
        String filePath = root + "/uploadFiles";
        File dir = new File(filePath);

        System.out.println("dir.getAbsolutePath(): " + dir.getAbsolutePath());

        if (!dir.exists()) {
            dir.mkdirs();
        }

        List<FileDTO> files = new ArrayList<>();

        try {
            for (MultipartFile file : multiFile) {
                String originFileName = file.getOriginalFilename();
                System.out.println("originFileName: " + originFileName);

                String ext = originFileName.substring(originFileName.lastIndexOf("."));
                String savedName = UUID.randomUUID() + ext;

                files.add(new FileDTO(originFileName, savedName, filePath, multiFileDescription));

                file.transferTo(new File(filePath + "/" + savedName));

                model.addAttribute("message", "파일 업로드 완료!");
            }
        } catch (IOException e) {
            for (FileDTO file : files) {
                new File(filePath + "/" + file.getSavedName()).delete();
            }
            model.addAttribute("message", "파일 업로드 실패!");
        }

        return "result";
    }
}
