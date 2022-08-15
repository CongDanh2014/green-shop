const mainPage = document.querySelector('.main-container');
const firstNextBtn = document.querySelector('.nextBtn');
const prevBtnSec = document.querySelector('.prev-1');
const step = document.querySelectorAll("ul li");
let current = 1;
function firstNextBtn1() {
    mainPage.style.marginLeft = "-100%";
    step[current - 1].classList.add("active");
    current++;
}

function nextBtnSec() {
    mainPage.style.marginLeft = "-200%";
    step[current - 1].classList.add("active");
    current++;
}

prevBtnSec.addEventListener('click', () => {
    mainPage.style.marginLeft = "0%";
    step[current - 2].classList.remove("active");
    current--;
});
function fetchCreate() {
    document.getElementById('loading-submit').style.display = "block";
    let data = new URLSearchParams();
    data.append("fullName", document.querySelector('#fullName').value);
    data.append("userID", document.querySelector('#userName').value);
    data.append("password", document.querySelector('#password').value);
    data.append("confirm", document.querySelector('#confirm').value);
    data.append("address", document.querySelector('#address').value);
    data.append("phone", document.querySelector('#phone').value);
    data.append("birthday", document.querySelector('#birthday').value);
    data.append("email", document.querySelector('#email').value);
    data.append("action", "Create");
    fetch("MainController", {
        method: "post",
        body: data
    })
            .then((response) => {

                return response.text();
            })
            .then((response) => {
                setTimeout(() => {
                    document.getElementById('loading-submit').style.display = "none";
                    let start = response.search('<button class="submit nextBtn"');
                    let end = response.search('NEXT ></button>') + 'NEXT ></button>'.length;
                    let result = response.slice(start, end);
                    $("#change1").html(result);
                    if (result.includes('firstNextBtn1()')) {
                        firstNextBtn1();
                        document.querySelector('#check-user').innerHTML = '';
                        document.querySelector('#check-email').innerHTML = '';
                    } else {
                        if (response.includes('Duplicate User name.')) {
                            document.getElementById('check-user').innerHTML = 'Duplicate User name.';
                        }
                        if (response.includes("<span id ='check-email' class='form-message'>This is not a valid email address.</span>")) {
                            document.getElementById('check-email').innerHTML = 'This is not a valid email address.';
                        } else if (response.includes("Email exist")) {
                            document.getElementById('check-email').innerHTML = 'Email exist.';
                        }
                    }
                }, 2000);
            })
    return false;
}


function fetchCheck() {
    let data = new URLSearchParams();
    data.append("fullName", document.querySelector('#fullName').value);
    data.append("userID", document.querySelector('#userName').value);
    data.append("password", document.querySelector('#password').value);
    data.append("confirm", document.querySelector('#confirm').value);
    data.append("address", document.querySelector('#address').value);
    data.append("phone", document.querySelector('#phone').value);
    data.append("birthday", document.querySelector('#birthday').value);
    data.append("email", document.querySelector('#email').value);
    data.append("verify", document.querySelector('#verify').value);
    data.append("g-recaptcha-response", document.querySelector('#g-recaptcha-response').value);

    data.append("action", "Check-Create");
    fetch("CreateController2", {
        method: "post",
        body: data
    })
            .then((response) => response.text())
            .then((text) => {
                console.log(text);
                if (text.includes("verify-success")) {
                    nextBtnSec();
                }
            })
            .catch(function (error) {
                console.log(error);
            })
    return false;
}