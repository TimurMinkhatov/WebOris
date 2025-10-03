package servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;

@WebServlet("/thankyou")
public class Second extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Спасибо!</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Спасибо за ваше сообщение!</h1>");
        out.println("<p>Данные успешно сохранены.</p>");
        out.println("<a href=\"form\">Вернуться к форме</a>");
        out.println("</body>");
        out.println("</html>");
    }
}