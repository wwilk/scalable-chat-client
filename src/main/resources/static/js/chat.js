var CHAT = (function(chat){
    $(function(){
        $.ajaxSetup({contentType: 'application/json'});
        init();
    });

    function init(){
        displayAllMessages();
    };

    function displayAllMessages(){
        $.get('message', function( data ) {
            for(var i=0;i<data.length;i++){
                appendMessage(data[i]);
            }
        });
    };

    function appendMessage(message){
        if(message.receiverId){
            $('.list-group').append('<li class="list-group-item list-group-item-success">'
                + '[' + message.receiverId + '] '
                + message.content
                + '<div style="float:right">' + formatDate(message.created)
                + '</div></li>');
        } else{
            $('.list-group').append('<li class="list-group-item list-group-item-info">'
                + '[' + message.senderId + '] '
                + message.content
                + '<div style="float:right">' + formatDate(message.created)
                + '</div></li>');
        }
    };

    function formatDate(epochDate){
        return new Date(epochDate);
    };

    return {};
})(CHAT || {});