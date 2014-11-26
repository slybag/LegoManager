function create() {
    $("#add").show();
    $(function () {
        $("#add").dialog({
            resizable: false,
            height: 500,
            width: 500,
            modal: true,
        });
    });
}