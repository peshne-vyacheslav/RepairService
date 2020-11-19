loadOrders();

async function loadOrders() {
    let response = await fetch("api/orders/");
    if (response.ok) {
        let orders = await response.json();
        var tableBody = $('#orders-table > tbody');
        for(let order of orders) {
            tableBody.last().append("<tr><td>" + order.id
                            + "</td><td>" + order.description
                            + "</td><td>" + order.user
                            + "</td><td>" + order.created
                            + "</td></tr>");

        }
    } else {
        console.log("error " + response.status);
    }
}

$("#create-order-btn").click(()=>addOrder());

async function addOrder() {
    let description = $("#description").val();
    if (description === undefined || description === "") {
        alert("enter description");
        return;
    }
    let orderData = {description:description}
    let response = await fetch("api/orders/", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(orderData)
    });
    if (response.ok) {
        var tableBody = $('#orders-table > tbody');
        let order = await response.json();
        $("#create-order-dialog").modal('hide');
        tableBody.last().append("<tr><td>" + order.id
                                    + "</td><td>" + order.description
                                    + "</td><td>" + order.user
                                    + "</td><td>" + order.created
                                    + "</td></tr>");
     } else {
            console.log("error " + response.status);
     }
}



function showCreateOrderDialog() {
    $('#myModal').on('shown.bs.modal', function () {
      $('#myInput').trigger('focus')
    })
}






