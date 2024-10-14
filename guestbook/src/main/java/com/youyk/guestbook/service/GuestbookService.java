package com.youyk.guestbook.service;

import com.youyk.guestbook.dto.GuestbookDTO;
import com.youyk.guestbook.dto.PageRequestDTO;
import com.youyk.guestbook.dto.PageResultDTO;
import com.youyk.guestbook.entity.Guestbook;

public interface GuestbookService{
    Long register(GuestbookDTO dto);
    GuestbookDTO read(Long gno);
    void remove(Long gno);
    void modify(GuestbookDTO dto);

    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);

    default Guestbook dtoToEntity(GuestbookDTO dto){
        return Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
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
