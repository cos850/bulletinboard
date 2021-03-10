package hyeri.bulletinboard.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "writer")
// @ToString() : 해당 클래스의 모든 멤버 변수 출력
//               writer(FK) 변수의 Member 객체 역시 출력해야함
//               때문에 Member의 toString()이 호출되고 이때 데이터베이스 연결이 필요함
// exculde : 지정된 변수를 toString에서 제외하기 때문에 lazyloading 시 꼭 지정해주는게 좋음
public class Post extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 기본 키 생성을 데이터베이스에 위임
    // id 값을 null로 하면 DB가 알아서 AUTO_INCREMENT해줌
    private Long pno;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch=FetchType.LAZY)
    // FK
    // fetchTye.LAZY : 자동 조인 막아줌, DB와 연결이 끊기기 때문에 실행 클래스에 @Transactional 추가해서 사용
    private Member writer;

    //@Column(nullable = false)
    //private String author;

    public void changeTitle(String title){
        this.title = title;
    }

    public void changeContent(String content){
        this.content = content;
    }
}
