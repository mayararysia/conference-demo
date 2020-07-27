package com.rysia.conferencedemo.controllers;

import com.rysia.conferencedemo.models.Slot;
import com.rysia.conferencedemo.repositories.SlotRepository;
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
public class SlotsController {
    @Autowired
    private SlotRepository slotRepository;

    @ApiOperation(value = "LIST ALL TIME SLOTS")
    @GetMapping("/slots")
    public ResponseEntity<List<Slot>> list() {
        List<Slot> slots = this.slotRepository.findAll();
        if (slots.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        slots.stream().forEach(slot -> slot.add(linkTo(methodOn(SlotsController.class)
                .get(slot.getId()))
                .withSelfRel()));

        return new ResponseEntity<List<Slot>>(slots, HttpStatus.OK);
    }

    @ApiOperation(value = "GET A UNIQUE TIME SLOT")
    @GetMapping("/slot/{id}")
    public ResponseEntity<Slot> get(@PathVariable(value = "id") Long id) {
        Optional<Slot> optionalSlot = this.slotRepository.findById(id);

        if (!optionalSlot.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        optionalSlot.get().add(linkTo(methodOn(SlotsController.class)
                .list())
                .withRel("List of Slots"));

        return new ResponseEntity<Slot>(optionalSlot.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "CREATE A TIME SLOT")
    @PostMapping("/slot")
    public ResponseEntity<Slot> create(@RequestBody @Valid final Slot slot) {
        return new ResponseEntity<Slot>(this.slotRepository.saveAndFlush(slot), HttpStatus.CREATED);
    }

    @ApiOperation(value = "UPDATE A TIME SLOT")
    @RequestMapping(value = "/slot/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Slot> update(@PathVariable(value = "id") Long id, @RequestBody @Valid Slot slot) {
        Optional<Slot> optionalSlot = this.slotRepository.findById(id);
        boolean existsSlot = optionalSlot.isPresent();
        Slot existingSlot = new Slot();

        if (existsSlot) {
            existingSlot = optionalSlot.get();
            BeanUtils.copyProperties(slot, existingSlot, "id");
        }

        return existsSlot ? new ResponseEntity<Slot>(this.slotRepository.saveAndFlush(existingSlot), HttpStatus.ACCEPTED) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "DELETE A TIME SLOT")
    @RequestMapping(value = "/slot/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        if (Optional.ofNullable(this.slotRepository.findById(id)).isPresent()) {
            this.slotRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
