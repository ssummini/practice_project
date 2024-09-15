package member.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class LoginService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//데이터
	    request.setCharacterEncoding("UTF-8");
	    String id = request.getParameter("id");
	    String pwd = request.getParameter("pwd");

	    //DB
	    MemberDAO memberDAO = MemberDAO.getInstance();
	    MemberDTO memberDTO = memberDAO.memberLogin(id, pwd);
	    
	    if(memberDTO == null)
	    	return "/member/loginFail.jsp";
	    else {
	    	//세션
	    	HttpSession session = request.getSession(); //세션생성
	    
	    	// 세션에 저장
	        session.setAttribute("memName", memberDTO.getName());
	        session.setAttribute("memId", id);
	        session.setAttribute("memEmail", memberDTO.getEmail1() + "@" + memberDTO.getEmail2());
	    	
	        session.setAttribute("memDTO", memberDTO);
	        
	        return "/member/loginOk.jsp";
	    }
	}

}
