let token = document.querySelector(".token")
let form = document.querySelector(".formLogin")
token.addEventListener('click',(event)=>{
    event.preventDefault()
    let username =document.querySelector(".username").value
    let password =document.querySelector(".password").value
    let role =document.querySelector(".role").value
    let request = new XMLHttpRequest();
    let objek = {
        username : username,
        password : password,
        role : role
    }
    request.open("POST",'http://localhost:8081/api/account/authenticate')
    request.setRequestHeader("Content-Type","application/json")
    request.send(JSON.stringify(objek))
    request.onload = () => {
        if(request.status == 200){
            localStorage.setItem("tokenTroll", request.response)
            form.submit()
        }else{
            alert("Username/Password/Role Wrong")
        }

        
    }
}) 