package wilk.chat.client.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by wilk.wojtek@gmail.com.
 */
@RequestMapping("/message")
@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Message> getAllMessages(@RequestParam String contact,
                                        @RequestParam boolean withPolling){
        if(withPolling) {
            messageService.pollForMessages();
        }
        return messageService.findAllForContact(contact);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Message createIncoming(@RequestBody Message message){
        return messageService.create(message);
    }
}
