package com.firstproject.conferencedemo.controllers;

import com.firstproject.conferencedemo.models.Workshop;
import com.firstproject.conferencedemo.repositories.WorkshopRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/workshops")
public class WorkshopsController {
    @Autowired
    private WorkshopRepository workshopRepository;

    @GetMapping
    @ResponseBody
    public List<Workshop> list(){
        return this.workshopRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    @ResponseBody
    public Workshop get(@PathVariable Long id){
        return this.workshopRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Workshop create(@RequestBody final Workshop workshop){
        return this.workshopRepository.saveAndFlush(workshop);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Workshop update(@PathVariable Long id, @RequestBody Workshop workshop){
        Workshop existingWorkshop = this.workshopRepository.getOne(id);
        BeanUtils.copyProperties(workshop, existingWorkshop, "id");
        return this.workshopRepository.saveAndFlush(existingWorkshop);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        this.workshopRepository.deleteById(id);
    }
}
