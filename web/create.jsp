<%-- Document : create Created on : Mar 9, 2022, 10:53:17 AM Author : ASUS --%>

    <%@page import="sample.users.CategoryDTO" %>
        <%@page import="java.util.ArrayList" %>
            <%@page import="java.util.List" %>
                <%@page contentType="text/html" pageEncoding="UTF-8" %>
                    <!DOCTYPE html>
                    <html>

                    <head>
                        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                        <title>Create Page</title>
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
                        <% List<CategoryDTO> list = (List) session.getAttribute("LIST_CATEGORY");
                            if (list == null) {
                            list = new ArrayList<CategoryDTO>();
                                }
                                %>
                                <h1>Welcome: ${sessionScope.LOGIN_USER.getFullName()}</h1>
                                <h2>Add a new product</h2>
                                <a href="MainController?search=${param.search}&action=Search" method="post">Back</a>
                                <form action="MainController" method="POST"><br>
                                    <label>Product ID</label><input type="text" name="productID" required="""><br>
                                    <label>Product name</label><input type="text" name="productName"  required=""><br>
                                    <label>Image</label><input type="file" accept="image/*" id="uploadFile"
                                        onchange="processFile(uploadFile)" required=""><br>
                                    <img id='showImage' src=""
                                        style="max-width: 500px; max-height: 250px;width: 40%;height: 40%"><br>
                                    <input type="hidden" name="image" value="" id='imageInput'>
                                    <label>Price</label><input type="number" name="price" required="" min="0" step="0.01">$<br>
                                    <label>Quantity</label><input type="number" name="quantity" required="" min="1">
                                    <br>
                                    <label>Category ID</label><select name="categoryID" id="categoryID">
                                        <option selected="" value="<%= list.get(0).getCategoryID()%>">
                                            <%= list.get(0).getCategoryID()%>
                                        </option>
                                        <% for (int i=1; i < list.size(); i++) {%>

                                            <option disabled value="<%= list.get(i).getCategoryID()%>">
                                                <%= list.get(i).getCategoryID()%>
                                            </option>
                                            <% }%>
                                    </select><br>
                                    <label>Category name</label><select id="categoryName" name="categoryName">
                                        <% for (int i=0; i < list.size(); i++) {%>
                                            <option value="<%= list.get(i).getCategoryName()%>">
                                                <%= list.get(i).getCategoryName()%>
                                            </option>
                                            <% }%>
                                    </select><br>
                                    <label>Using Date</label><input type="number" name="usingDate" required=""
                                        min="1">days<br>
                                    <input type="hidden" name ='search' value='${param.search}'/>
                                    <input id='submit' type="submit" name="action" value="Insert">
                                </form>
                                <script>
                                    var imgPreview = document.getElementById('showImage');
                                    var fileUpload = document.getElementById('uploadFile');
                                    var valueImg = document.getElementById('imageInput');
                                    var submit = document.getElementById('submit')
                                    var CLOUDINARY_UPLOAD_PRESET = 'rvswxotu';
                                    var CLOUDINARY_URL = 'https://api.cloudinary.com/v1_1/dwz7hut39/upload';
                                    var categoryName = document.getElementById('categoryName');
                                    var categoryID = document.getElementById('categoryID');
                                    categoryName.addEventListener('change', () => {
                                        categoryID.options[categoryID.selectedIndex].disabled = true;
                                        categoryID.options[categoryName.selectedIndex].disabled = false;
                                        categoryID.options[categoryName.selectedIndex].selected = true;
                                    });


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