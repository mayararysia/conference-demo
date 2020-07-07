package com.rysia.conferencedemo.controllers;

import com.rysia.conferencedemo.models.DiscountCode;
import com.rysia.conferencedemo.repositories.DiscountCodeRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/discount")
public class DiscountCodesController {

    @Autowired
    private DiscountCodeRepository discountCodeRepository;

    @ApiOperation(value = "LIST ALL DISCOUNT CODES")
    @GetMapping("/codes")
    public List<DiscountCode> list() {
        return this.discountCodeRepository.findAll();
    }

    @ApiOperation(value = "GET A UNIQUE DISCOUNT CODE")
    @GetMapping("/code/{id}")
    public DiscountCode get(@PathVariable Long id) {
        return this.discountCodeRepository.getOne(id);
    }

    @ApiOperation(value = "CREATE A DISCOUNT CODE")
    @PostMapping("/code")
    @ResponseStatus(HttpStatus.CREATED)
    public DiscountCode create(@RequestBody final DiscountCode discountCode) {
        return this.discountCodeRepository.saveAndFlush(discountCode);
    }

    @ApiOperation(value = "UPDATE A DISCOUNT CODE")
    @RequestMapping(value = "/code/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public DiscountCode update(@PathVariable Long id, @RequestBody DiscountCode discountCode) {
        DiscountCode existingDiscountCode = this.discountCodeRepository.getOne(id);
        BeanUtils.copyProperties(discountCode, existingDiscountCode, "id");
        return this.discountCodeRepository.saveAndFlush(existingDiscountCode);
    }

    @ApiOperation(value = "DELETE A DISCOUNT CODE")
    @RequestMapping(value = "/code/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.discountCodeRepository.deleteById(id);
    }
}
