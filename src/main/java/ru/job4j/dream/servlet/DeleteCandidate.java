package ru.job4j.dream.servlet;

import ru.job4j.dream.Store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class DeleteCandidate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("id");
        for (File file : new File("c:\\images\\").listFiles()) {
            if (file.getName().startsWith(name)) {
                file.delete();
                break;
            }
        }
        PsqlStore.instOf().deleteCandidate(Integer.valueOf(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }
}
