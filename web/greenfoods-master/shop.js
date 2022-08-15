var search = document.querySelector("#search");

search.addEventListener("input", () => {
    var formData = new URLSearchParams();
    formData.append("search_client", search.value);
    formData.append("action", "Search_Client");

    fetch("../MainController", {
        method: "post",
        body: formData,
    })
            .then((response) => {
                return response.text();
            })
            .then((response) => {
                let start = response.search('<div class="row">');
                let end = response.search('<section class="ftco-section ftco-no-pt ftco-no-pb py-5 bg-light">') - 44;

                let result = response.slice(start, end);
                //console.log(result);
                $("#load").html(result);
            });
    return false;
});

function changeIndex(event) {
    let data = new URLSearchParams();
    data.append("index", event.childNodes[1].value);
    data.append("action", "Search_Client");
    fetch("../MainController", {
        method: "post",
        body: data,
    })
            .then((response) => {
                return response.text();
            })
            .then((response) => {
                let start = response.search('<div class="row">');
                let end = response.search('<section class="ftco-section ftco-no-pt ftco-no-pb py-5 bg-light">') - 44;

                let result = response.slice(start, end);
                //console.log(result);
                $("#load").html(result);
            });
    return false;

}