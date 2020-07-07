package com.rysia.conferencedemo.controllers;

import com.rysia.conferencedemo.dto.SessionDTO;
import com.rysia.conferencedemo.models.Session;
import com.rysia.conferencedemo.repositories.SessionRepository;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class SessionsController {
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "LIST ALL SESSIONS")
    @GetMapping("/sessions")
    @ResponseBody
    public List<Session> list() {
        return this.sessionRepository.findAll();
    }

    @ApiOperation(value = "GET A UNIQUE SESSION")
    @GetMapping("/session/{id}")
    @ResponseBody
    public Session get(@PathVariable Long id) {
        return this.sessionRepository.getOne(id);
    }

    @ApiOperation(value = "CREATE A SESSION")
    @PostMapping("/session")
    @ResponseStatus(HttpStatus.CREATED)
    public Session create(@RequestBody final SessionDTO sessionDTO) {
        Session session = convertToEntity(sessionDTO);
        return this.sessionRepository.saveAndFlush(session);
    }

    @ApiOperation(value = "DELETE A SESSION")
    @RequestMapping(value = "/session/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.sessionRepository.deleteById(id);
    }

    @ApiOperation(value = "UPDATE A SESSION")
    @RequestMapping(value = "/session/{id}", method = RequestMethod.PUT, produces="application/json", consumes="application/json")
    @ResponseStatus(HttpStatus.OK)
    public Session update(@PathVariable Long id, @RequestBody SessionDTO sessionDTO) {
        Session session = convertToEntity(sessionDTO);
        Session existingSession = this.sessionRepository.getOne(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return this.sessionRepository.saveAndFlush(existingSession);
    }

    private Session convertToEntity(SessionDTO sessionDTO) {
        Session session = modelMapper.map(sessionDTO, Session.class);
        return session;
    }
}
