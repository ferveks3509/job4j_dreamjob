package ru.job4j.dream.servlet;

import ru.job4j.dream.Store.PsqlStore;
import ru.job4j.dream.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = PsqlStore.instOf().findByEmail(email);
        if (user != null) {
            req.setAttribute("error", "User существует в системе");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        } else {
            user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            PsqlStore.instOf().save(user);
            HttpSession hs = req.getSession();
            hs.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/post.do");
        }
    }
}
