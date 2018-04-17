<%--
  Created by IntelliJ IDEA.
  User: Jay
  Date: 2018/4/14
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>testVideo</title>
</head>
<body>
    <form method="post" enctype="multipart/form-data" action="/Video/insertOneVideo.do">
        <input type="text" placeholder="title" name="title" value="">
        <input type="file" placeholder="videofile" name="videofile">
        <input type="text" placeholder="user_id_f" name="user_id_f" value="">
        <input type="text" name="video_type" placeholder="video_type">
        <input type="submit" value="insertOneVideo">
    </form>
</body>
</html>
