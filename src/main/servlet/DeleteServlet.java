package src.main.servlet;

import src.main.model.User;
import src.main.service.UserService;
import src.main.service.UserServiceHebernate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServiceHebernate userServiceHebernate = UserServiceHebernate.getInstance();
        Long id = Long.parseLong(req.getParameter("id"));
        if (id==null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("Не введен пользователь");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            userServiceHebernate.deleteUserById(id);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
