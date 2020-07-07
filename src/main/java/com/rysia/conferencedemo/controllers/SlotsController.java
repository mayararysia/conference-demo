package com.rysia.conferencedemo.controllers;

import com.rysia.conferencedemo.models.Slot;
import com.rysia.conferencedemo.repositories.SlotRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1")
public class SlotsController {
    @Autowired
    private SlotRepository slotRepository;

    @ApiOperation(value="LIST ALL TIME SLOTS")
    @GetMapping("/slots")
    public List<Slot> list() {
        return this.slotRepository.findAll();
    }

    @ApiOperation(value="GET A UNIQUE TIME SLOT")
    @GetMapping("/slot/{id}")
    public Slot get(@PathVariable Long id) {
        return this.slotRepository.getOne(id);
    }

    @ApiOperation(value="CREATE A TIME SLOT")
    @PostMapping("/slot")
    @ResponseStatus(HttpStatus.CREATED)
    public Slot create(@RequestBody final Slot slot) {
        return this.slotRepository.saveAndFlush(slot);
    }

    @ApiOperation(value="UPDATE A TIME SLOT")
    @RequestMapping(value = "/slot/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Slot update(@PathVariable Long id, @RequestBody Slot slot) {
        Slot existingSlot = this.slotRepository.getOne(id);
        BeanUtils.copyProperties(slot, existingSlot, "id");
        return this.slotRepository.saveAndFlush(existingSlot);
    }

    @ApiOperation(value="DELETE A TIME SLOT")
    @RequestMapping(value = "/slot/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.slotRepository.deleteById(id);
    }
}
