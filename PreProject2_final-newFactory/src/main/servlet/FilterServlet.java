package src.main.servlet;

import src.main.model.User;
import src.main.service.Service;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
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
        if (act != null) {
            active = (act.toUpperCase().equals("TRUE"));
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        Service service = Service.getInstance();

        String name = httpServletRequest.getParameter("name");
        String password = httpServletRequest.getParameter("password");
        User user = new User(name, password);

        String role = service.getRoleByName(name);

        if (role.equals("Admin")) {
            servletRequest.getRequestDispatcher("admin.jsp").forward(servletRequest, servletResponse);
        } else if(role.equals("User")){
            servletRequest.getRequestDispatcher("user.jsp").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}