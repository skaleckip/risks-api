package org.norman.customer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.norman.customer.dto.CustomerDto;
import org.norman.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "Norman")
public class CustomerController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "List all customers")
    @GetMapping("/customers")
    public List<CustomerDto> listAllCustomers() {
        logger.trace("listAllCustomers");
        return customerService.listAllCustomers();
    }
}
