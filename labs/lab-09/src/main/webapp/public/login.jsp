<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="Stylesheet" type="text/css" href="<c:url value='/style.css'/>"/>
</head>
<body>
    <form method="post" action="<c:url value='/j_spring_security_check'/>">
        <input type="hidden" name="_spring_security_remember_me" value="yes">
        <table>
            <tr>
                <td>Username:</td>
                <td><input type="text" name="j_username"></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="j_password"></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Login"></td>
            </tr>
        </table>
    </form>
</body>
</html>