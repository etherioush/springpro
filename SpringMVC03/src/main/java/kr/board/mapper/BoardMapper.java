package kr.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import kr.board.entity.Board;

@Mapper // mybatis API
public interface BoardMapper {
	public List<Board> getLists(); // 전체 리스트
	public void boardInsert(Board vo);
	public Board boardContent(int idx); // 값을 넘겨받는게 하나여서 Board로 작성
	public void boardDelete(int idx);
	public void boardUpdate(Board vo);
	
	@Update("update myboard set count=count+1 where idx=#{idx}")
	public void boardCount(int idx);
}
