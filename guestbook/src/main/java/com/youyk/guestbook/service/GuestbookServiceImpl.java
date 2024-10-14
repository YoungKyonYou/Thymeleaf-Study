package com.youyk.guestbook.service;

import com.querydsl.core.types.dsl.BooleanExpression;
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

import java.util.Optional;
import java.util.function.Function;

import static com.youyk.guestbook.entity.QGuestbook.guestbook;

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
    public GuestbookDTO read(Long gno) {
        Optional<Guestbook> result = repository.findById(gno);
        return result.map(this::entityToDto).orElse(null);
    }

    @Override
    public void remove(Long gno) {
        repository.deleteById(gno);
    }

    @Override
    public void modify(GuestbookDTO dto) {
        Optional<Guestbook> result = repository.findById(dto.getGno());

        if(result.isPresent()){
            Guestbook entity = result.get();
            entity.changeTitle(dto.getTitle());
            entity.changeContent(dto.getContent());

            repository.save(entity);
        }
    }

    @Override
    public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());

        BooleanExpression booleanExpression = getSearch(requestDTO);


        Page<Guestbook> result = repository.findAll(booleanExpression, pageable);

        Function<Guestbook, GuestbookDTO> fn = (this::entityToDto);

        return new PageResultDTO<>(result, fn);
    }

    private BooleanExpression getSearch(PageRequestDTO requestDTO){
        String type = requestDTO.getType();

        String keyword = requestDTO.getKeyword();

        BooleanExpression booleanExpression = guestbook.gno.gt(0L); // gno > 0 조건만 생성

        if(type == null || type.trim().isEmpty()){
            return booleanExpression;
        }

        if(type.contains("t")){
            booleanExpression = booleanExpression.and(guestbook.title.contains(keyword));
        }
        if(type.contains("c")){
            booleanExpression = booleanExpression.and(guestbook.content.contains(keyword));
        }
        if(type.contains("w")){
            booleanExpression = booleanExpression.and(guestbook.writer.contains(keyword));
        }

        return booleanExpression;
    }
}
