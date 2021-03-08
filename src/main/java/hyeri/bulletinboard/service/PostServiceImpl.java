package hyeri.bulletinboard.service;

import hyeri.bulletinboard.dto.PageRequestDTO;
import hyeri.bulletinboard.dto.PageResultDTO;
import hyeri.bulletinboard.dto.PostDTO;
import hyeri.bulletinboard.entity.Post;
import hyeri.bulletinboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository repository;

    @Override
    public Long register(PostDTO dto){

        log.info("DTO---------------------------");
        log.info(dto);

        Post entity = dtoToEntity(dto);

        log.info(entity);

        repository.save(entity);

        return entity.getPostId();
    }

    @Override
    public PageResultDTO<PostDTO, Post> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("postId").descending());

        Page<Post> result = repository.findAll(pageable);

        Function<Post, PostDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PostDTO read(Long postId){
        Optional<Post> result = repository.findById(postId);

        return result.isPresent()? entityToDto(result.get()):null;
    }

    @Override
    public void remove(Long postId){
        repository.deleteById(postId);
    }

    @Override
    public void modify(PostDTO dto){
        Optional<Post> result = repository.findById(dto.getPostId());

        if(result.isPresent()){
            Post entity = result.get();

            entity.changeTitle(dto.getTitle());
            entity.changeContent(dto.getContent());

            repository.save(entity);
        }
    }
}
