package src.main.servlet;

import src.main.model.User;
import src.main.service.Service;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

//@WebServlet("/filter")
@WebFilter("/filter")
public class FilterServlet implements Filter {
    private FilterConfig filterConfig = null;
    private boolean active = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        String act = filterConfig.getInitParameter("active");
        if(act != null){
            active = (act.toUpperCase().equals("TRUE"));
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Service service = Service.getInstance();
        if (active){
            String name = servletRequest.getParameter("name");
            String password = servletRequest.getParameter("password");
            User user = new User(name, password);
            String role = service.getRoleByUser(user);

            if(role==null){
                System.out.println("role = null!");
            } else if(role.equals("Admin")){
                servletRequest.getRequestDispatcher("admin.jsp").forward(servletRequest, servletResponse);
            } else if(role.equals("User")){
                servletRequest.getRequestDispatcher("user.jsp").forward(servletRequest, servletResponse);
            } else {
                System.out.println("Какая-то фигня!");
            }
        }
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}