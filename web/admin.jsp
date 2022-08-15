<%-- 
    Document   : admin
    Created on : Mar 4, 2022, 8:40:24 PM
    Author     : ASUS
--%>

<%@page import="sample.users.UserDTO"%>
<%@page import="java.util.List"%>
<%@page import="sample.users.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Security-Policy" content="script-src 'self' https://apis.google.com">
        <script src="dist/xss-filters.min.js"></script>
        <STYLE>body{
                -moz-binding:url("http://xss.rocks/xssmoz.xml#xss")
            }</STYLE>
        <title>Admin Page</title>
    </head>
    <body>
        <%
            if (session.getAttribute("LOGIN_USER") != null) {
                if (!((UserDTO) session.getAttribute("LOGIN_USER")).getRoleID().equals("AD")) {
                    response.sendRedirect("login.jsp");
                }
            } else {
                response.sendRedirect("login.jsp");

            }
        %>
        <h1>Welcome: ${sessionScope.LOGIN_USER.getFullName()}</h1>
        <form action ='MainController'>
            Search <input type="text" name="search" value="${param.search}">
            <input type="submit" name="action" value="Search">
        </form>
        <a href="MainController?action=InsertPage&search=${param.search}">Add a new product</a><br>
        <a href="greenfoods-master/main.jsp">Go to the main page</a><br>
        <a href="MainController?action=Logout">Logout</a>
        <%
            List<ProductDTO> list = (List<ProductDTO>) request.getAttribute("LIST_USER");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Photo</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Category ID</th>
                    <th>Category Name</th>
                    <th>Import Date</th>
                    <th>Using Date</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (ProductDTO product : list) {
                %>
            <form action="update.jsp" method="POST">
                <tr>
                    <td><%= ++count%></td>
                    <td>
                        <input type="text"  value="<%= product.getProductID()%>" readonly=""/>
                    </td>
                    <td><input type="text" name="productName" value="<%= product.getProductName()%>"  readonly=""/>
                    </td>
                    <td><input type="hidden" name="image" value="<%= product.getImage()%>"/><img src="<%= product.getImage()%>" style="max-width: 500px; max-height: 250px;width: 100%;height: 100%"/>
                    </td>
                    <td><input type="text" name="price" value="<%= product.getPrice()%>"  readonly=""/>$
                    <td><input type="text" name="quantity" value="<%= product.getQuantity()%>"  readonly=""/>
                    <td><input type="text" name="catagoryID" value="<%= product.getCategoryID()%>"  readonly=""/>
                    <td><input type="text" name="catagoryName" value="<%= product.getCategoryName()%>"  readonly=""/>
                    <td><input type="text" name="importDate" value="<%= product.getImportDate()%>"  readonly=""/>
                    <td><input type="text" name="usingDate" value="<%= product.getUsingDate()%>"  readonly=""/>
                    <td>
                        <a href="MainController?action=Delete&productID=<%= product.getProductID()%>&search=<%= request.getParameter("search")%>">Delete</a>
                    </td>
                    <td>
                        <input type="hidden" name="productID" value="<%= product.getProductID()%>">
                        <input type="hidden" name="search" value="<%= request.getParameter("search")%>">
                        <input type="submit" name="action" value="Update">                        
                    </td>
                </tr>
            </form>

            <%
                }
            %>
        </tbody>
    </table>

    <%
            }
        }
    %>
</body>
</html>
