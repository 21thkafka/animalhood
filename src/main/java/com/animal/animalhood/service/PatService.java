package com.animal.animalhood.service;

import com.animal.animalhood.domain.Pat;
import com.animal.animalhood.repository.PatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PatService {

    private final PatRepository patRepository;

    @Transactional
    public Long savePat(Pat pat){
        patRepository.save(pat);
        return pat.getId();
    }

    @Transactional
    public Pat updatePat(Long id, String gubun, String name, int age){
        Pat findPat = patRepository.findOne(id);
        findPat.setPetName(name);
        findPat.setPetAge(age);
        return findPat;
    }

    public List<Pat> findPat(Long id){
        List<Pat> pats = patRepository.findPat(id);
        return pats;
    }
}
