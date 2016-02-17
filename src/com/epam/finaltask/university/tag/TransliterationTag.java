package com.epam.finaltask.university.tag;

import com.epam.finaltask.university.service.util.TextTransliterator;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

/**
 * Created by Zheny Chaichits on 16.02.2016.
 */
public class TransliterationTag extends BodyTagSupport {

    private static final String LOCALE = "locale";
    private static final String RU_LOCALE = "ru";


    @Override
    public int doEndTag() throws JspException {
        HttpSession session = pageContext.getSession();
        if (session != null) {
            setTransliterated(session);
        }
        return EVAL_PAGE;
    }

    private void setTransliterated(HttpSession session) throws JspException {
        try {
            String locale = (String) session.getAttribute(LOCALE);

            String content = getBodyContent().getString();
            getBodyContent().clearBody();

            if (locale != null) {
                if (!locale.contains(RU_LOCALE)) {
                    content = TextTransliterator.translitetare(content);
                }
            }

            pageContext.getOut().write(content);
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

}
