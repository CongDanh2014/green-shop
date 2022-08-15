/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.controllers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
@WebServlet(name = "CreateController2", urlPatterns = {"/CreateController2"})
public class CreateController2 extends HttpServlet {

    private static final String ERROR = "register.jsp";
    private static final String SUCCESS = "register.jsp";
    static final Logger LOGGER = Logger.getLogger(AddController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            int birthday = Integer.parseInt(request.getParameter("birthday"));
            String email = request.getParameter("email");
            UserDTO user = new UserDTO(userID.toLowerCase(), fullName, password, "US", address, birthday, phone, email, "true");
            String verify = request.getParameter("verify");
            HttpSession session = request.getSession();
            String verifyCode = (String) session.getAttribute("VERIFY_CODE");
            String gRecaptchaReponse = request.getParameter("g-recaptcha-response");
            boolean verifyRecaptcha = verify(gRecaptchaReponse);
            if (verifyRecaptcha) {
                if (verifyCode.equals(verify)) {
                    UserDAO dao = new UserDAO();
                    boolean check = dao.createUser(user);
                    if (check) {
                        url = SUCCESS;
                        session.setAttribute("VERIFY_CODE", null);
                        request.setAttribute("SUCCESS", "SUCESS");

                    }
                }
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

    private boolean verify(String gRecaptchaResponse) {
        String url = "https://www.google.com/recaptcha/api/siteverify";
        String secret = "6LcwwOYeAAAAAJj_MZWiI-_O0ZEvTWwRk983LJEK";
        String USER_AGENT = "Mozilla/5.0";
        if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
            return false;
        }

        try {
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            // add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String postParams = "secret=" + secret + "&response=" + gRecaptchaResponse;

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            // System.out.println("\nSending 'POST' request to URL : " + url);
            // System.out.println("Post parameters : " + postParams);
            // System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            // System.out.println(response.toString());
            // parse JSON response and return 'success' value
            JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            return jsonObject.getBoolean("success");
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
    }
}
