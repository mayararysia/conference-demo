package com.rysia.conferencedemo.controllers;

import com.rysia.conferencedemo.models.Workshop;
import com.rysia.conferencedemo.repositories.WorkshopRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1")
public class WorkshopsController {
    @Autowired
    private WorkshopRepository workshopRepository;

    @ApiOperation(value="LIST ALL WORKSHOPS")
    @GetMapping("/workshops")
    public List<Workshop> list(){
        return this.workshopRepository.findAll();
    }

    @ApiOperation(value=" GET A UNIQUE WORKSHOP")
    @GetMapping("/workshop/{id}")
    public Workshop get(@PathVariable Long id){
        Workshop workshop = this.workshopRepository.getOne(id);
        if(workshop == null)
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        return workshop;
    }

    @ApiOperation(value="CREATE A WORKSHOP")
    @PostMapping("/workshop")
    @ResponseStatus(HttpStatus.CREATED)
    public Workshop create(@RequestBody final Workshop workshop){
        if(workshop == null)
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        return this.workshopRepository.saveAndFlush(workshop);
    }

    @ApiOperation(value="UPDATE A WORKSHOP")
    @RequestMapping(value = "/workshop/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Workshop update(@PathVariable Long id, @RequestBody final Workshop workshop){
        if(id == null || workshop == null)
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        Workshop existingWorkshop = this.workshopRepository.getOne(id);
        if(existingWorkshop == null)
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        BeanUtils.copyProperties(workshop, existingWorkshop, "id");
        return this.workshopRepository.saveAndFlush(existingWorkshop);
    }

    @ApiOperation(value="DELETE A WORKSHOP")
    @RequestMapping(value = "/workshop/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        if(id == null)
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        this.workshopRepository.deleteById(id);
    }
}
