package com.rysia.conferencedemo.controllers;

import com.rysia.conferencedemo.models.Speaker;
import com.rysia.conferencedemo.repositories.SpeakerRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1")
public class SpeakersController {
    @Autowired
    private SpeakerRepository speakerRepository;

    @ApiOperation(value="LIST ALL SPEAKERS")
    @GetMapping("/speakers")
    public List<Speaker> list() {
        return this.speakerRepository.findAll();
    }

    @ApiOperation(value="GET A UNIQUE SPEAKER")
    @GetMapping("/speaker/{id}")
    public Speaker get(@PathVariable Long id) {
        return this.speakerRepository.getOne(id);
    }

    @ApiOperation(value="CREATE A SPEAKER")
    @PostMapping("/speaker")
    @ResponseStatus(HttpStatus.CREATED)
    public Speaker create(@RequestBody final Speaker speaker) {
        return this.speakerRepository.saveAndFlush(speaker);
    }

    @ApiOperation(value="DELETE A SPEAKER")
    @RequestMapping(value = "/speaker/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.speakerRepository.deleteById(id);
    }

    @ApiOperation(value="UPDATE A SPEAKER")
    @RequestMapping(value = "/speaker/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker) {
        Speaker existingSpeaker = this.speakerRepository.getOne(id);
        BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
        return this.speakerRepository.saveAndFlush(existingSpeaker);
    }
}
