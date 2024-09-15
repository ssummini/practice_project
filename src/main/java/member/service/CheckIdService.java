package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.control.CommandProcess;
import member.dao.MemberDAO;

public class CheckIdService implements CommandProcess {
    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        
    // 요청으로부터 아이디 값을 가져옴
    String id = request.getParameter("id");

    // 데이터베이스로부터 아이디 중복 여부를 확인
    MemberDAO memberDAO = MemberDAO.getInstance();
    int su = memberDAO.isExistId(id);

    // 중복 체크 결과를 request 객체에 저장
    request.setAttribute("su", su);

    // forward를 위해 checkId.jsp로 경로 반환
    return "/member/checkId.jsp";  
    }
}