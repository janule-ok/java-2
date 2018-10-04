<%@taglib prefix="jstl"
          uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<jsp:useBean id="customers"
             type="java.util.List<net.sevecek.videoboss.entity.Customer>"
             scope="request"/>
<jstl:url var="cssLink" value="/css/style.css"/>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>VideoBoss</title>
    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css"
          href="${cssLink}"/>
</head>
<body>

    <h1>Customers</h1>

    <h4>
        <jstl:url var="addLink" value="/customers/new.html"/>
        <a href="${addLink}" class="actionButtonLink">
            Add a customer
        </a>
    </h4>

    <table>
        <tr>
            <th>Number</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Address</th>
            <th>Action</th>
        </tr>

        <jstl:forEach var="customer" items="${customers}">
            <tr>
                <td>${customer.id}</td>
                <td><jstl:out value="${customer.firstName}"/></td>
                <td><jstl:out value="${customer.lastName}"/></td>
                <td><jstl:out value="${customer.address}"/></td>
                <td>
                    <jstl:url var="editLink" value="/customers/${customer.id}.html"/>
                    <a href="${editLink}" class="actionButtonLink">Edit</a>

                    <jstl:url var="deleteLink" value="/customers/${customer.id}/${customer.version}"/>
                    <form action="${deleteLink}" method="post" class="actionButtonForm">
                        <input type="hidden" name="_method" value="delete"/>
                        <input type="submit" value="Remove"/>
                    </form>
                </td>
            </tr>
        </jstl:forEach>
    </table>

</body>
</html>