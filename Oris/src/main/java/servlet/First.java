package servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.*;

@WebServlet("/form")
public class First extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Форма обратной связи</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Форма обратной связи</h1>");
        out.println("<form method=\"post\">");
        out.println("<div>");
        out.println("<label>Логин:</label>");
        out.println("<input type=\"text\" name=\"login\" required>");
        out.println("</div>");
        out.println("<div>");
        out.println("<label>Email:</label>");
        out.println("<input type=\"text\" name=\"email\" required>");
        out.println("</div>");
        out.println("<div>");
        out.println("<label>Пароль:</label>");
        out.println("<input type=\"password\" name=\"password\" required>");
        out.println("</div>");
        out.println("<div>");
        out.println("<label>Сообщение:</label>");
        out.println("<textarea name=\"message\" required></textarea>");
        out.println("</div>");
        out.println("<button type=\"submit\">Отправить</button>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String message = request.getParameter("message");

        List<String> errors = new ArrayList<>();

        if (login == null || login.trim().isEmpty()) {
            errors.add("Логин не может быть пустым");
        }

        if (email == null || email.trim().isEmpty()) {
            errors.add("Email не может быть пустым");
        } else if (!email.contains("@")) {
            errors.add("Email должен содержать @");
        }

        if (password == null || password.trim().isEmpty()) {
            errors.add("Пароль не может быть пустым");
        } else if (password.length() < 6) {
            errors.add("Пароль должен быть не менее 6 символов");
        }

        if (message == null || message.trim().isEmpty()) {
            errors.add("Сообщение не может быть пустым");
        }

        if (!errors.isEmpty()) {
            sendErrorPage(response, errors);
            return;
        }

        saveToFile(login, email, password, message);
        response.sendRedirect("thankyou");
    }

    private void sendErrorPage(HttpServletResponse response, List<String> errors) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Ошибка</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Исправьте ошибки:</h1>");
        out.println("<ul>");

        for (String error : errors) {
            out.println("<li>" + error + "</li>");
        }

        out.println("</ul>");
        out.println("<a href=\"javascript:history.back()\">Вернуться назад</a>");
        out.println("</body>");
        out.println("</html>");
    }

    private void saveToFile(String login, String email, String password, String message) throws IOException {
        String data = String.format(
                "Логин: %s, Email: %s, Пароль: %s, Сообщение: %s, Время: %s%n",
                login, email, password, message, new Date()
        );

        try (FileWriter writer = new FileWriter("form_data.txt", true)) {
            writer.write(data);
        }
    }
}