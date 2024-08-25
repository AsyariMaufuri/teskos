let showpopups = document.querySelectorAll('.showpopup');
let closepopup = document.querySelector('.closePopup');

showpopups.forEach(showpopup => {
    showpopup.addEventListener('click', (event) => {
        event.preventDefault();
        let membershipNumber = showpopup.textContent.trim();
        let popup = document.querySelector('#popup');
        let popupContent = document.querySelector('#popup .popup-content');

        popupContent.innerHTML = '';

        let xhr = new XMLHttpRequest();

        xhr.open('GET', `http://localhost:8081/api/region?membershipNumber=${membershipNumber}`, true);

        xhr.onload = function () {
            // Mengurai data JSON
            let data = JSON.parse(xhr.responseText);

            let membershipNumberElem = document.createElement('p');
            let membershipNumberLabel = document.createElement('strong');
            membershipNumberLabel.textContent = 'Membership Number: ';
            membershipNumberElem.appendChild(membershipNumberLabel);
            membershipNumberElem.appendChild(document.createTextNode(data.membershipNumber));
            popupContent.appendChild(membershipNumberElem);

            let fullNameElem = document.createElement('p');
            let fullNameLabel = document.createElement('strong');
            fullNameLabel.textContent = 'Full Name: ';
            fullNameElem.appendChild(fullNameLabel);
            fullNameElem.appendChild(document.createTextNode(data.firstName + ' ' + data.lastName));
            popupContent.appendChild(fullNameElem);

            let birthDateElem = document.createElement('p');
            let birthDateLabel = document.createElement('strong');
            birthDateLabel.textContent = 'Birth Date: ';
            birthDateElem.appendChild(birthDateLabel);
            birthDateElem.appendChild(document.createTextNode(data.birthDate));
            popupContent.appendChild(birthDateElem);

            let genderElem = document.createElement('p');
            let genderLabel = document.createElement('strong');
            genderLabel.textContent = 'Gender: ';
            genderElem.appendChild(genderLabel);
            genderElem.appendChild(document.createTextNode(data.gender));
            popupContent.appendChild(genderElem);

            let phoneElem = document.createElement('p');
            let phoneLabel = document.createElement('strong');
            phoneLabel.textContent = 'Phone: ';
            phoneElem.appendChild(phoneLabel);
            phoneElem.appendChild(document.createTextNode(data.phone));
            popupContent.appendChild(phoneElem);

            let addressElem = document.createElement('p');
            let addressLabel = document.createElement('strong');
            addressLabel.textContent = 'Address: ';
            addressElem.appendChild(addressLabel);
            addressElem.appendChild(document.createTextNode(data.address));
            popupContent.appendChild(addressElem);

            let closeButton = document.createElement('button');
            closeButton.textContent = 'Close';
            closeButton.className = 'closePopup';
            popupContent.appendChild(closeButton);

            closeButton.addEventListener('click', () => {
                popup.style.display = 'none';
            });

            popup.style.display = 'flex';
        };

        xhr.send();
    });
});

closepopup.addEventListener('click', () => {
    let popup = document.querySelector('#popup');
    popup.style.display = 'none';
    console.log(popup);
});
