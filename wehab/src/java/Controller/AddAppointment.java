/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Sai Vividh
 */
@WebServlet("/AddAppointment")
public class AddAppointment extends HttpServlet {

    private int i;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        try {
            String pname = request.getParameter("pname");
            String gender = request.getParameter("gender");
             String age = request.getParameter("age");
            String bgroup = request.getParameter("bgroup");
            String rov = request.getParameter("rov");
            String dateapp = request.getParameter("dateapp");
            String docname = request.getParameter("docname");
            

            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement pst = con.prepareStatement("insert into appointment values(?,?,?,?,?,?,?,?,?)");
            pst.setString(9, bgroup);
            pst.setString(1, pname);
            pst.setString(2, gender);
            pst.setInt(3, Integer.parseInt(age));
            pst.setString(4, bgroup);
            pst.setString(5, rov);
            pst.setString(6, docname);
            pst.setString(7, dateapp);
            

            i = pst.executeUpdate();
            if (i > 0) {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Appointment created successfully...!');");
                pw.println("window.location.href = \"UserHome.jsp\";");
                pw.println("</script>");
                //RequestDispatcher rd = request.getRequestDispatcher("UserHome.jsp");
                //rd.forward(request, response);
            } else {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Incorrect Data..!');");
                pw.println("window.location.href = \"addpatient.jsp\";");
                pw.println("</script>");
                //RequestDispatcher rd = request.getRequestDispatcher("addpatient.jsp");
                //rd.forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddPatient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddPatient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
