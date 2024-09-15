package member.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import member.bean.MemberDTO;

public class MemberDAO {
	private static MemberDAO memberDAO = new MemberDAO();
	private SqlSessionFactory sqlSessionFactory;
	
	public static MemberDAO getInstance() {
		return memberDAO;
	}
	
	public MemberDAO() {
		try {
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			
			//InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			//sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int isExistId(String id) {	
		 SqlSession sqlSession = sqlSessionFactory.openSession(); //생성
	    int su = 0;
	    su = sqlSession.selectOne("userSQL.isExistId", id);
	    sqlSession.close();
	    System.out.println("MemberDAO su = " + su);
	    System.out.println("MemberDAO id = " + id);
	    return su;
	    
	}

	public void memberWrite(MemberDTO memberDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession(); //생성
		sqlSession.insert("userSQL.memberWrite", memberDTO);
		sqlSession.commit();
		sqlSession.close();
	}
	
	public MemberDTO memberLogin(String id, String pwd){
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pwd", pwd);
		
		SqlSession sqlSession = sqlSessionFactory.openSession(); //생성
		MemberDTO memberDTO = sqlSession.selectOne("userSQL.memberLogin", map);
		sqlSession.close();
		return memberDTO;
	}
	
	public MemberDTO getMember(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession(); //생성
		MemberDTO memberDTO = sqlSession.selectOne("userSQL.getMember", id);
		sqlSession.close();
		return memberDTO;		
	}
	
	public void memberUpdate(MemberDTO memberDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession(); //생성
		sqlSession.update("userSQL.memberUpdate", memberDTO);
		sqlSession.commit();
		sqlSession.close();
	}
}
