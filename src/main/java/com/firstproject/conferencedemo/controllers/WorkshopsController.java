package com.firstproject.conferencedemo.controllers;

import com.firstproject.conferencedemo.models.Workshop;
import com.firstproject.conferencedemo.repositories.WorkshopRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/workshops")
public class WorkshopsController {
    @Autowired
    private WorkshopRepository workshopRepository;

    @GetMapping
    public List<Workshop> list(){
        return this.workshopRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Workshop get(@PathVariable Long id){
        return this.workshopRepository.getOne(id);
    }

    @PostMapping
    public Workshop create(@RequestBody final Workshop workshop){
        return this.workshopRepository.saveAndFlush(workshop);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Workshop update(@PathVariable Long id, @RequestBody Workshop workshop){
        Workshop existingWorkshop = this.workshopRepository.getOne(id);
        BeanUtils.copyProperties(workshop, existingWorkshop, "id");
        return this.workshopRepository.saveAndFlush(existingWorkshop);
    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        this.workshopRepository.deleteById(id);
    }
}
