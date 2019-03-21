//package com.gymchampion.GymChampion.service;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.type.TypeFactory;
//import com.gymchampion.GymChampion.model.SetScheme;
//import com.gymchampion.GymChampion.repository.SetSchemeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.List;
//
//@Service
//public class SetSchemeService {
//
//    private SetSchemeRepository setSchemeRepository;
//
//    @Autowired
//    public SetSchemeService(SetSchemeRepository repository) {
//        this.setSchemeRepository = repository;
//    }
//
//    public SetScheme addSchemeToRepository(SetScheme scheme)  {
//        return setSchemeRepository.save(scheme);
//
//    }
//
//    public List<SetScheme> addSchemeListToRepository(List<SetScheme> schemeList) {
//        return setSchemeRepository.saveAll(schemeList);
//    }
//}
