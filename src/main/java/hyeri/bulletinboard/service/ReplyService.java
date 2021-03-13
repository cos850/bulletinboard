package hyeri.bulletinboard.service;

import hyeri.bulletinboard.dto.ReplyDTO;
import hyeri.bulletinboard.entity.Post;
import hyeri.bulletinboard.entity.Reply;

import java.util.List;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);

    List<ReplyDTO> getList(Long pno);

    void modify(ReplyDTO replyDTO);

    void remove(Long rno);

    default Reply dtoToEntity(ReplyDTO replyDTO){

        Post post = Post.builder().pno(replyDTO.getPno()).build();

        Reply reply = Reply.builder()
                .rno(replyDTO.getRno())
                .text(replyDTO.getText())
                .replyer(replyDTO.getReplyer())
                .post(post)
                .build();

        return reply;
    }

    default ReplyDTO entityToDto(Reply reply){
        ReplyDTO dto = ReplyDTO.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .replyer(reply.getReplyer())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();

        return dto;
    }
}
