function deleteUser(login, button) {
    // пока идёт запрос, кнопка заблокирована, её нажимать нельзя
    button.disabled = true;

    var request = new XMLHttpRequest();
    // encodeURIComponent(login) - экранируем login
    request.open("delete", "/api/users?login = " + encodeURIComponent(login), true);

    // обработчик результата:
    request.onreadystatechange = function () {

        if (request.readyState === 4){
            button.disabled = false;

            if (request.status === 200){
                window.alert("User delete: " + request.responseText);
            } else {
                window.alert("Failed to delete user");
            }
        }
    };
    request.send();
}