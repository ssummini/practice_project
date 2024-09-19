package board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import board.bean.BoardPaging;
import board.dao.BoardDAO;

public class PageService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int pg = Integer.parseInt(request.getParameter("pg"));
        
		BoardDAO boardDAO = BoardDAO.getInstance();
		int totalA = boardDAO.getTotalA();
		
		 // 페이징 객체 생성 및 설정
        BoardPaging boardPaging = new BoardPaging();
        boardPaging.setCurrentPage(pg);
        boardPaging.setTotalA(totalA);
        boardPaging.setPageBlock(3);  // [이전] [1][2][3] [다음] 으로 3개씩 표시
        boardPaging.setPageSize(5);   // 페이지당 5개씩
        boardPaging.makePagingHTML();
		
        // 페이징 HTML을 클라이언트로 반환
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(boardPaging.getPagingHTML().toString());
        
        return "none";
	}

}
