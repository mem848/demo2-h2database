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
@Table(name="pep_material")
public class MaterialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @SerializedName(value = "MaterialID", alternate = "materialId")
    @Column(name="id")
    public Integer id;

    @Column(name="created_at")
    @CreationTimestamp
    public Date createdAt;

    @Column(name="length")
    public double length;

    @Column(name="width")
    public double width;

    @Column(name="sqft_per_gallon")
    private double sqftPerGallon;

    @Column(name="gallons_required")
    public double gallonsRequired;
}
