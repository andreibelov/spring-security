<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>List contacts ...</title>
    <link rel="Stylesheet" type="text/css" href="<c:url value='/style.css'/>"/>
    <script type="text/javascript">
        function remove(id, name) {
            if (confirm("You are about to remove '" + name + "'. Sure?")) {
                document.location.href = "<c:url value='/contact/remove.do'/>?id=" + id;

            }
        }
    </script>
</head>
<body>
    <table border="1" cellpadding="5" class="list">
        <tr class="list_header">
            <td width="200" align="center">Name</td>
            <td width="120" align="center">Telephone Number</td>
            <td width="170" align="center">Actions</td>
        </tr>

        <c:forEach items="${contacts}" var="contact">
            <tr>
                <td>${contact.name}</td>
                <td align="center">${contact.telephoneNumber}</td>
                <td align="center">
                	[<a href="<c:url value='/contact/edit.do'/>?id=${contact.id}">Edit</a>]&nbsp;
                    [<a href="javascript:remove(${contact.id},'${contact.name}')">Remove</a>]&nbsp;
                    [<a href="<c:url value='/acl/list.do'/>?id=${contact.id}">ACL</a>]&nbsp;
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="3" align="center">
                [<a href="<c:url value='/contact/edit.do'/>">Create New</a>]&nbsp;
            </td>
        </tr>
    </table>
</body>
</html>