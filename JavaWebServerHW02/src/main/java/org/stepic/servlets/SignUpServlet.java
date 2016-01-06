package org.stepic.servlets;

import org.stepic.accounts.AccountService;
import org.stepic.exception.LoginAlreadyExistsException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet  extends HttpServlet {

    AccountService accService = AccountService.instance;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            accService.signUp(login, password);
        } catch (LoginAlreadyExistsException e) {
            throw new IOException(e);
        }
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
