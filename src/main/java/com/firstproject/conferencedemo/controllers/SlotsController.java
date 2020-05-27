package com.firstproject.conferencedemo.controllers;

import com.firstproject.conferencedemo.models.Slot;
import com.firstproject.conferencedemo.repositories.SlotRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/slots")
public class SlotsController {
    @Autowired
    private SlotRepository slotRepository;

    @GetMapping
    @ResponseBody
    public List<Slot> list() {
        return this.slotRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Slot get(@PathVariable Long id) {
        return this.slotRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Slot create(@RequestBody final Slot slot) {
        return this.slotRepository.saveAndFlush(slot);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Slot update(@PathVariable Long id, @RequestBody Slot slot) {
        Slot existingSlot = this.slotRepository.getOne(id);
        BeanUtils.copyProperties(slot, existingSlot, "id");
        return this.slotRepository.saveAndFlush(existingSlot);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.slotRepository.deleteById(id);
    }
}
