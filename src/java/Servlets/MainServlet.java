package Servlets;

import Services.MainServices;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author 1396582
 */
public class MainServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            session.setAttribute("regId", null);
            session.setAttribute("user", null);
            session.setAttribute("role", null);
            session.invalidate();
            resp.sendRedirect("index.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter pw = resp.getWriter();
        String action = req.getParameter("action");
        if (action.equalsIgnoreCase("register")) {
            try {
                String username = req.getParameter("username");
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                JSONObject response = new MainServices().register(username, email, password);
                pw.print(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("login")) {
            try {
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                JSONObject login = new MainServices().login(username, password);
                HttpSession session = req.getSession();
                session.setAttribute("regId", login.get("regId"));
                session.setAttribute("user", login.get("user"));
                session.setAttribute("role", login.get("role"));
//                System.out.print(login.get("regId"));
                pw.print(login);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("Logout")) {
            try {
                HttpSession session = req.getSession();
                session.setAttribute("regId", null);
                session.invalidate();
                pw.print("No Session");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("getAssignments")){
            try{
                int start  = Integer.parseInt(req.getParameter("start"));
                int length = Integer.parseInt(req.getParameter("length"));
                String searchQuery = req.getParameter("search[value]");
                String order = req.getParameter("order[0][dir]");
                JSONObject assignments = new MainServices().getAssignments(start, length, searchQuery, order);
                pw.print(assignments);
            }catch(Exception e){
                e.printStackTrace();
            }
        } else if(action.equalsIgnoreCase("traineeAssg")){
            JSONArray assignments=new MainServices().traineeAssignments();
            pw.print(assignments);
        } else if(action.equalsIgnoreCase("getSubmissions")){
            int assgId = Integer.parseInt(req.getParameter("id"));
            System.out.println(assgId);
//            JSONArray submissions = new MainServices().getSubmissions(assgId);
//            pw.print(submissions);
            pw.print(new MainServices().getSubmissions(assgId));
        }
    }
    
}
