$(function () {
    $('#writeBtn').on('click', function(event) {
        event.preventDefault();  // 기본 폼 제출 방지

        $('#subjectDiv').empty();
        $('#contentDiv').empty();
        let isValid = true;

        // 제목 검사
        if ($('#subject').val().trim() === '') {
            $('#subjectDiv').html('제목을 입력하세요.');
            $('#subject').focus();
            isValid = false;
        }

        // 내용 검사
        if ($('#content').val().trim() === '') {
            $('#contentDiv').html('내용을 입력하세요.');
            $('#content').focus();
            isValid = false;
        }

        // 유효성 검사를 모두 통과한 경우에만 ajax 요청 실행
        if (isValid) {
            $.ajax({
                type: 'post',
                url: '../board/boardWrite.jsp',
                data: $('#boardWriteForm').serialize(),  
                success: function() {
                    alert('게시물 작성에 성공하였습니다!');
                    window.location.href = "../board/boardList2.jsp?pg=1";  
                },
                error: function(e) {
                    console.log(e);
                }
            });
        }
    });
});
