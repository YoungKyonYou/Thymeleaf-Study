package com.youyk.guestbook.dto;

import lombok.Builder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
public record PageRequestDTO(
        int page,
        int size
) {
    public PageRequestDTO(int page, int size) {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort){
        return PageRequest.of(page-1,size,sort);
    }
}
