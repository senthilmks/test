<%-- 
    Document   : MyView
    Created on : 27 Jun, 2022, 3:42:21 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!--        <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>-->
        <link rel="stylesheet" href="resources/css/validationEngine.jquery.css"/>
        <script src="resources/scripts/jquery-3.6.0.min.js" type="text/javascript"></script> 
        <script src="resources/scripts/jquery-ui.min.js" type="text/javascript"></script> 
        <link rel="stylesheet" href="resources/css/jquery-ui.min_cupertino.css"/>  
        <!--<link rel="stylesheet" href="resources/css/jquery-ui.min.css"/>-->
        
        <script src="resources/scripts/jquery.validationEngine.min.js" type="text/javascript"></script> 
        <script src="resources/scripts/jquery.validationEngine-en.min.js" type="text/javascript"></script> 
        <script src="resources/scripts/jquery-ui-timepicker-addon.min.js" type="text/javascript"></script> 
        <link rel="stylesheet" href="resources/css/jquery-ui-timepicker-addon.min.css"/> 
        <link rel="stylesheet" href="resources/css/jquery.dataTables.yadcf.css"/> 
        <script src="resources/scripts/jquery.dataTables.yadcf.min.js" type="text/javascript"></script>

<!--        <link rel="stylesheet" href="resources/css/datatables.min.css"/> 
        <script src="resources/scripts/datatables.min.js" type="text/javascript"></script> 
        <script src="resources/scripts/dataTables.tableTools.min.js" type="text/javascript"></script>
        <script src="resources/scripts/buttons.dataTables.min.js" type="text/javascript"></script>
        <script src="resources/scripts/buttons.html5.min.js" type="text/javascript"></script>
        <script src="resources/scripts/jszip.min.js" type="text/javascript"></script>
        <script src="resources/scripts/pdfmake.min.js" type="text/javascript"></script>
        <script src="resources/scripts/vfs_fonts.js" type="text/javascript"></script>  
         <link rel="stylesheet" href="resources/css/jquery.dataTables.min.css"/> 
         <link rel="stylesheet" href="resources/css/buttons.dataTables.min.css"/> 
         <link rel="stylesheet" href="resources/css/dataTables.tableTools.min.css"/>         -->
         
         
         
         
         
         
        
        <jsp:include page="MyPageHeader.jsp" flush="false" />
        <script type="text/javascript">
            var myFormTitle = "Test";
            var myFormHandlerURL = "pages/MyModel.jsp";
            var myTables = ['', 'tblTest', 'tblEmpData'];
            $(document).ready(function () {
                //$("#frmTest").validationEngine({validationEventTrigger: 'submit'}); 
                
                $("#frmTest").validationEngine();
                
                //alert( $("#frmTest").validationEngine('validate') );
                
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
//                                    funViewData();
                            }
                    );
                    $(".butsave").click(
                            function () {
                                funSaveData();
                            }
                    )
                    $(".butview").click(
                            function () {
                                funViewData();
                            }
                    )
                    $(".butreset").click(
                            function () {
                                funResetData();
                            }
                    )
                    $("button").button().click(
                            function (event) {
                                event.preventDefault();
                            }
                    );

                }
                );

                $(function () {
                    $("#txtDate").datetimepicker(
                            {
                                controlType: 'select',
                                changeMonth: true,
                                changeYear: true,
                                onLine: true,
                                dateFormat: 'dd-mm-yy',
                                use24hours: true, timeFormat: 'HH:mm', showAnim: 'explode', yearRange: '-0:+3',
                                onSelect: function (selected) {
                                    $("#txtDate").datetimepicker("option", "minDate", selected);
                                }
                            }
                    )
                }
                );

            }
            );

            function funLoadData(opr, filterVal, containerDiv, loadTableName) {
                //alert("funLoadData called >> opr " + opr+", filterVal >>"+filterVal+", containerDiv >>"+containerDiv+", loadTableName >> "+loadTableName);
                var paramValues = $("#frmTest").serializeArray();
                paramValues.push({name: "opr", value: 1});
                paramValues.push({name: "filterval", value: filterVal});
                $(containerDiv).html("<img src='resources/images/imgloading.gif'/>");
                $(containerDiv).show();
                $.post(myFormHandlerURL, paramValues, function (resultdata) {
                    $(containerDiv).html(resultdata);
                    $("#divDataContainer").html(resultdata);
                }, "html").fail(function (jqXHR, textstatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
                )
            }

            function funCloseLoaderDiv(argObj)
            {
                divObj = $(argObj).parents(".clsdivs:first");
                //  alert(divObj.attr("handle"));
                txtObj = divObj.prevAll(".clsajaxcall:first");
                //   alert(txtObj.val());
                hdnObj = divObj.next(".clsajaxcall");
                txtObj.val("");
                hdnObj.val("0");
                $(argObj).parents(".clsdivs:first").html("");
                $(argObj).parents(".clsdivs:first").hide();
                document.getElementById($(divObj).attr("id")).style.display = "none";
            }

            function funSelectVal(argObj, callBackId) {
                //   alert("funselect called");
                divObj = $(argObj).parents(".clsdivs:first");
                //  alert(divObj.attr("handle"));
                txtObj = divObj.prevAll(".clsajaxcall:first");

                hdnObj = divObj.next(".clsajaxcall");
                txtObj.val($(argObj).attr("handle"));
                // alert(txtObj.val());
                hdnObj.val($(argObj).attr("id"));
                alert(hdnObj.val());
                $(argObj).parents(".clsdivs:first").html("");
                $(argObj).parents(".clsdivs:first").hide();
                document.getElementById($(divObj).attr("id")).style.display = "none";
            }

            function funViewData() {

                var paramValues = $("#frmTest").serializeArray();
                paramValues.push({name: "opr", value: 2});
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

            function funResetData() {
                $("#divDataContainer").html("");
                $("#divDataContainer").hide();
                $("#divUAInfo").html("");
                $("#divUAInfo").hide();
            }

            function funSaveData() {
              if(!($("#frmTest").validationEngine('validate',{autoHidePrompt:true})))
              {
                  alert("validation Engine called. Please fill all the details");
                  return;
              }
                alert("validation success ");
                var dialogueHTML = "<p><span class='ui-icon ui-icon-circle-close' style='float:left; margin: 0 7px 50px 0;'></span> Do you want to continue? </p>";
                $("#divAlert").html(dialogueHTML);
                $("#divAlert").attr(myFormTitle);
                $("#divAlert").dialog(
                        {
                            resizable: false,
                            modal: true,
                            buttons: {
                                "Yes": function () {
                                    $(this).dialog("close");
                                    var paramValues = $("#frmTest").serializeArray();
                                    paramValues.push({name: "opr", value: 3});
                                    paramValues.push({name: "filterval", value: ""});
                                    $.post(myFormHandlerURL, paramValues, function (resultdata) {
                                        //alert("resultdata >> "+resultdata);                                        
                                        if (resultdata.resultstatus == 1)
                                        {
                                            dialogueHTML = "<p><span class='ui-icon ui-icon-circle-close' style='float:left; margin: 0 7px 50px 0;'></span>" + resultdata.result + " </p>";
                                            $("#divAlert").html(dialogueHTML);
                                            $("#divAlert").attr("title", myFormTitle);
                                            $("#divAlert").dialog(
                                                    {
                                                        resizable: false,
                                                        modal: true,
                                                        buttons: {
                                                            "Ok": function () {
                                                                $(this).dialog("close");
                                                                //document.location.reload(true);
                                                            }
                                                        }
                                                    }
                                            ) // End of divAlert.dialog
                                        }
                                        $("#divUAInfo").removeClass("ui-state-error");
                                        $("#divUAInfo").addClass(resultdata.resultclass);
                                        $("#divUAInfo").html(resultdata.result);
                                        $("#divUAInfo").show();

                                    }, "json").fail(function (jqXHR, textstatus, errorThrown) {
                                        alert(jqXHR.responseText);
                                    }
                                    )

                                }, // End of Yes function
                                "Cancel": function () {
                                    $(this).dialog("close");
                                }
                            }
                        }
                )

            }
        </script> 
        <title>MyView</title>
    </head>
    <body>
        <form id="frmTest" name="frmTest" method="post" autocomplete="off">
            <h1 align="center">Hello World! View</h1>

            <table cellspacing="2" cellpadding="1" class="ui-corner-all ui-widget ui-widget-content" align="center">
                <tr class="ui-widget-header">
                    <td>
                        Enter Details
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="uname" id="uname" class="clsajaxcall listbox" data-validation-engine="validate[required, minSize[10], maxSize[10]]" value=""/>                                    
                        <div id="divNames" name="divNames" class="clsdivs" handle="1" style="position: absolute; width: 600px; height: 100px;overflow-x: auto; overflow-y: auto;z-index: none; display: none;">       </div>
                        <input type="hidden" id="hdnEmpId" name="hdnEmpId" class="clsajaxcall" value="" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="password" id="password" class="validate[required]" value=""/>                                    
                        <div id="divNames" name="divNames" class="clsdivs" handle="1" style="position: absolute; width: 600px; height: 100px;overflow-x: auto; overflow-y: auto;z-index: none; display: none;">       </div>
                        <input type="hidden" id="hdnPassword" name="hdnPassword" class="clsajaxcall" value="" />

                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="txtDate" id="txtDate" class="datepicker" value=""/>                                                           
                    </td>
                </tr>
                <tr>
                    <td>
                        <button id="butView" class="butview">View</button>
                        <button id="butSave" class="butsave">Save</button>
                        <button id="butReset" class="butreset ui-button ui-corner-all">Reset</button>
                        <div id="divAlert" style="display:none;"></div>
                        <div id="divUAInfo" name="divUAInfo" style="display:none;">UATinfo</div>
                    </td>
                </tr>
            </table>

            <table style="width:80%" border="0" align="center" >
                <tr>                    
                    <td>                               
                        <div id="divDataContainer" name="divDataContainer" style="width: 100%; height: auto;overflow-x: none; overflow-y: none; display: none;" >Default Content</div>   
                    </td>
                </tr>
            </table>

        </form>
    </body>
</html>
