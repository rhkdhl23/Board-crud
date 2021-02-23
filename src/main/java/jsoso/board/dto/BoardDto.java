package jsoso.board.dto;

//컨트롤러와 서비스 사이에서 데이터를 주고받는 DTO(Data Access Object)

import jsoso.board.domain.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {

  private Long id;
  private String author;
  private String title;
  private String content;
  private Long fileId;
  private LocalDateTime createdDate;
  private LocalDateTime modifiedDate;

  //toEntity : DTO에서 필요한 부분을 빌더 패턴을 통해 엔티티로 만드는 일을 함
  public Board toEntity() {
    Board build = Board.builder()
          .id(id)
          .author(author)
          .title(title)
          .content(content)
          .build();
    return build;
  }

  @Builder
  public BoardDto(Long id, String author, String title, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
    this.id = id;
    this.author = author;
    this.title = title;
    this.content = content;
    this.createdDate = createdDate;
    this.modifiedDate = modifiedDate;
  }
}
