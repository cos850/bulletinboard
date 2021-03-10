package hyeri.bulletinboard.repository;

import hyeri.bulletinboard.entity.Post;
import hyeri.bulletinboard.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Modifying
    @Query("delete from Reply r where r.post.pno = :pno")
    void deleteByPno(Long pno);

    // 댓글 게시물로 가져오기
    List<Reply> getRepliesByPostOrderByRno(Post post);

}
