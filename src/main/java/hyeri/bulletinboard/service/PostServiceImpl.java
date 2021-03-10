package hyeri.bulletinboard.service;

import hyeri.bulletinboard.dto.PageRequestDTO;
import hyeri.bulletinboard.dto.PageResultDTO;
import hyeri.bulletinboard.dto.PostDTO;
import hyeri.bulletinboard.entity.Member;
import hyeri.bulletinboard.entity.Post;
import hyeri.bulletinboard.repository.PostRepository;
import hyeri.bulletinboard.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository repository;
    private final ReplyRepository replyRepository;

    // 글 등록
    @Override
    public Long register(PostDTO dto){

        log.info("DTO---------------------------");
        log.info(dto);

        Post post = dtoToEntity(dto);

        log.info(post);

        repository.save(post);

        return post.getPno();
    }

    //목록 처리
    @Override
    public PageResultDTO<PostDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        log.info(pageRequestDTO);

        Function<Object[], PostDTO> fn = (en -> entityToDto( (Post)en[0], (Member)en[1], (Long)en[2] ) );

        Page<Object[]> result = repository.getPostWithReplyCount(
                pageRequestDTO.getPageable(Sort.by("pno").descending() ));

        return new PageResultDTO<>(result, fn);
    }

    // 게시물 조회
    @Override
    public PostDTO get(Long pno){
        Object result = repository.getPostByPno(pno);

        Object[] arr = (Object[])result;

        return entityToDto((Post)arr[0], (Member)arr[1], (Long)arr[2]);
    }

    // 글 삭제
    @Transactional
    @Override
    public void removeWithReplies(Long pno){

        replyRepository.deleteByPno(pno);

        repository.deleteById(pno);
    }


    @Override
    public void modify(PostDTO dto){

        Post post = repository.getOne(dto.getPno());
        //getOne() : 필요한 순간까지 로딩 지연

        if(post != null){

            post.changeTitle(dto.getTitle());
            post.changeContent(dto.getContent());

            repository.save(post);
        }
    }

//
//    @Override
//    public PostDTO read(Long postId){
//        Optional<Post> result = repository.findById(postId);
//
//        return result.isPresent()? entityToDto(result.get()):null;
//    }
//


}
