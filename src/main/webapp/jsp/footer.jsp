<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 23.02.2020
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  <%--jstl tag--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <%--jstl tag--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="locale"
       value="${not empty locale ? locale : 'en'}"
       scope="session"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.ticket"/>

<footer id="footer" class="page-footer font-small bg-success darken-3">


    <div class="footer-copyright text-center py-3">
        <fmt:message key="event.footer"/>
    </div>


</footer>