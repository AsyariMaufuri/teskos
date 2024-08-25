let popupTopUp = document.querySelector(".popupTopUp");
let addBalanceButton = document.querySelector(".add-balance");

popupTopUp.style.display = "none";

addBalanceButton.addEventListener('click', () => {
    popupTopUp.style.display = "block";
    document.querySelector("#banyaknya").value = "";
});

let form = popupTopUp.querySelector("form");
let submitButton = form.querySelector("button[type='submit']");

form.addEventListener('submit', (event) => {
    event.preventDefault();
    submitRequest();
    
});

let closeButton = popupTopUp.querySelector(".closeButton");
closeButton.addEventListener('click', () => {
    popupTopUp.style.display = "none";
});

let submitRequest = () => {
    let username = addBalanceButton.getAttribute("username");
    let balance = document.querySelector("#banyaknya").value;

    let objek = {
        username: username,
        balance: balance
    };

    let accessToken = (JSON.parse(localStorage.getItem("tokenTroll"))).token;

    let request = new XMLHttpRequest();
    request.open("POST", "http://localhost:8888/api/profile/post");
    request.setRequestHeader("Content-Type", "application/json");
    request.setRequestHeader("Authorization", "Bearer " + accessToken);

    request.send(JSON.stringify(objek));

    request.onload = () => {
            popupTopUp.style.display = "none";
            alert("TopUp successfully!");
            //location.href = `http://localhost:8081/profile/index?username=${username}`;
            location.reload();
        
    };
}


let topupButton = document.querySelectorAll(".topupButton")

topupButton.forEach(topup => {
    topup.addEventListener("click", () => {
        let balance = document.querySelector("#banyaknya");
        balance.value = topup.value;
        submitRequest();
    })
});

// topupButton.forEach(topup => {
//     topup.addEventListener('click',() => {
//         event.preventDefault();

//         let username = addBalanceButton.getAttribute("username");
//         let balance = topup.value;
    
//         let objek = {
//             username: username,
//             balance: balance
//         };
    
//         let accessToken = (JSON.parse(localStorage.getItem("tokenTroll"))).token;
    
//         let request = new XMLHttpRequest();
//         request.open("POST", "http://localhost:8081/api/profile/post");
//         request.setRequestHeader("Content-Type", "application/json");
//         request.setRequestHeader("Authorization", "Bearer " + accessToken);
    
//         request.send(JSON.stringify(objek));
    
//         request.onload = () => {
//                 popupTopUp.style.display = "none";
//                 alert("TopUp successfully!");
//                 //location.href = `http://localhost:8081/profile/index?username=${username}`;
//                 location.reload();
            
//         };
//     }
// )
// })