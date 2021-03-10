package hyeri.bulletinboard.repository;

import hyeri.bulletinboard.entity.Post;
import hyeri.bulletinboard.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertReply(){
        IntStream.rangeClosed(1, 300).forEach(i-> {

            long pno = (long)(Math.random()*100)+1;

            Post post = Post.builder().pno(pno).build();

            Reply reply = Reply.builder()
                    .text("Reply......."+i)
                    .post(post)
                    .replyer("guest")
                    .build();

            replyRepository.save(reply);
        });
    }

}
