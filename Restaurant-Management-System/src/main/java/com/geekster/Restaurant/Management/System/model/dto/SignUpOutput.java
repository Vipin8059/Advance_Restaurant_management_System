package com.geekster.Restaurant.Management.System.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpOutput {
    private Boolean signUpStatus;
    private String signUpStatusMessage;


}
