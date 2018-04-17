<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
%>
<%@page pageEncoding="UTF-8" %>
<html>
<body>
<h2>Hello World!</h2>
    <form action="/Dynamic/insertOneDynamic.do" method="post" enctype="multipart/form-data" >
        <%--<input type="text" name="dynamic_id" value="14">--%>
            <input type="text" name="title" value="">
            <input type="text" name="introduce" value="">
            <input type="file" name="picfile">
            <input type="file" name="picfile">
            <input type="text" name="user_id_f" value="3">
            <input type="text" name="dynamic_type" value="">
            <input type="submit" value="insertOneDynamic">
    </form>
    <form action="/Dynamic/modifyDynamicById.do" method="get">
        <input type="text" name="dynamic_id" value="14">
        <input type="text" name="title" value="">
        <input type="text" name="introduce" value="">
        <input type="text" name="user_id_f" value="3">
        <input type="submit" value="modifyDynamicById">
    </form>
    <form action="/Dynamic/modifyPicById.do" method="post" enctype="multipart/form-data">
        <input type="text" name="dynamic_id">
        <input type="text" name="prepicfilepath">
        <input type="file" name="picfile">
        <input type="submit" value="deletePicById">
    </form>

    <form action="/test/hello.do" method="post" enctype="multipart/form-data">
        <input type="file" name="picfile">
        <input type="submit" value="hello">
    </form>
    <form action="/Dynamic/deleteOneDynamicById.do" method="get">
        <input type="text" name="dynamic_id" value="">
        <input type="submit" value="deleteOneDynamicById">
    </form>

    <form action="/Dynamic/findDynamicByType.do">
        <input type="text" name="dynamic_type" value="">
        <input type="submit" value="findDynamicByType">
    </form>

    <form action="/Dynamic/findAllDynamicByUserId.do">
        <input type="text" name="user_id_f" value="">
        <input type="submit" value="findAllDynamicByUserId">
    </form>

    <form action="/Dynamic/findDynamicById.do">
        <input type="text" name="dynamic_id" value="">
        <input type="submit" value="findDynamicById">
    </form>
</body>
</html>
