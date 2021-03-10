package hyeri.bulletinboard.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // 이 어노테이션을 붙이면 해당 클래스가 테이블로 만들어지지 않는다.
@EntityListeners(value={AuditingEntityListener.class})
// EntityListeners : 엔티티 데이터 변경을 알려줌, DB저장 이전에 callback을 요청할 수 있음
// AuditingEntityListener : Auditing(=감시하다), 엔티티의 생성/수정 액션 시 특정 컬럼에 자동을 DB매핑
//                          createData와 같이 사용한다 (어떤 필드를 DB에 자동으로 매핑할지 정하는거)
//                          application(main 메소드가 있는 실행 파일)에 @EnableJpaAuditing을 추가해야 함
@Getter
public class BaseEntity {

    @CreatedDate
    @Column(name="regdate", updatable = false) // 엔티티 객체를 데이터베이스에 반영할 때 해당 칼럼값은 변경되지 않음
    private LocalDateTime regDate; // entity 생성 시간 처리

    @LastModifiedDate
    @Column(name="moddate")
    private LocalDateTime modDate; // 최종 수정 시간 처리

}
