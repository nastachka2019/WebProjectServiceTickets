<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <%--jstl tag--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="locale"
       value="${not empty locale ? locale : 'en'}"
       scope="session"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.ticket"/>

<html>
<head>
    <title>${user.name} ${user.surname}</title>

    <!-- SCRIPTS -->
    <!-- JQuery -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.8.10/js/mdb.min.js"></script>

    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700" rel="stylesheet">

    <!-- Material Design Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/mdb.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-social.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/event.css">
</head>
<body>

<jsp:include page="header.jsp"/>

<div id="page-container">
    <div id="content-wrap">
        <div class="container">
            <div class="card">
                <div class="container-fliud">
                    <div class="wrapper row">

                        <div class="details col-md-6" style="padding-left: 80px;">
                            <h3 class="event-title">${user.name} ${user.surname}</h3>

                            <h5 class="sizes"><fmt:message key="registration.email"/>
                                <span class="size" data-toggle="tooltip" title="small">${user.email}</span>
                            </h5>
                            <h5 class="sizes"><fmt:message key="registration.login"/>
                                <span class="size" data-toggle="tooltip" title="small">${user.login}</span>
                            </h5>
                            <h5 class="sizes"><fmt:message key="registration.date_of_birth"/>
                                <span class="size" data-toggle="tooltip" title="small">${user.dateOfBirth}</span>
                            </h5>
                            <h5 class="sizes"><fmt:message key="registration.phone"/>
                                <span class="size" data-toggle="tooltip" title="small">${user.phone}</span>
                            </h5>



                            <div class="form-group">
                                <div class="col-xs-12">

                                    <c:choose>
                                        <c:when test="${not greaterThanOneAdmin and user.userRole.name() == 'ADMINISTRATOR'}">
                                            <label>
                                                <select class="custom-select" name="userRole"
                                                        onchange="this.form.submit()"
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
                                            <label>
                                                <form method="post" action="change_user_role" class="form-horizontal">
                                                    <input type="hidden" name="command" value="change_user_role">
                                                    <input type="hidden" name="userId" value="${user.userId}">

                                                    <label>
                                                        <select class="custom-select" name="userRole"
                                                                onchange="this.form.submit()" required>
                                                            <option ${user.userRole.name()=="USER"?"selected":""}
                                                                    value="USER">
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

                                                    <c:if test="${ok != null}">
                                                        <p style="color:green;">${ok}</p>
                                                    </c:if>
                                                </form>
                                            </label>

                                        </c:otherwise>
                                    </c:choose>

                                    <c:choose>
                                        <c:when test="${not greaterThanOneAdmin and user.userRole.name() == 'ADMINISTRATOR'}">

                                            <button type="submit" class="btn btn-danger center-block" disabled>
                                                <fmt:message
                                                        key="user.delete"/></button>
                                        </c:when>
                                        <c:otherwise>
                                            <label>
                                                <form method="post" action="delete_user">
                                                    <input type="hidden" name="command" value="delete_user">
                                                    <input type="hidden" name="userId" value="${user.userId}">

                                                    <button type="submit" class="btn btn-danger center-block">
                                                        <fmt:message
                                                                key="user.delete"/></button>
                                                </form>
                                            </label>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>

</div>
</body>
</html>