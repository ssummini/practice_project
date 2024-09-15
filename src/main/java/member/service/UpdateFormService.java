package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class UpdateFormService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // 세션에서 사용자 ID를 가져옴
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("memId");

        // DB에서 회원 정보 가져오기
        MemberDAO memberDAO = MemberDAO.getInstance();
        MemberDTO memberDTO = memberDAO.getMember(id);

        // 회원 정보를 request에 저장 (JSP에서 사용)
        request.setAttribute("memberDTO", memberDTO);
        
		return "/member/memberUpdateForm.jsp";
	}

}
