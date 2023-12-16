<%-- 
    Document   : test1
    Created on : 18 Feb, 2023, 11:56:54 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>eLibrary</title>
        
        <!--<link type="text/css" href="../resources/css/jquery-ui.css" rel="stylesheet"/>      
        <link type="text/css" href="../resources/css/themes/redmond.css" rel="stylesheet"/> -->
        <link type="text/css" href="../resources/css/mystyle.css" rel="stylesheet"/>
        <script language="javascript" src="../resources/scripts/jquery-1.9.1.js" type="text/javascript"></script>        
        <!--<script language="javascript" src="../resources/scripts/jquery-3.6.0.min.js" type="text/javascript"></script>-->
        <script language="javascript" src="../resources/scripts/outline.js" type="text/javascript"></script>
        <script language="javascript" src="../resources/scripts/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
        <script language="javascript" src="../resources/scripts/jquery-ui-timepicker.js" type="text/javascript"></script>
        <script language=javascript>
            var frmTitle = "Book Master";
            var url = "test1Model.jsp";
            var tblList = ['', 'tblTest', '', '', '', '', '', ''];
	    $(document).ready(function () {
		$(':input.clsajaxload[type="Text"]').keyup(function (event) {
                    console.log("ready function called");
		    var ldivobj = $(this).nextAll('.clsdivs:first');
                    console.log(" ldivobj >> "+ldivobj);
		    ldiv = "#" + ldivobj.attr("id");
                    console.log(" ldiv >> "+ldiv);
		    var txtobj = document.getElementById($(this).attr("id"));
                    console.log(" txtobj >> "+txtobj);
		    if (!callKeyNavigation(event, tblList[ldivobj.attr("title")], txtobj)) {
			return;
		    }
		    if ($(this).val().length < 1)
			return;
                    console.log(" before data load title >> "+$(this).attr("title")+ ", val >> "+$(this).val()+", Table >> "+tblList[ldivobj.attr("title")]);
		    loadData($(this).attr("title"), $(this).val(), ldiv, tblList[ldivobj.attr("title")], $(this));
		});
                drawTableBorder("tableMain", 'ui-widget-content', 'ui-widget-content');
                $(function () {
                    $('.datetime').timepicker({
                        controlType: 'select',
                        timeFormat: 'hh:mm tt'
                    });
		    $('#txtGraceFromTime').timepicker({
			controlType: 'select', oneLine: true,
			timeFormat: "mm", showAnim: "explode"
		    });
		    $('#txtGraceToTime').timepicker({
			controlType: 'select', oneLine: true,
			timeFormat: "mm", showAnim: "explode"
		    });
		});           
	    });
	    
	    function loadData(argId, argFilter, argDiv, argTableName, argObject) {
		var ajaxParameter = $("#frmBookMaster").serializeArray();
		ajaxParameter.push({name: "opr", value: argId});
		ajaxParameter.push({name: "filterval", value: argFilter});
                console.log(" before post >> argFilter>> "+argFilter);
		$.post(url, ajaxParameter, function (data) {
                    console.log(" after post >> data>> "+data);
		    $(argDiv).html(data);
		    $(argDiv).show();
		    drawTableBorder('tblTest', "ui-widget-content", "ui-widget-content");
		}, "html").error(function (jqXHR, textStatus, errorThrown) {
		    alert(jqXHR.responseText);
		})
	    }
	    function selectAppSubmData(argObject, argCallbackId) {
		divObject = $(argObject).parents('.clsdivs:first');
		textObject = divObject.prevAll('.clsajaxload:first');
		hiddenObject = divObject.next('.clsajaxload');
		textObject.val($(argObject).attr("title"));
		hiddenObject.val($(argObject).attr("id"));
		$(argObject).parents('.clsdivs:first').html("");
		$(argObject).parents('.clsdivs:first').hide();
	    }

	    function funCloseLoaderDiv(argObject, argId) {
		divObject = $(argObject).parents('.clsdivs:first');
		textObject = divObject.prevAll('.clsajaxload:first');
		hiddenObject = divObject.next('.clsajaxload');
		textObject.val("");
		hiddenObject.val("");
		$(argObject).parents('.clsdivs:first').html("");
		$(argObject).parents('.clsdivs:first').hide();
	    }
            function superAlert(argTitle, argMsg, argObjectName) {
                try {
                    var dialogHtml = "<p><span class='ui-icon ui-icon-circle-close' style='float:left; margin:0 7px 50px 0;'></span>" +
                            argMsg + "</p>";
                    $("#divAlert").html(dialogHtml);
                    $("#divAlert").attr("title", argTitle);
                    $("#divAlert").dialog({
                        modal: true,
                        width: 250,
                        resizable: false,
                        position: [300, 200],
                        buttons: {
                            Ok: function () {
                                $(this).dialog("close");
                                if (argObjectName != "")
                                    $("#" + argObjectName).focus();
                            }
                        }
                    });
                } catch (e) {
                    //alert(argMsg);
                }
            }

            function funSaveSPM() {
                var ShiftDesc = $.trim($("#txtShiftDesc").val());
                var totime = $("#txtToTime").val();
                var fromtime = $("#txtFromTime").val();
                var gracefromtime = $("#txtGraceFromTime").val();
                var gracetotime = $("#txtGraceToTime").val();
                if (ShiftDesc == "" || totime == "" || fromtime == "" || gracefromtime == "" || gracetotime == "") {
                    superAlert(frmTitle, "Fill the Required Fields", "divAlert");
                    return false;
                }

                var dialogHtml = "<p><span class='ui-icon ui-icon-circle-close' style='float:left; margin:0 7px 50px 0;'></span>" +
                        "Wish to save..!" + "</p>";
                $("#divAlert").html(dialogHtml);
                $("#divAlert").attr("title", frmTitle);
                $("#divAlert").dialog({
                    resizable: false,
                    modal: true,
                    width: 350,
                    position: [500, 200],
                    buttons: {
                        "Save": function () {
                            $(this).dialog("close");
                            SPMParameter = $('#frmBookMaster').serializeArray();
                            SPMParameter.push({name: "ids", value: "1"});
                            SPMParameter.push({name: "filter", value: ""});
                            $.post(url, SPMParameter, function (data) {
                                if (data.resultstatus == 1) {
                                    funClearAll();
                                }
                                $('#divResultInfo').removeClass(data.resultclass);
                                $('#divResultInfo').addClass(data.resultclass)
                                $('#divResultInfo').html(data.result);
                                $('#divResultInfo').show();
                            }, "json").error(function (jqXHR, textStatus, errorThrown) {
                                alert(" Error " + jqXHR.responseText + " " + textStatus);
                            })
                        },
                        Cancel: function () {
                            $(this).dialog("close");
                        }
                    }
                });

            }

            function funUpdateSPM() {
                var cap = $("#btViewSPM").val();
                if (cap == "View") {
                    $("#divResultInfo").hide();
                    $("#btSaveSPM").hide();
                    $("#txtShiftDesc").val("");
                    $("#txtFromTime").val("00:00");
                    $("#txtToTime").val("23:59");
                    $("#txtGraceFromTime").val("00");
                    $("#txtGraceToTime").val("00");
                    $("#btViewSPM").val("Modify");
                    SPMParameter = [];
                    SPMParameter.push({name: "ids", value: "2"});
                    SPMParameter.push({name: "filter", value: $("#hdnShiftDescId").val()});
                    $.post(url, SPMParameter, function (data) {
                        $("#divView").html(data);
                        $("#divView").show();
                        drawTableBorder('tblShiftView', "ui-widget-content", "ui-widget-content");
                    }, "html").error(function (jqXHR, textStatus, errorThrown) {
                        alert(jqXHR.responseText);
                    })
                } else {
                    var ShiftDesc = $.trim($("#txtShiftDesc").val());
                    var totime = $("#txtToTime").val();
                    var fromtime = $("#txtFromTime").val();
                    var gracefromtime = $("#txtGraceFromTime").val();
                    var gracetotime = $("#txtGraceToTime").val();
                    if (ShiftDesc == "" || totime == "" || fromtime == "" || gracefromtime == "" || gracetotime == "") {
                        superAlert(frmTitle, "Fill the Required Fields", "divAlert");
                        return false;
                    }
                    var dialogHtml = "<p><span class='ui-icon ui-icon-circle-close' style='float:left; margin:0 7px 50px 0;'></span>" +
                            "Wish to Modify..!" + "</p>";
                    $("#divAlert").html(dialogHtml);
                    $("#divAlert").attr("title", frmTitle);
                    $("#divAlert").dialog({
                        resizable: false,
                        modal: true,
                        width: 250,
                        buttons: {
                            "Yes": function () {
                                $(this).dialog("close");
                                var SPMParameter = $('#frmBookMaster').serializeArray();
                                SPMParameter.push({name: "ids", value: "3"});
                                SPMParameter.push({name: "filter", value: ""});
                                $.post(url, SPMParameter, function (data) {
                                    if (data.resultstatus == 1) {
                                        funClearAll();
                                    }
                                    $("#divView").html("");
                                    $("#divView").hide();
                                    $('#divResultInfo').removeClass("ui-state-error");
                                    $('#divResultInfo').addClass(data.resultclass);
                                    $("#divResultInfo").html(data.result);
                                    $("#divResultInfo").show();
                                }, "json").error(function (jqXHR, textStatus, errorThrown) {
                                    alert(jqXHR.responseText);
                                })
                            },
                            Cancel: function () {
                                $(this).dialog("close");
                                $("#txtShiftDesc").focus();
                            }
                        }
                    });
                }
            }

            function funClickView(argid, argdesc, argfromtime, argtotime,argGraceFromTime,argGraceToTime,argOfficeName,argOfficeid,argShiftType) {
                $("#txtShiftDesc").val(argdesc);
                $("#txtFromTime").val(argfromtime);
                $("#txtToTime").val(argtotime);
                $("#txtGraceFromTime").val(argGraceFromTime);
                $("#txtGraceToTime").val(argGraceToTime);
		$("#txtOffice").val(argOfficeName);
		$("#hdnTitleId").val(argOfficeid);
//		$("#txtOffice").prop("readonly",true);
                $("#hdnShiftDescId").val(argid);
		if(argShiftType==1){
		    $("#radShiftDay").prop("checked",true);
		}else{
		    $("#radShiftNight").prop("checked",true);
		}
		$("#hdnShiftType").val(argShiftType);
                $("#divView").hide();
            }

            function funClearAll() {
                $("#txtShiftDesc").val("");
		$("#txtOffice").val("");
		$("#hdnTitleId").val("");
                $("#txtFromTime").val("00:00");
                $("#txtToTime").val("23:59");
                $("#txtGraceFromTime").val("00");
                $("#txtGraceToTime").val("00");
		$("#txtOffice").prop("readonly",false);
                $("#btViewSPM").val("View");
		$("#radShiftDay").prop("checked",true);
		$("#hdnShiftType").val("1");
                $("#btViewSPM").show();
                $("#btSaveSPM").show();
            }
            function funRefresh() {
                $("#divAlert").hide();
                $("#divView").hide();
                $("#divResultInfo").hide();
		$("#clsdivs").hide();
                funClearAll();
            }
	    function funShiftType(arg){
		$("#hdnShiftType").val(arg);
	    }

        </script> 
        
    </head>
     <body>      
        <form id="frmBookMaster" name="frmBookMaster" method=post autocomplete='on'>
            <table align="center" id="tableMain"  class='myTable' width="70%"  border=0 cellpadding='7' cellspacing='0'>
                <tr><td class="ui-widget-header" COLSPAN="4" align="center">Book Status</td></tr>
                <tr>
		    <td width="30%">Title <sup><font color='red'>*</font></sup></td>  
		    <td  nowrap colspan="4">
			<input type="Text" id="txtBook" name="txtBook" class="clsajaxload" title="1" style="width:50%">
                        <div id="divBook" title="1" class="clsdivs" style="display:none;position: absolute;height: auto;width:400px; overflow:auto;z-index:200;"></div>
                        <input class="clsajaxload" type="hidden" name="hdnTitleId" id="hdnTitleId" value="0">
		    </td>
		</tr>
		<tr> 
                    <td width="30%">Book Author<sup><font color='red'>*</font></sup></td>                
                    <td nowrap COLSPAN="4">
                        <input type="Text" id='txtAuthor' name='txtAuthor' size="30" >
                        <input  type="hidden" id="txtAuthorId" name="txtAuthorId" >
                    </td>
                </tr>
		<tr>
		    <td width="30%">Author Type<sup><font color='red'>*</font></sup></td>                
                    <td nowrap COLSPAN="4">
                        <input type="radio" id="radForeign" name="radAuthorType" onclick="funShiftType(1)" checked> Foreigner
			<input type="radio" id="radIndian" name="radAuthorType" onclick="funShiftType(2)"> Indian
			<input type="hidden" id="hdnAuthorType" name="hdnAuthorType" value="1"> 
                    </td> 
		</tr>
		
                <tr>
                    <td>Time<sup><font color='red'>*</font></sup>&nbsp;&nbsp;<font color='blue' size="1">(HH:MM)</font></td>
                    <td> From&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="txtFromTime" name="txtFromTime"  size="10" class="datetime">
                        &nbsp;&nbsp;&nbsp;&nbsp; To <sup><font color='red'>*</font></sup>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="txtToTime" name="txtToTime"  size="10"   class="datetime"></td>
                </tr>
                <tr>
                    <td>Grace Time<sup><font color='red'>*</font></sup>&nbsp;&nbsp;<font color='blue' size="1">(HH:MM)</font></td>
                    <td><input type="text" id="txtGraceFromTime" name="txtGraceFromTime"  size="10" class="timepicker">
                        <!--&nbsp;&nbsp;&nbsp;&nbsp; To <sup><font color='red'>*</font></sup>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="txtGraceToTime" name="txtGraceToTime"  size="10"   class="timepicker">-->
                    </td>
                </tr>
                <tr>
                    <td align="center" colspan="4">
                        <input type="button" id='btSaveSPM' name='btSaveSPM' value='Save'title="1" style='cursor:pointer' onClick='funSaveSPM()'>
                        <input type="button" id='btViewSPM' name='btViewSPM' value='View' title="2" style='cursor:pointer'  onClick='funUpdateSPM()'>
                        <input type="button" id='btRefreshPGM' name='btRefreshPGM' value='Refresh' style='cursor:pointer' onclick='funRefresh()'>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="center" >
                        <div id="divResultInfo" name="divResultInfo" style="display:none"></div> 
                        <div align='center' id="divView" name="divView" style="height:303px;overflow-y:auto;overflow-x:auto;display:none"></div>
                        <div id="divAlert" name="divAlert" style="display: none;"></div>
                    </td>
                </tr>

            </table>
        </form>
    </body>
</html>
