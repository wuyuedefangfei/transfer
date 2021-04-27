<%--
  Created by IntelliJ IDEA.
  User: yanxi
  Date: 2020/4/17
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>上传文件</title>
</head>
<body>
<form action="/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="imgs">
    <input type="file" name="imgs">
    <input type="file" name="imgs">
    <input type="file" name="imgs">
    <br>
    <br>
    <input type="submit" value="上传">
</form>
<br>

<c:if test="${filePath != null && filePath != '' && filePath != ' '}">
        <h2>上传${filePath}完成!</h2>
</c:if>

<c:if test="${filePath == null || filePath =='' && filePath ==' '}">
    <h2>上传了个寂寞</h2>
</c:if>

</body>
</html>
