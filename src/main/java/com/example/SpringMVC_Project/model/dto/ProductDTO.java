package com.example.SpringMVC_Project.model.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductDTO {
    private Integer product_id;
    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    @Size(min = 5, max = 15, message = "Invalid Name: Must be of 3 - 30 characters")
    private String name;
    private String category;
    private Integer price;
}
