<%@page import="sample.shopping.Cart"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="sample.users.ProductDTO" %>
<%@page import="sample.users.UserDTO" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Green shop</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link
            href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&display=swap"
            rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i&display=swap"
              rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Amatic+SC:400,700&display=swap"
              rel="stylesheet">
        <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
        <link rel="stylesheet" href="css/animate.css">

        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">

        <link rel="stylesheet" href="css/aos.css">

        <link rel="stylesheet" href="css/ionicons.min.css">

        <link rel="stylesheet" href="css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="css/jquery.timepicker.css">

        <link rel="stylesheet" href="shop.css">

        <link rel="stylesheet" href="css/flaticon.css">
        <link rel="stylesheet" href="css/icomoon.css">
        <link rel="stylesheet" href="css/style.css">
    </head>

    <body class="goto-here">
        <% if (request.getAttribute("END_PAGE") == null)
                request.getRequestDispatcher("../SearchClientController").forward(request, response);%>
        <div class="py-1 bg-primary">
            <div class="container">
                <div class="row no-gutters d-flex align-items-start align-items-center px-md-0">
                    <div class="col-lg-12 d-block">
                        <div class="row d-flex">
                            <div class="col-md pr-4 d-flex topper align-items-center">
                                <div class="icon mr-2 d-flex justify-content-center align-items-center">
                                    <span class="icon-phone2"></span>
                                </div>
                                <span class="text">+ 1235 2355 98</span>
                            </div>
                            <div class="col-md pr-4 d-flex topper align-items-center">
                                <div class="icon mr-2 d-flex justify-content-center align-items-center">
                                    <span class="icon-paper-plane"></span>
                                </div>
                                <span class="text">danhbcse150456@fpt.edu.vn</span>
                            </div>
                            <div class="col-md-5 pr-4 d-flex topper align-items-center text-lg-right">
                                <span class="text">3-5 Business days delivery &amp; Free Returns</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
             id="ftco-navbar">
            <div class="container">
                <a class="navbar-brand" href="main.jsp">Green shop</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span class="oi oi-menu"></span> Menu
                </button>

                <div class="collapse navbar-collapse" id="ftco-nav">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item"><a href="main.jsp" class="nav-link">Home</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="dropdown04"
                               data-toggle="dropdown" aria-haspopup="true"
                               aria-expanded="false">Shop</a>
                            <div class="dropdown-menu" aria-labelledby="dropdown04">
                                <a class="dropdown-item" href="shop.jsp">Shop</a>
                                <a class="dropdown-item" href="wishlist.jsp">Wishlist</a>
                                <a class="dropdown-item" href="product-single.jsp">Single Product</a>
                                <a class="dropdown-item" href="cart.jsp">Cart</a>
                                <a class="dropdown-item" href="checkout.jsp">Checkout</a>
                            </div>
                        </li>
                        <%
                            Cart cart = (Cart) session.getAttribute("CART");
                        %>
                        <li class="nav-item active"><a href="about.jsp" class="nav-link">About</a></li>
                        <li class="nav-item"><a href="blog.jsp" class="nav-link">Blog</a></li>
                        <li class="nav-item"><a href="contact.jsp" class="nav-link">Contact</a></li>
                        <li class="nav-item cta cta-colored"><a href="cart.jsp" class="nav-link"><span
                                    class="icon-shopping_cart"></span>[<%if (cart == null) {%><%=0%><%} else {%><%=cart.getCart().values().size()%><%}%>]</a></li>
                                <% if (session.getAttribute("LOGIN_USER") != null) { %>
                        <li class="nav-item"
                            style="max-width: 250px; overflow: hidden; display: -webkit-box;-webkit-line-clamp: 1;-webkit-box-orient: vertical;text-overflow: ellipsis;white-space: nowrap; ">
                            <a class="nav-link">Hello, ${sessionScope.LOGIN_USER.getFullName()}</a>
                        </li>
                        <li class="nav-item"><a href="../MainController?action=Logout"
                                                class="nav-link">Logout</a></li>
                            <% if (((UserDTO) session.getAttribute("LOGIN_USER")).getRoleID().equals("AD")) { %>
                        <li class="nav-item"><a href="../admin.jsp" class="nav-link">Manager</a>
                        </li>
                        <% }
                        } else { %>
                        <li class="nav-item"><a href="../login.jsp"
                                                class="nav-link">Login</a></li>
                            <% } %>

                    </ul>
                </div>
            </div>
        </nav>
        <!-- END nav -->

        <div class="hero-wrap hero-bread" style="background-image: url('images/bg_1.jpg');">
            <div class="container">
                <div class="row no-gutters slider-text align-items-center justify-content-center">
                    <div class="col-md-9 ftco-animate text-center">
                        <p class="breadcrumbs"><span class="mr-2"><a href="main.jsp">Home</a></span>
                            <span>Products</span>
                        </p>
                        <h1 class="mb-0 bread">Products</h1>
                    </div>
                </div>
            </div>
        </div>

        <section class="ftco-section">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="mb-5 text-center">
                        <form action="MainController" method="POST">
                            <div class="wrapper-input">
                                <div
                                    style="width: 75px;height:100%;background-color:#82ae46;border-bottom-left-radius: 5px;border-top-left-radius: 5px;display: flex;align-items: center;justify-content: center;">
                                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"
                                         width="25px" height="25px" color="#fff" style="color: #fff">
                                    <path
                                        d="M505 442.7L405.3 343c-4.5-4.5-10.6-7-17-7H372c27.6-35.3 44-79.7 44-128C416 93.1 322.9 0 208 0S0 93.1 0 208s93.1 208 208 208c48.3 0 92.7-16.4 128-44v16.3c0 6.4 2.5 12.5 7 17l99.7 99.7c9.4 9.4 24.6 9.4 33.9 0l28.3-28.3c9.4-9.4 9.4-24.6.1-34zM208 336c-70.7 0-128-57.2-128-128 0-70.7 57.2-128 128-128 70.7 0 128 57.2 128 128 0 70.7-57.2 128-128 128z"
                                        fill="#fff" />
                                    </svg>
                                </div>
                                <input type="text" id="search" name="search_client" 
                                       placeholder="Search item">
                            </div>
                        </form>
                    </div>
                </div>
                <div id="load">
                    <div class="row">
                        <% List<ProductDTO> products = (List<ProductDTO>) request.getAttribute("LIST_A");
                            if (products == null) {
                                products = new ArrayList<>();
                            }
                        %>
                        <% for (ProductDTO product : products) {%>
                        <div class="col-md-6 col-lg-3 ">
                            <div class="product">
                                <a href="#" class="img-prod"><img class="img-fluid"
                                                                  src="<%= product.getImage()%>"
                                                                  alt="Colorlib Template">
                                    <div class="overlay"></div>
                                </a>
                                <div class="text py-3 pb-4 px-3 text-center">
                                    <h3><a href="#">
                                            <%=product.getProductName()%>
                                        </a></h3>
                                    <div class="d-flex">
                                        <div class="pricing">
                                            <p class="price"><span>MFG:
                                                    <%=product.getImportDate()%>
                                                </span>
                                            </p>
                                            <p class="price"><span>EXP:
                                                    <%=product.getUsingDate()%>
                                                </span>
                                            </p>
                                            <p class="price"><span>Quantity
                                                    <%=product.getQuantity()%>
                                                </span>
                                            </p>
                                            <p class="price"><span>$
                                                    <%=product.getPrice()%>
                                                </span></p>
                                        </div>
                                    </div>
                                    <div class="bottom-area d-flex px-3">
                                        <div class="m-auto d-flex">
                                            <a href="#"
                                               class="add-to-cart d-flex justify-content-center align-items-center text-center">
                                                <span><i
                                                        class="ion-ios-menu"></i></span>
                                            </a>
                                            <a href="../MainController?action=Add&productID=<%= product.getProductID()%>&search_client=${requestScope.SEARCH_CLIENT}"
                                               class="buy-now d-flex justify-content-center align-items-center mx-1">
                                                <span><i
                                                        class="ion-ios-cart"></i></span>
                                            </a>
                                            <a href="#"
                                               class="heart d-flex justify-content-center align-items-center ">
                                                <span><i
                                                        class="ion-ios-heart"></i></span>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <% }%>

                    </div>
                    <div class="row mt-5">
                        <div class="col text-center">
                            <div class="block-27">
                                <ul>

                                    <% int active = 0;
                                        if (request.getParameter("index") != null) {
                                            active = Integer.parseInt(request.getParameter("index"));
                                        } else {
                                            active = 1;
                                        }
                                    %>
                                    <li>
                                        <a><p onclick="changeIndex(this)" style="cursor: pointer;">&lt;<input type="hidden" value="<%if(active!=1){%><%=active-1%><%}else{%><%=1%><%}%>"></p></a>                                    
                                    </li>

                                    <%
                                        for (int i = 0; i < (int) request.getAttribute("END_PAGE"); i++) {
                                            if (active == i + 1) {
                                    %>

                                    <li class="active">
                                        <a style="cursor: pointer;"><%=i + 1%></a>
                                    </li>
                                    <% } else {%>
                                    <li class="">
                                        <a><p onclick="changeIndex(this)" style="cursor: pointer;"><%=i + 1%><input type="hidden" value="<%=i + 1%>"></p></a>
                                    </li>
                                    <%
                                            }
                                        }%>
                                    <li><a><p onclick="changeIndex(this)" style="cursor: pointer;">&gt;<input type="hidden" value="<%if(active!=(int) request.getAttribute("END_PAGE")){%><%=active+1%><%}else{%><%=active%><%}%>"></p></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="ftco-section ftco-no-pt ftco-no-pb py-5 bg-light">
            <div class="container py-4">
                <div class="row d-flex justify-content-center py-5">
                    <div class="col-md-6">
                        <h2 style="font-size: 22px;" class="mb-0">Subcribe to our Newsletter</h2>
                        <span>Get e-mail updates about our latest shops and special offers</span>
                    </div>
                    <div class="col-md-6 d-flex align-items-center">
                        <form action="#" class="subscribe-form">
                            <div class="form-group d-flex">
                                <input type="text" class="form-control"
                                       placeholder="Enter email address">
                                <input type="submit" value="Subscribe" class="submit px-3">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <footer class="ftco-footer ftco-section">
            <div class="container">
                <div class="row">
                    <div class="mouse">
                        <a href="#" class="mouse-icon">
                            <div class="mouse-wheel"><span class="ion-ios-arrow-up"></span></div>
                        </a>
                    </div>
                </div>
                <div class="row mb-5">
                    <div class="col-md">
                        <div class="ftco-footer-widget mb-4">
                            <h2 class="ftco-heading-2">Vegefoods</h2>
                            <p>Far far away, behind the word mountains, far from the countries Vokalia
                                and Consonantia.</p>
                            <ul class="ftco-footer-social list-unstyled float-md-left float-lft mt-5">
                                <li class="ftco-animate"><a href="#"><span
                                            class="icon-twitter"></span></a></li>
                                <li class="ftco-animate"><a href="#"><span
                                            class="icon-facebook"></span></a></li>
                                <li class="ftco-animate"><a href="#"><span
                                            class="icon-instagram"></span></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md">
                        <div class="ftco-footer-widget mb-4 ml-md-5">
                            <h2 class="ftco-heading-2">Menu</h2>
                            <ul class="list-unstyled">
                                <li><a href="#" class="py-2 d-block">Shop</a></li>
                                <li><a href="#" class="py-2 d-block">About</a></li>
                                <li><a href="#" class="py-2 d-block">Journal</a></li>
                                <li><a href="#" class="py-2 d-block">Contact Us</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="ftco-footer-widget mb-4">
                            <h2 class="ftco-heading-2">Help</h2>
                            <div class="d-flex">
                                <ul class="list-unstyled mr-l-5 pr-l-3 mr-4">
                                    <li><a href="#" class="py-2 d-block">Shipping Information</a></li>
                                    <li><a href="#" class="py-2 d-block">Returns &amp; Exchange</a></li>
                                    <li><a href="#" class="py-2 d-block">Terms &amp; Conditions</a></li>
                                    <li><a href="#" class="py-2 d-block">Privacy Policy</a></li>
                                </ul>
                                <ul class="list-unstyled">
                                    <li><a href="#" class="py-2 d-block">FAQs</a></li>
                                    <li><a href="#" class="py-2 d-block">Contact</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md">
                        <div class="ftco-footer-widget mb-4">
                            <h2 class="ftco-heading-2">Have a Questions?</h2>
                            <div class="block-23 mb-3">
                                <ul>
                                    <li><span class="icon icon-map-marker"></span><span class="text">203
                                            Fake St. Mountain View, San Francisco, California,
                                            USA</span></li>
                                    <li><a href="#"><span class="icon icon-phone"></span><span
                                                class="text">+2 392 3929 210</span></a></li>
                                    <li><a href="#"><span class="icon icon-envelope"></span><span
                                                class="text">info@yourdomain.com</span></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 text-center">

                        <p>
                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                            Copyright &copy;
                            <script>document.write(new Date().getFullYear());</script> All rights
                            reserved | This template is made with <i class="icon-heart color-danger"
                                                                     aria-hidden="true"></i> by CD</a>
                            
                        </p>
                    </div>
                </div>
            </div>
        </footer>



        <!-- loader -->
        <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px">
            <circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4"
                    stroke="#eeeeee" />
            <circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4"
                    stroke-miterlimit="10" stroke="#F96D00" />
            </svg></div>


        <script src="js/jquery.min.js"></script>
        <script src="js/jquery-migrate-3.0.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.easing.1.3.js"></script>
        <script src="js/jquery.waypoints.min.js"></script>
        <script src="js/jquery.stellar.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/aos.js"></script>
        <script src="js/jquery.animateNumber.min.js"></script>
        <script src="js/bootstrap-datepicker.js"></script>
        <script src="js/scrollax.min.js"></script>
        <script
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
        <script src="js/google-map.js"></script>
        <script src="js/main.js"></script>
        <script src="shop.js"></script>
    </body>

</html>