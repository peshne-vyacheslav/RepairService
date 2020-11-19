package com.example.repair.entity;

import com.example.repair.entity.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "USER_ROLE")
public class UserRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "NAME", nullable = false)
    private Role role;

    @CreationTimestamp
    @Column(name = "CREATED", nullable = false)
    private LocalDate created;

    @UpdateTimestamp
    @Column(name = "MODIFIED", nullable = false)
    private LocalDate modified;

    @Column(name = "IS_DELETED", nullable = false)
    private boolean isDeleted = false;

    public UserRole(Role role) {
        this.role = role;
    }
}
