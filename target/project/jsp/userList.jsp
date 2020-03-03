<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <%--jstl tag--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<<%@ taglib prefix="ctg" uri="customtags" %>

<c:set var="locale"
       value="${not empty locale ? locale : 'en'}"
       scope="session"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.ticket"/>

<html>
<head>
    <title><fmt:message key="users.users"/></title>

    <!-- SCRIPTS -->
    <!-- JQuery -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/mdb.min.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/settings.js"></script>


    <script src="${pageContext.request.contextPath}/css/registration.css"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

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

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
<jsp:include page="header.jsp"/>

<div id="page-container">
    <div id="content-wrap">

        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="registration.login"/></th>
                <th scope="col"><fmt:message key="user.role"/></th>
                <th scope="col"></th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>

            <c:if test="${userList.size() > 0}">
                <c:forEach begin="${startIndexOfUserList}" end="${startIndexOfUserList + usersPerPage - 1}" var="user"
                           items="${userList}">
                    <tr>
                        <td>${user.login}</td>

                        <td>
                            <c:choose>
                                <c:when test="${not greaterThanOneAdmin and user.userRole.name() == 'ADMINISTRATOR'}">
                                    <label>
                                        <select class="custom-select" name="userRole" onchange="this.form.submit()"
                                                required
                                                disabled>
                                            <option ${user.userRole.name()=="USER"?"selected":""} value="USER">
                                                <fmt:message
                                                        key="user.role.user"/>
                                            </option>
                                            <!-- value отправляется на сервер-->
                                            <option ${user.userRole.name()=="ADMINISTRATOR"?"selected":""}
                                                    value="ADMINISTRATOR">
                                                <fmt:message
                                                        key="user.role.administrator"/>
                                            </option>
                                        </select>
                                    </label>
                                </c:when>
                                <c:otherwise>
                                    <form method="post" action="change_user_role" class="form-horizontal">
                                        <input type="hidden" name="command" value="change_user_role">
                                        <input type="hidden" name="userId" value="${user.userId}">

                                        <label>
                                            <select class="custom-select" name="userRole" onchange="this.form.submit()"
                                                    required>
                                                <option ${user.userRole.name()=="USER"?"selected":""} value="USER">
                                                    <fmt:message
                                                            key="user.role.user"/>
                                                </option>
                                                <!-- value отправляется на сервер-->
                                                <option ${user.userRole.name()=="ADMINISTRATOR"?"selected":""}
                                                        value="ADMINISTRATOR">
                                                    <fmt:message
                                                            key="user.role.administrator"/>
                                                </option>
                                            </select>
                                        </label>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <td>
                            <form method="get" action="show_user_details">
                                <input type="hidden" name="userId" value="${user.userId}">

                                <button type="submit" class="btn btn-primary center-block"><fmt:message
                                        key="event.details"/></button>
                            </form>
                        </td>
                        <!--Delete -->
                        <td>
                            <c:choose>
                                <c:when test="${not greaterThanOneAdmin and user.userRole.name() == 'ADMINISTRATOR'}">

                                    <button type="submit" class="btn btn-danger center-block" disabled><fmt:message
                                            key="user.delete"/></button>
                                </c:when>
                                <c:otherwise>
                                    <form method="post" action="delete_user">
                                        <input type="hidden" name="command" value="delete_user">
                                        <input type="hidden" name="userId" value="${user.userId}">

                                        <button type="submit" class="btn btn-danger center-block"><fmt:message
                                                key="user.delete"/></button>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>


            </tbody>
        </table>

        <ctg:pagination objectsPerPage="${usersPerPage}"
                        indexOfPage="${indexOfPage}"
                        numberOfObjects="${userListSize}"
                        locale="${locale}"
                        commandValue="user_list"/>

    </div>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>