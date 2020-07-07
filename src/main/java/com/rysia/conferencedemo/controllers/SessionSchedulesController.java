package com.rysia.conferencedemo.controllers;

import com.rysia.conferencedemo.dto.SessionScheduleDTO;
import com.rysia.conferencedemo.models.SessionSchedule;
import com.rysia.conferencedemo.repositories.SessionScheduleRepository;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1")
public class SessionSchedulesController {

    @Autowired
    private SessionScheduleRepository sessionScheduleRepository;

    @Autowired
    ModelMapper modelMapper;

    @ApiOperation(value = "LIST ALL SESSIONS SCHEDULES")
    @GetMapping("/sessions/schedules")
    public List<SessionSchedule> list() {
        return this.sessionScheduleRepository.findAll();
    }

    @ApiOperation(value = "GET A UNIQUE SESSION SCHEDULE")
    @GetMapping("/session/schedule/{id}")
    @ResponseBody
    public SessionSchedule get(@PathVariable Long id) {
        return this.sessionScheduleRepository.getOne(id);
    }

    @ApiOperation(value = "CREATE A SESSION SCHEDULE")
    @PostMapping("/session/schedule")
    @ResponseStatus(HttpStatus.CREATED)
    public SessionSchedule create(@RequestBody final SessionScheduleDTO sessionScheduleDTO) {
        SessionSchedule sessionSchedule = convertToEntity(sessionScheduleDTO);
        return this.sessionScheduleRepository.saveAndFlush(sessionSchedule);
    }

    @ApiOperation(value = "UPDATE A SESSION SCHEDULE")
    @RequestMapping(value = "/session/schedule/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public SessionSchedule update(@PathVariable Long id, @RequestBody SessionScheduleDTO sessionScheduleDTO) {
        SessionSchedule sessionSchedule = convertToEntity(sessionScheduleDTO);
        SessionSchedule existingSessionSchedule = this.sessionScheduleRepository.getOne(id);
        BeanUtils.copyProperties(sessionSchedule, existingSessionSchedule, "id");
        return this.sessionScheduleRepository.saveAndFlush(existingSessionSchedule);
    }

    @ApiOperation(value = "DELETE A SESSION SCHEDULE")
    @RequestMapping(value = "/session/schedule/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.sessionScheduleRepository.deleteById(id);
    }

    private SessionSchedule convertToEntity(SessionScheduleDTO sessionScheduleDTO) {
        SessionSchedule sessionSchedule = modelMapper.map(sessionScheduleDTO, SessionSchedule.class);
        return sessionSchedule;
    }
}
