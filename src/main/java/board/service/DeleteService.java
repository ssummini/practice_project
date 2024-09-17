package board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

public class DeleteService implements CommandProcess{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String no = request.getParameter("no");
		
		BoardDAO boardDAO = BoardDAO.getInstance();		
		BoardDTO boardDTO = boardDAO.boardDetail(no);
		
        // 해당 게시글이 존재하지 않는 경우
        if (boardDTO == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 Not Found
            return "none";
        }
        
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("memId"); //세션 ID
        System.out.println("id = " + id);
        // 해당 글을 작성한 회원이 아닌 경우 
        if (!id.equals(boardDTO.getId())) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403 Forbidden
            return "none";
        }

        // 해당 글 작성한 회원 맞는 경우
        boardDAO.boardDelete(no);
        response.setStatus(HttpServletResponse.SC_OK); // 200 OK

        return "none";
	}
}
