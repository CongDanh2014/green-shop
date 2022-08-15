    <%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
    <%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="login-layout.css">
        <script src="https://kit.fontawesome.com/7810fb5fc6.js" crossorigin="anonymous"></script>
        
    </head>
    <body>
        <div class="container">
            <div id="main">
                <div class="title">SIGN IN NOW</div>
                <form class="form" action="MainController" method="POST"  >
                    <div class="input"><i class="fas fa-user"></i><input type="text" name="userID" placeholder="Username" autocomplete="off" ></div>
                    <div class="input password"><i class="fas fa-lock"></i><input type="password" name="password" placeholder="Password"  autocomplete="off"></div>
                    <button class="submit" type="submit"><input  type="hidden" name="action" value="Login">GET STARTED</button>
                </form>
                <button class ='login-google'>
                    <div class="icon-google"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" width="40px" height="40px" viewBox="0 0 48 48" class="abcRioButtonSvg"><g><path fill="#EA4335" d="M24 9.5c3.54 0 6.71 1.22 9.21 3.6l6.85-6.85C35.9 2.38 30.47 0 24 0 14.62 0 6.51 5.38 2.56 13.22l7.98 6.19C12.43 13.72 17.74 9.5 24 9.5z"></path><path fill="#4285F4" d="M46.98 24.55c0-1.57-.15-3.09-.38-4.55H24v9.02h12.94c-.58 2.96-2.26 5.48-4.78 7.18l7.73 6c4.51-4.18 7.09-10.36 7.09-17.65z"></path><path fill="#FBBC05" d="M10.53 28.59c-.48-1.45-.76-2.99-.76-4.59s.27-3.14.76-4.59l-7.98-6.19C.92 16.46 0 20.12 0 24c0 3.88.92 7.54 2.56 10.78l7.97-6.19z"></path><path fill="#34A853" d="M24 48c6.48 0 11.93-2.13 15.89-5.81l-7.73-6c-2.15 1.45-4.92 2.3-8.16 2.3-6.26 0-11.57-4.22-13.47-9.91l-7.98 6.19C6.51 42.62 14.62 48 24 48z"></path><path fill="none" d="M0 0h48v48H0z"></path></g></svg></div>
                   
                    <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/Assignment/LoginGoogleController&response_type=code
                    &client_id=220289549455-2cmoo8tfu9kej3kisu5v3qsm5h8340sf.apps.googleusercontent.com&approval_prompt=force">Login With Google</a>
                </button>

                <div class="create"><a href="register.jsp">CREATE ACCOUNT</a><a href="" style="margin-left: 16%;">FORGOT ACCOUNT?</a><a href="greenfoods-master/main.jsp">GO TO MAIN PAGE</a></div>

            </div>
            <footer>
                <i class="far fa-copyright"></i> 2022 Login form for green shop | Design by CD
            </footer>
        </div>


    </body>
</html>

