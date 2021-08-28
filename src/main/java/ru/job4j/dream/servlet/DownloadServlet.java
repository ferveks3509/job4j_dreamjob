package ru.job4j.dream.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File image = null;
        for (File file : new File("/Users/ilya/IdeaProjects/job4j_dreamjob/images").listFiles()) {
            if (file.getName().startsWith(req.getParameter("id"))) {
                image = file;
                break;
            }
        }
        try (FileInputStream stream = new FileInputStream(image)) {
            resp.getOutputStream().write(stream.readAllBytes());
        }
    }
}