package com.rysia.conferencedemo.controllers;

import com.rysia.conferencedemo.models.Speaker;
import com.rysia.conferencedemo.repositories.SpeakerRepository;
import io.swagger.annotations.ApiOperation;
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
public class SpeakersController {
    @Autowired
    private SpeakerRepository speakerRepository;

    @ApiOperation(value = "LIST ALL SPEAKERS")
    @GetMapping("/speakers")
    public ResponseEntity<List<Speaker>> list() {
        List<Speaker> speakers = this.speakerRepository.findAll();
        if (speakers.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        speakers.stream().forEach(speaker -> speaker.add(linkTo(methodOn(SpeakersController.class)
                .get(speaker.getSpeakerId()))
                .withSelfRel()));

        return new ResponseEntity<List<Speaker>>(speakers, HttpStatus.OK);
    }

    @ApiOperation(value = "GET A UNIQUE SPEAKER")
    @GetMapping("/speaker/{id}")
    public ResponseEntity<Speaker> get(@PathVariable(value = "id") Long id) {
        Optional<Speaker> optionalSpeaker = this.speakerRepository.findById(id);

        if (!optionalSpeaker.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        optionalSpeaker.get().add(linkTo(methodOn(SpeakersController.class)
                .list())
                .withRel("List of Speakers"));

        return new ResponseEntity<Speaker>(optionalSpeaker.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "CREATE A SPEAKER")
    @PostMapping("/speaker")
    public ResponseEntity<Speaker> create(@RequestBody @Valid final Speaker speaker) {
        return new ResponseEntity<Speaker>(this.speakerRepository.saveAndFlush(speaker), HttpStatus.CREATED);
    }

    @ApiOperation(value = "UPDATE A SPEAKER")
    @RequestMapping(value = "/speaker/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Speaker> update(@PathVariable(value = "id") Long id, @RequestBody @Valid Speaker speaker) {
        Optional<Speaker> optionalSpeaker = this.speakerRepository.findById(id);
        Speaker existingSpeaker = new Speaker();
        boolean existsSpeaker = optionalSpeaker.isPresent();

        if (existsSpeaker) {
            existingSpeaker = optionalSpeaker.get();
            BeanUtils.copyProperties(speaker, existingSpeaker, "id");
            existingSpeaker.setSpeakerId(id);
        }
        return existsSpeaker ? new ResponseEntity<Speaker>(this.speakerRepository.saveAndFlush(existingSpeaker),
                HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "DELETE A SPEAKER")
    @RequestMapping(value = "/speaker/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        if (Optional.ofNullable(this.speakerRepository.findById(id)).isPresent()) {
            this.speakerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
