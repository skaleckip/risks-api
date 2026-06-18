package org.norman.risks.shared.dto;

import java.util.List;

public record PageOfDto<T> (
        List<T> items,
        int pageNumber,
        int pageSize,
        int pageCount) {
}