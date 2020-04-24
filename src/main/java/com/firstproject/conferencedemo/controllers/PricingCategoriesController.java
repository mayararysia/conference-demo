package com.firstproject.conferencedemo.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.firstproject.conferencedemo.models.PricingCategory;
import com.firstproject.conferencedemo.repositories.PricingCategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pricings")
public class PricingCategoriesController {
    @Autowired
    private PricingCategoryRepository pricingCategoryRepository;

    @GetMapping
    public List<PricingCategory> list() {
        return this.pricingCategoryRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public PricingCategory get(@PathVariable Long id) {
        return this.pricingCategoryRepository.getOne(id);
    }

    @PostMapping
    public PricingCategory create(@RequestBody final PricingCategory pricingCategory) {
        return this.pricingCategoryRepository.saveAndFlush(pricingCategory);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        this.pricingCategoryRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public PricingCategory update(@PathVariable Long id, @RequestBody PricingCategory pricingCategory) {
        PricingCategory existingPricing = pricingCategoryRepository.getOne(id);
        BeanUtils.copyProperties(pricingCategory, existingPricing, "id");
        return this.pricingCategoryRepository.saveAndFlush(existingPricing);
    }
}
