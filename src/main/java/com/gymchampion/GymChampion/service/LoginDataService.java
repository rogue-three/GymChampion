package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.LoginData;
import com.gymchampion.GymChampion.repository.LoginDataRepository;
import com.gymchampion.GymChampion.security.exceptions.UncorrectPasswordException;
import com.gymchampion.GymChampion.security.exceptions.UserNotExistException;
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
        int correctId = getCountOfLoginDataRecords() + 1;
        loginData.setLoginId(correctId);
        return this.loginDataRepository.save(loginData);
    }

    public void removeLoginData(LoginData loginData) {
        this.loginDataRepository.delete(loginData);
    }


    public LoginData validateUserAndGetLoginData(String login, String password)
            throws UserNotExistException, UncorrectPasswordException {

        LoginData data = this.loginDataRepository.findLoginDataByUserLogin(login);
        if (data == null) {
            throw new UserNotExistException();
        }
        if (data.isArchivized()) {
            throw new UserNotExistException();
        }
        if (!password.equals(data.getPassword())) {
            throw new UncorrectPasswordException();
        }
        return data;
    }

    private int getCountOfLoginDataRecords() {
        return loginDataRepository.countOfLoginDataRecords();
    }
}
