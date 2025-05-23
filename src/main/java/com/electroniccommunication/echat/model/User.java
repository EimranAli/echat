package com.electroniccommunication.echat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @NotBlank
    String email;
    @NotBlank
    String firstName;
    @NotBlank
    String lastName;
    @NotBlank
    String hashedPassword;
}
