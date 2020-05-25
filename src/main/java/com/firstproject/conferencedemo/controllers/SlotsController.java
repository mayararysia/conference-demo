package com.firstproject.conferencedemo.controllers;

import com.firstproject.conferencedemo.dto.SlotDTO;
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

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseBody
    public List<SlotDTO> list() {
        List<Slot> slots = this.slotRepository.findAll();
        return slots.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping
    @RequestMapping("{id}")
    @ResponseBody
    public SlotDTO get(@PathVariable Long id) {
        return convertToDTO(this.slotRepository.getOne(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Slot create(@RequestBody final SlotDTO slotDTO) {
        Slot slot = convertToEntity(slotDTO);
        return this.slotRepository.saveAndFlush(slot);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Slot update(@PathVariable Long id, @RequestBody SlotDTO slotDTO) {
        Slot slot = convertToEntity(slotDTO);
        Slot existingSlot = this.slotRepository.getOne(id);
        BeanUtils.copyProperties(slot, existingSlot, "id");
        return this.slotRepository.saveAndFlush(existingSlot);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.slotRepository.deleteById(id);
    }

    private Slot convertToEntity(SlotDTO slotDTO) {
        Slot slot = modelMapper.map(slotDTO, Slot.class);
        if (slotDTO.getId() != null) {
            Slot oldSlot = slotRepository.getOne(slotDTO.getId());
            slot.setTimeSlotDate(oldSlot.getTimeSlotDate());
            slot.setStartTime(oldSlot.getStartTime());
            slot.setEndTime(oldSlot.getEndTime());
            slot.setKeynoteTimeSlot(oldSlot.getKeynoteTimeSlot());
        }
        return slot;
    }

    private SlotDTO convertToDTO(Slot slot) {
        SlotDTO slotDTO = modelMapper.map(slot, SlotDTO.class);
        slotDTO.setId(slot.getId());
        slotDTO.setTimeSlotDate(slot.getTimeSlotDate());
        slotDTO.setStartTime(slot.getStartTime());
        slotDTO.setEndTime(slot.getEndTime());
        slotDTO.setKeynoteTimeSlot(slot.getKeynoteTimeSlot());
        return slotDTO;
    }
}
