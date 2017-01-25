<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List contacts ...</title>
    <link rel="Stylesheet" type="text/css" href="<c:url value='/style.css'/>"/>
    <script type="text/javascript">
        function remove() {
            if (confirm("You are about to remove all ACLs. Sure?")) {
                document.location.href = "<c:url value='/acl/clear.do'/>?id=${domainEntityId}";
            }
        }
    </script>
</head>
<body>
    <table border="1" cellpadding="5" class="list">
        <tr class="list_header">
            <td width="200" align="center">Type</td>
            <td width="120" align="center">User/Role</td>
            <td width="170" align="center">Permissions</td>
        </tr>

        <c:forEach items="${permissions}" var="permission">
            <tr>
                <td align="center">${permission.securityIdentType}</td>
                <td align="center">${permission.securityIdentName}</td>
                <td align="center">${permission.permission.pattern}</td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="3" align="center">
                [<a href="<c:url value='/acl/edit.do'/>?id=${domainEntityId}">Create New</a>]&nbsp;
                [<a href="javascript:remove();">Remove All</a>]

                <br><br>
                [<a href="<c:url value='/contact/list.do'/>">Back to contacts</a>]
            </td>
        </tr>
    </table>

</body>
</html>