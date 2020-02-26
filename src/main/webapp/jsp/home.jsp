<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12.02.2020
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%--@elvariable id="locale" type="by"--%>
<c:set var="locale"
       value="${not empty locale ? locale : 'en'}"
       scope="session"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename ="local.ticket"/>

<html>
<head>
    <title>TICKET.BY</title>

    <!-- SCRIPTS -->
    <!-- JQuery -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.8.10/js/mdb.min.js"></script>

    <!-- Bootstrap -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">

    <!-- Material Design Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/mdb.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-social.css" rel="stylesheet">


    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">

</head>
<body>
<jsp:include page="header.jsp"/>

<div id="page-container">
    <div id="content-wrap">
        <div class="overlay"></div>
        <div class="description ">
            <h1><fmt:message key="home.hello"/></h1>
            <p><fmt:message key="home.description"/></p>

            <c:if test="${User == null}">
                <li class="nav-item">
                    <form id="signInFormId" method="post" action="sign_in">
                        <input type="hidden" name="command" value="visit_sign_in">
                        <button type="submit" class="btn btn-success"><fmt:message key="header.sign_in"/> </button>
                    </form>
                </li>
            </c:if>

        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>


/body>
</html>