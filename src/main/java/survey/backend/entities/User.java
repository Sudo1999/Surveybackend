package survey.backend.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

// @Table => changée pour les tests en @Table(name = "users") et à remettre en place pour que ça fonctionne
// (sauf que dans le projet on a continué avec => ça restera comme ça)
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column
    private String userName;
    @Column
    private String userPass;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserRole> userRoles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) { this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }
    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }
    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
        for (UserRole role : userRoles) {
            role.setUser(this);
        }
    }
}