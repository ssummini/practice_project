package board.service;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

public class UpdateService implements CommandProcess{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");	
        
		int no = Integer.parseInt(request.getParameter("no"));
		
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
				
		System.out.println(subject);
		System.out.println(no);
		System.out.println(content);
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		BoardDTO boardDTO = boardDAO.boardDetail(String.valueOf(no));
		
        // 해당 게시글이 존재하지 않는 경우
        if (boardDTO == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 Not Found
            return "none";
        }
        
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("memId"); //세션 ID
        System.out.println("id = " + id);
        // 해당 글을 작성한 회원이 아닌 경우 
        if (id == null || !id.equals(boardDTO.getId())) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403 Forbidden
            return "none";
        }
        
		boardDTO.setSeq(no);
		boardDTO.setSubject(subject);
		boardDTO.setContent(content);
		
		int result = boardDAO.boardUpdate(boardDTO);
		
		 // 수정 실패 시
        if (result == 0)
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request
        else
            response.setStatus(HttpServletResponse.SC_OK); // 200 OK
        
        
        return "none";
	}
}
