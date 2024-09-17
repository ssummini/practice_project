package board.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import board.bean.BoardDTO;


public class BoardDAO {	
	private static BoardDAO boardDAO = new BoardDAO();
	private SqlSessionFactory sqlSessionFactory;
	
	public static BoardDAO getInstance() {
		return boardDAO;
	}
	
	public BoardDAO() {
		try {
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int boardWrite(BoardDTO boardDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession(); //생성
		int result = 0;
		
		result = sqlSession.insert("boardSQL.boardWrite", boardDTO);
		sqlSession.commit();
		sqlSession.close();
		
		return result;
	}
	
	public List<BoardDTO> boardList(int startNum, int endNum){
		Map<String, Integer> map = new HashMap<>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		SqlSession sqlSession = sqlSessionFactory.openSession(); //생성
		List<BoardDTO> list = sqlSession.selectList("boardSQL.boardList", map);
		sqlSession.close();
		return list;
	}
	
	public List<BoardDTO> boardSearch(String title){
		SqlSession sqlSession = sqlSessionFactory.openSession(); //생성
		List<BoardDTO> list = sqlSession.selectList("boardSQL.boardSearch", title);
		sqlSession.close();
		return list;
	}
	
	public BoardDTO boardDetail(String no) {
		SqlSession sqlSession = sqlSessionFactory.openSession(); //생성
		BoardDTO boardDTO = sqlSession.selectOne("boardSQL.boardDetail", no);
		sqlSession.close();
		return boardDTO;
	}
	
	public void boardDelete(String no) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.delete("boardSQL.boardDelete", no);
		sqlSession.commit();
		sqlSession.close();
	}
	
	public int getTotalA() {
		SqlSession sqlSession = sqlSessionFactory.openSession(); //생성
		int totalA = sqlSession.selectOne("boardSQL.getTotalA");
		sqlSession.close();
		return totalA;
	}
}
	
