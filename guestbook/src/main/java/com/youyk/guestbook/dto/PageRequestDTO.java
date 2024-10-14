package com.youyk.guestbook.dto;

import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@Builder
//Setter 가 없으면 localhost:8080/guestbook/list?page=1 에서 바인딩이 안됨
@Data
public class PageRequestDTO{
    private int page;
    private int size;
    private String type;
    private String keyword;
    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort){
        return PageRequest.of(page-1,size,sort);
    }
}
