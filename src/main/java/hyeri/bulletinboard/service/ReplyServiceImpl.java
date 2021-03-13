package hyeri.bulletinboard.service;

import hyeri.bulletinboard.dto.ReplyDTO;
import hyeri.bulletinboard.entity.Post;
import hyeri.bulletinboard.entity.Reply;
import hyeri.bulletinboard.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;

    @Override
    public Long register(ReplyDTO replyDTO){
        Reply reply = dtoToEntity(replyDTO);

        replyRepository.save(reply);
        return reply.getRno();
    }

    @Override
    public List<ReplyDTO> getList(Long pno){
        List<Reply> result = replyRepository
                .getRepliesByPostOrderByRno(Post.builder().pno(pno).build());

        return result.stream().map(reply -> entityToDto(reply)).collect(Collectors.toList());
    }

    @Override
    public void modify(ReplyDTO replyDTO){
        Reply reply = dtoToEntity(replyDTO);

        replyRepository.save(reply);
    }

    @Override
    public void remove(Long rno){
        replyRepository.deleteById(rno);
    }
}
