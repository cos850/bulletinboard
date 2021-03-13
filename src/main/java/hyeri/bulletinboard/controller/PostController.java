package hyeri.bulletinboard.controller;

import hyeri.bulletinboard.dto.PageRequestDTO;
import hyeri.bulletinboard.dto.PostDTO;
import hyeri.bulletinboard.security.dto.AuthMemberDTO;
import hyeri.bulletinboard.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/post/")
@Log4j2
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /*
    final : 엔티티를 한번만 할당한다 (즉 두번이상 할당하려하면 컴파일 오류가 나서 확인이 가능하다)
            Immutable/Read-only 속성을 선언하는 지시어
            클래스, 함수, 변수가 변하지 못하도록 의도하고 싶다면 final로 선언한다
            현재는 이 객체 내에서 postService의 값이 변하지 않기를 의도하고 사용됨
     */

    @GetMapping("/")
    public String index(){

        return "/post/index";
    }

    @GetMapping("/list") // return 값이 없으면  이름이 같은 view를 자동으로 보여줌
    public void list(PageRequestDTO pageRequestDTO, Model model){
        log.info("list............................" + pageRequestDTO);

        model.addAttribute("result", postService.getList(pageRequestDTO));

        log.info("model.getAttribute 결과............................");
        log.info(model.getAttribute("result"));
    }

    @GetMapping("/member")
    public void member(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, Model model){

        log.info("--------------memberDTO 반환값 ---------");
        log.info(authMemberDTO);

        model.addAttribute("dto", authMemberDTO);
    }

    @GetMapping("/register")
    public void register(){
        log.info("register get...........");
    }

    @PostMapping("/register")
    public String registerPost(PostDTO dto, RedirectAttributes redirectAttributes){
        log.info("dto...." + dto);

        Long postId = postService.register(dto);

        redirectAttributes.addFlashAttribute("msg", postId);

        return "redirect:/post/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(long pno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){
        log.info("pno : " + pno);

        PostDTO dto = postService.get(pno);

        model.addAttribute("dto", dto);
    }

    @PostMapping("/remove")
    public String remove(long pno, RedirectAttributes redirectAttributes){
        log.info("postId : " + pno);

        postService.removeWithReplies(pno);

        redirectAttributes.addFlashAttribute("msg", pno);

        return "redirect:/post/list";
    }

    @PostMapping("/modify")
    public String modify(PostDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes){
        log.info("post modify...........................");
        log.info("dto : " + dto);

        postService.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("pno", dto.getPno());

        return "redirect:/post/read";
    }

}
