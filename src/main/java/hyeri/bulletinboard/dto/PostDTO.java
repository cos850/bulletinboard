package hyeri.bulletinboard.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PostDTO {

    private Long pno;

    private String title;

    private String content;

    private String writerEmail;

    private String writerName;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private int replyCount;
}
