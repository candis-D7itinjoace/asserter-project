package tn.sharing.asserterproject.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class EnterpriseDto {

    @NotBlank(message = "enterprise name is mandatory")
    @Size(max = 50)
    private String name;


    @NotBlank(message = "enterprise description is mandatory")
    private String description;

    @NotBlank(message = "enterprise phone number is mandatory")
    private String phoneNumber;

}
