<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit contact ACL ...</title>
    <link rel="Stylesheet" type="text/css" href="<c:url value='/style.css'/>"/>
    <script type="text/javascript">
        function checkControls() {
            var sidType = document.getElementById("securityIdentType");
            var username = document.getElementById("username");
            var role = document.getElementById("role");

            username.disabled = true;
            role.disabled = true;

            if (sidType.value == "Authority") {
                role.disabled = false;
            } else if (sidType.value == "Principal") {
                username.disabled = false;
            }
        }
    </script>
</head>
<body>
    <form:form commandName="acl" action="save.do">
        <form:hidden path="domainEntityId"/>

        <table>
            <tr>
                <td>SID Type:</td>
                <td><form:select path="securityIdentType" items="${securityIdentTypes}" onchange="checkControls();"/></td>
            </tr>

            <tr>
                <td>User:</td>
                <td><form:select path="username" items="${usernames}"/></td>
            </tr>

            <tr>
                <td>Role:</td>
                <td><form:select path="role" items="${roles}"/></td>
            </tr>

            <tr>
                <td valign="top">Permissions:</td>
                <td>
                    <form:radiobuttons path="permissions" items="${permissions}" delimiter="<br>"/>
                </td>
            </tr>

            <tr>
                <td colspan="2"><input type="submit" value="Save"></td>
            </tr>
        </table>


    </form:form>

    <script type="text/javascript">
        checkControls();
    </script>

</body>
</html>