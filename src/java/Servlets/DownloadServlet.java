/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 1396582
 */
public class DownloadServlet extends HttpServlet {
     protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
         
        PrintWriter pw = resp.getWriter();
        try {
            String fileName = req.getParameter("file");
            resp.setContentType("APPLICATION/OCTET-STREAM");
            resp.setHeader("Content-Disposition", "attachment;fileName=\"" + fileName + "\"");
            try{
                FileInputStream file = new FileInputStream(fileName);
                int i;
                while((i = file.read()) != -1){
                    pw.write(i);
                }
                pw.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
         
     }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        try {
            String fileName = req.getParameter("file");
            resp.setContentType("APPLICATION/OCTET-STREAM");
            resp.setHeader("Content-Disposition", "attachment;fileName=\"" + fileName + "\"");
            try{
                FileInputStream file = new FileInputStream(fileName);
                int i;
                while((i = file.read()) != -1){
                    pw.write(i);
                }
                pw.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
