package Servlets;

import Services.MainServices;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

/**
 *
 * @author 1396582
 */
public class UploadServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private final Properties prop = new Properties();
    private String UPLOAD_DIR = "";
    String filename = "";
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter pw = resp.getWriter();
        InputStream in = this.getClass().getResourceAsStream("/Util/Properties.properties");
        prop.load(in);
        UPLOAD_DIR=prop.getProperty("ASSIGNMENT_DIR");
        String fileName = "", aname = "", deadline = "", description = "", issuer = "";
        JSONObject result = new JSONObject();
        HttpSession session = req.getSession();
        int regId = (int) session.getAttribute("regId");
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart) {
            try {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List<FileItem> multipart = upload.parseRequest(req);
                for (FileItem item : multipart) {
                    try {
                        if (!item.isFormField()) {
                            File upload_dir = new File(UPLOAD_DIR);
                            if (!upload_dir.exists()) {
                                File f = new File(UPLOAD_DIR);
                                upload_dir.mkdir();
                            }
                            String name = new File(item.getName()).getName();
                            fileName = (UPLOAD_DIR +"/" + regId + "_" + name).replace(" ", "");
                            String filename = regId+"_"+name;
                            item.write(new File(fileName));
//                            System.out.print(item.getSize());
                            
                        } //                        if (fileName != null || fileName != "") {
                        else if (item.getFieldName().equals("aname")) {
//                            System.out.println(item.getString());
                            aname = item.getString();
                        } else if (item.getFieldName().equals("deadline")) {
//                            System.out.println(item.getString());
                            
                            deadline = item.getString();
                        } else if (item.getFieldName().equals("desc")) {
//                            System.out.println(item.getString());
                            
                            description = item.getString();
                        } else if (item.getFieldName().equals("issuer")) {
//                            System.out.println(item.getString());
                            
                            issuer = item.getString();
                        }
//                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                
                result = new MainServices().uploadAssignment(aname, deadline, description, filename, issuer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        pw.print(result);
    }
    
}
