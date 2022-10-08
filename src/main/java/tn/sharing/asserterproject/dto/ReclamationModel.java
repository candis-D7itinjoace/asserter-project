package tn.sharing.asserterproject.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ReclamationModel {

    @NotNull(message = "can't be null ")
    private Long userId;
    @NotNull(message = "can't be null ")
    private Long enterpriseId;

    @NotEmpty(message = "title should not be empty")
    private String title;

    @NotEmpty(message = "body of reclamation should not be empty")
    private String body;


}
