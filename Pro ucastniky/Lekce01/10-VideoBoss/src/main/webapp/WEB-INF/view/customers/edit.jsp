<%@page contentType="text/html;charset=UTF-8"
        language="java" %>
<%@taglib prefix="jstl"
          uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<jsp:useBean id="customer"
             type="net.sevecek.videoboss.entity.Customer"
             scope="request"/>
<jstl:url var="cssLink" value="/css/style.css"/>
<jstl:if test="${customer.id ne null}">
    <jstl:url var="actionLink" value="/customers/${customer.id}.html"/>
</jstl:if>
<jstl:if test="${customer.id eq null}">
    <jstl:url var="actionLink" value="/customers/new.html"/>
</jstl:if>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>VideoBoss</title>
    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8"/>
    <link rel="stylesheet"
          type="text/css"
          href="${cssLink}"/>
</head>
<body>

    <h1>Customer Details</h1>

    <form action="${actionLink}" method="post">
        <table>
            <jstl:if test="${customer.id ne null}">
                <tr>
                    <th>Number</th>
                    <td>
                            ${customer.id}
                        <input type="hidden" name="version"
                               value="${customer.version}"/>
                        <input type="hidden" name="deleted"
                               value="${customer.deleted}"/>
                    </td>
                </tr>
            </jstl:if>
            <tr>
                <th><label for="firstName">First name</label></th>
                <td>
                    <input id="firstName"
                           name="firstName"
                           value="<jstl:out value='${customer.firstName}'/>"/>
                </td>
            </tr>
            <tr>
                <th><label for="lastName">Last name</label></th>
                <td>
                    <input id="lastName"
                           name="lastName"
                           value="<jstl:out value='${customer.lastName}'/>"/>
                </td>
            </tr>
            <tr>
                <th><label for="address">Address</label></th>
                <td>
                    <input id="address"
                           name="address"
                           value="<jstl:out value='${customer.address}'/>"/>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td>
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>