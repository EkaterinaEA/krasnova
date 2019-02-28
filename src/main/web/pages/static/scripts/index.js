function deleteUser(login, button) {
    button.disabled = true;

    var request = new XMLHttpRequest();
    request.open("delete", "/api/users?login = " + encodeURIComponent(login), true);

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