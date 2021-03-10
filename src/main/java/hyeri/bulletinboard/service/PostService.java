package hyeri.bulletinboard.service;

import hyeri.bulletinboard.dto.PageRequestDTO;
import hyeri.bulletinboard.dto.PageResultDTO;
import hyeri.bulletinboard.dto.PostDTO;
import hyeri.bulletinboard.entity.Member;
import hyeri.bulletinboard.entity.Post;

public interface PostService {

    Long register(PostDTO dto);

    PageResultDTO<PostDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    PostDTO get(Long postId);

    //게시글 등록
    default Post dtoToEntity(PostDTO dto){

        Member member = Member.builder().email(dto.getWriterEmail()).build();

        Post post = Post.builder()
                .pno(dto.getPno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return post;
    }

    //게시글 목록 처리
    default PostDTO entityToDto(Post post, Member member, Long replyCount){
        PostDTO postDTO = PostDTO.builder()
                .pno(post.getPno())
                .title(post.getTitle())
                .content(post.getContent())
                .regDate(post.getRegDate())
                .modDate(post.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue()) // int로 처리
                .build();

        return postDTO;
    }

    // 삭제
    void removeWithReplies(Long pno);

    //수정
    void modify(PostDTO dto);
}
