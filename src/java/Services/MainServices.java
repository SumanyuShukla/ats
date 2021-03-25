/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Model.Assignment;
import Model.Registration;
import Model.Submission;
import Util.HibernateUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author 1396582
 */
public class MainServices {

    public JSONObject register(String username, String email, String password) {
        JSONObject regObj = new JSONObject();
        Session regSession = HibernateUtil.getSessionFactory().openSession();
        Registration reg = new Registration();
        try {
            Transaction regTransaction = regSession.beginTransaction();
            reg.setUsername(username);
            reg.setEmail(email);
            reg.setPassword(password);
            reg.setUsertype("2");
            regSession.save(reg);
            regTransaction.commit();
            regObj.put("response", "Success");
        } catch (Exception e) {
            try {
                regObj.put("response", "Error");
            } catch (JSONException ex) {
                Logger.getLogger(MainServices.class.getName()).log(Level.SEVERE, null, ex);
            }
            e.printStackTrace();
        } finally {
            if (regSession.isOpen()) {
                regSession.close();
            }
        }
        return regObj;
    }

    public JSONObject login(String username, String password) {
        JSONObject loginObj = new JSONObject();
        Session loginSession = HibernateUtil.getSessionFactory().openSession();
        Registration reg = new Registration();
        try {
            Criteria crit = loginSession.createCriteria(Registration.class);
            crit.add(Restrictions.eq("username", username));
            List<Registration> list = crit.list();
            if (list != null && list.size() > 0) {
                reg = list.get(0);
                if (reg != null) {
                    String pwd = reg.getPassword();
                    if (pwd.equals(password)) {
                        String role = reg.getUsertype();
                        int regId = reg.getRegId();
                        loginObj.put("role", role);
                        loginObj.put("regId", regId);
                        loginObj.put("user", username);
                    } else {
                        loginObj.put("password", "null");
                    }
                } else {
                    loginObj.put("user", "unregistered");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (loginSession.isOpen()) {
                loginSession.close();
            }
        }
        return loginObj;
    }

    public JSONObject uploadAssignment(String aname, String deadline, String description, String fileName, String issuer) throws ParseException {
        JSONObject result = new JSONObject();
        Session uploadSession = HibernateUtil.getSessionFactory().openSession();
        Assignment assg = new Assignment();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(deadline);
        try {
            Transaction uploadTranc = uploadSession.beginTransaction();
            assg.setAname(aname);
            assg.setDeadline(date);
            assg.setDescription(description);
            assg.setFilepath(fileName);
            assg.setIssuer(issuer);
            uploadSession.save(assg);
            uploadTranc.commit();
            result.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                result.put("status", "error");
            } catch (JSONException ex) {
                Logger.getLogger(MainServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            if (uploadSession.isOpen()) {
                uploadSession.close();
            }
        }
        return result;
    }

    public JSONObject getAssignments(int start, int length, String searchQuery, String order) {
        JSONArray assignments = new JSONArray();
        JSONObject data = new JSONObject();
        Session assgSession = HibernateUtil.getSessionFactory().openSession();
        Assignment assg = new Assignment();
        try {
            Criteria crit = assgSession.createCriteria(Assignment.class);
            if (order.equalsIgnoreCase("asc") || order.equalsIgnoreCase("desc")) {
                if (order.equalsIgnoreCase("asc")) {
                    crit.addOrder(Order.desc("id"));
                }
                if (order.equalsIgnoreCase("desc")) {
                    crit.addOrder(Order.asc("id"));
                }
            }
            if (searchQuery != null && !searchQuery.trim().isEmpty() && !searchQuery.trim().equalsIgnoreCase("")) {
                Disjunction disc = Restrictions.disjunction(); // A or B or C
                disc.add(Restrictions.like("id", searchQuery, MatchMode.ANYWHERE));
                disc.add(Restrictions.like("aname", searchQuery, MatchMode.ANYWHERE));
                disc.add(Restrictions.like("description", searchQuery, MatchMode.ANYWHERE));
                disc.add(Restrictions.like("deadline", searchQuery, MatchMode.ANYWHERE));
                disc.add(Restrictions.like("issuer", searchQuery, MatchMode.ANYWHERE));
                crit.add(disc);
            }
            List<Assignment> getAssg = crit.list();
            if (!getAssg.isEmpty()) {
                for (int i = 0; i < getAssg.size(); i++) {
                    JSONObject assignment = new JSONObject();
                    assignment.put("id", getAssg.get(i).getAssgId());
                    assignment.put("aname", getAssg.get(i).getAname());
                    assignment.put("description", getAssg.get(i).getDescription());
                    assignment.put("fileName", "<a href=DownloadServlet?file=" + getAssg.get(i).getFilepath() + ">Download Assignment</a>");
                    assignment.put("deadline", getAssg.get(i).getDeadline());
                    assignment.put("issuer", getAssg.get(i).getIssuer());
                    assignments.put(assignment);
                }
            }
            data.put("recordsFiltered", getAssg.size());
            data.put("recordsTotal", getAssg.size());
            data.put("data", assignments);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (assgSession.isOpen()) {
                assgSession.close();
            }
        }
        return data;
    }

    public JSONArray traineeAssignments() {
        JSONArray assignments = new JSONArray();
        Session assgSession = HibernateUtil.getSessionFactory().openSession();
//        Assignment assg = new Assignment();
        try {
            Criteria crit = assgSession.createCriteria(Assignment.class);
            List<Assignment> list = crit.list();
            if (list.size() > 0 && list != null) {
                for (int i = 0; i < list.size(); i++) {
                    JSONObject assignment = new JSONObject();
                    assignment.put("id", list.get(i).getAssgId());
                    assignment.put("name", "<a href=DownloadServlet?file=" + list.get(i).getFilepath() + ">" + list.get(i).getAname() + "</a>");
                    assignment.put("description", list.get(i).getDescription());
                    assignment.put("deadline", list.get(i).getDeadline());
                    assignment.put("issuer", list.get(i).getIssuer());
                    assignments.put(assignment);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (assgSession.isOpen()) {
                assgSession.close();
            }
        }
        return assignments;

    }

//    public int getAssgIdfromAssgname(String aname) {
//        Session getSession = HibernateUtil.getSessionFactory().openSession();
////        Assignment assg = new Assignment();
//        int assgid = 0;
//        try {
////            Criteria crit = getSession.createCriteria(Assignment.class);
////            crit.add(Restrictions.ilike("aname", aname));
//
//            String hql = "select c.assgId from Assignment c where c.aname=:aname";
//            Query query = getSession.createQuery(hql);
//            query.setParameter("aname", aname);
//            List<Integer> list = query.list();
////            List<Assignment> list = crit.list();
//            System.out.println(list);
//            if (list.size() > 0 && list != null) {
//                assgid = list.get(0);
////                System.out.println(assgid);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (getSession.isOpen()) {
//                getSession.close();
//            }
//        }
//        return assgid;
//    }
    public JSONObject submitAssignment(String filename, int regId, int assg_Id) throws JSONException {
        JSONObject result = new JSONObject();
        Session submitSession = HibernateUtil.getSessionFactory().openSession();
        Submission sub = new Submission();
        try {
            Transaction submitTransaction = submitSession.beginTransaction();
            sub.setRegId(new Registration(regId));
//            int assgid = getAssgIdfromAssgname(aname);
            sub.setAssgId(new Assignment(assg_Id));
            sub.setFilepath(filename);
            submitSession.save(sub);
            submitTransaction.commit();
            result.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", "error");
        } finally {
            if (submitSession.isOpen()) {
                submitSession.close();
            }
        }
        return result;
    }

    public JSONArray getSubmissions(int assgId) {
        JSONArray submission = new JSONArray();
        Session submissionSession = HibernateUtil.getSessionFactory().openSession();
//        Submission sub = new Submission();
        try {
            Criteria crit = submissionSession.createCriteria(Submission.class);
            crit.add(Restrictions.eq("assgId", new Assignment(assgId)));
//            System.out.print(list.size());
            List<Submission> list = crit.list();
            System.out.print(list.size());
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    JSONObject subs = new JSONObject();
                    subs.put("subId", list.get(i).getSubId());
                    subs.put("assgName", list.get(i).getAssgId().getAname());
                    subs.put("username", list.get(i).getRegId().getUsername());
                    subs.put("filePath", "<a href=DownloadServlet?file=" + list.get(i).getFilepath() + ">Download</a>");
                    submission.put(subs);
                }
            } else {
                System.out.print("No Submissions");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (submissionSession.isOpen()) {
                submissionSession.close();
            }
        }
        return submission;
    }

    public Submission getSubmissionObjFromRegIdAndAssgId(int regId, int assgId) {
        Submission subObj = new Submission();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria crit = session.createCriteria(Submission.class);
            crit.add(Restrictions.and(Restrictions.eq("regId", new Registration(regId)), Restrictions.eq("assgId", new Assignment(assgId))));
            List<Submission> list = crit.list();
            if (list != null && list.size() > 0) {
                subObj = list.get(0);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return subObj;
    }

    public boolean checkSubmitAssignment(int regId, int assgId) {
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria crit = session.createCriteria(Submission.class);
            crit.add(Restrictions.and(Restrictions.eq("regId", new Registration(regId)), Restrictions.eq("assgId", new Assignment(assgId))));
            List<Submission> list = crit.list();
            if (list != null && list.size() > 0) {
                result = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return result;
    }

    public JSONObject updateSubmission(String filename, int regId, int assg_Id) {
        JSONObject updateObj = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction updateTransaction = session.beginTransaction();
            Submission subObj = new MainServices().getSubmissionObjFromRegIdAndAssgId(regId, assg_Id);
            subObj.setFilepath(filename);
            session.update(subObj);
            updateTransaction.commit();
            updateObj.put("status", "Successfully Updated");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return updateObj;
    }

    public JSONArray getAssignmentsAsPerSubmission(int regId) {
        JSONArray assg = new JSONArray();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria crit=session.createCriteria(Assignment.class);
            List<Assignment> l=crit.list();
            for(int i=0;i<l.size();i++){
                Criteria crit1=session.createCriteria(Submission.class);
                crit1.add(Restrictions.ne("assgId", l.get(i)));
                crit1.add(Restrictions.ne("regId", new Registration(regId)));
                List<Submission> l1=crit1.list();
                if(l1!=null || l1.size()>0){
                    JSONObject assignment = new JSONObject();
                    assignment.put("id", l1.get(i).getAssgId());
                    assignment.put("name", "<a href=DownloadServlet?file=" + l.get(i).getFilepath() + ">" + l.get(i).getAname() + "</a>");
                    assignment.put("description", l.get(i).getDescription());
                    assignment.put("deadline", l.get(i).getDeadline());
                    assignment.put("issuer", l.get(i).getIssuer());
                    assg.put(assignment);
                }
            }
            System.out.println(assg.length());
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return assg;
    }

    public static void main(String args[]) {
        System.out.println(new MainServices().getAssignmentsAsPerSubmission(3));
    }
}
