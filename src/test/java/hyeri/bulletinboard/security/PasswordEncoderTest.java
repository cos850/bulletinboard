package hyeri.bulletinboard.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordEncoderTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncode(){
        String password = "1111";

        String enPw = passwordEncoder.encode(password);

        System.out.println("enPw : " + enPw);

        boolean matchResult = passwordEncoder.matches(password, enPw);

        System.out.println("검증 결과 : " + matchResult);

    }
}
