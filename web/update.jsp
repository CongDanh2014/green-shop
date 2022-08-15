<%-- 
    Document   : update
    Created on : Mar 10, 2022, 8:56:36 PM
    Author     : ASUS
--%>

<%@page import="sample.users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Page</title>
        <style>
            input,
            select {
                min-height: 20px;
                min-width: 230px;
                font-size: 18px;
            }

            label {
                display: inline-block;
                min-height: 50px;
                min-width: 150px;
                font-size: 20px;
            }
        </style>
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
        <h2>Update a product</h2>
        <a href="MainController?search=${param.search}&action=Search" method="post">Back</a>
        <form action="MainController" method="POST">
            <label>Product name</label><input type="text" name="productName" required="" value="${param.productName}"><br>
            <label>Image</label><input type="file" accept="image/*" id="uploadFile" 
                                       onchange="processFile(uploadFile)"><br>
            <img id='showImage' src="${param.image}"
                 style="max-width: 500px; max-height: 250px;width: 40%;height: 40%"><br>
            <input type="hidden" name="image" value="${param.image}" id='imageInput'>
            <label>Price</label><input type="number" name="price" required="" min="0"  value="${param.price}" step="0.01">$<br>
            <label>Quantity</label><input type="number" name="quantity" required="" min="1" value="${param.quantity}">
            <br>
            <input type="hidden" name="productID" value="${param.productID}">
            <input type="hidden" name="search" value="${param.search}">
            <input id='submit' type="submit" name="action" value="Update"> 
        </form>
        <script>
            var imgPreview = document.getElementById('showImage');
            var fileUpload = document.getElementById('uploadFile');
            var valueImg = document.getElementById('imageInput');
            var submit = document.getElementById('submit')
            var CLOUDINARY_UPLOAD_PRESET = 'rvswxotu';
            var CLOUDINARY_URL = 'https://api.cloudinary.com/v1_1/dwz7hut39/upload';

            function processFile(uploadFile) {
                imgPreview.src = '';
                valueImg.value = '';
                if (uploadFile.files[0]) {
                    submit.style.display = 'none';

                    const file = uploadFile.files[0];
                    var pattern = /image-*/;
                    if (!file.type.match(pattern)) {
                        document.getElementById('uploadFile').value = '';
                        alert('This is not an image file');
                        imgPreview.src = '';
                        valueImg.value = '';
                        submit.style.display = 'block';
                        return;
                    } else {
                        var formData = new FormData();
                        formData.append('file', fileUpload.files[0]);
                        formData.append('upload_preset', CLOUDINARY_UPLOAD_PRESET);
                        axios({
                            url: CLOUDINARY_URL,
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/x-www-form-urlencoded',
                            },
                            data: formData

                        }).then(res => {

                            console.log(res.data.secure_url);
                            imgPreview.src = res.data.secure_url;
                            valueImg.value = res.data.secure_url;
                            submit.style.display = 'block';
                        }).catch(err => {
                            console.err(err);
                            imgPreview.src = '';
                            valueImg.value = '';
                            submit.style.display = 'block';
                        });
                    }
                }
            }
        </script>
        <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    </body>
</html>
