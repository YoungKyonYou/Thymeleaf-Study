package com.youyk.guestbook.service;

import com.youyk.guestbook.dto.GuestbookDTO;
import com.youyk.guestbook.dto.PageRequestDTO;
import com.youyk.guestbook.dto.PageResultDTO;
import com.youyk.guestbook.entity.Guestbook;

public interface GuestbookService{
    Long register(GuestbookDTO dto);

    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);

    default Guestbook dtoToEntity(GuestbookDTO dto){
        return Guestbook.builder()
                .gno(dto.gno())
                .title(dto.title())
                .content(dto.content())
                .writer(dto.writer())
                .build();
    }

    default GuestbookDTO entityToDto(Guestbook entity){
        return GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
    }
}
