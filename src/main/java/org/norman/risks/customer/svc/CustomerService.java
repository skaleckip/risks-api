package org.norman.risks.customer.svc;

import org.norman.risks.customer.dto.CustomerDto;
import org.norman.risks.customer.model.Customer;
import org.norman.risks.customer.model.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {
    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    public List<CustomerDto> listAllCustomers() {
        logger.trace("listAllCustomers()");
        var list = customerRepository.findAll();
        return this.toCustomerDtoList(list);
    }

    private CustomerDto toCustomerDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getName()
        );
    }

    private List<CustomerDto> toCustomerDtoList(List<Customer> customers) {
        return customers.stream().map(this::toCustomerDto).toList();
    }
}
