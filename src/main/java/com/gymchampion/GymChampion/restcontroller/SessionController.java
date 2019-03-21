package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.model.Session;
import com.gymchampion.GymChampion.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/session")
public class SessionController {

    private SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public List<Session> getAllSessions() {
        return this.sessionService.getAllSessions();
    }

    @GetMapping("/id/{id}")
    public Session getSessionById(@PathVariable("id") int id) {
        return this.sessionService.getSessionById(id);
    }

    @GetMapping("/session_key/{key}")
    public Session getSessionBySessionKey(@PathVariable("key") String key) {
        return this.sessionService.getSessionBySessionKey(key);
    }

    @PostMapping
    public Session addSession(@RequestBody Session session) {
        return this.sessionService.addSession(session);
    }

    @PutMapping("/{id}")
    public Session deactivateSession(@PathVariable("id") int id) {
        return this.sessionService.deactivateSession(id);
    }

    @GetMapping("/active")
    public List<Session> getActiveSessions() {
        return this.sessionService.getActiveSessions();
    }

    @GetMapping("/archivized")
    public List<Session> getArchivizedSessions() {
        return this.sessionService.getArchivizedSessions();
    }
}
