package by.epam.project.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

/**
 * Класс, который «подвал» сайта или раздела
 *
 * @author Shpakova A.
 */
@Deprecated
public class FooterTag extends BodyTagSupport {
    private static Logger logger = LogManager.getLogger();
    private String color = "black";

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write("<footer>");
            pageContext.getOut().write("<p><font color=" + color + ">");
            pageContext.getOut().write("Еhe project was developed by Anastasia Shpakova, Minsk, 2020");
            pageContext.getOut().write("</p></font>");
            pageContext.getOut().write("</footer>");
        } catch (IOException e) {
            logger.error("FooterTag: error.", e);
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return SKIP_BODY;
    }
}