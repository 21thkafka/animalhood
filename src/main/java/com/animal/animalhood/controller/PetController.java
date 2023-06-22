package com.animal.animalhood.controller;


import com.animal.animalhood.domain.Image;
import com.animal.animalhood.domain.Pet;
import com.animal.animalhood.file.FileUploadHandler;
import com.animal.animalhood.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
                           @RequestParam("petAge") int age,
                           @Validated @RequestParam("files") MultipartFile file,
                           @Value("${imgLocation}") String imgPath) throws Exception{
        Long petId = petService.savePat(memberId, name, age);

        Pet addpet = petService.findPat(petId);
        Image image = fileUploadHandler.uploadFile(file, imgPath);

        image.setPet(addpet);
        Long imgNo = petService.saveImg(image);
        System.out.println("imgNo : " + imgNo);

        return "redirect:/myPage";
    }

 /*   @PostMapping("/image")
    public String saveImg (@Validated @RequestParam("files") MultipartFile file)
            throws Exception {
        Pet addpet = petService.findPat(1L);
        Image image = fileUploadHandler.uploadFile(file);

        image.setPet(addpet);
        Long imgNo = petService.saveImg(image);
        System.out.println("imgNo : " + imgNo);

        return "redirect:/myPage";
    }*/
}
