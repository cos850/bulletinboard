package hyeri.bulletinboard.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PostDTO {

    // Data Transfer Object entity는 데이터베이스에 직접 저장할때 사용하고
    // 그 데이터를 주고 받고 전달하는 역할을 하는게 DTO 객체

    private Long pno;

    private String title;

    private String content;

    private String writerEmail;

    private String writerName;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private int replyCount;
}
