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

    public List<Session> getAllSessions() {
        return this.sessionRepository.findAll();
    }

    public Session getSessionById(int id) {
        Optional<Session> optionalSession = this.sessionRepository.findById(id);
        return optionalSession.orElseGet(Session::new);
    }

    public Session getSessionBySessionKey(String key) {
       return this.sessionRepository.findBySessionKey(key);
    }

    public Session addSession(Session session) {
        return this.sessionRepository.save(session);
    }

    public Session deactivateSession(int id) {
        Optional<Session> optionalSession = this.sessionRepository.findById(id);
        if (optionalSession.isPresent()) {
            Session session =  optionalSession.get();
            session.setActive(false);
            return this.sessionRepository.save(session);
        }
        return new Session();
    }

    public List<Session> getActiveSessions() {
        return this.sessionRepository.findAllByActive(true);
    }

    public List<Session> getArchivedSessions() {
        return this.sessionRepository.findAllByActive(false);
    }

}
