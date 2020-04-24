package com.firstproject.conferencedemo.controllers;

import com.firstproject.conferencedemo.models.SessionSchedule;
import com.firstproject.conferencedemo.repositories.SessionScheduleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sessions/schedules")
public class SessionSchedulesController {

    @Autowired
    private SessionScheduleRepository sessionScheduleRepository;

    @GetMapping
    public List<SessionSchedule> list() {
        return this.sessionScheduleRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public SessionSchedule get(@PathVariable Long id) {
        return this.sessionScheduleRepository.getOne(id);
    }

    @PostMapping
    public SessionSchedule create(@RequestBody final SessionSchedule SessionSchedule) {
        return this.sessionScheduleRepository.saveAndFlush(SessionSchedule);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public SessionSchedule update(@PathVariable Long id, @RequestBody SessionSchedule SessionSchedule) {
        SessionSchedule existingSessionSchedule = this.sessionScheduleRepository.getOne(id);
        BeanUtils.copyProperties(SessionSchedule, existingSessionSchedule, "id");
        return this.sessionScheduleRepository.saveAndFlush(existingSessionSchedule);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        this.sessionScheduleRepository.deleteById(id);
    }
}
