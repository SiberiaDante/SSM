<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <%--<script type="text/javascript"--%>
    <%--src="${APP_PATH }/statics/js/jquery-1.12.4.min.js"></script>--%>
    <%--<link--%>
    <%--href="${APP_PATH }/statics/bootstrap-3.3.7-dist/css/bootstrap.min.css"--%>
    <%--rel="stylesheet">--%>
    <%--<script--%>
    <%--src="${APP_PATH }/statics/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>--%>

    <%--<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"--%>
    <%--integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">--%>
    <%--<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js"--%>
    <%--integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"--%>
    <%--crossorigin="anonymous"></script>--%>
    <%--<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"--%>
    <%--integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"--%>
    <%--crossorigin="anonymous"></script>--%>
    <%--<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"--%>
    <%--integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"--%>
    <%--crossorigin="anonymous"></script>--%>
</head>
<body>
<jsp:forward page="/emps"></jsp:forward>
path:${APP_PATH}<br>
<button class="btn btn-success"> Button</button>
10000
</body>
</html>
