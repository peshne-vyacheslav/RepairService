package com.example.repair.entity;

import com.example.repair.entity.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Entity
@Table(name = "USERS_")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LOGIN", nullable = false)
    private String login;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "NAME", nullable = false)
    private String name;

    @CreationTimestamp
    @Column(name = "CREATED", nullable = false)
    private LocalDate created;

    @UpdateTimestamp
    @Column(name = "MODIFIED", nullable = false)
    private LocalDate modified;

    @Column(name = "IS_DELETED", nullable = false)
    private boolean isDeleted = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USERS_TO_USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ROLE_ID")
    )
    private Set<UserRole> userRoles;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders;

    public User(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }

    public Set<Role> getRoles() {
        if (CollectionUtils.isEmpty(userRoles)) {
            return Collections.emptySet();
        }
        return userRoles.stream()
                .map(UserRole::getRole)
                .collect(Collectors.toSet());
    }
}
