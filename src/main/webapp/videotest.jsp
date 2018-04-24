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
    <form method="get" enctype="multipart/form-data" action="/Video/insertOneVideo.do">
        <input type="text" placeholder="title" name="title" value="">
        <input type="file" placeholder="videofile" name="videofile">
        <input type="text" placeholder="user_id_f" name="user_id_f" value="">
        <input type="text" name="video_type" placeholder="video_type">
        <input type="text" placeholder="introduce" name="introduce">
        <input type="text" name="address" value="" placeholder="address">
        <input type="text" name="prize" value="" placeholder="prize">
        <input type="text" name="look_persons" value="" placeholder="look_persons">
        <input type="submit" value="insertOneVideo">
    </form>

    <form method="get" action="/Video/findVideoById.do">
        <input type="text" placeholder="introduce" name="video_id">
        <input type="submit" value="findVideoById">
    </form>

    <form method="get"action="/Video/findVideosByType.do">
        <input type="text" name="video_type" placeholder="video_type">
        <input type="submit" value="findVideosByType">
    </form>

    <form method="get" action="/Video/findVideosByUser_id_f.do">
        <input type="text" placeholder="user_id_f" name="user_id_f" value="">
        <input type="submit" value="findVideosByUser_id_f">
    </form>

    <form method="get" action="/Video/modifyVideoById.do">
        <input type="text" placeholder="video_id" name="video_id">
        <input type="text" placeholder="title" name="title" value="">
        <input type="text" placeholder="introduce" name="introduce">
        <input type="text" name="address" value="" placeholder="address">
        <input type="text" name="prize" value="" placeholder="prize">
        <input type="text" name="look_persons" value="" placeholder="look_persons">
        <input type="submit" value="modifyVideoById">
    </form>

    <form method="post" enctype="multipart/form-data" action="/Video/modifyVideo_videoById.do">
        <input type="text" placeholder="video_id" name="video_id" value="">
        <input type="file" placeholder="videofile" name="videofile">
        <input type="submit" value="modifyVideo_videoById">
    </form>

    <form method="get"action="/Video/deleteVideoById.do">
        <input type="text" placeholder="video_id" name="video_id" value="">
        <input type="submit" value="deleteVideoById">
    </form>

    <form action="/test/hello.do" method="post">
        <input type="text" name="video_type">
        <input type="submit" value="test">
    </form>
</body>
</html>
