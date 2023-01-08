package com.vector.megumin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="megumins")
public class Megumin {
    private @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
    private String imageURL;
}
