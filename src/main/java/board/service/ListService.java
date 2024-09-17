package board.service;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

public class ListService implements CommandProcess {
    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        BoardDAO boardDAO = BoardDAO.getInstance();
        
    	int pg = Integer.parseInt(request.getParameter("pg"));

    	//1페이지당 3개씩
    	int endNum = pg * 3;
    	int startNum = endNum - 2;
        
        List<BoardDTO> list = boardDAO.boardList(startNum, endNum);

        // 게시글이 없을 때
        if (list == null || list.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204 No Content
            return "none";
        }

        // JSON 배열 생성
        JSONArray jsonArray = new JSONArray();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // JSON으로 변환하기
        for (BoardDTO boardDTO : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("seq", boardDTO.getSeq());
            jsonObject.put("subject", boardDTO.getSubject());
            jsonObject.put("name", boardDTO.getName());
       
            String logtime = sdf.format(boardDTO.getLogtime());
            jsonObject.put("logtime", logtime);

            jsonObject.put("hit", boardDTO.getHit());

            // JSON 배열에 추가
            jsonArray.put(jsonObject);
        }

        // 게시글 있을 때
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK); // 200 OK
        response.getWriter().print(jsonArray.toString());

        return "none"; 
    }
}
