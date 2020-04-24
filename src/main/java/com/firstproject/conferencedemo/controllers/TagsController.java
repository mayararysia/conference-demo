package com.firstproject.conferencedemo.controllers;

import com.firstproject.conferencedemo.models.Tag;
import com.firstproject.conferencedemo.repositories.TagRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tags")
public class TagsController {
    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public List<Tag> list() {
        return this.tagRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Tag get(@PathVariable Long id) {
        return this.tagRepository.getOne(id);
    }

    @PostMapping
    public Tag create(@RequestBody final Tag tag) {
        return this.tagRepository.saveAndFlush(tag);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Tag update(@PathVariable Long id, @RequestBody Tag tag) {
        Tag existingTag = this.tagRepository.getOne(id);
        BeanUtils.copyProperties(tag, existingTag, "id");
        return this.tagRepository.saveAndFlush(existingTag);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        this.tagRepository.deleteById(id);
    }
}
