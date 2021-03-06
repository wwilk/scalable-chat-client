package wilk.chat.client.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wilk.wojtek@gmail.com.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<String> getAllAvailableRecipients(){
        return userService.getAllAvailableContactsExceptForAuthenticatedUser();
    }

    @RequestMapping(value = "/authenticated", method = RequestMethod.GET)
    public String getAuthenticatedUsername(){
        return userService.getAuthenticatedUsername();
    }
}
