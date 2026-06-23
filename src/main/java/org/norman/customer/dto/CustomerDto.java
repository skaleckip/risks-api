package org.norman.customer.dto;

import org.norman.customer.entity.Customer;

import java.util.List;
import java.util.UUID;

public record CustomerDto(
        UUID id,
        String name
) {
    public static CustomerDto from(Customer customer) {
        return customer == null ? null : new CustomerDto(
                customer.getId(),
                customer.getName());
    }

    public static List<CustomerDto> fromList(List<Customer> customers) {
        return customers == null ? null : customers
                .stream()
                .map(CustomerDto::from)
                .toList();
    }
}
