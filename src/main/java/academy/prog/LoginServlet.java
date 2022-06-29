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
    private boolean isAge = false;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession(true);


        //if (LOGIN.equals(login) && PASS.equals(password))
        //  if (PASS.equals(password)) {

        try {
            age = Integer.parseInt(request.getParameter("age"));
        } catch (NumberFormatException e) {
            System.out.println(e);
        } finally {
            isAge = false;
        }
        //-----------------------------------
        if (age < AGE) {
            session.setAttribute("age", false);
            session.removeAttribute("user_login");
            session.setAttribute("message_age", MESSAGE_AGE);
            isAge = false;
        } else {
            session.setAttribute("age", true);
            isAge = true;
        }
        //-----------------------------------
        if (login.length() < 5) {

            session.setAttribute("my_login", false);
            session.removeAttribute("user_login");
            session.setAttribute("message_login", MESSAGE_LOGIN);

        } else {
            session.setAttribute("my_login", true);
        }

        if (LOGIN.equals(login) && PASS.equals(password) && isAge) {

            session.setAttribute("user_login", login);

        }

        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String a = request.getParameter("a");
        HttpSession session = request.getSession(false);

        if ("exit".equals(a) && (session != null))
            session.removeAttribute("user_login");
        isAge = false;

        response.sendRedirect("index.jsp");
    }
}
