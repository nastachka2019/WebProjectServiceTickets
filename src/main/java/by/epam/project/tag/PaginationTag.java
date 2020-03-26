package by.epam.project.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class provides navigation links for pagination, that is, links to
 * the first, previous, next and last page of the pagination list
 *
 * @author Shpakova A.
 */

public class PaginationTag extends TagSupport {
    private int objectsPerPage;
    private int indexOfPage;
    private int numberOfObjects;
    private String locale;
    private String commandValue;
    private String nameOrWordInName;
    private String eventName;
    private String eventAddress;
    private int minPrice;
    private int maxPrice;


    @Override
    public int doStartTag() throws JspException {

        Locale localeOfPage = new Locale(locale);

        ResourceBundle rb = ResourceBundle.getBundle("ticket", localeOfPage);

        JspWriter out = pageContext.getOut();

       
        try {
            out.write("<nav aria-label=\"...\">");
            out.write("<ul class=\"pagination justify-content-center\">");

            if (indexOfPage == 1) {
                out.write("<li class=\"page-item disabled\">");
                out.write("<a class=\"page-link\">" + rb.getString("pagination.previous") + "</a>");
                out.write("</li>");
            }

            if (indexOfPage > 1) {
                out.write("<li class=\"page-item\">");
                out.write("<form id=\"previousPageForm\" method=\"get\" action=\"" + commandValue + "\">");
                out.write("<input type=\"hidden\" name=\"indexOfPage\" value=\"" + (indexOfPage - 1) + "\">");

                if (commandValue.equals("search")) {
                    out.write("<input type=\"hidden\" name=\"nameOrWordInName\" value=\"" + nameOrWordInName + "\">");
                } else if (commandValue.equals("event_list")) {
                    out.write("<input type=\"hidden\" name=\"nameOrWordInName\" value=\"" + nameOrWordInName + "\">");
                    out.write("<input type=\"hidden\" name=\"eventName\" value=\"" + eventName + "\">");
                    out.write("<input type=\"hidden\" name=\"eventAddress\" value=\"" + eventAddress + "\">");
                    out.write("<input type=\"hidden\" name=\"minPrice\" value=\"" + minPrice + "\">");
                    out.write("<input type=\"hidden\" name=\"maxPrice\" value=\"" + maxPrice + "\">");

                }

                out.write("<a class=\"page-link\" onclick=\"document.getElementById('previousPageForm').submit();\">" + rb.getString("pagination.previous") + "</a>");
                out.write("</form>");
                out.write("</li>");
            }

            out.write("<li class=\"page-item\"><a class=\"page-link\">" + indexOfPage + "</a></li>");

            if ((indexOfPage - 1) * objectsPerPage + objectsPerPage < numberOfObjects) {
                out.write("<li class=\"page-item\">");
                out.write("<form id=\"nextPageForm\" method=\"get\" action=\"" + commandValue + "\">");
                out.write("<input type=\"hidden\" name=\"indexOfPage\" value=\"" + (indexOfPage + 1) + "\">");

                if (commandValue.equals("search")) {
                    out.write("<input type=\"hidden\" name=\"nameOrWordInName\" value=\"" + nameOrWordInName + "\">");
                } else if (commandValue.equals("event_list")) {
                    out.write("<input type=\"hidden\" name=\"nameOrWordInName\" value=\"" + nameOrWordInName + "\">");
                    out.write("<input type=\"hidden\" name=\"eventName\" value=\"" + eventName+ "\">");
                    out.write("<input type=\"hidden\" name=\"eventAddress\" value=\"" + eventAddress + "\">");
                    out.write("<input type=\"hidden\" name=\"minPrice\" value=\"" + minPrice+ "\">");
                    out.write("<input type=\"hidden\" name=\"maxPrice\" value=\"" + maxPrice + "\">");
                }

                out.write("<a class=\"page-link\" onclick=\"document.getElementById('nextPageForm').submit();\">" + rb.getString("pagination.next") + "</a>");
                out.write("</form>");
                out.write("</li>");
            }

            if ((indexOfPage - 1) * objectsPerPage + objectsPerPage >= numberOfObjects) {
                out.write("<li class=\"page-item disabled\">");
                out.write("<a class=\"page-link\">" + rb.getString("pagination.next") + "</a>");
                out.write("</li>");
            }

            out.write("</ul>");
            out.write("</nav>");

        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }

    public void setObjectsPerPage(int objectsPerPage) {
        this.objectsPerPage = objectsPerPage;
    }

    public void setIndexOfPage(int indexOfPage) {
        this.indexOfPage = indexOfPage;
    }

    public void setNumberOfObjects(int numberOfObjects) {
        this.numberOfObjects = numberOfObjects;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setCommandValue(String commandValue) {
        this.commandValue = commandValue;
    }

    public void setNameOrWordInName(String nameOrWordInName) {
        this.nameOrWordInName = nameOrWordInName;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress=eventAddress;
    }

    public void setEventName(String eventName) {
        this.eventName=eventName;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice=minPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice=maxPrice;
    }
}

