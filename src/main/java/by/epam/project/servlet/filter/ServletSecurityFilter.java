//package by.epam.project.servlet.filter;
//
//
//import by.epam.project.command.Command;
//import by.epam.project.command.CommandMap;
//import by.epam.project.command.CommandType;
//import by.epam.project.entity.User;
//import by.epam.project.entity.UserRole;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.List;
//
///**
// * Этот фильтр используется для запрета пользователям посещать страницы, на которые у них нет прав.
// *
// * @author Shpakova A.
// */
//
//@WebFilter(urlPatterns = {"/*"})
//public class ServletSecurityFilter implements Filter {
//
//    private static final Logger logger = LogManager.getLogger();
//    private static final String USER = "User";
//    private static final String COMMAND = "command";
//    private static final String ERROR = "error";
//    private static final String SIGN_IN_PATH = "/sign_in";
//    private static final String EVENT_LIST_PATH = "/visit_event_list";
//
//    public void destroy() {
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;
//        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute(USER);
//        CommandMap commandMap = CommandMap.getInstance();
//        Command command = commandMap.receiveCommand((String) req.getAttribute(COMMAND));     //какой запрос от юзера
//        List<UserRole> userRoleList = CommandType.valueOf(((String) req.getAttribute(COMMAND))).getUserRoleList();  //в завис-ти от команды выдает список ,кому это разрешено
//        if (user != null) {
//            if (userRoleList.contains(user.getUserRole())) {
//                chain.doFilter(request, response);
//            } else {
//                req.getSession().setAttribute(ERROR, "Sorry! You don't have rights to visit this page.");
//                resp.sendRedirect(req.getContextPath() + EVENT_LIST_PATH);
//            }
//        } else {
//            if (userRoleList.contains(UserRole.ANONYMOUS)) {
//                chain.doFilter(request, response);
//            } else {
//                req.getSession().setAttribute(ERROR, "Sign in for visiting this page");
//                resp.sendRedirect(req.getContextPath() + SIGN_IN_PATH);
//            }
//        }
//        chain.doFilter(request, response); // передать запрос по цепочке фильтров
//    }
//}
//
