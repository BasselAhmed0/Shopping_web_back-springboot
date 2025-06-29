package com.shop.shop.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "name",unique = true)
    private String name;

    @Column(name = "password")
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "parent_id")
    @JsonIgnoreProperties({"parent", "children"})
    private List<User> children;

    @ManyToOne
    @JsonBackReference
    @JsonIgnoreProperties({"parent", "children"})
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    private User parent;

    @Column(name = "parent_id")
    private Long parent_id;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();

//    public User(String username, String password, String email,) {
//        this.name = username;
//        this.password = password;
//        this.email = email;
//    }

//    public Long getParen_id() {
//        if (parent != null) {
//            this.parent_id = parent.getId();
//        }
//        return parent_id;
//    }
//
//    public void setParen_id(Long paren_id) {
//        this.parent_id = paren_id;
//    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email='" + email + '\'' + ", name='" + name + '\'' + ", password='" + password + '\'' + ", children=" + children + ", parent=" + parent_id + '}';
    }

    public void addRole(Role role) {
        roles.add(role);
    }
}
