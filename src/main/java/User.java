import javax.persistence.*;
import java.util.List;


@NamedQueries({
        @NamedQuery(name = User.FIND_USER_BY_ID_QUERY, query = "from User where id = :id"),
        @NamedQuery(name = User.FIND_USER_BY_LOGIN_QUERY, query = "from User where login = :login"),
        @NamedQuery(name = User.FIND_USER_BY_EMAIL_QUERY, query = "from User where email = :email"),
        @NamedQuery(name = User.FIND_USERS_QUERY, query = "from User")
})

//@Getter
//@Setter
@Entity
@Table(name = "users")
public class User {

    public static final String FIND_USER_BY_ID_QUERY = "findUserById";
    public static final String FIND_USER_BY_LOGIN_QUERY = "findUserByLogin";
    public static final String FIND_USER_BY_EMAIL_QUERY = "findUserByEmail";
    public static final String FIND_USERS_QUERY = "findAllUsers";

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq")
    @Column(name = "user_id")
    private long userID;

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Message> messageList;

    public User() {}

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getUserID() {
        return userID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
