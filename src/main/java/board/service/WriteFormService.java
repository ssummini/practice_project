package board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

public class WriteFormService implements CommandProcess{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	    return "/board/boardWriteForm.html"; // 정적 파일의 경우 클라이언트에서 직접 접근 가능하도록
	}
}
