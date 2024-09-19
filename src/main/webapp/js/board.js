$(function() {
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

		let boardData =
		{
			'subject' : $('#subject').val().trim(),
			'content' : $('#content').val().trim(),
		};
		// 유효성 검사를 모두 통과한 경우에만 ajax 요청 실행
		if (isValid) {
			$.ajax({
				type: 'post',
				url: '/memberMVC/boards/write',
				contentType: 'application/json',
				data: JSON.stringify(boardData), // 제목 , 내용 JSON 형태로 RequestBody에 담기					
				success: function(response, textStatus, jqXHR) {
					// jqXHR.status => response 헤더의 상태 코드
					if (jqXHR.status === 201) {	// Created
						alert('게시물 작성에 성공하였습니다!');
						window.location.href = "/memberMVC/boards/listForm";
						// 게시글 목록 1페이지로 이동
					}
				},
				error: function(xhr, textStatus, errorThrown) {
					// xhr.status => response 헤더의 상태 코드
					if (xhr.status === 400) {	// Bad Request 게시글 작성 실패
						alert('게시글 작성에 실패했습니다.');
					}
					//window.location.href = "/memberMVC/board/boardListForm.html";
				}
			});
		}
		console.log(boardData);
	});
});
