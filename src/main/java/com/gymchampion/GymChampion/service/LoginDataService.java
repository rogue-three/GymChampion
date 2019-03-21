package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.LoginData;
import com.gymchampion.GymChampion.repository.LoginDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginDataService {

    private LoginDataRepository loginDataRepository;

    @Autowired
    public LoginDataService(LoginDataRepository loginDataRepository) {
        this.loginDataRepository = loginDataRepository;
    }

    public LoginData getLoginDataById(int id) {
        Optional<LoginData> optionalLoginData = this.loginDataRepository.findById(id);
        if (optionalLoginData.isPresent()) {
            return optionalLoginData.get();
        }
        return new LoginData();
    }

    public LoginData getLoginDataByLogin(String login) {
        return this.loginDataRepository.findLoginDataByUserLogin(login);
    }

    public LoginData addLoginData(LoginData loginData) {
        return this.loginDataRepository.save(loginData);
    }

    public void removeLoginData(LoginData loginData) {
        this.loginDataRepository.delete(loginData);
    }
}
