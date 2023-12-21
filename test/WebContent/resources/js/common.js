/*!
 * common.js
 *
 * Date: 2023-05-11T18:29Z
 */

             function funRequestDispatcher(e,targetPage) {
                 var targetPage=targetPage;
                  e = e || window.event;
                  e.stopPropagation;     
                  var frmMain=document.getElementById("frmMain");
                   frmMain.resourceid.value=targetPage;
                 //  alert("Before submit");
                    frmMain.submit();
            }
             
             function funLoadPage (event,page) {
 				//alert("target >> "+myFormHandlerURL);
 				event.preventDefault();
 				 $("#resourceid").val(page);
                // alert("value >> "+$("#resourceid").val());
 				
                 var paramValues = $("#frmMain").serializeArray();
			      
                 //paramValues.push({name: "action", value: 2});
                 paramValues.push({name: "action", value: "dispatch"});
                 //paramValues.push({name: "resourceid", value: page});

                 
                /* $(paramValues).each(function(i, field){
                	  alert(field.name+"="+ field.value);
                	});*/
                 
                 $.post(window.location.href, paramValues, function (resultdata) {
                     $("#divPageContainer").show();
                     $("#divPageContainer").html(resultdata);		
                     
                 }, "html").fail(function (jqXHR, textstatus, errorThrown) {
                     alert(jqXHR.responseText);
                 }
                 )
             }
