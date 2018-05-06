<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
%>
<%@page pageEncoding="UTF-8" %>
<html>
<body>
<h2>Hello World!</h2>
    <form action="/Commont/insertOneCommont.do" method="get">
        <%--<input type="text" name="dynamic_id" value="14">--%>
            <input type="text" name="user_id_f" value="" placeholder="user_id_f">
            <input type="text" name="commont_body" value="" placeholder="commont_body">
            <input type="text" name="father_commont_id" placeholder="father_commont_id">
            <input type="submit" value="insertOneCommont">
    </form>

    <form action="/Commont/findCommontById.do" method="get">
        <%--<input type="text" name="dynamic_id" value="14">--%>
        <input type="text" name="commont_id" value="" placeholder="commont_id">
        <input type="submit" value="findCommontById">
    </form>

    <form action="/Commont/findCommontsByUser_id.do" method="get">
        <%--<input type="text" name="dynamic_id" value="14">--%>
        <input type="text" name="user_id_f" value="" placeholder="user_id_f">
        <input type="submit" value="findCommontsByUser_id">
    </form>

    <form action="/Commont/findFather_CommontByCommont_id.do" method="get">
        <%--<input type="text" name="dynamic_id" value="14">--%>
        <input type="text" name="commont_id" value="" placeholder="commont_id">
        <input type="submit" value="findFather_CommontByCommont_id">
    </form>

    <form action="/Commont/findSon_Commont_idByCommont_id.do" method="get">
        <%--<input type="text" name="dynamic_id" value="14">--%>
        <input type="text" name="commont_id" value="" placeholder="commont_id">
        <input type="submit" value="findSon_Commont_idByCommont_id">
    </form>

    <form action="/Commont/deleteCommontById.do" method="get">
        <%--<input type="text" name="dynamic_id" value="14">--%>
        <input type="text" name="commont_id" value="" placeholder="commont_id">
        <input type="submit" value="deleteCommontById">
    </form>

    <form action="/test/hello.do" enctype="multipart/form-data" method="post">
        <input type="file" name="picfile">
        <input type="submit" value="OK"/>
    </form>

</body>
</html>
