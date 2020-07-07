package com.rysia.conferencedemo.controllers;

import com.rysia.conferencedemo.models.PricingCategory;
import com.rysia.conferencedemo.repositories.PricingCategoryRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/pricing")
public class PricingCategoriesController {
    @Autowired
    private PricingCategoryRepository pricingCategoryRepository;

    @ApiOperation(value = "LIST ALL PRICING CATEGORIES")
    @GetMapping("/categories")
    public List<PricingCategory> list() {
        return this.pricingCategoryRepository.findAll();
    }

    @ApiOperation(value = "GET A UNIQUE PRICING CATEGORY")
    @GetMapping("/category/{id}")
    public PricingCategory get(@PathVariable Long id) {
        return this.pricingCategoryRepository.getOne(id);
    }

    @ApiOperation(value = "CREATE PRICING CATEGORY")
    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    public PricingCategory create(@RequestBody final PricingCategory pricingCategory) {
        return this.pricingCategoryRepository.saveAndFlush(pricingCategory);
    }

    @ApiOperation(value = "DELETE PRICING CATEGORY")
    @RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.pricingCategoryRepository.deleteById(id);
    }

    @ApiOperation(value = "UPDATE PRICING CATEGORY")
    @RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public PricingCategory update(@PathVariable Long id, @RequestBody PricingCategory pricingCategory) {
        PricingCategory existingPricing = pricingCategoryRepository.getOne(id);
        BeanUtils.copyProperties(pricingCategory, existingPricing, "id");
        return this.pricingCategoryRepository.saveAndFlush(existingPricing);
    }
}
