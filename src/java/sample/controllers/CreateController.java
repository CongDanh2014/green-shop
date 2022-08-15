/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.controllers;

import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.users.Security;
import sample.users.SendMail;
import sample.users.UserDAO;
import sample.users.UserDTO;
import sample.users.UserError;

/**
 *
 * @author ASUS
 */
public class CreateController extends HttpServlet {

    private static final String ERROR = "register.jsp";
    private static final String SUCCESS = "register.jsp";
    static final Logger LOGGER = Logger.getLogger(AddController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        HttpSession session = request.getSession();
        session.setAttribute("VERIFY_CODE", null);
        try {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            int birthday = Integer.parseInt(request.getParameter("birthday"));
            String email = request.getParameter("email");
            UserError userError = new UserError();
            UserDAO dao = new UserDAO();
            boolean check = true;
            //check userID
            if (userID.length() < 5 || userID.length() > 20) {
                check = false;
                userError.setUserID("1");
            } else if (check) {
                for (int i = 0; i < userID.length(); i++) {
                    if (userID.charAt(i) == ' ') {
                        check = false;
                        break;
                    }
                }
            }

            boolean checkDuplicateID = dao.checkDuplicate(userID);
            if (checkDuplicateID) {
                userError.setUserID("Duplicate User name.");
                check = false;
            }
            if (fullName.length() < 6 || userID.length() > 20) {

                check = false;
            }

            if (password.length() < 5 || userID.length() > 20) {;

                check = false;
            }

            if (!password.equals(confirm)) {
                check = false;
            }

            if (address.length() < 1) {
                check = false;
            }

            if (check) {
                String phoneRegex = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
                if (!Pattern.matches(phoneRegex, phone)) {
                    check = false;
                }
            }

            if (check) {
                String emailRegex = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
                if (!Pattern.matches(emailRegex, email)) {
                    userError.setEnail("This is not a valid email address.");
                    check = false;
                } else {
                    boolean checkDuplicateEmail = dao.checkDuplicateEmail(email);
                    if (checkDuplicateEmail) {
                        check = false;
                        userError.setEnail("Email exist");

                    }

                }
            }

            if (check) {
                UserDTO user = new UserDTO(userID, fullName, password, "US", address, birthday, phone, email, "");
                request.setAttribute("user", user);
                url = SUCCESS;
                String verifyCode = String.valueOf((int) Math.floor(((Math.random() * 899999) + 100000)));
                session.setAttribute("VERIFY_CODE", verifyCode);
                Thread t = new Thread(() -> {
                    SendMail.send(email, "Verification code", "Your verification is: " + verifyCode, Security.USER, Security.PASSWORD);
                    try {
                        Thread.sleep(300000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    session.setAttribute("VERIFY_CODE", null);
                });
                t.start();

            } else {
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
