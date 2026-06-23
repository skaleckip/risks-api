package org.norman.risks.customer.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.norman.risks.customer.dto.CustomerDto;
import org.norman.risks.customer.svc.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(
            summary = "List all customers",
            security = {@SecurityRequirement(name = "Norman")}
    )
    @GetMapping("/customers")
    public List<CustomerDto> listAllCustomers() {
        logger.trace("listAllCustomers");
        return customerService.listAllCustomers();
    }
}
