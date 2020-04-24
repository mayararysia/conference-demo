package com.firstproject.conferencedemo.controllers;

import com.firstproject.conferencedemo.models.DiscountCode;
import com.firstproject.conferencedemo.repositories.DiscountCodeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/discount/codes")
public class DiscountCodesController {

    @Autowired
    private DiscountCodeRepository discountCodeRepository;

    @GetMapping
    public List<DiscountCode> list() {
        return this.discountCodeRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public DiscountCode get(@PathVariable Long id) {
        return this.discountCodeRepository.getOne(id);
    }

    @PostMapping
    public DiscountCode create(@RequestBody final DiscountCode discountCode) {
        return this.discountCodeRepository.saveAndFlush(discountCode);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public DiscountCode update(@PathVariable Long id, @RequestBody DiscountCode discountCode) {
        DiscountCode existingDiscountCode = this.discountCodeRepository.getOne(id);
        BeanUtils.copyProperties(discountCode, existingDiscountCode, "id");
        return this.discountCodeRepository.saveAndFlush(existingDiscountCode);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        this.discountCodeRepository.deleteById(id);
    }
}
