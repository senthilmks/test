
        <script type="text/javascript">
            
             function funNavigateController(e,targetPage) {
                 targetPage=targetPage;
                // alert("funNavigateController called targetPage >>"+targetPage);
                  e = e || window.event;
                  e.stopPropagation;     
                  var frmMain=document.getElementById("frmMain");
                   frmMain.resourceid.value=targetPage;
                 //  alert("Before submit");
                    frmMain.submit();
            }
            
//            funNavigateController (e,targetPage)
//            {
//                e = e || window.event;
//                e.stopPropagation;
//                var frmMain=document.getElementById("frmMain");
//                frmMain.targetPage.value=targetPage;
//                try {
//                    if(e.button ==2 )
//                    {
//                        frmMain.target="_blank";
//                    }
//                    else
//                    {
//                        frmMain.target="_self";
//                    }
//                } 
//                catch (Exception e)
//                {
//                    alert(e);
//                }
//                frmMain.submit();
//            }
        </script>

        <form id="frmMain" name="frmMain" method="post" action="<%=request.getContextPath()%>/AppManager">
            <input type="hidden" name="resourceid" id="resourceid" value="" />  
            <!-- <input type="hidden" id="resource" name="resource" value="menu" />  -->         
        </form>
 

