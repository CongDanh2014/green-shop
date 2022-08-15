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
import sample.users.UserDTO;
import sample.utils.GooglePojo;
import sample.utils.GoogleUtils;

/**
 *
 * @author ASUS
 */
public class LoginGoogleController extends HttpServlet {

    private static final String SUCCESS = "greenfoods-master/main.jsp";
    private static final String ERROR = "login.jsp";
    static final Logger LOGGER = Logger.getLogger(AddController.class);
    
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
        doPost(request, response);
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
        String url = ERROR;
        try {
            String code = request.getParameter("code");
            if (code != null && !code.isEmpty()) {
                String accessToken = GoogleUtils.getToken(code);
                GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
                UserDTO user = new UserDTO(googlePojo.getId(),googlePojo.getEmail(),"*****", googlePojo.getRoleID(), "", -1 , "", googlePojo.getEmail(), "true");
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", user);
                url = SUCCESS;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            response.sendRedirect(url);
        }
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
