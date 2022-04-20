package topjava.quest.model;

import java.util.*;

public class User extends AbstractNamedEntity{

    private String email;

    private String password;

    private Set<Role> roleSet;

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
}
