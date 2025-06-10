package com.ecom.Sara.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Pattern;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String brand;
    private String category;
    private String description;
    private BigDecimal price;
    private int stockQuantity;
    private Date releaseDate;
    private Boolean productAvailable;
    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageData;

}
