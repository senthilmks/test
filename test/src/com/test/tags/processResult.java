package com.test.tags;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.sql.*;

/**
 *  Generated tag class.
 */
public class processResult extends BodyTagSupport {
    
    /** property declaration for tag attribute: resultSet.
     *
     */
    private int intRowCount = 0;
    private java.sql.ResultSet rsTag = null;
    private ResultSetMetaData rsmd;
    private int colcount = 0;
    private boolean blnRsNext = false;
    private boolean blnNullValue = true;
    private long lngStartFrom = 0;
    private long lngProcessCount = 0;
    private boolean lblnCloseResultSetAtEnd = true;
    
    public processResult(){
        super();
    }
    
    
    ////////////////////////////////////////////////////////////////
    ///                                                          ///
    ///   User methods.                                          ///
    ///                                                          ///
    ///   Modify these methods to customize your tag handler.    ///
    ///                                                          ///
    ////////////////////////////////////////////////////////////////
    
    
    //
    // methods called from doStartTag()
    //
    /**
     *
     * Fill in this method to perform other operations from doStartTag().
     *
     */
    public void otherDoStartTagOperations()  {
        
        //
        // TODO: code that performs other operations in doStartTag
        //       should be placed here.
        //       It will be called after initializing variables,
        //       finding the parent, setting IDREFs, etc, and
        //       before calling theBodyShouldBeEvaluated().
        //
        //       For example, to print something out to the JSP, use the following:
        //
        //   try {
        //       JspWriter out = pageContext.getOut();
        //       out.println("something");
        //   } catch (java.io.IOException ex) {
        //       // do something
        //   }
        //
        //
        try{
            // moving the record set pointer to the lngStartFrom position
            //System.out.println("Process Result set called--!");
            
            
            if (lngStartFrom > 0){
                for (long rloop=1; rloop<lngStartFrom; rloop++){
                    if(!rsTag.next()) break;
                }
            }
            blnRsNext = false;
            try{if (rsTag.next()){blnRsNext = true;}}catch(Exception e){}
            String lstrFieldValue = "";
            if(blnRsNext){
                rsmd=rsTag.getMetaData();
                
                colcount=rsmd.getColumnCount();
                
                //System.out.println("colcount"+colcount);
                
                intRowCount = 1;
                pageContext.setAttribute("SLNUM", ""+intRowCount);
                for(int i=1;i<=colcount;i++){
                    //System.out.println(rsmd.getColumnName(i).toUpperCase()+":"+rs.getString(i));
                    lstrFieldValue = rsTag.getString(i);
                    //System.out.println("First:"+lstrFieldValue);
                    if (!blnNullValue){
                        try{
                            if(lstrFieldValue.equals("null")){
                                lstrFieldValue = "";
                                //System.out.println(lstrFieldValue);
                            }else{
                                //System.out.println(lstrFieldValue);
                            }
                        }catch(Exception e){lstrFieldValue = "";}
                    }
                    pageContext.setAttribute(rsmd.getColumnName(i).toUpperCase(),lstrFieldValue);
                    
                    
                }
            }
        }
        catch(Exception e){System.out.println(e);}
        
    }
    
    /**
     *
     * Fill in this method to determine if the tag body should be evaluated
     * Called from doStartTag().
     *
     */
    public boolean theBodyShouldBeEvaluated()  {
        //
        // TODO: code that determines whether the body should be
        //       evaluated should be placed here.
        //       Called from the doStartTag() method.
        //
        if (blnRsNext){return true;}else{return false;}
    }
    
    //
    // methods called from doEndTag()
    //
    /**
     *
     * Fill in this method to perform other operations from doEndTag().
     *
     */
    public void otherDoEndTagOperations()  {
        
        //
        // TODO: code that performs other operations in doEndTag
        //       should be placed here.
        //       It will be called after initializing variables,
        //       finding the parent, setting IDREFs, etc, and
        //       before calling shouldEvaluateRestOfPageAfterEndTag().
        //
        try{
            if (lblnCloseResultSetAtEnd){
                //System.out.println("Closing the ResultSet");
                rsTag.close();
            } //else{
              // System.out.println("NOT Closing the ResultSet");
            //}
        }catch (Exception e){}
    }
    
    /**
     *
     * Fill in this method to determine if the rest of the JSP page
     * should be generated after this tag is finished.
     * Called from doEndTag().
     *
     */
    public boolean shouldEvaluateRestOfPageAfterEndTag()  {
        //
        // TODO: code that determines whether the rest of the page
        //       should be evaluated after the tag is processed
        //       should be placed here.
        //       Called from the doEndTag() method.
        //
        return true;
    }
    
    
    ////////////////////////////////////////////////////////////////
    ///                                                          ///
    ///   Tag Handler interface methods.                         ///
    ///                                                          ///
    ///   Do not modify these methods; instead, modify the       ///
    ///   methods that they call.                                ///
    ///                                                          ///
    ////////////////////////////////////////////////////////////////
    
    
    /** .
     *
     * This method is called when the JSP engine encounters the start tag,
     * after the attributes are processed.
     * Scripting variables (if any) have their values set here.
     * @return EVAL_BODY_INCLUDE if the JSP engine should evaluate the tag body, otherwise return SKIP_BODY.
     * This method is automatically generated. Do not modify this method.
     * Instead, modify the methods that this method calls.
     *
     *
     */
    public int doStartTag() throws JspException, JspException {
        otherDoStartTagOperations();
        
        if (theBodyShouldBeEvaluated()) {
            return EVAL_BODY_BUFFERED;
        } else {
            return SKIP_BODY;
        }
    }
    
