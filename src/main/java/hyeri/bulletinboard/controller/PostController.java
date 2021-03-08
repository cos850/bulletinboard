package hyeri.bulletinboard.controller;

import hyeri.bulletinboard.dto.PageRequestDTO;
import hyeri.bulletinboard.dto.PostDTO;
import hyeri.bulletinboard.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/post")
@Log4j2
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @GetMapping("/")
    public String index(){

        return "/post/index";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        log.info("list............................" + pageRequestDTO);

        model.addAttribute("result", service.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register(){
        log.info("register get...........");
    }

    @PostMapping("/register")
    public String registerPost(PostDTO dto, RedirectAttributes redirectAttributes){
        log.info("dto...." + dto);

        Long postId = service.register(dto);

        redirectAttributes.addFlashAttribute("msg", postId);

        return "redirect:/post/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(long postId, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){
        log.info("postId : " + postId);

        PostDTO dto = service.read(postId);

        model.addAttribute("dto", dto);
    }

    @PostMapping("/remove")
    public String remove(long postId, RedirectAttributes redirectAttributes){
        log.info("postId : " + postId);

        service.remove(postId);

        redirectAttributes.addFlashAttribute("msg", postId);

        return "redirect:/post/list";
    }

    @PostMapping("/modify")
    public String modify(PostDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes){
        log.info("post modify...........................");
        log.info("dto : " + dto);

        service.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("postId", dto.getPostId());

        return "redirect:/post/read";
    }
}
