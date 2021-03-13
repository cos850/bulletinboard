package hyeri.bulletinboard.repository;

import hyeri.bulletinboard.entity.Member;
import hyeri.bulletinboard.entity.MemberRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertMembers(){
        IntStream.rangeClosed(1, 100).forEach(i-> {
            Member member = Member.builder()
                    .email("user"+i + "@hyeri.com")
                    .password(passwordEncoder.encode("1111"))
                    .name("USER"+i)
                    .build();

            //default role
            member.addMemberRole(MemberRole.USER);

            if(i<80){
                member.addMemberRole(MemberRole.STUDENT);
            }
            else if(i<100) {
                member.addMemberRole(MemberRole.STAFF);
            }
            else {
                member.addMemberRole(MemberRole.ADMIN);
            }

            memberRepository.save(member);
        });
    }

}
