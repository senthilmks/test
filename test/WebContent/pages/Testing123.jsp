<%-- 
    Document   : Testing Always
    Created on : 27 Jun, 2022, 3:42:21 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script src="resources/scripts/jquery-3.6.0.min.js" type="text/javascript"></script>
		<link href="resources/css/select2.min.css" rel="stylesheet" />
		<link href="resources/css/select2-bootstrap.min.css" rel="stylesheet" />
		<link href="resources/css/bootstrap.min.css" rel="stylesheet" />
		<script src="resources/scripts/select2.min.js"></script>
		
		<link rel="stylesheet" href="resources/css/dataTables.bootstrap5.min.css"/> 
		<script src="resources/js/dataTables.bootstrap5.min.js" type="text/javascript"></script> 
		<script src="resources/js/jquery.dataTables.min.js" type="text/javascript"></script> 
   
        <link rel="stylesheet" href="resources/css/validationEngine.jquery.css"/>
        <script src="resources/scripts/jquery-ui.min.js" type="text/javascript"></script> 
        <link rel="stylesheet" href="resources/css/jquery-ui.min_cupertino.css"/>  
        
        
        <link rel="stylesheet" href="resources/css/jquery.dataTables.min.css"/> 
       <!--  <script src="resources/scripts/jquery.dataTables.min.js" type="text/javascript"></script> --> 
         <script src="resources/scripts/datatables.min.js" type="text/javascript"></script>
         
          
        <script src="resources/scripts/jquery.validationEngine.min.js" type="text/javascript"></script> 
        <script src="resources/scripts/jquery.validationEngine-en.min.js" type="text/javascript"></script> 
        <script src="resources/scripts/jquery-ui-timepicker-addon.min.js" type="text/javascript"></script> 
        <link rel="stylesheet" href="resources/css/jquery-ui-timepicker-addon.min.css"/> 
        <link rel="stylesheet" href="resources/css/jquery.dataTables.yadcf.css"/> 
        <script src="resources/scripts/jquery.dataTables.yadcf.min.js" type="text/javascript"></script>
        
        
        
        <script type="text/javascript">
        var myFormHandlerURL = window.location.href;
        $(document).ready(function () {
            //$("#frmMain").validationEngine({validationEventTrigger: 'submit'}); 
            
            
            
            //alert( $("#frmMain").validationEngine('validate') );
            
            $(function () {
                // alert("I am ready");
                $(':input.clsajaxcall[type="text"]').keyup(
                        function (event) {
                            var ldivobj = $(this).nextAll(".clsdivs:first");
                            //  alert(ldivobj);
                            var ldiv = "#" + ldivobj.attr("id");
                            //alert("ldiv>>"+ldiv);
                            var txtObj = $(this).attr("id")[0];
                            //alert(txtObj);
                            //alert("ldivobj.attr('handle') >>"+ldivobj.attr("handle"));
                            funLoadData(ldivobj.attr("handle"), $(this).val(), ldiv, myTables[ldivobj.attr("handle")]);
//                                funViewData();
                        }
                );
             
                $(".butview").click(
                        function () {
                            funViewData();
                        }
                );
                
                $("button").button().click(
                        function (event) {
                            event.preventDefault();
                        }
                );

            }
            );
        }
        );
            
        
        function funViewData() {
			alert("target >> "+myFormHandlerURL);
            var paramValues = $("#frmMain").serializeArray();
            //paramValues.push({name: "action", value: 2});
            paramValues.push({name: "action", value: "generatemenu"});
            paramValues.push({name: "filterval", value: ""});
            
            $.post(myFormHandlerURL, paramValues, function (resultdata) {
                $("#divDataContainer").show();
                $("#divDataContainer").html(resultdata);
	
                $("#tblEmpData").dataTable({
                    "processing": true,
                    "bJqueryUI": true,
                    "bPagenate": false,
                    "bSort": true,
                    "destroy": true,
                    dom: "Bfrtip",
                    columnDefs: [{
                            "defaultContent": "-",
                            "targets": "_all"
                        }],
                    buttons: [
                        {
                            extend: "copyHtml5",
                        },
                        {
                            extend: "csvHtml5",
                            title: "My Title for CSV",
                        },
                        {
                            extend: "excelHtml5",
                            title: "My Title for Excel",
                        },
                        {
                            extend: "pdfHtml5",
                            title: "My Title for Excel",
                            orientation: "landscape",
                            pageSize: "LEGAL",
                        },
                    ]
                }
                ).yadcf([{column_number: 1, filter_match_mode: 'exact', errMode: 'throw'}]) // End of tblEmpData data tablse            

            }, "html").fail(function (jqXHR, textstatus, errorThrown) {
                alert(jqXHR.responseText);
            }
            )
        }
        </script> 
        <title>MyView</title>
    </head>
    <body>
        <form id="frmMain" name="frmMain" method="post" autocomplete="off">
            <h1 align="center">Hello World! Testing</h1>
            
            
            <button id="butView" class="butview">Test View</button>

            <table style="width:80%" border="0" align="center" >
                <tr>                    
                    <td>                               
                        <div id="divDataContainer" name="divDataContainer" style="width: 100%; height: auto;overflow-x: none; overflow-y: none; display: none;" >Default Content</div>   
                    </td>
                </tr>
            </table>
           
			<%--<input type="hidden" id="resource" name="resource" value='${requestScope.resource}' /> --%>
			
			<input type="hidden" id="resource" name="resource" value='testing' />
			
        </form>
        


    </body>
</html>