    /** .
     *
     *
     * This method is called after the JSP engine finished processing the tag.
     * @return EVAL_PAGE if the JSP engine should continue evaluating the JSP page, otherwise return SKIP_PAGE.
     * This method is automatically generated. Do not modify this method.
     * Instead, modify the methods that this method calls.
     *
     *
     */
    public int doEndTag() throws JspException, JspException {
        otherDoEndTagOperations();
        
        if (shouldEvaluateRestOfPageAfterEndTag()) {
            return EVAL_PAGE;
        } else {
            return SKIP_PAGE;
        }
    }
    public java.sql.ResultSet getResultSet() {
        return rsTag;
    }
    
    public void closeResultSet() throws SQLException {
        rsTag.close();
    }
    
    public void setResultSet(java.sql.ResultSet value) {
        try {
            rsTag = value;
            intRowCount = 0;        // initialise the RowCounter
        } catch(Exception e) {
            System.out.println("Tag:ProcessResult:Attribut ResultSet assignment error:");
            System.out.println("Error details:" + e);
        }
    }
    
    public void setNullValue(boolean val){
        blnNullValue = val;
    }
    
    public void setStartFrom(long value){
        lngStartFrom = value;
    }
    
    public void setProcessCount(long value){
        lngProcessCount = value;
    }

    public void setCloseResultSetAtEnd(boolean value){
        lblnCloseResultSetAtEnd = value;
    }
    
    public boolean getNullValue(){
        return blnNullValue;
    }
    
    /** .
     * Fill in this method to process the body content of the tag.
     * You only need to do this if the tag's BodyContent property
     * is set to "JSP" or "tagdependent."
     * If the tag's bodyContent is set to "empty," then this method
     * will not be called.
     *
     *
     */
    public void writeTagBodyContent(JspWriter out, BodyContent bodyContent) throws IOException {
        //
        // TODO: insert code to write html before writing the body content.
        //       e.g.  out.println("<B>" + getAttribute1() + "</B>");
        //             out.println("   <BLOCKQUOTE>");
        
        //
        // write the body content (after processing by the JSP engine) on the output Writer
        //
        
        bodyContent.writeOut(out);
        
        //
        // Or else get the body content as a string and process it, e.g.:
        //     String bodyStr = bodyContent.getString();
        //     String result = yourProcessingMethod(bodyStr);
        //     out.println(result);
        //
        
        // TODO: insert code to write html after writing the body content.
        //       e.g.  out.println("   <BLOCKQUOTE>");
        
        // clear the body content for the next time through.
        bodyContent.clearBody();
    }
    
    /** .
     *
     * Handles exception from processing the body content.
     *
     *
     */
    public void handleBodyContentException(Exception ex) throws JspException {
        // Since the doAfterBody method is guarded, place exception handing code here.
        throw new JspException("error in processResult: " + ex);
    }
    
    /** .
     *
     *
     * This method is called after the JSP engine processes the body content of the tag.
     * @return EVAL_BODY_AGAIN if the JSP engine should evaluate the tag body again, otherwise return SKIP_BODY.
     * This method is automatically generated. Do not modify this method.
     * Instead, modify the methods that this method calls.
     *
     *
     */
    public int doAfterBody() throws JspException {
        try {
            //
            // This code is generated for tags whose bodyContent is "JSP"
            //
            BodyContent bodyContent = getBodyContent();
            JspWriter out = bodyContent.getEnclosingWriter();
            
            writeTagBodyContent(out, bodyContent);
        } catch (Exception ex) {
            handleBodyContentException(ex);
        }
        
        if (theBodyShouldBeEvaluatedAgain()) {
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }
    }
    
    /**
     * Fill in this method to determine if the tag body should be evaluated
     * again after evaluating the body.
     * Use this method to create an iterating tag.
     * Called from doAfterBody().
     *
     *
     */
    public boolean theBodyShouldBeEvaluatedAgain() {
        //
        // TODO: code that determines whether the tag body should be
        //       evaluated again after processing the tag
        //       should be placed here.
        //       You can use this method to create iterating tags.
        //       Called from the doAfterBody() method.
        //
        boolean blnResult = true;
        
        if (intRowCount == lngProcessCount) {
            blnResult = false;
        }else{
            try {
                String lstrFieldValue = "";
                if(rsTag.next()){
                    rsmd=rsTag.getMetaData();
                    colcount=rsmd.getColumnCount();
                    intRowCount = intRowCount + 1;
                    pageContext.setAttribute("SLNUM", ""+intRowCount);
                    for(int i=1;i<=colcount;i++){
                        //System.out.println(rsmd.getColumnName(i).toUpperCase()+":"+rs.getString(i));
                        lstrFieldValue = rsTag.getString(i);
                        //System.out.println("First:"+lstrFieldValue);
                        if (!blnNullValue){
                            try{
                                if(lstrFieldValue.equals("null")){
                                    lstrFieldValue = "";
                                    //System.out.println(lstrFieldValue);
                                }else{
                                    //System.out.println(lstrFieldValue);
                                }
                            }catch(Exception e){lstrFieldValue = "";}
                        }
                        pageContext.setAttribute(rsmd.getColumnName(i).toUpperCase(),lstrFieldValue);
                        //pageContext.setAttribute(rsmd.getColumnName(i).toUpperCase(),""+rsTag.getString(i));
                    }
                    blnResult = true;
                }else{
                    blnResult = false;
                }
            } catch(Exception ioe) {
                System.out.println("Error in Processing Result set: " + ioe);
                blnResult = false;
            }
        }
        return blnResult;
    }
}