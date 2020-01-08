package src.main.servlet;


import src.main.model.User;
import src.main.service.Service;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = Service.getInstance();

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        if (name == null || password == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("Не введен пароль или логин");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else if(service.addUser(new User(name, password, role))) {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("Какая-то фигня");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}