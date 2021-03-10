package hyeri.bulletinboard.repository;

import hyeri.bulletinboard.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long>{

    @Query("select p, w from Post p left join p.writer w where p.pno=:pno")
    Object getPostWithWriter(@Param("pno") Long pno);

    @Query(value = "SELECT p, w, count(r) "
            + "FROM Post p "
            + "LEFT JOIN p.writer w "
            + "LEFT JOIN Reply r ON r.post = p "
            + "GROUP BY p",
            countQuery = "SELECT count(p) FROM Post p" )
    Page<Object[]> getPostWithReplyCount(Pageable pageable);

    @Query(value = "select p, w, count(r) "
            + "from Post p left join  p.writer w "
            + "left outer join Reply r ON r.post = p "
            + "where p.pno = :pno")
    Object getPostByPno(@Param("pno") Long pno);


}
