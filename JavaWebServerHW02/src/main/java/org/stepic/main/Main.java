package org.stepic.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.stepic.servlets.SignInServlet;
import org.stepic.servlets.SignUpServlet;

public class Main {

    public static void main(String[] args) throws Exception {
        ServletContextHandler servletHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletHandler.addServlet(new ServletHolder(new SignInServlet()), "/signin");
        servletHandler.addServlet(new ServletHolder(new SignUpServlet()), "/signup");

        Server server = new Server(8080);
        server.setHandler(servletHandler);

        server.start();
        System.out.println("Server started");
        server.join();
    }
}
