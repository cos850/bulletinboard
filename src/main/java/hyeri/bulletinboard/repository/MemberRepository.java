package hyeri.bulletinboard.repository;

import hyeri.bulletinboard.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

}