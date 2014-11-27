function create() {
    $("#add").show();
    $(function () {
        $("#add").dialog({
            resizable: false,
            height: 500,
            width: 'auto',
            modal: true,
        });
    });
}

function showMessage() {
    $("#message_container").show();
    $(function () {
        $("#message_container").dialog({
            resizable: false,
            height: 'auto',
            width: 'auto',
            modal: true,
        });
    });
}