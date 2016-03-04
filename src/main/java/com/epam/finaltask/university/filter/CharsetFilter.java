package com.epam.finaltask.university.filter;

import javax.servlet.*;
import java.io.IOException;


/**
 * Charset filter.
 */
public class CharsetFilter implements Filter {

    private static final String CHARSET_PARAMETER = "charset";

    private String charset;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        charset = filterConfig.getInitParameter(CHARSET_PARAMETER);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        servletRequest.setCharacterEncoding(charset);
        servletResponse.setCharacterEncoding(charset);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
