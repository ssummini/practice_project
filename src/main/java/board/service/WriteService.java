package board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

public class WriteService implements CommandProcess{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("memId");
		String name = (String) session.getAttribute("memName");
		String email = (String) session.getAttribute("memEmail");
		
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
        
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setId(id);
		boardDTO.setName(name);
		boardDTO.setEmail(email);
		boardDTO.setSubject(subject);
		boardDTO.setContent(content);
		
		BoardDAO boardDAO = BoardDAO.getInstance(); 
		try {
            boardDAO.boardWrite(boardDTO);
            response.setStatus(HttpServletResponse.SC_CREATED); // 201 Created
        } catch (Exception e) {
            // 에러 발생 시
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request
            e.printStackTrace();
        }
		
		return "none";
	}
}
