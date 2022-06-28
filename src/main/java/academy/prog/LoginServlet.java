package academy.prog;

import jakarta.servlet.http.*;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    static final String LOGIN = "admin";
    static final String PASS = "admin";
    static final String MESSAGE = "Age must be more than 17 years and doesn't contain non digital symbols";
    static final int AGE = 18;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        int age = 0;

        try {
            age = Integer.parseInt(request.getParameter("age"));
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        if (LOGIN.equals(login) && PASS.equals(password)) {
            HttpSession session = request.getSession(true);

            if (age >= AGE) {
                session.setAttribute("age", true);
                session.setAttribute("user_login", login);
            } else {
                session.setAttribute("age", false);
                session.setAttribute("message", MESSAGE);
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
