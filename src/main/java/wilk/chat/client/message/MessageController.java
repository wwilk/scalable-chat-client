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
    public List<Message> getAllMessages(){
        return messageService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Message createIncoming(@RequestBody Message message){
        message.setCreated(new Date());
        return messageService.create(message);
    }
}
