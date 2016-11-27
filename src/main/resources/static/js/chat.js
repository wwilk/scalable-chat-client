var CHAT = (function(chat){
    var senderId = "wojtek";

    $(function(){
        $.ajaxSetup({contentType: 'application/json'});
        init();
    });

    function init(){
        displayAllMessages();
        attachListenerToSendBtn();
    };

    function displayAllMessages(){
        $.get('message', function( data ) {
            for(var i=0;i<data.length;i++){
                appendMessage(data[i]);
            }
        });
    };

    function attachListenerToSendBtn(){
        $('#send-btn').click(function(){
            message = {
                senderId: $('#sender-id').val(),
                receiverId: $('#receiver-id').val(),
                content: $('#content').val()
            };
            $.post('message', JSON.stringify(message), function(data){
                console.log('message successfully sent ' + JSON.stringify(data));
                appendMessage(data);
            }, 'json');
        });
    };

    function appendMessage(message){
        if(message.receiverId === senderId){
            $('.list-group').append('<li class="list-group-item list-group-item-success">'
                + '[' + message.senderId + '] '
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