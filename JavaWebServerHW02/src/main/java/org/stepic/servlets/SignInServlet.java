package org.stepic.servlets;

import org.stepic.accounts.AccountService;
import org.stepic.exception.IncorrectPasswordException;
import org.stepic.exception.NoSuchLoginException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {

    AccountService accService = AccountService.instance;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            accService.signIn(req.getSession().getId(), login, password);
            resp.getWriter().println("Authorized");
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (NoSuchLoginException | IncorrectPasswordException e) {
            resp.getWriter().println("Unauthorized");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
