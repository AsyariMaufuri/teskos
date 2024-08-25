let popupShipper = document.querySelector(".popup-shipper");
popupShipper.style.display = "none";

let addShipperButton = document.querySelector(".add-button")



addShipperButton.addEventListener('click',() => {
    document.querySelector(".inputShipperName").value = ""
    document.querySelector(".inputPrice").value = ""
    document.querySelector(".inputService").value = false
    popupShipper.style.display = "block";
    let accessToken = (JSON.parse(localStorage.getItem("tokenTroll"))).token;

    let request = new XMLHttpRequest();
    request.open("GET","http://localhost:8081/api/shipment/get")
    request.setRequestHeader("Authorization", "Bearer " + accessToken)
    request.send()
    request.onload= () => {
    }
})


let editShipper = document.querySelectorAll(".editShipper");

editShipper.forEach(element => {
    element.addEventListener('click',()=>{
        popupShipper.style.display = "block";
        let shipperByName = element.getAttribute("shipper-name")
        let accessToken = (JSON.parse(localStorage.getItem("tokenTroll"))).token;

        let request = new XMLHttpRequest();
        console.log(shipperByName);
        request.open("GET",`http://localhost:8081/api/shipment/get?shipperName=${shipperByName}`)
        request.setRequestHeader("Authorization", "Bearer " + accessToken)
        request.send()
        request.onload= () => {
            console.log(request.response);
            let shipper = JSON.parse(request.response)
            let shipperName = shipper.shipperName
            let price = shipper.price
            let service = shipper.service
            

            document.querySelector(".inputShipperName").value = shipperName
            document.querySelector(".inputPrice").value = price
            document.querySelector(".inputService").checked = service
            
        }
    })
    
});


let submit = document.querySelector(".submitPopup")

submit.addEventListener('click',(event)=>{
    event.preventDefault();
    let shipperName = document.querySelector(".inputShipperName").value
    let price = document.querySelector(".inputPrice").value
    let service = document.querySelector(".inputService").checked
    
    let objek = {
        shipperName : shipperName,
        price : price,
        service : service
    }
    let accessToken = (JSON.parse(localStorage.getItem("tokenTroll"))).token;
    let request = new XMLHttpRequest();
    request.open("POST","http://localhost:8081/api/shipment/post")
    request.setRequestHeader("Content-Type","application/json")
    request.setRequestHeader("Authorization", "Bearer " + accessToken)
    request.send(JSON.stringify(objek))
    request.onload= () => {
        if(request.status === 422){
            typeof(request.response)
            console.log(JSON.parse(request.response));
            populateErrors(JSON.parse(request.response));
        }else if(request.status === 200){   
            popupShipper.style.display = "none";
            alert("Shipment added/edited successfully!");
            location.href = "http://localhost:8081/shipment/index";
        }
        
    }

})

let closepopup = document.querySelector(".closePopup")
    closepopup.addEventListener('click', (event) => {
        event.preventDefault();
        popupShipper.style.display = "none";
        
    })

let populateErrors = (error) => {
        for(let i = 0; i < error.length; i++){
            // if((error[i].code != null && error[i].code == "UniqueCategoryName") || error[i].field == "categoryName"){
            //     let categoryErrorField = document.querySelector("#categoryName");
            //     categoryErrorField.textContent = error[i].defaultMessage;
            // }
            if(error[i].field == "price"){
                let floorErrorField = document.querySelector(".inputPrice + .field-validation-error");
                floorErrorField.textContent = error[i].defaultMessage;
            }
            // if(error[i].field == "isle"){
            //     let isleErrorField = document.querySelector("#isle + .field-validation-error");
            //     isleErrorField.textContent = error[i].defaultMessage;
            // }
            // if(error[i].field == "bay"){
            //     let bayErrorField = document.querySelector("#bay + .field-validation-error");
            //     bayErrorField.textContent = error[i].defaultMessage;
            // }
        }
        setTimeout(clearAllErrorFields, 3000);
};