package com.example.SpringMVC_Project.model.dto;

import com.example.SpringMVC_Project.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ItemDTO {
    @NotBlank(message = "Invalid Code: Empty code")
    @NotNull(message = "Invalid Code: Code is NULL")
    private String itemCode;
    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    @Size(min = 5, max = 15, message = "Invalid Name: Must be of 3 - 30 characters")
    private String product;
}
