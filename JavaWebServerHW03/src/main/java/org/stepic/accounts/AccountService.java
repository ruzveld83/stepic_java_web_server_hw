package org.stepic.accounts;

import org.stepic.dbService.DBException;
import org.stepic.dbService.DBService;
import org.stepic.dbService.dataSets.UsersDataSet;
import org.stepic.exception.IncorrectPasswordException;
import org.stepic.exception.LoginAlreadyExistsException;
import org.stepic.exception.NoSuchLoginException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AccountService {

    public static AccountService instance = new AccountService();

    private AccountService() {}

    DBService dbService = new DBService();

    public void signUp(String login, String password) throws LoginAlreadyExistsException, DBException {
        Objects.requireNonNull(login);
        Objects.requireNonNull(password);
        if (dbService.getUser(login) != null) {
            throw new LoginAlreadyExistsException("Login '" + login + "' is not available. Try another one.");
        } else {
            dbService.addUser(login, password);
        }
    }

    public void signIn(String sessionId, String login, String password)
            throws NoSuchLoginException, IncorrectPasswordException, DBException {
        Objects.requireNonNull(sessionId);
        Objects.requireNonNull(login);
        Objects.requireNonNull(password);
        if (dbService.getUser(login) == null) {
            throw new NoSuchLoginException("Login '" + login + "' not found");
        } else {
            UsersDataSet user = dbService.getUser(login);
            if (!password.equals(user.getPassword())) {
                throw new IncorrectPasswordException();
            }
        }
    }
}
