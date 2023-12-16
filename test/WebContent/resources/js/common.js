/**
 * 
 */
<script type="text/javascript">
            
             function funNavigateController(e,targetPage) {
                 var targetPage=targetPage;
                  e = e || window.event;
                  e.stopPropagation;     
                  var frmMain=document.getElementById("frmMain");
                   frmMain.resourceid.value=targetPage;
                 //  alert("Before submit");
                    frmMain.submit();
            }
</script>