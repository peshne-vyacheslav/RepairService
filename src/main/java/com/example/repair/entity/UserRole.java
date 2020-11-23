package com.example.repair.entity;

import com.example.repair.entity.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "USER_ROLE")
@Where(clause="is_deleted=false")
@SQLDelete(sql = "UPDATE USER_ROLE SET is_deleted = true WHERE id = ?", check = ResultCheckStyle.COUNT)
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
