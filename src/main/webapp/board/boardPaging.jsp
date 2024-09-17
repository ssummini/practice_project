<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.bean.BoardDTO"%>
<%@ page import="board.bean.BoardPaging"%>
<%@ page import="board.dao.BoardDAO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.List" %>
<%

	//int pg = Integer.parseInt(request.getParameter("pg"));
	int pg = 1;
	//1페이지당 5개씩
	int endNum = pg * 3;
	int startNum = endNum - 2;

	//DB
    BoardDAO boardDAO = BoardDAO.getInstance();
    //List<BoardDTO> list = boardDAO.boardList(startNum, endNum);
    List<BoardDTO> list = new ArrayList<>();
    for(int i = 0; i <35; i++){
    	BoardDTO dto = new BoardDTO();
    	dto.setSeq(i+1);
    	list.add(dto);
    }
    //페이징 처리
    int totalA = boardDAO.getTotalA();
    
    BoardPaging boardPaging = new BoardPaging();
    boardPaging.setCurrentPage(pg);
    boardPaging.setPageBlock(3);
    boardPaging.setPageSize(3);
    boardPaging.setTotalA(totalA);
    
    boardPaging.makePagingHTML();
%>
<%=boardPaging.getPagingHTML() %>