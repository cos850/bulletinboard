package hyeri.bulletinboard.repository;

import hyeri.bulletinboard.entity.Member;
import hyeri.bulletinboard.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void insertPost(){               //  Post 테이블에 100개의 인스턴스 추가
        IntStream.rangeClosed(1, 100).forEach(i-> {

            Member member = Member.builder()
                    .email("user"+i + "@aaa.com")
                    .build();

            Post post = Post.builder()
                    .title("Title....." + i)
                    .content("Content...." + i)
                    .writer(member)
                    .build();

            postRepository.save(post);
        });
    }

    @Transactional
    @Test
    public void testRead1(){
        Optional<Post> result = postRepository.findById(100L);

        Post post = result.get();

        System.out.println(post);
        System.out.println(post.getWriter());
    }

    @Test
    public void testReadWithWiriter(){
        Object result = postRepository.getPostWithWriter(100L);

        Object[] arr = (Object[])result;

        System.out.println("-------------------------------");
        System.out.println(Arrays.toString(arr));
    }

}
