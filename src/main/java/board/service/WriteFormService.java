package board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

public class WriteFormService implements CommandProcess{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	    HttpSession session = request.getSession();
	    String id = (String) session.getAttribute("memId");
	    if(id == null) {
	    	 response.sendRedirect(request.getContextPath() + "/member/loginForm.do");
	    	 return "none";
	    }else
	    	return "/board/boardWriteForm.html";
	 
	    
	}
}
