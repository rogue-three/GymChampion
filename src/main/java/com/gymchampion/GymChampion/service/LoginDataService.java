package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.LoginData;
import com.gymchampion.GymChampion.repository.LoginDataRepository;
import com.gymchampion.GymChampion.exceptions.UncorrectPasswordException;
import com.gymchampion.GymChampion.exceptions.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginDataService {

    private LoginDataRepository loginDataRepository;

    @Autowired
    public LoginDataService(LoginDataRepository loginDataRepository) {
        this.loginDataRepository = loginDataRepository;
    }

    public boolean doesLoginDataExist(LoginData loginData) {
        return this.loginDataRepository.findByUser_Login(loginData.getUser().getLogin()) != null;
    }

    public List<LoginData> getAllLoginData() {
        return this.loginDataRepository.findAll();
    }

    public LoginData getLoginDataByPassword(String password) {
        return this.loginDataRepository.findByPassword(password);
    }

    public LoginData getLoginDataById(int id) {
        Optional<LoginData> optionalLoginData = this.loginDataRepository.findById(id);
        return optionalLoginData.orElseGet(null);
    }

    public LoginData getLoginDataByLogin(String login) {
        return this.loginDataRepository.findByUser_Login(login);
    }

    public void addLoginData(LoginData loginData) {
        int correctId = getCountOfLoginDataRecords() + 1;
        loginData.setLoginId(correctId);
        this.loginDataRepository.save(loginData);
    }

    public List<LoginData> getLoginDataFromActiveUsers() {
        return this.loginDataRepository.findAllByArchived(false);
    }

    public List<LoginData> getArchivedLoginData() {
        return this.loginDataRepository.findAllByArchived(true);
    }

    public void updateLoginData(LoginData loginData) {
        this.loginDataRepository.save(loginData);
    }

    public void removeLoginData(int id) {
        Optional<LoginData> optionalLoginData = this.loginDataRepository.findById(id);
        optionalLoginData.ifPresent(loginData -> this.loginDataRepository.delete(loginData));
    }

    public LoginData validateUserAndGetLoginData(String login, String password)
            throws UserNotExistException, UncorrectPasswordException {

        LoginData data = this.loginDataRepository.findByUser_Login(login);
        if (data == null) {
            throw new UserNotExistException();
        }
        if (data.isArchived()) {
            throw new UserNotExistException();
        }
        if (!password.equals(data.getPassword())) {
            throw new UncorrectPasswordException();
        }
        return data;
    }

    private int getCountOfLoginDataRecords() {
        return this.loginDataRepository.findAll().size();
    }
}
