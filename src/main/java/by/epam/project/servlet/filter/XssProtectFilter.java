package by.epam.project.servlet.filter;

import by.epam.project.command.PathForJsp;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Этот фильтр используется для ззащиты от XSS-атак.
 *
 * @author Shpakova A.
 */

@WebFilter(urlPatterns = {"/*"})
public class XssProtectFilter implements Filter {

    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";
    private static final String JS_SCRIPT_BEGIN_TAG = "<script>";
    private static final String JS_SCRIPT_END_TAG = "</script>";
    public void init(FilterConfig config)  {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String text = request.getParameter(parameterNames.nextElement()).toLowerCase();
            if (text.contains(JS_SCRIPT_BEGIN_TAG) || text.contains(JS_SCRIPT_END_TAG)) {
                request.setAttribute(ERROR, "XSS!!!");
                request.setAttribute(STATUS_CODE, 404);
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher(PathForJsp.ERROR.getUrl());
                dispatcher.forward(request, response);
                return;
            }
        }
        chain.doFilter(request, response);
    }
    public void destroy() {
    }
}
