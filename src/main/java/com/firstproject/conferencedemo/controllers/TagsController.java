package com.firstproject.conferencedemo.controllers;

import com.firstproject.conferencedemo.dto.TagDTO;
import com.firstproject.conferencedemo.models.Tag;
import com.firstproject.conferencedemo.repositories.TagRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tags")
public class TagsController {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseBody
    public List<Tag> list() {
        return this.tagRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    @ResponseBody
    public Tag get(@PathVariable Long id) {
        return this.tagRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tag create(@RequestBody final TagDTO tagDTO) {
        Tag tag = convertToEntity(tagDTO);
        return this.tagRepository.saveAndFlush(tag);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Tag update(@PathVariable Long id, @RequestBody TagDTO tagDTO) {
        Tag tag = convertToEntity(tagDTO);
        Tag existingTag = this.tagRepository.getOne(id);
        BeanUtils.copyProperties(tag, existingTag, "id");
        return this.tagRepository.saveAndFlush(existingTag);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.tagRepository.deleteById(id);
    }

    private Tag convertToEntity(TagDTO tagDTO){
        Tag tag = modelMapper.map(tagDTO, Tag.class);
        if (tagDTO.getDescription() != null) {
           tag.setDescription(tagDTO.getDescription());
        }
        return tag;
    }
}
