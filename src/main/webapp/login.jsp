<%--
  Created by IntelliJ IDEA.
  User: jinchao.xu
  Date: 14-10-31
  Time: 下午3:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>乐道互动OA办公系统</title>
    <style type="text/css">

    </style>
    <SCRIPT LANGUAGE="JavaScript">
        <!--

        var screenwidth;//分辨率宽度
        var screenheight;//分辨率高度
        screenwidth = screen.width;
        screenheight = screen.height;
        //-->
    </SCRIPT>
    <SCRIPT LANGUAGE="JavaScript" src="js/cookie.js"></SCRIPT>
    <script LANGUAGE="JavaScript" src="js/log.js"></script>
    <script src="/jsoa/webmail/ajax_util.js"></script>
    <link href="/jsoa/dist/css/uioa.min.css" rel="stylesheet" type="text/css" />
    <script LANGUAGE="JavaScript" src="jsoa/dist/js/sea.js"></script>
    <script LANGUAGE="JavaScript" src="jsoa/dist/js/init.js"></script>
</head>
<body class="logbody" onUnload="javascript:unLoad();" onLoad="javascript:load();checkIE11();jumpErr();">
<center>
    <div class="loginbox">
        <div >
            <form name="CheckUserForm" method="post" action="/jsoa/CheckUser.jspx">
                <input type="hidden" name="checkBrow" id="checkBrow" value=""/>
                <input type="hidden" name="pwdError" value="0"/>
                <div class="log-t"></div>
                <div class="log-c" >
                    <div class="loginp-error"></div>

                    <div class="loginp-name">
                        <input name="userName" type="text" class="but_z">
                    </div>
                    <div class="loginp-passport">
                        <input name="userPassword" type="password" class="but_z">
                    </div>




                    <div style="width:225px; text-align:left;">
                        <input type="checkbox" name="checkbox" id="checkbox"/>
                        <font style="font-weight: bold;">记住密码</font>
                    </div>
                    &nbsp;


                    <div class="loginp-button"><a href="javascript:submitForm();"></a></div>
                </div>
            </form>
        </div></center>
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">

    function initHeight(){
        if(navigator.userAgent.indexOf("MSIE")!=-1) {
            if(navigator.userAgent.indexOf("MSIE 10.0")==-1){
                document.getElementById("tdHeight").style.height="80";
            }else{
                document.getElementById("tdHeight").style.height="40";
            }
        }else if(navigator.userAgent.indexOf("Trident/7.0")!=-1){//ie 11
            document.getElementById("tdHeight").style.height="40";
        }
        if(navigator.userAgent.indexOf("Firefox")!=-1){//火狐
            document.getElementById("tdHeight").style.height="40";
        }
        if(navigator.userAgent.indexOf("Safari")!=-1){//苹果
            document.getElementById("tdHeight").style.height="40";
        }

    }
    var error=document.CheckUserForm.pwdError.value;
    //<!--
            function msg(){

                alert("对不起，此账号不存在，请确认您所填写的账号及密码无误！");
                document.CheckUserForm.userName.select();

            }

    function submit(){
        /*if(navigator.userAgent.indexOf("iPad")>0){
         unLoad();
         }*/

        document.CheckUserForm.submit();
    }

    function checkForm(){
        if(document.CheckUserForm.userName.value==""){
            alert("请输入正确的用户帐号！");
            document.CheckUserForm.userName.focus();
            return false;
        }else if( document.CheckUserForm.userPassword.value==""){
            alert("请输入正确的密码！");
            document.CheckUserForm.userPassword.focus();
            return false;
        }
        return true;
    }
    function load(){
        //判断是否在框架内或子页面打开了登陆页
        /*if(opener){
         opener.location.href="/jsoa/login.jsp";
         window.close();
         }
         if(self.frameElement && self.frameElement.tagName=="IFRAME"){
         window.parent.location.href="/jsoa/login.jsp";
         }*/

        msg();
        //deleteCookie("jsoaUSERNAME");
        if(Cookie("jsoaUserName") != null || Cookie("jsoaDomainAccount") != null){
            if(Cookie("jsoaUserName") != null){
                document.CheckUserForm.userName.value = Cookie("jsoaUserName");
            }

        }
        if(document.getElementById('checkbox')){
            if(Cookie("MarkPwd") != null || Cookie("jsoaDomainAccount") != null){
                if(Cookie("MarkPwd") != null){
                    if(Cookie("MarkPwd")=='1'){
                        document.CheckUserForm.checkbox.checked=true;
                        if(Cookie("jsoaUserPwd") != null || Cookie("jsoaDomainAccount") != null){
                            if(Cookie("jsoaUserPwd") != null){
                                document.CheckUserForm.userPassword.value = Cookie("jsoaUserPwd");
                            }
                        }
                    }else{
                        document.CheckUserForm.checkbox.checked=false;
                    }
                }
            }
        }

        document.CheckUserForm.userName.select();

    }

    function addFavorite(){
        window.external.addFavorite(window.location.href, document.title);
        return false;
    }
    function setHomePage(){
        document.body.style.behavior='url(#default#homepage)';
        document.body.setHomePage('http://web.oa.ledo.com/jsoa/login.jsp');
        return false;
    }
    function jumpSite(){
        location.href="http://www.jiusi.net";
    }
    function jumpErr(){

    }

    function checkIE11(){
        if(navigator.userAgent.indexOf("Trident/7.0")>=0 && navigator.userAgent.indexOf("MSIE")>=0){
            document.getElementById("checkBrow").value="IE11And360";
        }else if(navigator.userAgent.indexOf("Trident/7.0")>=0 && navigator.userAgent.indexOf("MSIE")<0){
            document.getElementById("checkBrow").value="IE11";
        }
    }
    //-->

</script>
