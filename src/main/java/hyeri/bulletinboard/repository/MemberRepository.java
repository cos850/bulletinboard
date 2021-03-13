package hyeri.bulletinboard.repository;

import hyeri.bulletinboard.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from Member m where m.email =:email") // from 뒤에 DB 이름 기준이 아니라 클래스 이름을 넣어야하기 때문에 첫 글자는 대문자로 !
    Optional<Member> findByEmail(String email);

}
