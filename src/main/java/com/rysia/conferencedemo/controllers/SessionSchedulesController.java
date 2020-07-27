package com.rysia.conferencedemo.controllers;

import com.rysia.conferencedemo.dto.SessionScheduleDTO;
import com.rysia.conferencedemo.models.SessionSchedule;
import com.rysia.conferencedemo.repositories.SessionScheduleRepository;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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
    public ResponseEntity<List<SessionSchedule>> list() {
        List<SessionSchedule> sessionSchedules = this.sessionScheduleRepository.findAll();
        if (sessionSchedules.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        sessionSchedules.stream().forEach(sessionSchedule ->
                sessionSchedule.add(linkTo(methodOn(SessionSchedulesController.class)
                        .get(sessionSchedule.getId()))
                        .withSelfRel()));

        return new ResponseEntity<List<SessionSchedule>>(sessionSchedules, HttpStatus.OK);
    }

    @ApiOperation(value = "GET A UNIQUE SESSION SCHEDULE")
    @GetMapping("/session/schedule/{id}")
    public ResponseEntity<SessionSchedule> get(@PathVariable(value = "id") Long id) {
        Optional<SessionSchedule> optionalSessionSchedule = this.sessionScheduleRepository.findById(id);

        if (!optionalSessionSchedule.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        optionalSessionSchedule.get().add(linkTo(methodOn(SessionSchedulesController.class)
                .list())
                .withRel("List of Session Schedules"));

        return new ResponseEntity<SessionSchedule>(optionalSessionSchedule.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "CREATE A SESSION SCHEDULE")
    @PostMapping("/session/schedule")
    public ResponseEntity<SessionScheduleDTO> create(@RequestBody @Valid final SessionScheduleDTO sessionScheduleDTO) {
        return new ResponseEntity<SessionScheduleDTO>(convertEntityToDTO(this.sessionScheduleRepository
                .saveAndFlush(convertToEntity(sessionScheduleDTO))), HttpStatus.CREATED);
    }

    @ApiOperation(value = "UPDATE A SESSION SCHEDULE")
    @RequestMapping(value = "/session/schedule/{id}", method = RequestMethod.PUT)
    public ResponseEntity<SessionSchedule> update(@PathVariable(value = "id") Long id, @RequestBody @Valid SessionScheduleDTO sessionScheduleDTO) {
        Optional<SessionSchedule> optionalSessionSchedule = this.sessionScheduleRepository.findById(id);
        boolean existsSessionSchedule = optionalSessionSchedule.isPresent();
        SessionSchedule existingSessionSchedule = new SessionSchedule();

        if (existsSessionSchedule) {
            existingSessionSchedule = optionalSessionSchedule.get();
            BeanUtils.copyProperties(convertToEntity(sessionScheduleDTO), existingSessionSchedule, "id");
        }

        return existsSessionSchedule ? new ResponseEntity<SessionSchedule>(this.sessionScheduleRepository
                .saveAndFlush(existingSessionSchedule), HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "DELETE A SESSION SCHEDULE")
    @RequestMapping(value = "/session/schedule/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        if (Optional.ofNullable(this.sessionScheduleRepository.findById(id).isPresent()).isPresent()) {
            this.sessionScheduleRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private SessionSchedule convertToEntity(SessionScheduleDTO sessionScheduleDTO) {
        SessionSchedule sessionSchedule = modelMapper.map(sessionScheduleDTO, SessionSchedule.class);
        return sessionSchedule;
    }

    private SessionScheduleDTO convertEntityToDTO(SessionSchedule sessionSchedule) {
        return modelMapper.map(sessionSchedule, SessionScheduleDTO.class);
    }
}
