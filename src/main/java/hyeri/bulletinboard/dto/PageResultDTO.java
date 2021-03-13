package hyeri.bulletinboard.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO, EN> {

    private List<DTO> dtoList;

    private int totalPage;

    private int page;

    private int size;

    private int start, end;

    private boolean prev, next;

    private List<Integer> pageList;

    // Repository가 반환하는 Page<Post>객체로부터
    // List<Post> 객체로 변환하는 메소드
    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn){
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        // 매핑된 결과들을 모아서 리스트로 만들어줌

        totalPage = result.getTotalPages();

        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable){
        this.page = pageable.getPageNumber()+1;
        this.size = pageable.getPageSize();

        int tempEnd = (int)(Math.ceil(page/10.0))*10;

        start = tempEnd-9;

        prev = start > 1;

        end = totalPage > tempEnd ? tempEnd: totalPage;

        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }
}
