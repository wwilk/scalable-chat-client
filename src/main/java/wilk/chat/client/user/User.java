package wilk.chat.client.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Created by wilk.wojtek@gmail.com.
 */
@Entity
public class User {

    @Id
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
