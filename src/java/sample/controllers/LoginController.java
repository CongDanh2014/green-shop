/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.users.UserDAO;
import sample.users.UserDTO;

/**
 *
 * @author ASUS
 */
public class LoginController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String US = "US";
    private static final String USER_PAGE = "greenfoods-master/main.jsp";
    private static final String ADMIN_PAGE = "admin.jsp";
    private static final String AD = "AD";
    static final Logger LOGGER = Logger.getLogger(AddController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");
            UserDAO dao = new UserDAO();
            UserDTO loginUser = dao.getConnection(userID, password);
            HttpSession session = request.getSession();
            if (loginUser != null) {
                session.setAttribute("LOGIN_USER", loginUser);
                String roleID = loginUser.getRoleID();
                if (AD.equals(roleID)) {
                    url = ADMIN_PAGE;
                } else if (US.equals(roleID)) {
                    url = USER_PAGE;
                } else {
                    session.setAttribute("ERROR_MESSAGE", "You role is not support");
                }
            } else {
                session.setAttribute("ERROR_MESSAGE", "Incorrect UserID or Password");
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            response.sendRedirect(url);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
