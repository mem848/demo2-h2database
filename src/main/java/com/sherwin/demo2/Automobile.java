package com.sherwin.demo2;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Automobile {
    @Min(1)
    private int numberOfWheels;
    @NotEmpty()
    private String color;
    @Email()
    private String ownerEmailAddress;
}
