package com.rysia.conferencedemo.controllers;

import com.rysia.conferencedemo.models.PricingCategory;
import com.rysia.conferencedemo.repositories.PricingCategoryRepository;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/api/v1/pricing")
public class PricingCategoriesController {
    @Autowired
    private PricingCategoryRepository pricingCategoryRepository;

    @ApiOperation(value = "LIST ALL PRICING CATEGORIES")
    @GetMapping("/categories")
    public ResponseEntity<List<PricingCategory>> list() {
        List<PricingCategory> pricingCategories = this.pricingCategoryRepository.findAll();

        if (pricingCategories.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        pricingCategories.stream().forEach(category ->
                category.add(linkTo(methodOn(PricingCategoriesController.class)
                        .get(category.getId()))
                        .withSelfRel()));

        return new ResponseEntity<List<PricingCategory>>(pricingCategories, HttpStatus.OK);
    }

    @ApiOperation(value = "GET A UNIQUE PRICING CATEGORY")
    @GetMapping("/category/{id}")
    public ResponseEntity<PricingCategory> get(@PathVariable(value = "id") Long id) {
        Optional<PricingCategory> optionalPricingCategory = this.pricingCategoryRepository.findById(id);

        if (!optionalPricingCategory.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        optionalPricingCategory.get().add(linkTo(methodOn(PricingCategoriesController.class)
                .list())
                .withRel("List of Categories"));

        return new ResponseEntity<PricingCategory>(optionalPricingCategory.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "CREATE PRICING CATEGORY")
    @PostMapping("/category")
    public ResponseEntity<PricingCategory> create(@RequestBody @Valid final PricingCategory pricingCategory) {
        return new ResponseEntity<PricingCategory>(this.pricingCategoryRepository.saveAndFlush(pricingCategory),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "UPDATE PRICING CATEGORY")
    @RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PricingCategory> update(@PathVariable(value = "id") Long id, @RequestBody @Valid PricingCategory pricingCategory) {
        Optional<PricingCategory> optionalPricingCategory = pricingCategoryRepository.findById(id);
        boolean existsCategory = optionalPricingCategory.isPresent();
        PricingCategory existingPricing = new PricingCategory();

        if (existsCategory) {
            existingPricing = optionalPricingCategory.get();
            BeanUtils.copyProperties(pricingCategory, existingPricing, "id");
        }

        return existsCategory ? new ResponseEntity<PricingCategory>(this.pricingCategoryRepository
                .saveAndFlush(existingPricing), HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "DELETE PRICING CATEGORY")
    @RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        if (Optional.ofNullable(this.pricingCategoryRepository.findById(id)).isPresent()) {
            this.pricingCategoryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
