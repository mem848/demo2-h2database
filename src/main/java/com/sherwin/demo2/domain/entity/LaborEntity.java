package com.sherwin.demo2.domain.entity;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="pep_labor")
public class LaborEntity { //this represents one entry in the labor repository
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@SerializedName(value = "LaborId", alternate = "LaborId")
    @Column(name="id")
    public Integer id;

    @Column(name="created_at")
    @CreationTimestamp
    public Date createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp
    public Date updatedAt;

    @Column(name="length")
    public double length;

    @Column(name="width")
    public double width;

    @Column(name="price_per_sqft")
    private double pricePerSqft;

    @Column(name="labor_cost")
    public double cost;

}
