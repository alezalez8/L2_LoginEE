package academy.prog;

import jakarta.servlet.http.*;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    static final String LOGIN = "admin";
    static final String PASS = "admin";
    static final String MESSAGE_AGE = "Age must be more than 17 years and doesn't contain non digital symbols";
    static final String MESSAGE_LOGIN = "Login can't be less than 5 symbols";
    static final String MESSAGE_PASSWORD = "Password too short(can't be less than 10 symbols) or too simple";
    static final int AGE = 18;
    int age = 0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");



        //if (LOGIN.equals(login) && PASS.equals(password))
        if (PASS.equals(password)) {
            HttpSession session = request.getSession(true);


            try {
                age = Integer.parseInt(request.getParameter("age"));
            } catch (NumberFormatException e) {
                System.out.println(e);
            }




            if (login.length() < 3) {

                session.setAttribute("my_login", false);
                session.setAttribute("user_login", "");
                session.setAttribute("message_login", MESSAGE_LOGIN);
            } else {
                session.setAttribute("my_login", true);
                session.setAttribute("user_login", login);
            }
           /* if (password.length() < 10) {
                session.setAttribute("message_password", MESSAGE_PASSWORD);
                session.setAttribute("password", false);
            } else {
                session.setAttribute("password", true);
            }*/


            if (age >= AGE) {
                session.setAttribute("age", true);
                session.setAttribute("user_login", login);
            } else {
                session.setAttribute("age", false);
                session.setAttribute("message_age", MESSAGE_AGE);
            }
        }

        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String a = request.getParameter("a");
        HttpSession session = request.getSession(false);

        if ("exit".equals(a) && (session != null))
            session.removeAttribute("user_login");

        response.sendRedirect("index.jsp");
    }
}
