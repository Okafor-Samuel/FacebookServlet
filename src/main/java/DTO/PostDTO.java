package DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.stream.events.Comment;
import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class PostDTO {
    private Long id;
    private String content;
    private List<Comment> commentList;
}
