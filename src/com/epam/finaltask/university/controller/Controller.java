package com.epam.finaltask.university.controller;

import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.CommandHelper;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.util.AjaxIdentifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Zheny Chaichits on 21.01.16.
 */
public final class Controller extends HttpServlet {

    private static final String CONTENT_TYPE = "text/html";
    private static final String ERROR_MESSAGE = "ERROR";

    private static final Logger LOG = LogManager.getLogger(Controller.class.getClass());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
        Command command = CommandHelper.getInstance().getCommand(commandName);

        boolean isAjax = AjaxIdentifier.isRequestAjax(request);

        String responseString = getResponseString(request, response, command, isAjax);
        if (isAjax && !AjaxIdentifier.isResponseContextXml(response)) {
            writeToResponse(response, responseString);
        } else {
            forwardPage(request, response, responseString);
        }
    }

    private String getResponseString(HttpServletRequest request, HttpServletResponse response, Command command, boolean isAjax) {
        String responseString;
        try {
            responseString = command.execute(request, response);
        } catch (CommandException e) {
            LOG.error("Couldn't execute command", e);
            if (isAjax) {
                responseString = ERROR_MESSAGE;
            } else {
                responseString = JspPageName.ERROR_PAGE;
            }
        } catch (Exception e) {
            responseString = "";
            //TODO: add exception handler
        }
        return responseString;
    }

    private void forwardPage(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        } else {
            writeToResponse(response, ERROR_MESSAGE);
        }
    }

    private void writeToResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType(CONTENT_TYPE);
        response.getWriter().println(message);
    }
}
