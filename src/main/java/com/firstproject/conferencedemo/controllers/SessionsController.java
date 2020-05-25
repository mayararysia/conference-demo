package com.firstproject.conferencedemo.controllers;

import com.firstproject.conferencedemo.dto.SessionDTO;
import com.firstproject.conferencedemo.models.Session;
import com.firstproject.conferencedemo.repositories.SessionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseBody
    public List<Session> list() {
        return this.sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    @ResponseBody
    public Session get(@PathVariable Long id) {
        return this.sessionRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Session create(@RequestBody final SessionDTO sessionDTO) {
        Session session = convertToEntity(sessionDTO);
        return this.sessionRepository.saveAndFlush(session);
    }
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Session update(@PathVariable Long id, @RequestBody SessionDTO sessionDTO) {
        Session session = convertToEntity(sessionDTO);
        Session existingSession = this.sessionRepository.getOne(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return this.sessionRepository.saveAndFlush(existingSession);
    }

    private Session convertToEntity(SessionDTO sessionDTO) {
        Session session = modelMapper.map(sessionDTO, Session.class);
        if (sessionDTO.getSessionId() != null) {
            Session oldSession = sessionRepository.getOne(sessionDTO.getSessionId());
            session.setTags(oldSession.getTags());
            session.setSessionName(oldSession.getSessionName());
            session.setSessionDescription(oldSession.getSessionDescription());
            session.setSessionLength(oldSession.getSessionLength());
        }
        return session;
    }
}
