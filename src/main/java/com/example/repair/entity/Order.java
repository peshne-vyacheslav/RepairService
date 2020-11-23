package com.example.repair.entity;

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
@Table(name = "ORDERS")
@Where(clause="is_deleted=false")
@SQLDelete(sql = "UPDATE ORDERS SET is_deleted = true WHERE id = ?", check = ResultCheckStyle.COUNT)
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @CreationTimestamp
    @Column(name = "CREATED", nullable = false)
    private LocalDate created;

    @UpdateTimestamp
    @Column(name = "MODIFIED", nullable = false)
    private LocalDate modified;

    @Column(name = "IS_DELETED", nullable = false)
    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Order(String description, User user) {
        this.description = description;
        this.user = user;
    }
}
