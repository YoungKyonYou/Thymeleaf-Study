package com.youyk.guestbook.controller;

import com.youyk.guestbook.dto.GuestbookDTO;
import com.youyk.guestbook.dto.PageRequestDTO;
import com.youyk.guestbook.dto.PageResultDTO;
import com.youyk.guestbook.entity.Guestbook;
import com.youyk.guestbook.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/guestbook")
@RequiredArgsConstructor
@Slf4j
public class GuestbookController {
    private final GuestbookService service;
    @GetMapping({"/"})
    public String list(){
        log.info("list...");
        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        log.info("list............."+pageRequestDTO);
        model.addAttribute("result", service.getList(pageRequestDTO));
    }

    // localhost:8080/guestbook/register로 접속하기 위한 메소드
    @GetMapping("/register")
    public void register(){
        log.info("register get...");
    }

    //submit 버튼을 눌렀을 때 처리하는 메소드
    @PostMapping("/register")
    public String registerPost(GuestbookDTO dto, RedirectAttributes redirectAttributes){
        log.info("dto.."+dto);
        Long gno = service.register(dto);
        //한 번만 화면에서 "msg"라는 이름의 변수를 사용할 수 있도록 처리함
        redirectAttributes.addFlashAttribute("msg",gno);
        return "redirect:/guestbook/list";
    }

}
