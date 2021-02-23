package jsoso.board.controller;

import jsoso.board.dto.BoardDto;
import jsoso.board.service.BoardService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {
  private BoardService boardService;

  public BoardController(BoardService boardService) {
    this.boardService = boardService;
  }

  @GetMapping("/")
  public String list(Model model) {
    List<BoardDto> boardDtoList = boardService.getBoardList();
    model.addAttribute("postList", boardDtoList);
    return "board/list.html";
  }

  //
  @GetMapping("/post")
  public String post() {
    return "board/post.html";
  }

  //
  @GetMapping("/post/{id}")
  public String detail(@PathVariable("id") Long id, Model model) {
    BoardDto boardDto = boardService.getPost(id);
    model.addAttribute("post", boardDto);
    return "board/detail.html";
  }

  //수정
  @GetMapping("/post/edit/{id}")
  public String edit(@PathVariable("id") Long id, Model model) {
    BoardDto boardDto = boardService.getPost(id);

    model.addAttribute("post", boardDto);
    return "board/edit.html";
  }

  //수정
  //서버에게 put 요청이 오면, db에 변경된 데이터 저장 -
  // edit.html에서 변경한 후 수정 클릭하면
  //put형식으로 아래 로 서버에게 요청옴
  @PutMapping("/post/edit/{id}")
  public String update(BoardDto boardDto) {
    boardService.savePost(boardDto);
    return "redirect:/";
  }

  //삭제 요청 처리
  @DeleteMapping("/post/{id}")
  public String delete(@PathVariable("id") Long id) {
    boardService.deletePost(id);
    return "redirect:/";
  }

  //글쓰기 form에서 받은 데이터 '글쓰기'버튼 클릭시 /post로 post요청함
  //post로 받은 데이터를 데이터베이스에 추가
  @PostMapping("/post")
  public String write(BoardDto boardDto) {
    boardService.savePost(boardDto);
    return "redirect:/";
  }


}
