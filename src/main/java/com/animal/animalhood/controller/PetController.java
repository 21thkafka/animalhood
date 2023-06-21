package com.animal.animalhood.controller;


import com.animal.animalhood.domain.Image;
import com.animal.animalhood.domain.Pet;
import com.animal.animalhood.file.FileUploadHandler;
import com.animal.animalhood.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PetController {

    final private PetService petService;
    final private FileUploadHandler fileUploadHandler;

    @PostMapping("/pet")
    public String savePat (@RequestParam("memberId") Long memberId,
                           @RequestParam("petName") String name,
                           @RequestParam("petAge") int age){
        petService.savePat(memberId, name, age);

        return "redirect:/myPage";
    }

    @PostMapping("/image")
    public String saveImg (@Validated @RequestParam("files") List<MultipartFile> files)
            throws Exception {
        Pet addpet = petService.findPat(1L);
        List<Image> images = fileUploadHandler.multiUpload(files);
        for(Image image : images){
            image.setPet(addpet);
            Long imgNo = petService.saveImg(image);
            System.out.println("imgNo : " + imgNo);
        }
        return "redirect:/myPage";
    }
}
