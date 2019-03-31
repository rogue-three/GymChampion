package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.Session;
import com.gymchampion.GymChampion.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    private SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public boolean doesSessionExist(Session session) {
        return this.getSessionBySessionKey(session.getSessionKey()) != null;
    }

    public void addSession(Session session) {
        this.sessionRepository.save(session);
    }

    public List<Session> getAllSessions() {
        return this.sessionRepository.findAll();
    }

    public Session getSessionBySessionKey(String key) {
        return this.sessionRepository.findBySessionKey(key);
    }

    public Session getSessionById(int id) {
        Optional<Session> optionalSession = this.sessionRepository.findById(id);
        return optionalSession.orElse(null);
    }

    public List<Session> getActiveSessions() {
        return this.sessionRepository.findAllByActive(true);
    }

    public List<Session> getArchivedSessions() {
        return this.sessionRepository.findAllByActive(false);
    }

    public void updateSession(Session session) {
        this.sessionRepository.save(session);
    }

    public void removeSessionById(int id) {
        Optional<Session> optionalSession = this.sessionRepository.findById(id);
        optionalSession.ifPresent(session -> this.sessionRepository.delete(session));
    }
}
