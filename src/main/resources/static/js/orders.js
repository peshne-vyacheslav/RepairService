let selectedRow = null;
initOrdersTable();

async function initOrdersTable() {
    await loadOrders();
    $('#orders-table.selectable > tbody > tr').click((e) => selectRow(e.currentTarget));
    $("#delete-order-btn").click(() => deleteRow());
    $("#edit-order-btn").click(() => showEditOrderDialog());
    $("#create-order-dialog .save-order-btn").click(() => addOrder());
    $("#edit-order-dialog .save-order-btn").click(() => editOrder());

}

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
    }
}

async function addOrder() {
    let description = $("#create-order-dialog textarea").val();
    if (description === undefined || description === "") {
        alert("description can't be empty");
        return;
    }
    let orderData = {
        description: description
    };
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
        $('#orders-table .selectable > tbody').last().click((e) => selectRow(e.currentTarget));
     }
}

async function editOrder() {
    let description = $("#edit-order-dialog textarea").val();
    if (description === undefined || description === "") {
        alert("description can't be empty");
        return;
    }

    let orderData = {
        description: description
    };
    let response = await fetch("api/orders/" + getOrderId(selectedRow), {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(orderData)
    });

    if (response.ok) {
        setOrderDescription(selectedRow, description);
        $("#edit-order-dialog").modal('hide');
    }
}

async function deleteRow() {
    if (selectedRow == null) {
        return;
    }
    let deleteButton = $('.delete-btn');
    deleteButton.prop('disabled', true);
    let orderId = getOrderId(selectedRow);
    let response = await fetch("api/orders/" + orderId, {
        method: 'DELETE'
     });
     if (response.ok) {
        selectedRow.remove();
        selectedRow == null;
     }
     selectedRow.remove();
     selectedRow == null;

     deleteButton.prop('disabled', false);
     deleteButton.blur();
}

function showEditOrderDialog() {
    if (selectedRow === null) {
            return;
    }

    $("#edit-order-form #order-id").val(getOrderId(selectedRow));
    $("#edit-order-form textarea").val(getOrderDescription(selectedRow));
    $("#edit-order-form #user").val(getOrderUser(selectedRow));
    $("#edit-order-form #created").val(getOrderCreated(selectedRow));
    $("#edit-order-dialog").modal('show');
}

function getOrderId(row) {
    return row.find(">:first-child").text();
}

function getOrderDescription(row) {
    return row.find(">:nth-child(2)").text();
}

function setOrderDescription(row, value) {
    return row.find(">:nth-child(2)").text(value);
}

function getOrderUser(row) {
    return row.find(">:nth-child(3)").text();
}

function getOrderCreated(row) {
    return row.find(">:nth-child(4)").text();
}


function selectRow(row) {
    if (selectedRow !== null) {
        selectedRow.removeClass("selected");
        if (getOrderId(selectedRow) === getOrderId($(row))) {
            selectedRow = null;
            return;
        }
        selectedRow = $(row);
        selectedRow.addClass("selected");
    }

    selectedRow = $(row);
    selectedRow.addClass("selected");
}


function showCreateOrderDialog() {
    $('#myModal').on('shown.bs.modal', function () {
      $('#myInput').trigger('focus')
    })
}






