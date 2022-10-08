package tn.sharing.asserterproject.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AddOwnerModel {

    @NotBlank(message = "first name is mandatory")
    @Size(max = 50)
    private String firstName;

    @NotBlank(message = "last name is mandatory")
    @Size(max = 50)
    private String lastName;

    @NotBlank(message = "password is mandatory")
    @Size(max = 150)
    private String password;

    @NotBlank(message = "matching password is mandatory")
    @Size(max = 150)
    private String matchingPassword;

    @NotBlank(message = "email is mandatory")
    @Size(max = 120)
    @Email
    private String email;

    @NotNull(message = "you need to add your first enterprise")
    @Valid
    private EnterpriseDto enterpriseModel;
}
