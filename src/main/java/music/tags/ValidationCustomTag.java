/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/TagHandler.java to edit this template
 */
package music.tags;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.jsp.JspContext;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 *
 * @author jared
 */
public class ValidationCustomTag extends SimpleTagSupport {

    private String entryName;
    private Object entryValue;
    
    public void setEntryName(String entryName){
        this.entryName = entryName;
    }
    
    public void setEntryValue(Object entryValue){
        this.entryValue = entryValue;
    }
    
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        
       // Check if the request is a POST request to display the asterisk
        JspContext jspContext = getJspContext();
        HttpServletRequest request = (HttpServletRequest) ((PageContext) jspContext).getRequest();
        String method = request.getMethod();

        // Display the blue asterisk only if it's a POST request and the field is empty
        if ("POST".equalsIgnoreCase(method) && (entryValue == null || entryValue.toString().trim().isEmpty())) {
            out.print("<span style='color: blue;'>*</span>");
        }
    }
}