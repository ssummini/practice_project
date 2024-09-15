// 아이디 중복 검사
$(function () {
    $('#id').on('focusout', function(){
		$('#idDiv').empty();
        let id = $('#id').val(); 

        if (id === '') {
            $('#idDiv').html('아이디를 입력하세요.').css('color', 'red'); 
            return;
        }

        console.log(id);

        $.ajax({
            type: 'post',
            url: '/memberMVC/member/checkId.do', 
            data: {'id': id},
            dataType: 'text',
            success: function(data){			   
                if (data.trim() == 'exist') {
                    $('#idDiv').html('이미 사용 중인 아이디입니다.').css('color', 'red');
                } else {
                    $('#idDiv').html('사용가능한 아이디입니다.').css('color', 'green'); 
                	$('#check').val($('#id').val());
				}
            },
            error: function(e){
                console.log(e);
            }
        });
    });
});
//이메일
function change() {
	document.getElementById("email2").value = document.getElementById("email3").value;
}

//회원가입
function memberWrite(e){
    let isValid = true; // 유효성 검사를 통과하면 true, 아니면 false

    document.getElementById("nameDiv").innerHTML = "";
    document.getElementById("idDiv").innerHTML = "";
    document.getElementById("pwdDiv").innerHTML = "";
    document.getElementById("repwdDiv").innerHTML = "";
    
    if(document.memberWriteForm.name.value == ""){
        document.getElementById("nameDiv").innerHTML = "이름을 입력해주세요.";
		e.preventDefault();
		isValid = false;
    }
    
    if(document.getElementById("id").value == ""){
        document.getElementById("idDiv").innerHTML = "아이디를 입력해주세요.";
        e.preventDefault();
		isValid = false;
    }

    if(document.getElementById("pwd").value == ""){
        document.getElementById("pwdDiv").innerHTML = "비밀번호를 입력해주세요.";
		e.preventDefault();
		isValid = false;
    }
    
    if(document.getElementById("pwd").value != document.getElementById("repwd").value){
        document.getElementById("repwdDiv").innerHTML = "비밀번호가 맞지 않습니다.";
		e.preventDefault();
		isValid = false;
    }

    // 중복 체크 확인
    if(document.getElementById("check").value != document.getElementById("id").value){
        document.getElementById("idDiv").innerHTML = "중복체크를 해주세요!";
		e.preventDefault();
		isValid = false;
    }

    if(isValid)
		 document.memberWriteForm.submit();
}

//회원정보 수정
$('#updateBtn').on('click', function(event){
    event.preventDefault();

    $('#nameDiv').empty();
    $('#pwdDiv').empty();
    $('#repwdDiv').empty();
    let isValid = true;
    
    // 이름 검사
    if($('#name').val().trim() == ''){
        $('#nameDiv').html('비밀번호를 입력하세요.').css('color', 'red');
        $('#name').focus();
        isValid = false;
    }
    
    // 비밀번호 검사
    if($('#pwd').val().trim() == ''){
        $('#pwdDiv').html('비밀번호를 입력하세요.').css('color', 'red');
        $('#user_pwd').focus();
        isValid = false;
    }
    
    // 비밀번호 확인 검사
    if($('#repwd').val() == ''){
        $('#repwdDiv').html('비밀번호 확인을 해주세요.').css('color', 'red');
        $('#repwd').focus();
        isValid = false;
    } else if($('#pwd').val() != $('#repwd').val()){
        $('#repwdDiv').html('비밀번호가 일치하지 않습니다.').css('color', 'red');
        $('#repwdDiv').val('');
        $('#repwdDiv').focus();
        isValid = false;
    }
    
    if(isValid){
        $.ajax({
            type: 'post',
            url: '/memberMVC/member/update.do',
            data: $('form').serialize(),
            dataType: 'text',
            success: function(data){
                alert('회원정보가 수정되었습니다.');
                window.location.href = "../index.jsp";
            },
            error: function(e){
                console.log(e);
            }
        });
    }
});


//우편번호 - Daum API
function checkPost() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('zipcode').value = data.zonecode;
            document.getElementById("addr1").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("addr2").focus();
        }
    }).open();
}