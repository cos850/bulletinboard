package hyeri.bulletinboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import hyeri.bulletinboard.security.dto.AuthMemberDTO;

@Controller
@Log4j2
@RequiredArgsConstructor
public class WebController {
    @GetMapping("/")
    public String index(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, Model model){
        model.addAttribute("Authdto", authMemberDTO);
        return "post/index";
    }

    @GetMapping("signup")
    public void signup(){
        log.info("signup..............");

        email
    }

}
