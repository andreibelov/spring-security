<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit contact ...</title>
    <link rel="Stylesheet" type="text/css" href="<c:url value='/style.css'/>"/>
</head>
<body>
    <form:form commandName="contact" action="save.do">
        <form:hidden path="id"/>
        <table>
            <tr>
                <td>Name:</td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td>Telephone Number:</td>
                <td><form:input path="telephoneNumber"/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit"></td>
            </tr>
        </table>
    </form:form>

</body>
</html>