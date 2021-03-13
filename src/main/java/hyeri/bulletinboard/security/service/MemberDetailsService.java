package hyeri.bulletinboard.security.service;

import hyeri.bulletinboard.entity.Member;
import hyeri.bulletinboard.repository.MemberRepository;
import hyeri.bulletinboard.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("UserdetailsService loadUserByUsername =  " + username);

        Optional<Member> result = memberRepository.findByEmail(username);

        if(result.isEmpty()){
            throw new UsernameNotFoundException("check Email"); // 사용자가 존재하지 않을 때
        }

        Member member = result.get();

        log.info("-------------member entity ----------------");
        log.info(member);

        // DTO로 전환
        AuthMemberDTO authMemberDTO = new AuthMemberDTO(
                member.getEmail(),
                member.getPassword(),
                member.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toList())
        );

        authMemberDTO.setName(member.getName());

        return authMemberDTO;
    }
}
