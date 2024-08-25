let popupCart = document.querySelector(".popUpAddCart");
popupCart.style.display = "none";

let addToCard = document.querySelectorAll(".addToCard");

// Function to fetch and populate the shipper options
let OptionForEach = () => {
    let accessToken = (JSON.parse(localStorage.getItem("tokenTroll"))).token;
    let request = new XMLHttpRequest();
    request.open("GET", "http://localhost:8081/api/shop/get");
    request.setRequestHeader("Authorization", "Bearer " + accessToken);
    request.send();
    request.onload = () => {
        let optionContainer = document.querySelector(".shipperInput");
        optionContainer.innerHTML = ""; // Clear previous options
        let shippers = JSON.parse(request.response);
        shippers.forEach(({ shipperName, price }) => {
            let newOption = document.createElement('option');
            newOption.textContent = `${shipperName} - ${price}`;
            newOption.value = shipperName;
            optionContainer.appendChild(newOption);
        });
    };
};

let closeButton = popupCart.querySelector("button[type='button']");

// Event listener for each "Add to Cart" button
addToCard.forEach(button => {
    button.addEventListener('click', () => {
        // Tampilkan popup
        popupCart.style.display = "block";
        // Panggil function untuk populate options jika diperlukan
        OptionForEach();
        let productId = document.querySelector(".product-id")
        productId.setAttribute("value",button.getAttribute("product-idd"));
        let username = document.querySelector(".id");
        username.setAttribute("value",button.getAttribute("id"))
        
    });
    
});

// Handle form submission
let submitButton = popupCart.querySelector("button[type='submit']");

        submitButton.addEventListener('click', (event) => {
            event.preventDefault(); // Mencegah form submit default
            let productId = document.querySelector(".product-id").value;
            let username = document.querySelector(".id").value;
            let quantity = document.querySelector(".quantityInput").value;
            let shipper = document.querySelector(".shipperInput").value;
            let note = document.querySelector(".noteInput").value;   
            console.log(note);
                     
        
            // Validasi form (pastikan semua field diisi)
            if (quantity && shipper) {
                let objek = {
                    username: username,
                    shipperName: shipper,
                    productId: productId,
                    quantity: quantity,
                    note : note
                };
                console.log(objek);
                
        
                let accessToken = (JSON.parse(localStorage.getItem("tokenTroll"))).token;
                let request = new XMLHttpRequest();
                request.open("POST", "http://localhost:8081/api/shop/post");
                request.setRequestHeader("Content-Type", "application/json");
                request.setRequestHeader("Authorization", "Bearer " + accessToken);
                request.send(JSON.stringify(objek));
                request.onload = () => {
                    popupCart.style.display = "none";
                    alert("Cart added successfully!");
                    window.location.reload(); // Reload halaman setelah data dikirim
                };
            } else {
                alert("Please fill in all fields before submitting.");
            }
        });

// Handle close button click
closeButton.addEventListener('click', () => {
    popupCart.style.display = "none"; // Sembunyikan popup saat tombol Close diklik
});