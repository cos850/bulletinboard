package hyeri.bulletinboard.controller;

import hyeri.bulletinboard.dto.ReplyDTO;
import hyeri.bulletinboard.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
// RequiredArgsConstructor
// 초기화 되지 않은 final, @NonNull이 붙은 필드에 생성자를 생성해준다
// 주로 의존성 주입의 편리성을 위해 사용함
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping(value="/post/{pno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReplyDTO>> getListByPost(@PathVariable("pno") Long pno){
        return new ResponseEntity<>(replyService.getList(pno), HttpStatus.OK);
    }

}
