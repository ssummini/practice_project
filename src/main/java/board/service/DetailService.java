package board.service;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

public class DetailService implements CommandProcess{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String no = request.getParameter("no");
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		BoardDTO boardDTO = boardDAO.boardDetail(no);
		
		 // 게시글이 없을 때
        if (boardDTO == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 Not Found
            return "none";
        }
        
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("memId"); //세션 ID
        boolean isWriter = false;
        if (id != null && id.equals(boardDTO.getId())) {  // 로그인 상태이면서 로그인 회원이랑 작성자 같은지 여부 체크 !
            isWriter = true;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        // JSON 객체
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isWriter", isWriter);  // 
        jsonObject.put("seq", boardDTO.getSeq());
        jsonObject.put("name", boardDTO.getName());
        jsonObject.put("subject", boardDTO.getSubject());
        jsonObject.put("content", boardDTO.getContent());
        String logtime = sdf.format(boardDTO.getLogtime());
        jsonObject.put("logtime", logtime);
        jsonObject.put("hit", boardDTO.getHit());
        
        // 응답 설정
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK); // 200 OK
        response.getWriter().print(jsonObject.toString());

        return "none";
	}
}
