let purchase = document.querySelector(".purchase-all");

purchase.addEventListener('click',()=>{
    let username = document.querySelector(".username").textContent
    let objek = {
        username : username
    }
    let accessToken = (JSON.parse(localStorage.getItem("tokenTroll"))).token;

    let request = new XMLHttpRequest();
    request.open("POST", "http://localhost:8888/api/purchase");
    request.setRequestHeader("Content-Type", "application/json");
    request.setRequestHeader("Authorization", "Bearer " + accessToken);

    request.send(JSON.stringify(objek));
    request.onload = () => {
        location.reload();
    }
    
})