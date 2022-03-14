package org.bank.bank.models.dtio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private long id;
    private String title;
    private String description;
    private String type;
}
