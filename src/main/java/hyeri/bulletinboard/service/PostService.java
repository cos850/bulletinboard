package hyeri.bulletinboard.service;

import hyeri.bulletinboard.dto.PageRequestDTO;
import hyeri.bulletinboard.dto.PageResultDTO;
import hyeri.bulletinboard.dto.PostDTO;
import hyeri.bulletinboard.entity.Post;

public interface PostService {
    Long register(PostDTO dto);
    PageResultDTO<PostDTO, Post> getList(PageRequestDTO requestDTO);
    PostDTO read(Long postId);

    default Post dtoToEntity(PostDTO dto){
        Post entity = Post.builder()
                .postId(dto.getPostId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(dto.getAuthor())
                .build();
        return entity;
    }

    default PostDTO entityToDto(Post entity){
        PostDTO dto = PostDTO.builder()
                .postId(entity.getPostId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .author(entity.getAuthor())
                .build();
        return dto;
    }

    void remove(Long postId);
    void modify(PostDTO dto);
}
