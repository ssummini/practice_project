<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<style type="text/css">
body {
    background-color: #FFE2FA;
    font-family: 'Arial', sans-serif;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 80vh;
    flex-direction: column;
}

table {
    border-collapse: collapse;
    border-radius: 10px;
    background-color: white;
    width: 700px;
    padding: 15px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    margin: 0 auto;
}

th, td {
    padding: 8px 10px;
    text-align: left;
    font-size: 14px;
}

th {
    color: #333;
    font-size: 16px;
}

td {
    color: #333;
}

div {
    color: red;
    font-size: 8pt;
    font-weight: bold;
    margin-top: 5px;
}

input[type="text"], input[type="password"], select {
     margin-top: 5px;
    padding: 5px;
    border: 1px solid #CCC;
    border-radius: 5px;
    box-sizing: border-box;
}

input[type="text"][size], input[type="password"][size] {
    width: auto;
}

input[type="button"], input[type="submit"], input[type="reset"] {
    background-color: #FF9ADB;
    border: none;
    font-size: 14px;
    padding: 6px 10px;
    border-radius: 5px;
    cursor: pointer;
    color: white;
    transition: background-color 0.3s ease;
    margin: 4px;
}

input[type="button"]:hover, input[type="submit"]:hover, input[type="reset"]:hover {
    background-color: #FF70C0;
}

img {
    margin-bottom: 15px;
    cursor: pointer;
    display: block;
}

table td[colspan="2"] {
    text-align: center;
}
</style>
</head>
<body>
<img src="../image/free-icon-love-4096198.png" alt="홈" width="60" height="60" onclick="location.href='/memberMVC/index.do'" />
<form name="memberWriteForm" method="post" action="${ pageContext.request.contextPath }/member/write.do">
    <table>
        <tr>
            <th>이름</th>
            <td>
                <input type="text" name="name" id="name" placeholder="이름 입력" size="15"/>
                <div id="nameDiv"></div>
            </td>
        </tr>
        
        <tr>
            <th>아이디</th>
            <td>
                <input type="text" name="id" id="id" placeholder="아이디 입력" size="15"/>
                <input type="hidden" id="check" value=""/>
                <div id="idDiv"></div>
            </td>
        </tr>
        
        <tr>
            <th>비밀번호</th>
            <td>
                <input type="password" name="pwd" id="pwd" placeholder="비밀번호 입력" size="25"/>
                <div id="pwdDiv"></div>    
            </td>
        </tr>
        
        <tr>
            <th>재확인</th>
            <td>
                <input type="password" name="repwd" id="repwd" placeholder="비밀번호 입력" size="25"/>
                <div id="repwdDiv"></div>    
            </td>
        </tr>
        
        <tr>
            <th>성별</th>
            <td>
                <input type="radio" name="gender" value="0" checked/> 남자
                <input type="radio" name="gender" value="1"/> 여자
            </td>
        </tr>
        
        <tr>
            <th>이메일</th>
            <td>
                <input type="text" name="email1" size="15"/> @
                <input type="text" name="email2" id="email2" size="15"/>
                <input type="text" name="email3" id="email3" list="email3_list" size="15" oninput="change()"/>
                <datalist id="email3_list">
                    <option value="직접입력">직접입력</option>
                    <option value="naver.com"></option>
                    <option value="gmail.com"></option>
                    <option value="daum.net"></option>
                </datalist>                        
            </td>
        </tr>
        
        <tr>
            <th>휴대전화</th>
            <td>
                <select name="tel1" style="width: 70px;">
                    <option value="010">010</option>
                    <option value="011">011</option>
                    <option value="019">019</option>
                </select>
                -
                <input type="text" name="tel2" size="4" maxlength="4"/>
                -
                <input type="text" name="tel3" size="4" maxlength="4"/>
            </td>
        </tr>
        
        <tr>
            <th>주소</th>
            <td>
                <input type="text" name="zipcode" id="zipcode" placeholder="우편번호" size="6" readonly/>
                <input type="button" value="우편번호 검색" onclick="checkPost()"/><br>
                <input type="text" name="addr1" id="addr1" placeholder="주소" size="50" readonly/><br>
                <input type="text" name="addr2" id="addr2" placeholder="상세주소" size="50"/>
            </td>
        </tr>
        
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="회원가입" onclick="memberWrite(event)" />
                <input type="reset" value="다시입력" />
            </td>
        </tr>
    </table>
</form>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="../js/member.js"></script>
<script type="text/javascript">
// 아이디 중복 검사
$(function () {
    $('#id').on('focusout', function(){
        $('#idDiv').empty(); // 메시지 초기화
        let id = $('#id').val(); 

        if (id === '') {
            $('#idDiv').html('아이디를 입력하세요.').css('color', 'red'); 
            return;
        }

        $.ajax({
            type: 'post',
            url: '/memberMVC/member/checkId.do', 
            data: {'id': id},
            dataType: 'text',
            success: function(data){			   
                if (data.trim() === 'exist') {
                    $('#idDiv').html('이미 사용 중인 아이디입니다.').css('color', 'red');
                } else {
                    $('#idDiv').html('사용가능한 아이디입니다.').css('color', 'green'); 
                    $('#check').val($('#id').val()); // 중복 체크가 완료된 아이디 저장
                }
            },
            error: function(e){
                console.log(e);
            }
        });
    });
});
</script>
</body>
</html>
