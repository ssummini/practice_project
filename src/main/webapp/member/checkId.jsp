<%@page import="member.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%-- <%
    // CheckIdService에서 전달받은 result 값 출력
    int result = (Integer)request.getAttribute("result");
%>

<%= result %> --%>

<c:if test="${su==1 }">exist</c:if>
<c:if test="${su==0 }">non_exist</c:if>