package com.youyk.thymeleafstudy.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SampleDTO(
        Long sno,
        String first,
        String last,
        LocalDateTime regTime
) {
}
