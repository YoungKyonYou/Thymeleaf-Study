package com.youyk.guestbook.service;

import com.youyk.guestbook.dto.GuestbookDTO;
import com.youyk.guestbook.dto.PageRequestDTO;
import com.youyk.guestbook.dto.PageResultDTO;
import com.youyk.guestbook.entity.Guestbook;
import com.youyk.guestbook.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
@Service
public class GuestbookServiceImpl implements GuestbookService{
    private final GuestbookRepository repository;
    @Override
    public Long register(GuestbookDTO dto) {
       log.info("DTO----------------");
       log.info("{}",dto);

       Guestbook entity = dtoToEntity(dto);

       log.info("{}",entity);

       repository.save(entity);
       return entity.getGno();
    }

    @Override
    public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
        Page<Guestbook> result = repository.findAll(pageable);

        Function<Guestbook, GuestbookDTO> fn = (this::entityToDto);

        return new PageResultDTO<>(result, fn);
    }
}
