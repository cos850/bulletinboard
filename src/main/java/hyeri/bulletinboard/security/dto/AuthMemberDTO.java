package hyeri.bulletinboard.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Log4j2
@Getter
@Setter
@ToString
public class AuthMemberDTO extends User {

    private String email;
    private String name;

    public AuthMemberDTO(String username,   // id
                         String password,
                         Collection<? extends GrantedAuthority> authorities){       // 계정이 가지고 있는 권환들
        super(username, password, authorities);
        this.email = username;
    }

}