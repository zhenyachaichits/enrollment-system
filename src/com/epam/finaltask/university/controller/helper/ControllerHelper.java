package com.epam.finaltask.university.controller.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zheny Chaichits on 06.02.16.
 */
public class ControllerHelper {

    private static final String AJAX_HEADER_NAME = "X-Requested-With";
    private static final String AJAX_HEADER_VALUE = "XMLHttpRequest";

    private static final String XML_CONTENT_TYPE = "application/xml";

    public static boolean isRequestAjax(HttpServletRequest request) {
        String headerValue = request.getHeader(AJAX_HEADER_NAME);

        return AJAX_HEADER_VALUE.equals(headerValue);
    }

    public static boolean isResponseXml(HttpServletResponse response) {
        String contentType = response.getContentType();

        return XML_CONTENT_TYPE.equals(contentType);
    }
}
