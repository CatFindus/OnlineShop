package ru.puchinets.userservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "name")
@Table(name = "roles", schema = "user_management")
public class Role extends BaseEntity {
    @Id
    @SequenceGenerator(name = "role_seq", allocationSize = 1, sequenceName = "roles_id_seq", schema = "user_management")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    private Integer id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
}
