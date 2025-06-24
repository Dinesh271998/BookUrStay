package com.practice.BookUrStay.Model;

import com.practice.BookUrStay.Model.Enum.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Guest")
public class Guest extends BaseModel {

    private String name;
    private String email;
    private Gender gender;
    private String phoneNumber;
    private Integer age;
    private String address;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Assuming Guest is linked to a User entity
}
