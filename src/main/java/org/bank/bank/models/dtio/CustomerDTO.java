package org.bank.bank.models.dtio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String passNumber;


}
