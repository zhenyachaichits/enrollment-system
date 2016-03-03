package com.epam.finaltask.university.controller.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Ajax identifier.
 */
public class AjaxIdentifier {

    private static final String AJAX_HEADER_NAME = "X-Requested-With";
    private static final String AJAX_HEADER_VALUE = "XMLHttpRequest";

    private static final String XML_CONTENT_TYPE = "application/xml";

    /**
     * Is request ajax boolean.
     *
     * @param request the request
     * @return the boolean
     */
    public static boolean isRequestAjax(HttpServletRequest request) {
        String headerValue = request.getHeader(AJAX_HEADER_NAME);

        return AJAX_HEADER_VALUE.equals(headerValue);
    }

    /**
     * Is response context xml boolean.
     *
     * @param response the response
     * @return the boolean
     */
    public static boolean isResponseContextXml(HttpServletResponse response) {
        String contentType = response.getContentType();
        return contentType != null && contentType.contains(XML_CONTENT_TYPE);
    }
}
