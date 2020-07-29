package com.rysia.conferencedemo.controllers;

import com.rysia.conferencedemo.dto.WorkshopDTO;
import com.rysia.conferencedemo.models.Workshop;
import com.rysia.conferencedemo.repositories.WorkshopRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1")
public class WorkshopsController {
    @Autowired
    private WorkshopRepository workshopRepository;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "LIST ALL WORKSHOPS")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Workshops Not Found")})
    @GetMapping("/workshops")
    public ResponseEntity<List<Workshop>> list() {
        List<Workshop> workshops = this.workshopRepository.findAll();

        if (workshops.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        workshops.stream().forEach(workshop -> workshop.add(linkTo(methodOn(WorkshopsController.class)
                .get(workshop.getId()))
                .withSelfRel()));

        return new ResponseEntity<List<Workshop>>(workshops, HttpStatus.OK);
    }

    @ApiOperation(value = " GET A UNIQUE WORKSHOP")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Worshop Not Found")})
    @GetMapping("/workshop/{id}")
    public ResponseEntity<Workshop> get(@PathVariable(value = "id") Long id) {
        Optional<Workshop> optionalWorkshop = this.workshopRepository.findById(id);

        if (!optionalWorkshop.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        optionalWorkshop.get().add(linkTo(methodOn(WorkshopsController.class)
                .list())
                .withRel("List of Workshops"));

        return new ResponseEntity<Workshop>(optionalWorkshop.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "CREATE A WORKSHOP")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Workshop Created")})
    @PostMapping("/workshop")
    public ResponseEntity<WorkshopDTO> create(@RequestBody @Valid final Workshop workshop) {
        return new ResponseEntity<WorkshopDTO>(convertEntityToDTO(this.workshopRepository.saveAndFlush(workshop)), HttpStatus.CREATED);
    }

    @ApiOperation(value = "UPDATE A WORKSHOP")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Updated Workshop"),
            @ApiResponse(code = 404, message = "Workshop Not Found")
    })
    @RequestMapping(value = "/workshop/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Workshop> update(@PathVariable(value = "id") Long id, @RequestBody @Valid final Workshop workshop) {
        Optional<Workshop> optionalWorkshop = this.workshopRepository.findById(id);
        boolean existsWorshop = optionalWorkshop.isPresent();
        Workshop existingWorkshop = new Workshop();

        if (existsWorshop) {
            existingWorkshop = optionalWorkshop.get();
            BeanUtils.copyProperties(workshop, existingWorkshop, "id");
        }
        return existsWorshop ? new ResponseEntity<Workshop>(this.workshopRepository
                .saveAndFlush(existingWorkshop), HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "DELETE A WORKSHOP")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleted Workshop"),
            @ApiResponse(code = 404, message = "Workshop Type Not Found")
    })
    @RequestMapping(value = "/workshop/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        if (Optional.ofNullable(this.workshopRepository.findById(id)).isPresent()) {
            this.workshopRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private WorkshopDTO convertEntityToDTO(Workshop workshop) {
        return modelMapper.map(workshop, WorkshopDTO.class);
    }
}
