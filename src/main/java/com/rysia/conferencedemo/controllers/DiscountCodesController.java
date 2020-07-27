package com.rysia.conferencedemo.controllers;

import com.rysia.conferencedemo.models.DiscountCode;
import com.rysia.conferencedemo.repositories.DiscountCodeRepository;
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
@RequestMapping("api/v1/discount")
public class DiscountCodesController {

    @Autowired
    private DiscountCodeRepository discountCodeRepository;

    @ApiOperation(value = "LIST ALL DISCOUNT CODES")
    @GetMapping("/codes")
    public ResponseEntity<List<DiscountCode>> list() {
        List<DiscountCode> discountCodes = this.discountCodeRepository.findAll();

        if (discountCodes.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        discountCodes.stream().forEach(discount -> discount.add(linkTo(methodOn(DiscountCodesController.class)
                .get(discount.getId()))
                .withSelfRel()));

        return new ResponseEntity<List<DiscountCode>>(discountCodes, HttpStatus.OK);
    }

    @ApiOperation(value = "GET A UNIQUE DISCOUNT CODE")
    @GetMapping("/code/{id}")
    public ResponseEntity<DiscountCode> get(@PathVariable(value = "id") Long id) {
        Optional<DiscountCode> optionalDiscountCode = this.discountCodeRepository.findById(id);

        if (!optionalDiscountCode.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        optionalDiscountCode.get().add(linkTo(methodOn(DiscountCodesController.class)
                .list())
                .withRel("List of Discounts"));

        return new ResponseEntity<DiscountCode>(optionalDiscountCode.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "CREATE A DISCOUNT CODE")
    @PostMapping("/code")
    public ResponseEntity<DiscountCode> create(@RequestBody @Valid final DiscountCode discountCode) {
        return new ResponseEntity<DiscountCode>(this.discountCodeRepository.saveAndFlush(discountCode),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "UPDATE A DISCOUNT CODE")
    @RequestMapping(value = "/code/{id}", method = RequestMethod.PUT)
    public ResponseEntity<DiscountCode> update(@PathVariable(value = "id") Long id, @RequestBody @Valid DiscountCode discountCode) {
        Optional<DiscountCode> optionalDiscountCode = this.discountCodeRepository.findById(id);
        boolean existsDicount = optionalDiscountCode.isPresent();
        DiscountCode existingDiscountCode = new DiscountCode();

        if (existsDicount) {
            existingDiscountCode = optionalDiscountCode.get();
            BeanUtils.copyProperties(discountCode, existingDiscountCode, "id");
        }

        return existsDicount ? new ResponseEntity<DiscountCode>(this.discountCodeRepository.saveAndFlush(existingDiscountCode),
                HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "DELETE A DISCOUNT CODE")
    @RequestMapping(value = "/code/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        if (Optional.ofNullable(this.discountCodeRepository.findById(id)).isPresent()) {
            this.discountCodeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
