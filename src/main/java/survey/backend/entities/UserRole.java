package survey.backend.entities;

import javax.persistence.*;

@Table
@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column
    private String role;
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) { this.role = role; }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
