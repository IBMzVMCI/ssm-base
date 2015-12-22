
var ver=navigator.appVersion.substring(navigator.appVersion.indexOf("MSIE ")+5,navigator.appVersion.indexOf("MSIE ")+8);

if(getOs()!="1"){
    alert("本系统暂不支持您正在使用的浏览器,推荐使用IE6、IE8浏览器浏览本系统!");

    window.close();
}

/*if(IsMaxthon()){
 alert("您正在使用傲游Maxthon浏览器,建议您使用IE6.0以上版本浏览本系统!");
 }*/



if ( ver<5.6 ){
    if(confirm("本系统暂不支持您正在使用的浏览器,推荐使用IE6、IE8浏览器浏览本系统!")){
        window.location.href="http://www.microsoft.com/windows/ie/downloads/ie6/default.asp";
    }
}

document.onkeydown=look;

function look(){
    if(getEvent().keyCode=="13"){
        submitForm();
    }
}
function submitForm(){
    if(checkForm() && getOs()=="1"){
        document.CheckUserForm.userPassword.blur();
        if(document.CheckUserForm.code){
            if(document.CheckUserForm.code.value==""){
                alert("请输入验证码！");
                return false;
            }else{
                var sessionCode=document.CheckUserForm.code.value;
                var url=((window.location.href).substring(0,(window.location.href).indexOf("/jsoa")))+"/jsoa/CheckUser.do?action=checkSessionCode&sessionCode="+sessionCode;
                sendG(url,getResult,null);
            }
        }else{
            submit();
        }
    }
}

function getResult(cartXML){
    var cart = cartXML.getElementsByTagName("result")[0];
    var code = cart.getElementsByTagName("code")[0].firstChild.nodeValue;
    if(code=='0'){
        alert("验证码输入错误，请重新输入");
        document.CheckUserForm.code.value="";
        document.CheckUserForm.code.focus();
        document.getElementById("pwdImage").click();
        return false;
    }else{
        submit();
    }
}

function unLoad(){
    setCookie("jsoaUserName",document.CheckUserForm.userName.value, expdate, "/", null, false);
    //setCookie("jsoaDomainAccount",document.CheckUserForm.domainAccount.value, expdate, "/", null, false);
    if(document.CheckUserForm.checkbox && document.CheckUserForm.checkbox.checked==true){
        setCookie("MarkPwd",'1', expdate, "/", null, false);
        setCookie("jsoaUserPwd",document.CheckUserForm.userPassword.value, expdate, "/", null, false);
    }else{
        setCookie("MarkPwd",'0', expdate, "/", null, false);
    }
}

function myReset(){
    document.all.domainAccount.value = "";
    document.all.userName.value = "";
    document.all.userPassword.value = "";
}

function changeLan(){
    var lan=document.all.localeCode.value;
    location.href="login.jsp?localeCode="+lan;
}

function getOs()
{
    //alert(navigator.userAgent);
    if(navigator.userAgent.indexOf("MSIE")>0)return 1;
    if(navigator.userAgent.indexOf("Trident/7.0")>0)return 1;//ie11
    if(isFirefox=navigator.userAgent.indexOf("Firefox")>0)return 1;
    if(isSafari=navigator.userAgent.indexOf("Safari")>0)return 1;
    //if(isCamino=navigator.userAgent.indexOf("Camino")>0)return 4;
    //if(isMozilla=navigator.userAgent.indexOf("Gecko/")>0)return 5;
    //if(navigator.userAgent.indexOf("MAXTHON")>0)return 6;
    //return 1;
}

function IsMaxthon()
{
    try{
        window.external.max_invoke("GetHotKey");
        return true;
    }catch(ex){
        return false;
    }
}

//获取事件对象

function getEvent()
{
    if(document.all)return window.event;
    func=getEvent.caller;
    while(func!=null){
        var arg0=func.arguments[0];
        if(arg0){
            if((arg0.constructor==Event || arg0.constructor ==MouseEvent)
                || (typeof(arg0)=="object" && arg0.preventDefault && arg0.stopPropagation)){
                arg0.x = arg0.pageX;
                arg0.y = arg0.pageY ;
                arg0.srcElement = arg0.target;
                return arg0;
            }
        }
        func=func.caller;
    }
    return null;

}