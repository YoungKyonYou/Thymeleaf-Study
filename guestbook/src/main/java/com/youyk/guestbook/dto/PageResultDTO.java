package com.youyk.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResultDTO<DTO, EN>{
    private List<DTO> dtoList;

    //총 페이지 번호
    private int totalPage;

    //현재 페이지 번호
    private int size;

    private int page;

    //시작 페이지 번호, 끝 페이지 번호
    private int start, end;

    //이전, 다음
    private boolean prev,next;

    //페이지 번호 목록
    private List<Integer> pageList;

    private void makePageList(Pageable pageable){
        this.page = pageable.getPageNumber()+1; //0부터 시작하므로 1을 추가
        this.size = pageable.getPageSize();

        //temp end page
        int tempEnd = (int)(Math.ceil(page/10.0))*10;
        start = tempEnd - 9;
        prev = start > 1;

        end = Math.min(totalPage, tempEnd);
        next = totalPage > end;

        pageList = IntStream.rangeClosed(start, end).boxed().toList();

    }

    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn){
        dtoList = result.stream().map(fn).toList();
        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }
}
