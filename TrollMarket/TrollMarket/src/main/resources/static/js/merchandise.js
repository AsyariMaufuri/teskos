document.addEventListener('DOMContentLoaded', (event) => {
    let popupProduct = document.querySelector('.popupProduct');
    let showPopUps = document.querySelectorAll('.infoProduct');
    let closePopup = document.querySelector('.closeButton');

    popupProduct.style.display = "none";

    showPopUps.forEach(showPopUp => {
        showPopUp.addEventListener('click', () => {
            popupProduct.style.display = "block";
            let productId = showPopUp.getAttribute("product-id");
            let accessToken = JSON.parse(localStorage.getItem("tokenTroll")).token;
            let request = new XMLHttpRequest();
            request.open('GET', `http://localhost:8081/api/merchandise/get?productId=${productId}`);
            request.setRequestHeader("Authorization", "Bearer " + accessToken);
            request.send();
            request.onload = () => {
                
                    let product = JSON.parse(request.response);
                    document.querySelector('.textName').innerText = product.productName;
                    document.querySelector('.textCategory').innerText = product.category;
                    document.querySelector('.textDescription').innerText = product.description;
                    document.querySelector('.textPrice').innerText = product.price;
                    document.querySelector('.textDiscontinue').innerText = product.discontinue ? 'Yes' : 'No';
                
            };
        });
    });

    closePopup.addEventListener('click', () => {
        popupProduct.style.display = "none";
    });
});
