package com.rysia.conferencedemo.controllers;

import com.rysia.conferencedemo.dto.TagDTO;
import com.rysia.conferencedemo.models.Tag;
import com.rysia.conferencedemo.repositories.TagRepository;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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
    public ResponseEntity<List<Tag>> list() {
        List<Tag> tags = this.tagRepository.findAll();

        if (tags.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        tags.stream().forEach(tag -> tag.add(linkTo(methodOn(WorkshopsController.class)
                .get(tag.getId()))
                .withSelfRel()));

        return new ResponseEntity<List<Tag>>(tags, HttpStatus.OK);
    }

    @ApiOperation(value = "GET A UNIQUE TAG")
    @GetMapping("/tag/{id}")
    public ResponseEntity<Tag> get(@PathVariable(value = "id") Long id) {
        Optional<Tag> optionalTag = this.tagRepository.findById(id);

        if (!optionalTag.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        optionalTag.get().add(linkTo(methodOn(TagsController.class)
                .list())
                .withRel("List of Tags"));

        return new ResponseEntity<Tag>(optionalTag.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "CREATE A TAG")
    @PostMapping("/tag")
    public ResponseEntity<Tag> create(@RequestBody @Valid final TagDTO tagDTO) {
        return new ResponseEntity<Tag>(this.tagRepository.saveAndFlush(convertToEntity(tagDTO)), HttpStatus.CREATED);
    }

    @ApiOperation(value = "UPDATE A TAG")
    @RequestMapping(value = "/tag/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Tag> update(@PathVariable(value = "id") Long id, @RequestBody @Valid Tag tag) {
        Optional<Tag> optionalTag = this.tagRepository.findById(id);
        boolean existsTag = optionalTag.isPresent();
        Tag existingTag = new Tag();

        if (existsTag) {
            existingTag = optionalTag.get();
            BeanUtils.copyProperties(tag, existingTag, "id");
        }

        return existsTag ? new ResponseEntity<Tag>(this.tagRepository.saveAndFlush(existingTag), HttpStatus.ACCEPTED) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "DELETE A TAG")
    @RequestMapping(value = "/tag/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        if (Optional.ofNullable(this.tagRepository.findById(id)).isPresent()) {
            this.tagRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private Tag convertToEntity(TagDTO tagDTO) {
        Tag tag = modelMapper.map(tagDTO, Tag.class);
        if (tagDTO.getDescription() != null) {
            tag.setDescription(tagDTO.getDescription());
        }
        return tag;
    }
}
