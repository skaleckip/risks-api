package org.norman.shared.dto;

import java.util.List;

public record PageDto<T> (
        List<T> items,
        int pageNumber,
        int pageSize,
        int pageCount) {
}