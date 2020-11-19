loadUsers();

async function loadUsers() {
    let response = await fetch("api/users/");
    if (response.ok) {
        let users = await response.json();
        var tableBody = $('#users-table > tbody');
        for(let user of users) {
            tableBody.last().append("<tr><td>" + user.name
                            + "</td><td>" + user.roles
                            + "</td><td>" + user.created
                            + "</td></tr>");
        }
    } else {
        console.log("error " + response.status);
    }
}
