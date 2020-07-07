package com.rysia.conferencedemo.controllers;

import com.rysia.conferencedemo.dto.TagDTO;
import com.rysia.conferencedemo.models.Tag;
import com.rysia.conferencedemo.repositories.TagRepository;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1")
public class TagsController {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "LIST ALL TAGS")
    @GetMapping("/tags")
    public List<Tag> list() {
        return this.tagRepository.findAll();
    }

    @ApiOperation(value = "GET A UNIQUE TAG")
    @GetMapping("/tag/{id}")
    public Tag get(@PathVariable Long id) {
        return this.tagRepository.getOne(id);
    }

    @ApiOperation(value = "CREATE A TAG")
    @PostMapping("/tag")
    @ResponseStatus(HttpStatus.CREATED)
    public Tag create(@RequestBody final TagDTO tagDTO) {
        Tag tag = convertToEntity(tagDTO);
        return this.tagRepository.saveAndFlush(tag);
    }

    @ApiOperation(value = "UPDATE A TAG")
    @RequestMapping(value = "/tag/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Tag update(@PathVariable Long id, @RequestBody Tag tag) {
        Tag existingTag = this.tagRepository.getOne(id);
        BeanUtils.copyProperties(tag, existingTag, "id");
        return this.tagRepository.saveAndFlush(existingTag);
    }

    @ApiOperation(value = "DELETE A TAG")
    @RequestMapping(value = "/tag/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.tagRepository.deleteById(id);
    }

    private Tag convertToEntity(TagDTO tagDTO) {
        Tag tag = modelMapper.map(tagDTO, Tag.class);
        if (tagDTO.getDescription() != null) {
            tag.setDescription(tagDTO.getDescription());
        }
        return tag;
    }
}
