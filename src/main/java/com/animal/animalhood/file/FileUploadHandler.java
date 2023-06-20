package com.animal.animalhood.file;

import com.animal.animalhood.domain.Image;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;
import java.util.UUID;


@Component
public class FileUploadHandler {

    // 루트 경로
    private final String rootPath = "Desktop/test_image";
    //프로젝트 루트 경로에 있는 files 디렉토리
    private final String fileDir = rootPath + "/files/";

    public String getFullPath(String filename) { return fileDir + filename; }


    public Image uploadFile(MultipartFile multipartFiles) throws IOException {
        List<Image> fileList = new ArrayList<>();

        if (multipartFiles.isEmpty()){
            return null;
        }

        String originalFilename = multipartFiles.getOriginalFilename();
        String serverFilename = UUID.randomUUID() + "." + extractExt(originalFilename);

        // 파일 저장
        multipartFiles.transferTo(new File(getFullPath(serverFilename)));

        Image image = new Image();
        image.setOriginalName(originalFilename);
        image.setServerName(serverFilename);

        return image;
    }

    public List<Image> multiUpload(List<MultipartFile> multipartFiles) throws IOException {
        List<Image> fileList = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFiles){
            if(!multipartFile.isEmpty()){
                fileList.add(uploadFile(multipartFile));
            }
        }
        return fileList;
    }

    //확장자 추출
    private String extractExt(String originalFilename){
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
