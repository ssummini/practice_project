<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<style type="text/css">
body {
    background-color: #FFE2FA;
    font-family: 'Arial', sans-serif;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 50vh;
    flex-direction: column;
}

table {
   width: 500px;
   margin: auto;
   background-color: white;
   border-radius: 10px;
   padding: 20px;
}

th {
   padding: 10px 15px;
   text-align: left;
   color: #333;
   font-size: 16px;
}

td {
   padding: 10px 10px;   
}

div {
   color: red;
   font-size: 10pt;
   font-weight: bold;
   margin-top: 5px;
}

input[type="text"], input[type="password"] {
   width: 100%;
   padding: 8px;
   border: 1px solid #CCC;
   border-radius: 5px;
}

input[type="button"], input[type="submit"] {
   background-color: #FF9ADB; 
   border: none;
   font-size: 16px; 
   padding: 10px 15px;
   border-radius: 5px;
   cursor: pointer;
   color: white;
   transition: background-color 0.3s ease;
}

input[type="button"]:hover, input[type="submit"]:hover {
   background-color: #FF70C0;
}

img {
   margin: 20px 0;
   cursor: pointer;
}


</style>
</head>
<body>
<img src="../image/free-icon-love-4096198.png" alt="홈" width="60" height="60" onclick="location.href='/memberMVC/index.do'" />
   <form id="loginForm">
      <table>
         <tr>
            <th>아이디</th>
            <td>
               <input type="text" name="id" id="id" placeholder="아이디 입력" />
               <div id="idDiv"></div>
            </td>
         </tr>
         <tr>
            <th>비밀번호</th>
            <td>
               <input type="password" name="pwd" id="pwd" placeholder="비밀번호 입력" />
               <div id="pwdDiv"></div>
            </td>
         </tr>
         <tr>
             <td colspan="2" align="center">
                 <input type="submit" value="로그인" id="loginBtn"/>
                 <input type="button" value="회원가입" onclick="location.href='/memberMVC/member/writeForm.do'" />
             </td>
         </tr>
      </table>
      <!--  쿠키 = <span></span> -->
   </form>   
<script src="../js/member.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$('#loginBtn').click(function(event) {
    event.preventDefault(); // 기본 제출 동작 방지
    
    $('#idDiv').empty();
    $('#pwdDiv').empty();
    
    if ($('#id').val() == '') {
        $('#idDiv').html('아이디를 입력하세요.');
    } else if ($('#pwd').val() == '') {
        $('#pwdDiv').html('비밀번호를 입력하세요.');
    } else {
        $.ajax({
            type: 'POST',
            url: '/memberMVC/member/login.do',
            data: {
                'id': $('#id').val(),
                'pwd': $('#pwd').val()
            },
            dataType: 'text', // 서버로부터 순수한 텍스트만 받음
            success: function(data) {
               console.log(data);
                if (data.trim() === 'fail') {
                    alert("아이디 또는 비밀번호가 틀렸습니다.");
                } else {
                    alert(data.trim() + "님이 로그인하였습니다.");                    
                    location.href = '/memberMVC/index.do'
                }
            },
            error: function(e) {
                console.log(e);
            }
        });
    }
});

</script>

</body>
</html>
