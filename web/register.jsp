
<%@page import="sample.users.UserError"%>
<%@page import="java.util.Date" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link rel="stylesheet" href="register-layout.css">
        <script src="https://kit.fontawesome.com/7810fb5fc6.js" crossorigin="anonymous"></script>
        <script src="https://www.google.com/recaptcha/api.js"></script>
    </head>

    <body>

        <% boolean check = false; %>
        <%
            UserError userError = (UserError) request.getAttribute("USER_ERROR");

            if (userError == null) {
                userError = new UserError();
                check = true;
            }

        %>
        <div class="container">
            <header>
                <div class="process">
                    <ul>
                        <li class="step-1">
                            <p>1</p>
                            <div>Account</div>
                        </li>
                        <li class="step-2">
                            <p>2</p>
                            <div>Check</div>
                        </li>
                    </ul>
                </div>
            </header>
            <div id="main-create">
                <div class="title">CREATE YOUR ACCOUNT</div>
                <div class="main-container">
                    <form id="form-1" onsubmit="return fetchCreate()">
                        <div class="wrapper">Full Name<div class="input"><input id="fullName" type="text"
                                                                                placeholder="Characters length must in [6, 20]" name="fullName" required=""
                                                                                autocomplete="off"></div>
                            <span class="form-message"></span>
                        </div>
                        <div class="wrapper">Username<div class="input"><input id="userName" type="text"
                                                                               placeholder="in [5, 20] | no spaces" name="userID" required=""
                                                                               autocomplete="off"></div><span id="check-user" class="form-message"><%= userError.getUserID()%></span>
                        </div>
                        <div class="wrapper">Password<div class="input"><input id="password" type="password"
                                                                               placeholder="in [5,20]" name="password" required="" autocomplete="off"></div>
                            <span class="form-message"></span>
                        </div>
                        <div class="wrapper">Re-type Password<div class="input"><input id="confirm" type="password"
                                                                                       name="confirm" required="" placeholder="Confirm password" autocomplete="off">
                            </div><span class="form-message"></span>
                        </div>
                        <div class="wrapper">Address<div class="input"><input id="address" type="text"
                                                                              name="address" required="" placeholder="e.g. YourCountry-YourCity-YourDistrict"
                                                                              autocomplete="off">
                            </div><span class="form-message"></span>
                        </div>
                        <div class="wrapper">Phone<div class="input"><input id="phone" type="text" name="phone"
                                                                            required="" placeholder="e.g. 0123456789 | only numbers" autocomplete="off">
                            </div><span class="form-message"></span>
                        </div>
                        <div class="wrapper">Birthday<select name="" class="input" name="birthday" id="birthday">
                                <% for (int i = 0; i < 100; i++) {%>
                                <option value="<%= 1799 + new Date().getYear() + i%>">
                                    <%= 1799 + new Date().getYear() + i%>
                                </option>
                                <% }%>
                            </select></div>
                        <div class="wrapper">Email<div class="input"><input id="email" type="text" name="email"
                                                                            required="" placeholder="e.g. example@example.com" autocomplete="off"></div>
                            <span id ='check-email' class='form-message'><%= userError.getEnail()%></span>
                        </div>
                        <div class="wrapper-1">
                            <button class="back"><a href="login.jsp">
                                    < BACK</a></button>
                            <div id="change1"><button class="submit nextBtn" type="submit"
                                                      onload='<%if (check) {%>firstNextBtn1()<%} else {%> <%}%>'>
                                    <input type="hidden" name="action" value="Create" />NEXT ></button></div>
                        </div>
                    </form>
                    <div class="main-check">
                        <form onsubmit="return fetchCheck()">
                            <div style="margin: 0px 60px 20px;text-align:justify">We've sent a verification code
                                email
                                to the address you provided. Please check your email to receive the verification
                                code.
                                It is also your final step in the sign up process.</div>
                            <div class="wrapper">Verify Code<div class="input"><input type="text" name="verify" required=""
                                                                                      placeholder="Verification code in under 5 minutes" id="verify<%if (request.getAttribute("SUCCESS") != null) {%>-success<%} else {%><%}%>" autocomplete="off">
                                </div>
                            </div>
                                 <div id="recaptcha" class="g-recaptcha" data-sitekey="6LcwwOYeAAAAAORYGCJzL8Apj2XMFGZpg47GtBUD" style="margin: 0 60px 20px"></div>
                            <div class="wrapper-1">
                                <div class="back prev-1">
                                    < PREVIOUS</div>
                               

                                <button class="submit next-1" type="submit"><input type="hidden" name="action" value="Check-Create">CONFIRM
                                    ></button>
                            </div>
                        </form>

                    </div>
                    <div class="main-success">
                        <div style="margin: 0px 60px 20px;text-align:center">Your registration has been successfully
                            completed!</div>
                        <div class="wrapper-2"> <button class="back success"><a href="login.jsp">
                                    GO TO THE LOGIN PAGE</a></button></div>
                    </div>
                </div>

            </div>
            <footer>
                <i class="far fa-copyright"></i> 2022 Register form for green shop | Design by CD
            </footer>
            <div id="loading-submit"></div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
        <script src="register-event.js"></script>
        <script src="form-validation.js"></script>

        <script>
                                                          document.addEventListener('DOMContentLoaded', function () {
                                                              Validator({
                                                                  form: '#form-1',
                                                                  formGroupSelector: '.wrapper',
                                                                  errorSelector: '.form-message',
                                                                  rules: [
                                                                      Validator.minLength('#fullName', 6, 'Please fill at least 6 character.'),
                                                                      Validator.maxLength('#fullName', 20, 'Please fill at most 20 character.'),
                                                                      Validator.minLength('#userName', 5, 'Please fill at least 5 character.'),
                                                                      Validator.maxLength('#userName', 20, 'Please fill at most 20 character.'),
                                                                      Validator.notSpaces('#userName', 'User name must not be contain spaces.'),
                                                                      Validator.minLength('#password', 5, 'Please fill at least 5 character.'),
                                                                      Validator.maxLength('#userName', 20, 'Please fill at most 20 character.'),
                                                                      Validator.isRequired('#confirm', 'Please fill out this field'),
                                                                      Validator.isConfirmed('#confirm', function () {
                                                                          return document.querySelector('#form-1 #password').value;
                                                                      }, "The password confirmation doesn't match."),
                                                                      Validator.isRequired('#address', 'Please fill out this field'),
                                                                      Validator.isPhoneNumber('#phone', 'This is not a valid phone number.'),
                                                                      Validator.isEmail('#email', 'This is not a valid email address.')

                                                                  ]
                                                              })
                                                          })
        </script> 
    </body>

</html>