<%@ page import="java.sql.*,java.io.*,java.util.*,java.text.*,javax.sql.*,javax.naming.*"%>
<%@ page contentType="text/html"%>
<%@include file="../usermanager/LoingCheck.jsp"%>

<% boolean logoutUser = false;%>
<script language='javascript'>
    function xbDetectBrowser(){
        var oldOnError = window.onerror;
        var element = null;
        window.onerror = null;
        // work around bug in xpcdom Mozilla 0.9.1
        window.saveNavigator = window.navigator;
        navigator.OS    = '';
        navigator.version  = parseFloat(navigator.appVersion);
        navigator.org    = '';
        navigator.family  = '';

        var platform;
        if (typeof(window.navigator.platform) != 'undefined'){
            platform = window.navigator.platform.toLowerCase();
            if (platform.indexOf('win') != -1)
                navigator.OS = 'win';
            else if (platform.indexOf('mac') != -1)
                navigator.OS = 'mac';
            else if (platform.indexOf('unix') != -1 || platform.indexOf('linux') != -1 || platform.indexOf('sun') != -1)
                navigator.OS = 'nix';
        }

        var i = 0;
        var ua = window.navigator.userAgent.toLowerCase();

        if (ua.indexOf('safari') != -1){
            i = ua.indexOf('safari');
            navigator.family = 'safari';
            navigator.org = 'safari';
            navigator.version = parseFloat('0' + ua.substr(i+7), 10);
        }else if (ua.indexOf('opera') != -1){
            i = ua.indexOf('opera');
            navigator.family  = 'opera';
            navigator.org    = 'opera';
            navigator.version  = parseFloat('0' + ua.substr(i+6), 10);
        }else if ((i = ua.indexOf('msie')) != -1){
            navigator.org    = 'microsoft';
            navigator.version  = parseFloat('0' + ua.substr(i+5), 10);
            if (navigator.version < 4)
                navigator.family = 'ie3';
            else
                navigator.family = 'ie4'
        }else if (ua.indexOf('gecko') != -1){
            navigator.family = 'gecko';
            var rvStart = ua.indexOf('rv:');
            var rvEnd   = ua.indexOf(')', rvStart);
            var rv      = ua.substring(rvStart+3, rvEnd);
            var rvParts = rv.split('.');
            var rvValue = 0;
            var exp     = 1;
            for (var i = 0; i < rvParts.length; i++){
                var val = parseInt(rvParts[i]);
                rvValue += val / exp;
                exp *= 100;
            }
            navigator.version = rvValue;
            if (ua.indexOf('netscape') != -1)
                navigator.org = 'netscape';
            else if (ua.indexOf('compuserve') != -1)
                navigator.org = 'compuserve';
            else
                navigator.org = 'mozilla';
        }else if ((ua.indexOf('mozilla') !=-1) && (ua.indexOf('spoofer')==-1) && (ua.indexOf('compatible') == -1) && (ua.indexOf('opera')==-1)&& (ua.indexOf('webtv')==-1) && (ua.indexOf('hotjava')==-1)){
            var is_major = parseFloat(navigator.appVersion);
            if (is_major < 4)
                navigator.version = is_major;
            else{
                i = ua.lastIndexOf('/')
                navigator.version = parseFloat('0' + ua.substr(i+1), 10);
            }
            navigator.org = 'netscape';
            navigator.family = 'nn' + parseInt(navigator.appVersion);
        }else if ((i = ua.indexOf('aol')) != -1 ){
            // aol
            navigator.family  = 'aol';
            navigator.org    = 'aol';
            navigator.version  = parseFloat('0' + ua.substr(i+4), 10);
        }else if ((i = ua.indexOf('hotjava')) != -1 ){
            // hotjava
            navigator.family  = 'hotjava';
            navigator.org    = 'sun';
            navigator.version  = parseFloat(navigator.appVersion);
        }
        window.onerror = oldOnError;
    }
    xbDetectBrowser();

    // Definition of class Folder     
    function Folder(folderDescription, hreference) //constructor 
    { 
        //constant data 
        this.desc = folderDescription; 
        this.hreference = hreference;
        this.id = -1;
        this.navObj = 0;
        this.iconImg = 0; 
        this.nodeImg = 0;
        this.isLastNode = 0;
        this.iconSrc = ICONPATH + "tvufolderopen.gif";
        this.iconSrcClosed = ICONPATH + "tvufolderclosed.gif";
        this.children = new Array;
        this.nChildren = 0;
        this.level = 0;
        this.leftSideCoded = "";
        this.isLastNode=false;
        this.parentObj = null;
        this.maySelect=true;
        this.prependHTML = ""

        //dynamic data 
        this.isOpen = false
        this.isLastOpenedFolder = false
        this.isRendered = 0

        //methods 
        this.initialize = initializeFolder 
        this.setState = setStateFolder 
        this.addChild = addChild 
        this.addChildren = addChildren
        this.createIndex = createEntryIndex 
        this.escondeBlock = escondeBlock
        this.esconde = escondeFolder 
        this.folderMstr = folderMstr 
        this.renderOb = drawFolder 
        this.totalHeight = totalHeight 
        this.subEntries = folderSubEntries 
        this.linkHTML = linkFolderHTML
        this.blockStartHTML = blockStartHTML
        this.blockEndHTML = blockEndHTML
        this.nodeImageSrc = nodeImageSrc
        this.iconImageSrc = iconImageSrc
        this.getID = getID
        this.forceOpeningOfAncestorFolders = forceOpeningOfAncestorFolders
    } 
 
    function initializeFolder(level, lastNode, leftSide) 
    { 
        var j=0 
        var i=0       
        nc = this.nChildren 

        this.createIndex() 
        this.level = level
        this.leftSideCoded = leftSide

        if (browserVersion == 0 || STARTALLOPEN==1)
            this.isOpen=true;

        if (level>0)
            if (lastNode) //the last child in the children array 
                leftSide = leftSide + "0"
        else
            leftSide = leftSide + "1"

        this.isLastNode = lastNode

        if (nc > 0) 
        { 
            level = level + 1 
            for (i=0 ; i < this.nChildren; i++)  
            {
                if (typeof this.children[i].initialize == 'undefined') //document node was specified using the addChildren function
                {
                    if (typeof this.children[i][0] == 'undefined' || typeof this.children[i] == 'string')
                    {
                        this.children[i] = ["item incorrectly defined", ""];
                    }

                    //Basic initialization of the Item object
                    //These members or methods are needed even before the Item is rendered
                    this.children[i].initialize=initializeItem;
                    this.children[i].createIndex=createEntryIndex;
                    if (typeof this.children[i].maySelect == 'undefined')
                        this.children[i].maySelect=true
                    this.children[i].forceOpeningOfAncestorFolders = forceOpeningOfAncestorFolders
                }
                if (i == this.nChildren-1) 
                    this.children[i].initialize(level, 1, leftSide)
                else 
                    this.children[i].initialize(level, 0, leftSide)
            } 
        } 
    } 

    function drawFolder(insertAtObj){ 
        var nodeName = ""
        var auxEv = ""
        var docW = ""
        var i=0

        finalizeCreationOfChildDocs(this)

        var leftSide = leftSideHTML(this.leftSideCoded)

        if (browserVersion > 0) 
            auxEv = "<a  href='javascript:clickOnNode(\""+this.getID()+"\")'>" 
        else 
            auxEv = "<a >" 

        nodeName = this.nodeImageSrc()

        if (this.level>0) 
            if (this.isLastNode) //the last child in the children array 
                leftSide = leftSide + "<td valign=top>" + auxEv + "<img name='nodeIcon" + this.id + "' id='nodeIcon" + this.id + "' src='" + nodeName + "' width=16 height=22 border=0></a></td>"
        else 
            leftSide = leftSide + "<td valign=top background=" + ICONPATH + "tvuvertline.gif>" + auxEv + "<img name='nodeIcon" + this.id + "' id='nodeIcon" + this.id + "' src='" + nodeName + "' width=16 height=22 border=0></a></td>"

        this.isRendered = 1

        if (browserVersion == 2) { 
            if (!doc.yPos) 
                doc.yPos=20 
        } 

        docW = this.blockStartHTML("folder");

        docW = docW + "<tr>" + leftSide + "<td valign=top>";
        if (USEICONS)
        {
            docW = docW + this.linkHTML(false) 
            docW = docW + "<img id='folderIcon" + this.id + "' name='folderIcon" + this.id + "' src='" + this.iconImageSrc() + "' border=0></a>"
        }
        else
        {
            if (this.prependHTML == "")
                docW = docW + "<img src=" + ICONPATH + "tvublank.gif height=2 width=2>"
        }
        if (WRAPTEXT)
            docW = docW + "</td>"+this.prependHTML+"<td valign=middle width='100%'>"
        else
            docW = docW + "</td>"+this.prependHTML+"<td valign=middle nowrap width='100%'>"
        if (USETEXTLINKS) 
        { 
            docW = docW + this.linkHTML(true) 
            docW = docW + this.desc + "</a>"
        } 
        else 
            docW = docW + this.desc
        docW = docW + "</td>"

        docW = docW + this.blockEndHTML()

        if (insertAtObj == null)
        {
            if (supportsDeferral) {
                doc.write("<div id=domRoot></div>") //transition between regular flow HTML, and node-insert DOM DHTML
                insertAtObj = getElById("domRoot")
                insertAtObj.insertAdjacentHTML("beforeEnd", docW)
            }
            else
                doc.write(docW)
        }
        else
        {
            insertAtObj.insertAdjacentHTML("afterEnd", docW)
        }

        if (browserVersion == 2) 
        { 
            this.navObj = doc.layers["folder"+this.id] 
            if (USEICONS)
                this.iconImg = this.navObj.document.images["folderIcon"+this.id] 
            this.nodeImg = this.navObj.document.images["nodeIcon"+this.id] 
            doc.yPos=doc.yPos+this.navObj.clip.height 
        } 
        else if (browserVersion != 0)
        { 
            this.navObj = getElById("folder"+this.id)
            if (USEICONS)
                this.iconImg = getElById("folderIcon"+this.id) 
            this.nodeImg = getElById("nodeIcon"+this.id)
        } 
    } 

    function setStateFolder(isOpen) 
    { 
        var subEntries 
        var totalHeight 
        var fIt = 0 
        var i=0 
        var currentOpen

        if (isOpen == this.isOpen) 
            return 

        if (browserVersion == 2){ 
            totalHeight = 0 
            for (i=0; i < this.nChildren; i++) 
                totalHeight = totalHeight + this.children[i].navObj.clip.height 
            subEntries = this.subEntries() 
            if (this.isOpen) 
                totalHeight = 0 - totalHeight 
            for (fIt = this.id + subEntries + 1; fIt < nEntries; fIt++) 
                indexOfEntries[fIt].navObj.moveBy(0, totalHeight) 
        }  
        this.isOpen = isOpen;

        if (!this.isOpen && this.isLastOpenedfolder){
            lastOpenedFolder = null;
            this.isLastOpenedfolder = false;
        }
        propagateChangesInState(this) 
    } 

    function propagateChangesInState(folder) 
    {   
        var i=0 

        //Change icon
        if (folder.nChildren > 0 && folder.level>0)  //otherwise the one given at render stays
            folder.nodeImg.src = folder.nodeImageSrc()

        //Change node
        if (USEICONS)
            folder.iconImg.src = folder.iconImageSrc()

        //Propagate changes
        for (i=folder.nChildren-1; i>=0; i--) {
            if (folder.isOpen) 
                folder.children[i].folderMstr(folder.navObj)
            else 
                folder.children[i].esconde() 
        }
    } 

    function escondeFolder(){ 
        this.escondeBlock()
        this.setState(0) 
    } 

    function linkFolderHTML(isTextLink){ 
        var docW = "";

        if (this.hreference){ 
            if (USEFRAMES)
                docW = docW + "<a  href='" + this.hreference + "' TARGET=\"basefrm\" "
            else
                docW = docW + "<a  href='" + this.hreference + "' TARGET=_top "
            if (isTextLink) {
                docW += "id=\"itemTextLink"+this.id+"\" ";
            }
            if (browserVersion > 0) 
                docW = docW + "onClick='javascript:clickOnFolder(\""+this.getID()+"\")'"
            docW = docW + ">"
        } 
        else 
            docW = docW + "<a >" 
        return docW;
    } 

    function addChild(childNode){ 
        this.children[this.nChildren] = childNode 
        childNode.parentObj = this
        this.nChildren++ 
        return childNode 
    } 

    //The list can contain either a Folder object or a sub list with the arguments for Item 
    function addChildren(listOfChildren) 
    { 
        this.children = listOfChildren 
        this.nChildren = listOfChildren.length
        for (i=0; i<this.nChildren; i++)
            this.children[i].parentObj = this
    } 

    function folderSubEntries() 
    { 
        var i = 0 
        var se = this.nChildren 

        for (i=0; i < this.nChildren; i++){ 
            if (this.children[i].children) //is a folder 
                se = se + this.children[i].subEntries() 
        } 

        return se 
    } 

    function nodeImageSrc() {
        var srcStr = "";

        if (this.isLastNode) //the last child in the children array 
        { 
            if (this.nChildren == 0)
                srcStr = ICONPATH + "tvulastnode.gif"
            else
                if (this.isOpen)
                    srcStr = ICONPATH + "tvumlastnode.gif"  
            else
                srcStr = ICONPATH + "tvuplastnode.gif"  
        } 
        else 
        { 
            if (this.nChildren == 0)
                srcStr = ICONPATH + "tvunode.gif"
            else
                if (this.isOpen)
                    srcStr = ICONPATH + "tvumnode.gif"
            else
                srcStr = ICONPATH + "tvupnode.gif"
        }   
        return srcStr;
    }

    function iconImageSrc() {
        if (this.isOpen)
            return(this.iconSrc)
        else
            return(this.iconSrcClosed)
    } 

    // Definition of class Item (a document or link inside a Folder) 
    // ************************************************************* 

    function Item(itemDescription) // Constructor 
    { 
        // constant data 
        this.desc = itemDescription 

        this.level = 0
        this.isLastNode = false
        this.leftSideCoded = ""
        this.parentObj = null

        this.maySelect=true

        this.initialize = initializeItem;
        this.createIndex = createEntryIndex;
        this.forceOpeningOfAncestorFolders = forceOpeningOfAncestorFolders;

        finalizeCreationOfItem(this)
    } 

    //Assignments that can be delayed when the item is created with folder.addChildren
    //The assignments that cannot be delayed are done in addChildren and in initializeFolder
    //Additionaly, some assignments are also done in finalizeCreationOfChildDocs itself
    function finalizeCreationOfItem(itemArray)
    {
        itemArray.navObj = 0 //initialized in render() 
        itemArray.iconImg = 0 //initialized in render() 
        itemArray.iconSrc = ICONPATH + "tvudoc.gif" 
        itemArray.isRendered = 0
        itemArray.nChildren = 0
        itemArray.prependHTML = ""

        // methods 
        itemArray.escondeBlock = escondeBlock
        itemArray.esconde = escondeBlock
        itemArray.folderMstr = folderMstr 
        itemArray.renderOb = drawItem 
        itemArray.totalHeight = totalHeight 
        itemArray.blockStartHTML = blockStartHTML
        itemArray.blockEndHTML = blockEndHTML
        itemArray.getID = getID
    }

    function initializeItem(level, lastNode, leftSide) 
    {  
        this.createIndex() 
        this.level = level
        this.leftSideCoded = leftSide
        this.isLastNode = lastNode
    } 

    function drawItem(insertAtObj) 
    { 
        var leftSide = leftSideHTML(this.leftSideCoded)
        var docW = ""

        var fullLink = "href=\""+this.link+"\" target=\""+this.target+"\" onClick=\"clickOnLink('"+this.getID()+"\', '"+this.link+"','"+this.target+"');return false;\"";
        this.isRendered = 1

        if (this.level>0) 
            if (this.isLastNode) //the last 'brother' in the children array 
        { 
            leftSide = leftSide + "<td valign=top><img src='" + ICONPATH + "tvulastnode.gif' width=16 height=22></td>"
        } 
        else 
        { 
            leftSide = leftSide + "<td valign=top background=" + ICONPATH + "tvuvertline.gif><img src='" + ICONPATH + "tvunode.gif' width=16 height=22></td>"
        } 

        docW = docW + this.blockStartHTML("item")

        docW = docW + "<tr>" + leftSide + "<td valign=top>"
        if (USEICONS)
            docW = docW + "<a  " + fullLink  + " id=\"itemIconLink"+this.id+"\">" + "<img id='itemIcon"+this.id+"' " + "src='"+this.iconSrc+"' border=0>" + "</a>"
        else
            if (this.prependHTML == "")
                docW = docW + "<img src=" + ICONPATH + "tvublank.gif height=2 width=3>"

        if (WRAPTEXT)
            docW = docW + "</td>"+this.prependHTML+"<td valign=middle width=100%>"
        else
            docW = docW + "</td>"+this.prependHTML+"<td valign=middle nowrap width=100%>"

        if (USETEXTLINKS) 
            docW = docW + "<a  " + fullLink + " id=\"itemTextLink"+this.id+"\">" + this.desc + "</a>"
        else 
            docW = docW + this.desc

        docW = docW + "</td>"

        docW = docW + this.blockEndHTML()

        if (insertAtObj == null)
        {
            doc.write(docW)
        }
        else
        {
            insertAtObj.insertAdjacentHTML("afterEnd", docW)
        }

        if (browserVersion == 2) { 
            this.navObj = doc.layers["item"+this.id] 
            if (USEICONS)
                this.iconImg = this.navObj.document.images["itemIcon"+this.id] 
            doc.yPos=doc.yPos+this.navObj.clip.height 
        } else if (browserVersion != 0) { 
            this.navObj = getElById("item"+this.id)
            if (USEICONS)
                this.iconImg = getElById("itemIcon"+this.id)
        } 
    } 


    // Methods common to both objects 
    function forceOpeningOfAncestorFolders() {
        if (this.parentObj == null || this.parentObj.isOpen)
            return
        else {
            this.parentObj.forceOpeningOfAncestorFolders()
            clickOnNodeObj(this.parentObj)
        }
    }

    function escondeBlock(){ 
        if (browserVersion == 1 || browserVersion == 3) { 
            if (this.navObj.style.display == "none") 
                return 
            this.navObj.style.display = "none" 
        } else { 
            if (this.navObj.visibility == "hidden") 
                return 
            this.navObj.visibility = "hidden" 
        }     
    } 

    function folderMstr(domObj){ 
        if (!this.isRendered)
            this.renderOb(domObj)
        else
            if (browserVersion == 1 || browserVersion == 3) 
                this.navObj.style.display = "block" 
        else 
            this.navObj.visibility = "show" 
    } 

    function blockStartHTML(idprefix) {
        var idParam = "id='" + idprefix + this.id + "'"
        var docW = ""

        if (browserVersion == 2) 
            docW = "<layer "+ idParam + " top=" + doc.yPos + " visibility=show>"
        else if (browserVersion != 0)
            docW = "<div " + idParam + " style='display:block;'>"

        docW = docW + "<table border=0 cellspacing=0 cellpadding=0 width=100% >"

        return docW
    }

    function blockEndHTML(){
        var docW = ""

        docW = "</table>"

        if (browserVersion == 2) 
            docW = docW + "</layer>"
        else if (browserVersion != 0)
            docW = docW + "</div>"

        return docW
    }

    function createEntryIndex() 
    { 
        this.id = nEntries 
        indexOfEntries[nEntries] = this 
        nEntries++ 
    } 

    // total height of subEntries open 
    function totalHeight() //used with browserVersion == 2 
    { 
        var h = this.navObj.clip.height 
        var i = 0 

        if (this.isOpen) //is a folder and _is_ open 
            for (i=0 ; i < this.nChildren; i++)  
                h = h + this.children[i].totalHeight() 

        return h 
    } 

    function leftSideHTML(leftSideCoded){
        var i;
        var retStr = "";
        for (i=0; i<leftSideCoded.length; i++){
            if (leftSideCoded.charAt(i) == "1"){
                retStr = retStr + "<td valign=top background=" + ICONPATH + "tvuvertline.gif><img src='" + ICONPATH + "tvuvertline.gif' width=16 height=22></td>"
            }
            if (leftSideCoded.charAt(i) == "0"){
                retStr = retStr + "<td valign=top><img src='" + ICONPATH + "tvublank.gif' width=16 height=22></td>"
            }
        }
        return retStr
    }

    function getID()
    {
        if (typeof this.xID != "undefined") 
            return this.xID
        else
            return this.id
    }

    // Events 
    function clickOnFolder(folderId){ 
        var clicked = findObj(folderId)

        if (typeof clicked=='undefined' || clicked==null){
            alert("Treeview was not able to find the node object corresponding to ID=" + folderId + ". If the configuration file sets a.xID values, it must set them for ALL nodes, including the foldersTree root.")
            return;
        }

        if (!clicked.isOpen) {
            clickOnNodeObj(clicked) 
        }

        if (lastOpenedFolder != null && lastOpenedFolder != folderId)
            clickOnNode(lastOpenedFolder); //sets lastOpenedFolder to null

        if (clicked.nChildren==0) {
            lastOpenedFolder = folderId;
            clicked.isLastOpenedfolder = true
        }

        if (isLinked(clicked.hreference)) {
            highlightObjLink(clicked);
        }
    } 

    function clickOnNode(folderId) { 
        fOb = findObj(folderId);
        if (typeof fOb=='undefined' || fOb==null){
            alert("Treeview was not able to find the node object corresponding to ID=" + folderId + ". If the configuration file sets a.xID, it must set foldersTree.xID as well.")
            return;
        }
        clickOnNodeObj(fOb);
    }

    function clickOnNodeObj(folderObj){ 
        var state = 0 
        var currentOpen
        state = folderObj.isOpen 
        folderObj.setState(!state) //open<->close  
    }

    function clickOnLink(clickedId, target, windowName) {
        highlightObjLink(findObj(clickedId));
        if (isLinked(target)) {
            window.open(target,windowName);
        }
    }

    // Other Proce related to TVu
    function finalizeCreationOfChildDocs(folderObj) {
        for(i=0; i < folderObj.nChildren; i++)  {
            child = folderObj.children[i]
            if (typeof child[0] != 'undefined')
            {
                // Amazingly, arrays can have members, so   a = ["a", "b"]; a.desc="asdas"   works
                // If a doc was inserted as an array, we can transform it into an itemObj by adding 
                // the missing members and functions
                child.desc = child[0] 
                setItemLink(child, GLOBALTARGET, child[1])   
                finalizeCreationOfItem(child)
            }
        }
    }

    function findObj(id)
    {
        var i=0;
        var nodeObj;

        if (typeof foldersTree.xID != "undefined") {
            nodeObj = indexOfEntries[i];
            for(i=0;i < nEntries && indexOfEntries[i].xID!=id;i++) //may need optimization
                ;
            id = i
        }
        if (id >= nEntries)
            return null; //example: node removed in DB
        else
            return indexOfEntries[id];
    }

    function isLinked(hrefText) {
        var result = true;
        result = (result && hrefText !=null);
        result = (result && hrefText != '');
        result = (result && hrefText.indexOf('undefined') < 0);
        result = (result && hrefText.indexOf('parent.op') < 0);
        return result;
    }

    // toggle back and front colours
    function highlightObjLink(nodeObj) {
        if (!HIGHLIGHT || nodeObj==null || nodeObj.maySelect==false) {//node deleted in DB 
            return;
        }
        if (browserVersion == 1 || browserVersion == 3) {
            var clickedDOMObj = getElById('itemTextLink'+nodeObj.id);
            if (clickedDOMObj != null) {
                if (lastClicked != null) {
                    var prevClickedDOMObj = getElById('itemTextLink'+lastClicked.id);
                    prevClickedDOMObj.style.color=lastClickedColor;
                    prevClickedDOMObj.style.backgroundColor=lastClickedBgColor;
                }
                lastClickedColor    = clickedDOMObj.style.color;
                lastClickedBgColor  = clickedDOMObj.style.backgroundColor;
                clickedDOMObj.style.color=HIGHLIGHT_COLOR;
                clickedDOMObj.style.backgroundColor=HIGHLIGHT_BG;
            }
        }
        lastClicked = nodeObj;
    }

    function insFld(parentFolder, childFolder){ 
        return parentFolder.addChild(childFolder) 
    } 

    function insDoc(parentFolder, document){ 
        return parentFolder.addChild(document) 
    } 

    function gFld(description, hreference){ 
        folder = new Folder(description, hreference);
        return folder;
    } 

    function gLnk(optionFlags, description, linkData){ 
        newItem = new Item(description);
        setItemLink(newItem, optionFlags, linkData);
        return newItem;
    } 

    function setItemLink(item, optionFlags, linkData) {
        var targetFlag = "";
        var target = "";
        var protocolFlag = "";
        var protocol = "";

        targetFlag = optionFlags.charAt(0)
        if (targetFlag=="B")
            target = "_blank"
        if (targetFlag=="P")
            target = "_parent"
        if (targetFlag=="R")
            target = "basefrm"
        if (targetFlag=="S")
            target = "_self"
        if (targetFlag=="T")
            target = "_top"

        if (optionFlags.length > 1) {
            protocolFlag = optionFlags.charAt(1)
            if (protocolFlag=="h")
                protocol = "http://"
            if (protocolFlag=="s")
                protocol = "https://"
            if (protocolFlag=="f")
                protocol = "ftp://"
            if (protocolFlag=="m")
                protocol = "mailto:"
        }

        item.link = protocol+linkData;    
        item.target = target
    }
    function preLoadIcons() {
        var auxImg
        auxImg = new Image();
        auxImg.src = ICONPATH + "tvuvertline.gif";
        auxImg.src = ICONPATH + "tvumlastnode.gif";
        auxImg.src = ICONPATH + "tvumnode.gif";
        auxImg.src = ICONPATH + "tvuplastnode.gif";
        auxImg.src = ICONPATH + "tvupnode.gif";
        auxImg.src = ICONPATH + "tvublank.gif";
        auxImg.src = ICONPATH + "tvulastnode.gif";
        auxImg.src = ICONPATH + "tvunode.gif";
        auxImg.src = ICONPATH + "tvufolderclosed.gif";
        auxImg.src = ICONPATH + "tvufolderopen.gif";
        auxImg.src = ICONPATH + "tvudoc.gif";
    }

    //Open some folders for initial layout, if necessary
    function setInitialLayout() {
        if (browserVersion > 0 && !STARTALLOPEN)
            clickOnNodeObj(foldersTree);
    }

    //Used with NS4 and STARTALLOPEN
    function renderAllTree(nodeObj, parent) {
        var i=0;
        nodeObj.renderOb(parent)
        if (supportsDeferral)
            for (i=nodeObj.nChildren-1; i>=0; i--) 
                renderAllTree(nodeObj.children[i], nodeObj.navObj)
        else
            for (i=0 ; i < nodeObj.nChildren; i++) 
                renderAllTree(nodeObj.children[i], null)
    }

    function hideWholeTree(nodeObj, hideThisOne, nodeObjMove) {
        var i=0;
        var heightContained=0;
        var childrenMove=nodeObjMove;

        if (hideThisOne)
            nodeObj.escondeBlock()

        if (browserVersion == 2)
            nodeObj.navObj.moveBy(0, 0-nodeObjMove)

        for (i=0 ; i < nodeObj.nChildren; i++){
            heightContainedInChild = hideWholeTree(nodeObj.children[i], true, childrenMove)
            if (browserVersion == 2) {
                heightContained = heightContained + heightContainedInChild + nodeObj.children[i].navObj.clip.height
                childrenMove = childrenMove + heightContainedInChild
            }
        }
        return heightContained;
    }

    // Simulating inserAdjacentHTML on NS6
    if(typeof HTMLElement!="undefined" && !HTMLElement.prototype.insertAdjacentElement){
        HTMLElement.prototype.insertAdjacentElement = function (where,parsedNode)
        {
            switch (where){
                case 'beforeBegin':
                    this.parentNode.insertBefore(parsedNode,this)
                    break;
                case 'afterBegin':
                    this.insertBefore(parsedNode,this.firstChild);
                    break;
                case 'beforeEnd':
                    this.appendChild(parsedNode);
                    break;
                case 'afterEnd':
                    if (this.nextSibling) 
                        this.parentNode.insertBefore(parsedNode,this.nextSibling);
                    else this.parentNode.appendChild(parsedNode);
                    break;
            }
        }

        HTMLElement.prototype.insertAdjacentHTML = function(where,htmlStr){
            var r = this.ownerDocument.createRange();
            r.setStartBefore(this);
            var parsedHTML = r.createContextualFragment(htmlStr);
            this.insertAdjacentElement(where,parsedHTML)
        }
    }

    function getElById(idVal){
        if (document.getElementById != null)
            return document.getElementById(idVal)
        if (document.all != null)
            return document.all[idVal]
        alert("Problem getting element by id")
        return null
    }

    var USETEXTLINKS = 0;
    var STARTALLOPEN = 0;
    var USEFRAMES = 0;
    var USEICONS = 1;
    var WRAPTEXT = 0;
    var ICONPATH = '';
    var HIGHLIGHT = 0;
    var HIGHLIGHT_COLOR = 'white';
    var HIGHLIGHT_BG    = 'blue';
    var BUILDALL = 0;    
    var GLOBALTARGET = "T"; // variable only applicable for addChildren uses

    //Other variables
    var lastClicked = null;
    var lastClickedColor;
    var lastClickedBgColor;
    var indexOfEntries = new Array 
    var nEntries = 0 
    var browserVersion = 0 
    var selectedFolder=0
    var lastOpenedFolder=null
    var doc = document
    var supportsDeferral = false

    doc.yPos = 0

    // function 2 be called to render the tree view 
    function initializeDocument() 
    { 
        preLoadIcons();
        switch(navigator.family)
        {
            case 'ie4':
                browserVersion = 1 //Simply means IE > 3.x
                break;
            case 'opera':
                browserVersion = (navigator.version > 6 ? 1 : 0); //opera7 has a good DOM
                break;
            case 'nn4':
                browserVersion = 2 //NS4.x 
                break;
            case 'gecko':
                browserVersion = 3 //NS6.x
                break;
            case 'safari':
                browserVersion = 1 //Safari Beta 3 seems to behave like IE in spite of being based on Konkeror
                break;
            default:
                browserVersion = 0 //other, possibly without DHTML  
                break;
        }

        supportsDeferral = ((navigator.family=='ie4' && navigator.version >= 5 && navigator.OS != "mac") || browserVersion == 3);
        supportsDeferral = supportsDeferral & (!BUILDALL)
        if (!USEFRAMES && browserVersion == 2)
            browserVersion = 0;  

        //foldersTree (with the site's data) is created in an external .js (demoFramesetNode.js, for example)
        foldersTree.initialize(0, true, "") 
        if (supportsDeferral && !STARTALLOPEN) {
            foldersTree.renderOb(null) //delay construction of nodes
        }

        else {
            renderAllTree(foldersTree, null);

            //To force the scrollable area to be big enough
            if (browserVersion == 2) 
                doc.write("<layer top=" + indexOfEntries[nEntries-1].navObj.top + ">&nbsp;</layer>") 

            if (browserVersion != 0 && !STARTALLOPEN)
                hideWholeTree(foldersTree, false, 0)
        }
        setInitialLayout()
    } 
    var idMenuTimeOut;
    var actLeft=0;
    var blnShowingMenu = 0;
    
    function funShowTVuMenu(){
        if (blnShowingMenu ==0){
            document.getElementById('divMenuPanel').style.display = 'block';            
            actLeft = -20;
            blnShowingMenu = 1;
            document.getElementById('divMenuPanel').style.left = actLeft;
            //document.getElementById('divMenuPanel').style.top = actLeft;
            document.getElementById('divMenu').style.display = 'none';
            if ( idMenuTimeOut > 0 ){ clearTimeout(idMenuTimeOut); }
            idMenuTimeOut = setTimeout("posMenuLeft()", 20);
        }
    }
    
    function posMenuLeft(){
        actLeft = actLeft + 3;
        if (actLeft < 0){
            document.getElementById('divMenuPanel').style.left = actLeft; 
            //document.getElementById('divMenuPanel').style.top = actLeft; 
        }else{
            document.getElementById('divMenuPanel').style.left = 0;             
            //document.getElementById('divMenuPanel').style.top = 0;
            clearTimeout(idMenuTimeOut);
        }
        if (document.getElementById('divMenuPanel').style.left == 0){
            document.getElementById('divCloseMenu').style.display = 'block';            
            if ( idMenuTimeOut > 0 ){ clearTimeout(idMenuTimeOut); }
        }else{
            idMenuTimeOut = setTimeout("posMenuLeft()", 20);
        }        
    }
    
    function funHideTVuMenu(){
        blnShowingMenu = 0;
        document.getElementById('divMenuPanel').style.display = 'none';
        document.getElementById('divCloseMenu').style.display = 'none';
        document.getElementById('divMenu').style.display = 'block';
    }
    
    // Decide if the names are links or just the icons
    USETEXTLINKS = 1;  //replace 0 with 1 for hyperlinks
    USEFRAMES = 0;
    USEICONS = 0;
    WRAPTEXT = 1;
    PRESERVESTATE = 1;
    STARTALLOPEN = 1; //replace 0 with 1 to show the whole tree
    ICONPATH = '../icons/'; 

    foldersTree = gFld("<FONT color='black' class='bd_mnu'>Menu</FONT>", "javascript:undefined");
    <%  String strOpen = "";
    boolean logout;
    long lngMenuid = 0;
    String strMenutype = "";
    try {
        lngMenuid = Long.parseLong(request.getParameter("menuid"));
        strMenutype = request.getParameter("submenuorpopupmenu");
    } catch (Exception e) {
    }
    if (strMenutype == null) {
        strMenutype = "";
    }
    try {
        int t = 1;%>
    <jsp:useBean id="usermenus" class="firstline.userManager.usermanager" scope="page"/>
    <jsp:setProperty name="usermenus" property="loginUser" value="<%=lu%>"/>
    <%      java.sql.ResultSet rs = usermenus.getMenus(lu.getDigitalSignature());
    boolean rsNext = rs.next();
    String strMenuName = "";
    String strPageName = "";
    long mmid = 0, smid = 0, pmid = 0;
    try {
        while (rsNext) {
            strMenuName = "";
            if (mmid != rs.getInt("mainmenuid")) {
                try {
                    strMenuName = rs.getString("MainMenuName");
                } catch (Exception e) {
                }%>
                    aux1 = insFld(foldersTree, gFld("<FONT color='blue' TITLE='<%=strMenuName%>' class='bd_mnu'><%=strMenuName%></FONT>", "javascript:undefined"));
    <%
                        mmid = rs.getInt("mainmenuid");
                        //}
                    }
                    if (smid != rs.getInt("submenuid")) {
                        try {
                            strMenuName = rs.getString("SubMenuName");
                            strPageName = rs.getString("submenupage");
                        } catch (Exception e) {
                        }
                        if (strPageName == null) {
                            strPageName = "javascript:undefined";
                        } else {
                            strPageName = "../" + strPageName;
                        }
                        if (rs.getInt("submenuid") > 0) {
                            if (strMenutype.equals("S") && lngMenuid == rs.getInt("submenuid")) {%>
                                aux2 = insFld(aux1, gFld("<TABLE style='border-collapse: collapse; CURSOR: hand;'><TR><TD nowrap><FONT color='blue' TITLE='<%=strMenuName%>' class='bd_smnu'><%=strMenuName%></FONT></TD></TR></TABLE>", "<%=strPageName%>"));
    <%} else {%>
                    aux2 = insFld(aux1, gFld("<FONT TITLE='<%=strMenuName%>' color='blue' class='bd_mnu'><%=strMenuName%></FONT>", "<%=strPageName%>"));
    <%}
                            smid = rs.getInt("submenuid");
                        }
                    }
                    if (pmid != rs.getInt("popupmenuid")) {
                        try {
                            strMenuName = rs.getString("PopupMenuName");
                            strPageName = rs.getString("popupumenupage");
                        } catch (Exception e) {
                        }
                        if (strPageName == null) {
                            strPageName = "javascript:undefined";
                        } else {
                            strPageName = "../" + strPageName;
                        }
                        if (strPageName.indexOf("LoginForm.jsp") == -1) {
                            strOpen = "T";
                        } else {
                            strOpen = "T";
                        }
                        if (rs.getInt("popupmenuid") > 0) {
                            if (strMenutype.equals("P") && lngMenuid == rs.getInt("popupmenuid")) {%>
                                insDoc(aux2, gLnk("<%=strOpen%>", "<TABLE style='border-collapse: collapse; CURSOR: hand;'><TR><TD nowrap><FONT color='blue' TITLE=\"<%=strMenuName%>\" class='bd_smnu'><%=strMenuName%></FONT></TD></TR></TABLE>", "<%=strPageName%>"));
    <%} else {%>
                    insDoc(aux2, gLnk("<%=strOpen%>", "<FONT color='blue' TITLE='<%=strMenuName%>' class='bd_mnu'><%=strMenuName%></FONT>", "<%=strPageName%>"));
    <%}
                                pmid = rs.getInt("popupmenuid");
                            }
                        }
                        rsNext = rs.next();
                    }
                } catch (Exception pe) {%>
    <%="<!--" + pe + "-->"%>
    <%}
        } catch (Exception e) {%>
            document.write("Error in menu creation!"+<%=e%>);
    <%}
    %>
</script>
<div width='200px' class="bd_shell" style="position:absolute; 
     top:0; left:0; height:100%; overflow: auto;z-index:99" id="divMenuPanel" name="divMenuPanel">
    <span style='border:1px'>        
        <table width="200px" style="border-collapse: collapse;" 
               cellpadding="0" cellspacing="0" topmargin="0" 
               bottommargin="0" leftmargin="0" rightmargin="0">
            <tr>
                <td>
                    <script>initializeDocument()</script>
                    <noscript>Sorry! Can not load menu!</noscript>
                </td>
            </tr>
        </table>
    </span>
</div>