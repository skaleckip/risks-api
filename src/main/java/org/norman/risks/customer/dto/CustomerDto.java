package org.norman.risks.customer.dto;

import java.util.UUID;

public record CustomerDto(
        UUID id,
        String name
) {
}
