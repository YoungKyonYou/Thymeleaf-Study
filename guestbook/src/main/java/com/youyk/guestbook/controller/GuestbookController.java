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
import org.springframework.web.bind.annotation.*;
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
    //GuestbookDTO dto 로는 .html 파일의 name 속성을 매핑해서 가져온다
    @PostMapping("/register")
    public String registerPost(GuestbookDTO dto, RedirectAttributes redirectAttributes){
        log.info("dto.."+dto);
        Long gno = service.register(dto);
        //한 번만 화면에서 "msg"라는 이름의 변수를 사용할 수 있도록 처리함
        redirectAttributes.addFlashAttribute("msg",gno);
        return "redirect:/guestbook/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(@RequestParam("gno") long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){
        log.info("gno: "+gno);
        GuestbookDTO dto = service.read(gno);
        model.addAttribute("dto",dto);
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("gno")long gno, RedirectAttributes redirectAttributes){
        log.info("gno: " + gno);

        service.remove(gno);

        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/guestbook/list";
    }
    //submit 버튼을 눌렀을 때 처리하는 메소드
    //GuestbookDTO dto 로는 .html 파일의 name 속성을 매핑해서 가져온다
    @PostMapping("/modify")
    public String modify(GuestbookDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes){
        log.info("post modify..................................");
        log.info("dto: "+dto);

        service.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("gno", dto.getGno());
        return "redirect:/guestbook/read";
    }

}
