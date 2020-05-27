package com.firstproject.conferencedemo.controllers;

import com.firstproject.conferencedemo.dto.SessionScheduleDTO;
import com.firstproject.conferencedemo.models.SessionSchedule;
import com.firstproject.conferencedemo.repositories.SessionScheduleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sessions/schedules")
public class SessionSchedulesController {

    @Autowired
    private SessionScheduleRepository sessionScheduleRepository;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    @ResponseBody
    public List<SessionSchedule> list() {
        return this.sessionScheduleRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    @ResponseBody
    public SessionSchedule get(@PathVariable Long id) {
        return this.sessionScheduleRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SessionSchedule create(@RequestBody final SessionScheduleDTO sessionScheduleDTO) {
        SessionSchedule sessionSchedule = convertToEntity(sessionScheduleDTO);
        return this.sessionScheduleRepository.saveAndFlush(sessionSchedule);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public SessionSchedule update(@PathVariable Long id, @RequestBody SessionScheduleDTO sessionScheduleDTO) {
        SessionSchedule sessionSchedule = convertToEntity(sessionScheduleDTO);
        SessionSchedule existingSessionSchedule = this.sessionScheduleRepository.getOne(id);
        BeanUtils.copyProperties(sessionSchedule, existingSessionSchedule, "id");
        return this.sessionScheduleRepository.saveAndFlush(existingSessionSchedule);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.sessionScheduleRepository.deleteById(id);
    }

    private SessionSchedule convertToEntity(SessionScheduleDTO sessionScheduleDTO) {
        SessionSchedule sessionSchedule = modelMapper.map(sessionScheduleDTO, SessionSchedule.class);
        return sessionSchedule;
    }
}
