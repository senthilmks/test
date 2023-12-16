<%-- 
    Document   : MyView
    Created on : 27 Jun, 2022, 3:42:21 PM
    Author     : Administrator
--%>

<%@page import="com.test.LoginUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title>My Login</title>
        <script type="text/javascript">
             function funSubmit()
             {
                 //alert("submit called");
                 document.getElementById("hdnUserName").value=document.getElementById("uname").value;
                 document.getElementById("hdnPassword").value=document.getElementById("password").value;
                // alert("hidden set");
                 document.getElementById("uname").value="areyouuser";
                 document.getElementById("password").value="yourpass";
                 document.getElementById("action").value="AUTHENTICATE";
                // alert( "action >> "+document.getElementById("hdnAction").value);
                 document.getElementById("frmLogin").method="post";
                 document.getElementById("frmLogin").action="<%=request.getContextPath( )%>/AppManager";
                 //alert("before submit");
                 //document.getElementById("frmLogin").action="MyLogin.jsp";
                 document.getElementById("frmLogin").submit();
                 
             }
             </script>
    </head>
    <body>
        <form id="frmLogin" name="frmLogin" method="post" autocomplete="off" >
            <h1>Hello World!</h1>
            <table>
                <tr>
                    <td>
                        <input type="text" name="uname" id="uname" class="validate[required]" value=""/>  
                        <input type="hidden" id="hdnUserName" name="hdnUserName" class="clsajaxcall" value="" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="password" id="password" class="validate[required]" value=""/>
                        <input type="hidden" id="hdnPassword" name="hdnPassword" class="clsajaxcall" value="" />
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <button id="butView" class="butview" onclick="funSubmit();">Login</button>
                        <button id="butReset" class="butreset">Reset</button>
                        <div id="divAlert" style="display:none;"></div>
                        <div id="divUAInfo" name="divUAInfo" style="display:none;"></div>
                        <input type="hidden" name="action" id="action"  value=""/> 
                        <input type="hidden" name="hdnUserName" id="hdnUserName"  value=""/> 
                        <input type="hidden" name="hdnPassword" id="hdnPassword"  value=""/> 
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
