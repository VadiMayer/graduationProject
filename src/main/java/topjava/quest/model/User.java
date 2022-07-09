package topjava.quest.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "users")
public class User extends AbstractNamedEntity{

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank
    @Email
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 128)
    private String password;

    //Будем enum сохранять как стинги
    @Enumerated(EnumType.STRING)
    //user_roles связывается с user_id
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roleSet;

    public User() {
    }

    public User(User user) {
        this(user.id, user.name, user.email, user.password, user.roleSet);
    }

    public User(Integer id, String name, String email, String password, Role... roleSet) {
        super(id, name);
        this.email = email;
        this.password = password;
        setRoleSet(List.of(roleSet));
    }

    public User(Integer id, String name, String email, String password, Collection<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        setRoleSet(roles);
    }

    public Role getRole() {
        Role role = roleSet.stream().filter(el -> el.equals(Role.ADMIN)).findFirst().get();
        if (role == Role.ADMIN)
            return role;
        else
            return Role.USER;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Collection<Role> roleSet) {
        this.roleSet = roleSet.isEmpty() ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roleSet);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roleSet=" + roleSet +
                '}';
    }
}
