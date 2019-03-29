package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.GymChampionApplication;
import com.gymchampion.GymChampion.exceptions.ResourceAlreadyExistsException;
import com.gymchampion.GymChampion.exceptions.ResourceDoesNotExistException;
import com.gymchampion.GymChampion.model.Session;
import com.gymchampion.GymChampion.service.SessionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/v1/sessions")
public class SessionController {

    private SessionService sessionService;
    private static Logger logger = GymChampionApplication.logger;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<?> addSession(@RequestBody Session session, UriComponentsBuilder ucBuilder) {
        logger.info(String.format("Creating Session with date: %s.", session.getLoginDate()));
        if (this.sessionService.doesSessionExist(session)) {
             logger.error(String.format("Unable to create. A Session with session key %s already exist.", session.getSessionKey()));
            return new ResponseEntity<>(new ResourceAlreadyExistsException("Unable to create. A Session with session key " +
                    session.getSessionKey() + " already exist.").getMessage(), HttpStatus.CONFLICT);
        }
        this.sessionService.addSession(session);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/v1/sessions/id/{id}").buildAndExpand(session.getSessionId()).toUri());
        logger.info("Session created.");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Session>> getAllSessions() {
        logger.info("Fetching all Sessions");
        List<Session> sessions = this.sessionService.getAllSessions();
        if (sessions.isEmpty()) {
            logger.error("Sessions not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }

    @GetMapping("/session_key/{key}")
    public ResponseEntity<?> getSessionBySessionKey(@PathVariable("key") String sessionKey) {
        logger.info(String.format("Fetching Session with session key %s.", sessionKey));
        Session session = this.sessionService.getSessionBySessionKey(sessionKey);
        if (session == null) {
            logger.error(String.format("Session with session key %s not found.", sessionKey));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Session with session key " + sessionKey
                    + " not found.").getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(session, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getSessionById(@PathVariable("id") int id) {
        logger.info(String.format("Fetching Session with id %d.", id));
        Session session = this.sessionService.getSessionById(id);
        if (session == null) {
            logger.error(String.format("Session with id %d not found.", id));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Session with id" + id
                    + " not found.").getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(session, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Session>> getActiveSessions() {
        logger.info("Fetching active Sessions.");
        List<Session> sessions = this.sessionService.getActiveSessions();
        if (sessions.isEmpty()) {
            logger.error("Active sessions not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }

    @GetMapping("/archived")
    public ResponseEntity<List<Session>> getArchivedSessions() {
        logger.info("Fetching archived Sessions.");
        List<Session> sessions = this.sessionService.getArchivedSessions();
        if (sessions.isEmpty()) {
            logger.error("Archived sessions not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> archiveSession(@PathVariable("id") int id) {
        logger.info(String.format("Archiving Session with id %d.", id));
        Session session = this.sessionService.getSessionById(id);
        if (session == null) {
            logger.error(String.format("Unable to archive. Session with id %d not found.", id));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to update. Session with id " +
                    id + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        session.setActive(false);
        this.sessionService.updateSession(session);
        return new ResponseEntity<>(session, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeSession(@PathVariable("id") int id) {
        logger.info(String.format("Fetching & Deleting Session with id %d", id));
        Session session = this.sessionService.getSessionById(id);
        if (session == null) {
            logger.error(String.format("Unable to delete. Session with id %d not found.", id));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to delete. Session with id " +
                    id + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        this.sessionService.removeSessionById(id);
        return new ResponseEntity<Session>(HttpStatus.NO_CONTENT);
    }
}
