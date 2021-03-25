package Util;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 1396582
 */
public class FilterAll implements Filter {

    private boolean loggedIn;
    private String userType;
    static ArrayList<String> visitor = null;
    static ArrayList<String> admin = null;
    static ArrayList<String> trainee = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void checkSession(ServletRequest req) {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession ses = request.getSession(true);
        visitor = new ArrayList();
        visitor.add("/index.jsp");
        visitor.add("/Error.jsp");
        try {
            if (ses != null && ses.getAttribute("role") != null) {
                userType = ses.getAttribute("role").toString();
                loggedIn = true;
                if (userType.equals("1")) {
                    admin = new ArrayList();
                    admin.add("/AdminHomepage.jsp");
                } else if (userType.equals("2")) {
                    trainee = new ArrayList();
                    trainee.add("/TraineePage.jsp");
                }
            }
        } catch (Exception e) {
            loggedIn = false;
            e.printStackTrace();
        }

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String file = req.getServletPath();
        String baseURL = req.getContextPath();
        checkSession(request);
        if (file.endsWith(".jsp")) {
            if (visitor.contains(file)) {

            } else {
                if (loggedIn) {
                    if (userType.equalsIgnoreCase("1") && admin.contains(file)) {

                    } else if (userType.equalsIgnoreCase("2") && trainee.contains(file)) {

                    } else {
                        resp.sendRedirect(baseURL + "/Error.jsp");
                    }
                }else{
                    resp.sendRedirect(baseURL + "/Error.jsp");
                }
            }
//            else {
//                 else {
//                    resp.sendRedirect(baseURL + "/index.jsp");
//                }
//            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
