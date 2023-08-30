package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@Controller
public class BoardController {	
	// /boardList.do
	@Autowired // 의존성 주입 (DI)
	private BoardMapper mapper;
	// HandlerMapping
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
		
		List<Board> list=mapper.getLists();
		model.addAttribute("list",list);
		return "boardList"; // /WEB=INF/views/boardList.jsp -> forward
	}
	@GetMapping("/boardForm.do")
	public String boardForm() { // 매서드 이름은 막 지어도 됨
		return "boardForm"; // /WEB=INF/views/boardForm.jsp -> forward
	}
	@PostMapping("/boardInsert.do")
	public String boardInsert(Board vo) { // title, content, writer -> 파라메터수집(Board)
		mapper.boardInsert(vo); // 등록
		return "redirect:/boardList.do"; // redirect
	}
	@GetMapping("/boardContent.do")
	public String boardContent(@RequestParam("idx") int idx, Model model) { // ?idx=6
		Board vo=mapper.boardContent(idx);
		// 조회수 증가
		mapper.boardCount(idx); // 상세보기 클릭시 1 증가
		model.addAttribute("vo", vo); // 객체 바인딩 , ${vo.idx}...
		return "boardContent"; // boardContent.jsp
	}
	@GetMapping("/boardDelete.do/{idx}")
	public String boardDelete(@PathVariable("idx") int idx) {
		mapper.boardDelete(idx);
		return "redirect:/boardList.do";
	}
	@GetMapping("/boardUpdateForm.do/{idx}")
	public String boardUpdateForm(@PathVariable("idx") int idx, Model model) {
		Board vo=mapper.boardContent(idx);
		model.addAttribute("vo", vo);
		return "boardUpdate"; // boardUpdate.jsp
	}
	@PostMapping("/boardUpdate.do")
	public String boardUpdate(Board vo) { // idx, title, content
		mapper.boardUpdate(vo); // 수정
		return "redirect:/boardList.do";
	}
}