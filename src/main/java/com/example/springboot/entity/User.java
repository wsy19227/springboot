package com.example.springboot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter

@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
   @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
   //@OneToMany(mappedBy = "user",fetch = FetchType.EAGER)

   private List<Address> addresses;
    @Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP",
            insertable = false
            , updatable = false)
    private LocalDateTime insertTime;

    public User(String name) {
        this.name = name;
    }

}
