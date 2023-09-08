package com.sherwin.demo2.domain.entity;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="pep_labor")
public class LaborEntity { //this represents one entry in the labor repository
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SerializedName(value = "LaborId", alternate = "LaborId")
    @Column(name="Id")
    public Integer Id;

    @Column(name="CreatedAt")
    @CreationTimestamp
    public Date time_stamp;

    @Column(name="Length")
    public double length;

    @Column(name="Width")
    public double width;

    @Column(name="price_per_sqft")
    private double pricePerSqft;

    @Column(name="labor_cost")
    public double cost;

}
