package com.animal.animalhood.file;

import com.animal.animalhood.domain.Image;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Component
public class FileUploadHandler {

    // 루트 경로
  /*  @Value("${imgLocation}")
    private String rootPath;
    //프로젝트 루트 경로에 있는 files 디렉토리
    private final String fileDir = rootPath + "/img/";

    public String getFullPath(String filename) { return fileDir + filename; }*/


    public Image uploadFile(MultipartFile multipartFiles, String imgPath) throws IOException {
        List<Image> fileList = new ArrayList<>();

        if (multipartFiles.isEmpty()){
            return null;
        }

        String originalFilename = multipartFiles.getOriginalFilename();

        // 날짜 가져오기
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentDate = simpleDateFormat.format(new Date());

        //프로젝트 폴더 절대경로 설정
        //String absolutePath = new File("").getAbsolutePath() + "/src/main/resources/static/";

    /*    String path = "img/" + currentDate;
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();  //해당 날짜 디렉토리가 없으면 생성
        }*/

        //이미지 파일
        String ext = extractExt(originalFilename);

        String serverFilename = UUID.randomUUID() + "." + ext;
     //   String serverFilePath = absolutePath + "/img/" +serverFilename;
        String serverFilePath = imgPath +serverFilename;

        // 파일 저장
        multipartFiles.transferTo(new File(serverFilePath));

        Image image = new Image();
        image.setOriginalName(originalFilename);
        image.setServerName(serverFilename);
        image.setServerPath(serverFilePath);

        return image;
    }

    public List<Image> multiUpload(List<MultipartFile> multipartFiles, String imgPath) throws IOException {
        List<Image> fileList = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFiles){
            if(!multipartFile.isEmpty()){
                fileList.add(uploadFile(multipartFile, imgPath));
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
