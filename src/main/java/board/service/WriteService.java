package board.service;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

public class WriteService implements CommandProcess{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		BufferedReader reader = request.getReader();
        StringBuilder jsonStringBuilder = new StringBuilder();
        String line;
        
        while ((line = reader.readLine()) != null) {
            jsonStringBuilder.append(line);
        }
        
        String jsonString = jsonStringBuilder.toString(); // 요청에서 받은 JSON 문자열

        // 2. JSON 문자열을 파싱하여 JSONObject로 변환

	    JSONObject jsonObject = new JSONObject(jsonString);
	    String subject = jsonObject.getString("subject");
	    String content = jsonObject.getString("content");
	     
		String id = (String) session.getAttribute("memId");
		String name = (String) session.getAttribute("memName");
		String email = (String) session.getAttribute("memEmail");
		
        if (id == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);  // 400 Bad Request
            return "none";
        }
        
		//String subject = request.getParameter("subject");
		//String content = request.getParameter("content");
        
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setId(id);
		boardDTO.setName(name);
		boardDTO.setEmail(email);
		boardDTO.setSubject(subject);
		boardDTO.setContent(content);
		
		BoardDAO boardDAO = BoardDAO.getInstance(); 
		int result = boardDAO.boardWrite(boardDTO);
		
		if (result > 0) 
			response.setStatus(HttpServletResponse.SC_CREATED);  // 201 Created
		else
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);  // 400 Bad Request
	        		
		return "none";
	}
}
