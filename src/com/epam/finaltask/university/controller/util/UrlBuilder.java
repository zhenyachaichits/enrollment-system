package com.epam.finaltask.university.controller.util;

import com.epam.finaltask.university.controller.RequestParameterName;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

/**
 * Created by Zheny Chaichits on 18.02.2016.
 */
public class UrlBuilder {

    private static final String URI_DELIMITER_PATTERN = "\\?";

    private static final String URI_DELIMITER = "?";
    private static final String PARAM_DELIMITER = "&";
    private static final String PARAM_VALUE_DELIMITER = "=";

    public static String build(HttpServletRequest request) {
        String uri = request.getRequestURI();

        uri = uri.split(URI_DELIMITER_PATTERN)[0];
        Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();

        StringBuilder stringBuilder = new StringBuilder();

        if (!entrySet.isEmpty()) {
            stringBuilder.append(uri);
            stringBuilder.append(URI_DELIMITER);
        }

        int count = 1;

        for (Map.Entry<String, String[]> parameter : entrySet) {
            if (!RequestParameterName.CURRENT_PAGE.equals(parameter.getKey())) {
                stringBuilder.append(parameter.getKey());
                stringBuilder.append(PARAM_VALUE_DELIMITER);
                stringBuilder.append(parameter.getValue()[0]);
                if (count < entrySet.size()) {
                    stringBuilder.append(PARAM_DELIMITER);
                }
            }
            count++;
        }

        return stringBuilder.toString();
    }
}
