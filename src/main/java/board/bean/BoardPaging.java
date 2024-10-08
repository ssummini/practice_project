package board.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardPaging {
    private int currentPage;  // 현재 페이지
    private int pageBlock;    // [이전][1][2][3][다음] 블록 크기
    private int pageSize;     // 페이지당 표시할 게시글 수
    private int totalA;       // 총 게시글 수
    private StringBuffer pagingHTML;  // 페이징 HTML을 저장할 StringBuffer

    public void makePagingHTML() {
        pagingHTML = new StringBuffer();

        int totalP = (totalA + pageSize - 1) / pageSize;  // 총 페이지 수 계산
        int startPage = (currentPage - 1) / pageBlock * pageBlock + 1;
        int endPage = startPage + pageBlock - 1;
        if (endPage > totalP) endPage = totalP;

        if (startPage != 1) {
            pagingHTML.append("<span id='paging' onclick='boardPaging(" + (startPage - 1) + ")'>이전</span>");
        }

        for (int i = startPage; i <= endPage; i++) {
            if (i == currentPage) {
                pagingHTML.append("<span id='currentPaging' onclick='boardPaging(" + i + ")'>" + i + "</span>");
            } else {
                pagingHTML.append("<span id='paging' onclick='boardPaging(" + i + ")'>" + i + "</span>");
            }
        }

        if (endPage < totalP) {
            pagingHTML.append("<span id='paging' onclick='boardPaging(" + (endPage + 1) + ")'>다음</span>");
        }
    }

    public StringBuffer getPagingHTML() {
        return pagingHTML;
    }
}
