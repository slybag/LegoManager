$(document).ready(function(){                          

            $("#add").dialog({
                resizable: false,
                height: 'auto',
                maxHeight: 800,
                width: 'auto',
                modal: true,
                show: 'clip',
                dialogClass: 'info',
                autoOpen: false
            });
            
            $('.dialog_button').click(function(){
                $('#add').dialog('open');  
            });
            
            $("#message_container").dialog({
                resizable: false,
                height: 'auto',
                width: 'auto',
                modal: true,
                dialogClass: 'info'
            });
            
            function showMessage() {
                $('#message_container').dialog('open');  
            }
});