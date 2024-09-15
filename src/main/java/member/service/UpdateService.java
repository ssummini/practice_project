package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class UpdateService implements CommandProcess {

    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        String gender = request.getParameter("gender");
        String email1 = request.getParameter("email1");
        String email2 = request.getParameter("email2");
        String tel1 = request.getParameter("tel1");
        String tel2 = request.getParameter("tel2");
        String tel3 = request.getParameter("tel3");
        String zipcode = request.getParameter("zipcode");
        String addr1 = request.getParameter("addr1");
        String addr2 = request.getParameter("addr2");

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(id);  // 세션에서 가져온 ID 설정
        memberDTO.setName(name);
        memberDTO.setPwd(pwd);
        memberDTO.setGender(gender);
        memberDTO.setEmail1(email1);
        memberDTO.setEmail2(email2);
        memberDTO.setTel1(tel1);
        memberDTO.setTel2(tel2);
        memberDTO.setTel3(tel3);
        memberDTO.setZipcode(zipcode);
        memberDTO.setAddr1(addr1);
        memberDTO.setAddr2(addr2);

        // DB
        MemberDAO memberDAO = MemberDAO.getInstance();
        memberDAO.memberUpdate(memberDTO);  // 회원 정보 업데이트

        return "none"; 
    }
}
