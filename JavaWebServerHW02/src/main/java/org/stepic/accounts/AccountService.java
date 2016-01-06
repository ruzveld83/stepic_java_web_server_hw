package org.stepic.accounts;

import org.stepic.exception.IncorrectPasswordException;
import org.stepic.exception.LoginAlreadyExistsException;
import org.stepic.exception.NoSuchLoginException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AccountService {

    public static AccountService instance = new AccountService();

    private AccountService() {}

    private Map<String, UserProfile> signedUp = new HashMap<>();
    private Map<String, UserProfile> signedIn = new HashMap<>();

    public void signUp(String login, String password) throws LoginAlreadyExistsException {
        Objects.requireNonNull(login);
        Objects.requireNonNull(password);
        if (signedUp.containsKey(login)) {
            throw new LoginAlreadyExistsException("Login '" + login + "' is not available. Try another one.");
        } else {
            signedUp.put(login, new UserProfile(login, password));
        }
    }

    public void signIn(String sessionId, String login, String password)
            throws NoSuchLoginException, IncorrectPasswordException {
        Objects.requireNonNull(sessionId);
        Objects.requireNonNull(login);
        Objects.requireNonNull(password);
        if (!signedUp.containsKey(login)) {
            throw new NoSuchLoginException("Login '" + login + "' ");
        } else {
            UserProfile user = signedUp.get(login);
            if (!password.equals(user.getPassword())) {
                throw new IncorrectPasswordException();
            } else {
                signedIn.put(sessionId, user);
            }
        }
    }
}
