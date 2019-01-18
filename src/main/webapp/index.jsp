<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>This is Title</title>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <%--引入jquery--%>
    <script type="text/javascript" src="${APP_PATH }/statics/js/jquery-1.12.4.min.js"></script>
    <%--引入bootstrap--%>
    <script type="text/javascript" src="${APP_PATH }/statics/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <link href="${APP_PATH }/statics/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<button class="btn btn-success">This is a Button</button>
</body>
</html>
