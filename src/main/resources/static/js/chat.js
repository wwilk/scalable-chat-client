var CHAT = (function(chat){
	var authenticatedUser = 'wojtek';
    var currentlySelectedSender;

    $(function(){
        $.ajaxSetup({contentType: 'application/json'});
        init();
    });

    function init(){
        displayAvailableContacts();
        attachListenerToSendBtn();
    };

    function displayAllMessagesOfContact(contact){
        $.get('message?contact=' + contact, function(messages){
            $('#messages').empty(); // remove all previous messages
            for(var i=0;i<messages.length;i++){
                appendMessage(messages[i]);
            }
        });
    };

    function displayAvailableContacts(){
        $.get('user', function(contacts){
            for(var i=0;i<contacts.length;i++){
                appendContact(contacts[i]);
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
                if(data.senderId === currentlySelectedSender || data.senderId === authenticatedUser){
                	appendMessage(data);
                }
            }, 'json');
        });
    };

    function appendContact(contact){
        var contactEntry = $('<li class="list-group-item list-group-item-warning">'
                      + contact
                      + '</li>');
        $('#contacts').append(contactEntry);
        contactEntry.click(function(){
        	currentlySelectedSender = contact;
            displayAllMessagesOfContact(contact);
        });
    };

    function appendMessage(message){
        if(message.receiverId === authenticatedUser){
            $('#messages').append('<li class="list-group-item list-group-item-success">'
                + '[' + message.senderId + '] '
                + message.content
                + '<div style="float:right">' + formatDate(message.created)
                + '</div></li>');
        } else{
            $('#messages').append('<li class="list-group-item list-group-item-info">'
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