package com.n11.UserApp.dao.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @NotBlank(message = "Name cannot be blank")
    @NotEmpty(message = "Name cannot be empty")
    @NotNull
    @Column(name = "name", columnDefinition = "text", nullable = false)
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    @NotEmpty(message = "Surname cannot be empty")
    @NotNull
    @Column(name = "surname", columnDefinition = "text", nullable = false)
    private String surname;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotNull(message = "Latitude cannot be null")
    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @NotNull(message = "Longitude cannot be null")
    @Column(name = "longitude", nullable = false)
    private Double longitude;
}
