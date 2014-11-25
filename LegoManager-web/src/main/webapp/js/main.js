function create() {
    $(function () {
        $("#add").dialog({
            resizable: false,
            height: 500,
            modal: true,
            buttons: {
                "Delete all items": function () {
                    $(this).dialog("close");
                },
                Cancel: function () {
                    $(this).dialog("close");
                }
            }
        });
    });
}