package Servlets;

import Services.MainServices;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author 1396582
 */
public class SubmissionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final Properties prop = new Properties();
    private String UPLOAD_DIR = "";
    String filename = "";
    String filepath = "";
    String aname = "";
    int assg_Id = 0;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        JSONObject result = new JSONObject();
        InputStream in = this.getClass().getResourceAsStream("/Util/Properties.properties");
        prop.load(in);
        UPLOAD_DIR=prop.getProperty("SUBMISSION_DIR");
        HttpSession ses = req.getSession();
        int regId = (int) ses.getAttribute("regId");
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart) {
            try {
                DiskFileItemFactory disk = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(disk);
                List<FileItem> list = upload.parseRequest(req);
                for (FileItem item : list) {
                    try {
                        if (!item.isFormField()) {
                            File upload_dir = new File(UPLOAD_DIR);
                            if (!upload_dir.exists()) {
                                File f = new File(UPLOAD_DIR);
                                upload_dir.mkdir();
                            }
                            String assgName = aname.replace(" ", "");
                            File nameFolder = new File(UPLOAD_DIR + File.separator + assgName);
                            if (!nameFolder.exists()) {
                                File f = new File(UPLOAD_DIR + File.separator + aname);
                                nameFolder.mkdir();
                            }
                            String name = new File(item.getName()).getName();
                            filename = (UPLOAD_DIR + "/" + aname + "/" + regId + "_" + name).replace(" ", "");
                            filepath=regId+"_"+name;
                            item.write(new File(filename));
//                            System.out.println(filename);
                        } else if (item.getFieldName().equals("assignmentName")) {
//                            System.out.println(item.getString());
                            aname = item.getString();
                        } else if (item.getFieldName().equals("assg_Id")) {
                            assg_Id = Integer.parseInt(item.getString());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            if (new MainServices().checkSubmitAssignment(regId, assg_Id)) {
                result = new MainServices().updateSubmission(filepath, regId, assg_Id);
            } else {
                result = new MainServices().submitAssignment(filepath, regId, assg_Id);
            }
        } catch (JSONException ex) {
            Logger.getLogger(SubmissionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        pw.print(result);
    }

}
