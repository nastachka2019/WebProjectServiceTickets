<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 02.02.2020
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <%--jstl tag--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%--@elvariable id="locale" type="by"--%>
<c:set var="locale"
       value="${not empty locale ? locale : 'en'}"
       scope="session"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.ticket"/>

<html>
<head>
    <title><fmt:message key="settings.settings"/></title>

    <!-- SCRIPTS -->
    <!-- JQuery -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.8.10/js/mdb.min.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/settings.js"></script>

    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet"
          id="bootstrap-css">
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registration.css">

</head>
<body>

<jsp:include page="header.jsp"/>

<div id="page-container">
    <div id="content-wrap">
        <div class="container">
            <div style="padding-top:20px"></div>

            <form action="settings" method="post" class="form-horizontal" role="form">
                <input type="hidden" name="command" value="settings">

                <h2><fmt:message key="header.profile.settings"/></h2>
                <div class="form-group">
                    <label for="name" class="col-sm-7 control-label">
                        <fmt:message key="registration.name"/>
                    </label>
                    <div class="col-sm-11">
                        <input type="text" name="name" value="${User.name}" maxlength="50"
                               id="name"
                               placeholder="<fmt:message key="registration.name"/> "
                               class="form-control" required autofocus>
                        <span style="color:red;">${errorName}</span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="surname" class="col-sm-7 control-label"><fmt:message
                            key="registration.surname"/></label>
                    <div class="col-sm-11">
                        <input type="text" name="surname" value="${User.surname}" maxlength="50" id="surname"
                               placeholder="<fmt:message key="registration.surname"/> " required
                               class="form-control">
                        <span style="color:red;">${errorSurname}</span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="email" class="col-sm-7 control-label"><fmt:message
                            key="registration.email"/> </label>
                    <div class="col-sm-11">
                        <input type="email" name="email" value="${User.email}"
                               maxlength="50"
                               id="email"
                               placeholder="<fmt:message key="registration.email"/>" class="form-control"
                               required>
                        <span style="color:red;">${errorEmail}</span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="login" class="col-sm-7 control-label"><fmt:message
                            key="registration.login"/></label>
                    <div class="col-sm-11">
                        <input type="text" name="login" value="${User.login}"
                               pattern="^[\w][.\w]{2,48}[\w]$" minlength="4" maxlength="50"
                               id="login"
                               placeholder="<fmt:message key="registration.login"/>"
                               class="form-control" required>
                        <small class="form-text text-muted">
                            <fmt:message key="registration.login.title"/>
                        </small>
                        <span style="color:red;">${errorLogin}</span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="dateOfBirth" class="col-sm-7 control-label"><fmt:message
                            key="registration.date_of_birth"/> </label>
                    <div class="col-sm-11">
                        <input type="date" name="dateOfBirth" value="${User.dateOfBirth}" maxlength="20"
                               id="dateOfBirth"
                               class="form-control">
                    </div>
                </div>


                <div class="form-group">
                    <label for="phone" class="col-sm-7 control-label"><fmt:message
                            key="registration.phone"/></label>
                    <div class="col-sm-11">
                        <input type="text" name="phone" value="${User.phone}"
                               pattern="[0-9]{12}" minlength="12" maxlength="12"
                               id="phone"
                               placeholder="<fmt:message key="registration.phone"/>"
                               class="form-control" required>
                        <small class="form-text text-muted">
                            <fmt:message key="registration.phone.title"/>
                        </small>
                        <span style="color:red;">${errorPhone}</span>
                    </div>
                </div>



                <div class="form-group">
                    <label for="password" class="col-sm-7 control-label"><fmt:message
                            key="registration.password"/></label>
                    <div class="col-sm-11">
                        <input type="password" name="currentPassword"
                               pattern="(?=.*[\d])(?=.*[a-z])(?=.*[A-Z]).{7,16}" minlength="7" maxlength="16"
                               id="password"
                               placeholder="<fmt:message key="settings.current_password"/>"
                               class="form-control">
                        <small class="form-text text-muted">
                            <fmt:message key="registration.password.title"/>
                        </small>
                        <span style="color:red;">${errorPassword}</span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="new_password" class="col-sm-7 control-label"><fmt:message
                            key="settings.new_password"/></label>
                    <div class="col-sm-11">
                        <input type="password" name="newPassword"
                               pattern="(?=.*[\d])(?=.*[a-z])(?=.*[A-Z]).{7,16}" minlength="7" maxlength="16"
                               id="new_password"
                               placeholder="<fmt:message key="settings.new_password"/>" class="form-control">
                        <span style="color:red;">${errorPassword}</span>
                        <span style="color:red;">${errorPassAndConfirmedPassMessage}</span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="confirm_new_password" class="col-sm-7 control-label"><fmt:message
                            key="settings.confirm_new_password"/></label>
                    <div class="col-sm-11">
                        <input type="password" name="confirmedNewPassword"
                               pattern="(?=.*[\d])(?=.*[a-z])(?=.*[A-Z]).{7,16}" minlength="7" maxlength="16"
                               id="confirm_new_password"
                               placeholder="<fmt:message key="settings.confirm_new_password"/>"
                               class="form-control">
                        <span style="color:red;">${errorPassword}</span>
                        <span style="color:red;">${errorPassAndConfirmedPassMessage}</span>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-12">
                        <br>
                        <button class="btn btn-lg btn-success" type="submit"><i
                                class="glyphicon glyphicon-ok-sign"></i>
                            <fmt:message key="settings.button.save"/>
                        </button>
                        <span style="color:red;">${errorData}</span>
                        <span style="color:green;">${ok}</span>
                    </div>
                </div>
            </form>

            <div style="padding-bottom:20px"></div>

        </div>
    </div>

    <jsp:include page="footer.jsp"/>
</div>


</body>
</html>