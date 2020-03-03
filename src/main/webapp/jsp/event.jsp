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
    <title>${activity.name}</title>

    <!-- SCRIPTS -->
    <!-- JQuery -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/mdb.min.js"></script>

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

<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/event.css">--%>


</head>
<body>

<jsp:include page="header.jsp"/>

<%--<c:if test="${ok != null}">--%>
<%--    <div class="alert alert-success block1" role="alert">--%>
<%--            ${ok}--%>
<%--    </div>--%>
<%--</c:if>--%>

<div id="page-container">
    <div id="content-wrap">
        <div class="container">
            <div class="card">
                <div class="container-fliud">
                    <div class="wrapper row">
                        <div class="preview col-md-4">

<%--                            <div class="preview-pic tab-content">--%>
<%--                                <div class="tab-pane active" id="pic-1"><img--%>
<%--                                        src="${pageContext.request.contextPath}/images/events/${activity.imageURL}"--%>
<%--                                        width="200"--%>
<%--                                        height="250"/></div>--%>
<%--                            </div>--%>

                        </div>
                        <div class="details col-md-6" style="padding-left: 80px;">
                            <h3 class="event-title">${activity.name}</h3>

                            <p class="event-description">${event.description}</p>
                            <h5 class="sizes"><fmt:message key="event.name"/>
                                <span class="size" data-toggle="tooltip" title="small">${event.name}</span>
                            </h5>
                            <h5 class="sizes"><fmt:message key="event.address"/>
                                <span class="size" data-toggle="tooltip" title="small">${event.address}</span>
                            </h5>
                            <h5 class="sizes"><fmt:message key="event.date"/>
                                <span class="size" data-toggle="tooltip" title="small">${event.date}</span>
                            </h5>
                            <h5 class="sizes"><fmt:message key="event.price"/>
                                <span class="size" data-toggle="tooltip" title="small">${event.price}</span>
                            </h5>


                            <c:choose>
                                <c:when test="${User == null}">
                                    <p style="color: red"><fmt:message key="event.disabled.add"/></p>
                                </c:when>

                                <c:otherwise>
                                    <form method="post" action="add_ticket" class="form-horizontal">
                                        <input type="hidden" name="command" value="add_meal">
                                        <input type="hidden" name="eventId" value="${event.eventId}">

                                        <label>
                                            <input type="date" name="ticketDate" value="${ticketDate}" maxlength="20"
                                                   id="dateOfBirth"
                                                   class="form-control" required>
                                        </label>

                                        <label>
                                            <select class="custom-select" name="eventType" required>
                                                <option ${eventType=="concert"?"selected":""} value="concert">
                                                    <fmt:message
                                                            key="event.concert"/>
                                                </option>
                                                <!-- value отправляется на сервер-->
                                                <option ${eventType=="ballet"?"selected":""} value="ballet"><fmt:message
                                                        key="event_type.ballet"/>
                                                </option>
                                                <option ${eventType=="sport"?"selected":""} value="sport"><fmt:message
                                                        key="event_type.sport"/>
                                                </option>
                                            </select>
                                        </label>

                                        <!--Quantity -->
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"
                                                      id="inputGroup-sizing-default" style="width: 100px;">
                                                    <fmt:message key="event.quantity"/>
                                                </span>
                                            </div>
                                            <input type="number" min="1" max="999"
                                                   name="quantity" value="${quantity}"

                                                   class="form-control" aria-label="Sizing example input"
                                                   aria-describedby="inputGroup-sizing-default">
                                            <span style="color:red;">${errorQuantity}</span>
                                        </div>


                                        <div class="action">
                                            <button class="add-to-cart btn btn-default" type="submit"><fmt:message
                                                    key="event.add"/></button>
                                        </div>

                                    </form>

                                </c:otherwise>
                            </c:choose>

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
