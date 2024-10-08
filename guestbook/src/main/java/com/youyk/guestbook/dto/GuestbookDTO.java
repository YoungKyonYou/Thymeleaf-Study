package com.youyk.guestbook.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record GuestbookDTO(
        Long gno,
        String title,
        String content,
        String writer,
        LocalDateTime regDate,
        LocalDateTime modDate
        ) {
}
