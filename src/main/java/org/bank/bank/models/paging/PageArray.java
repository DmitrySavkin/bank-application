package org.bank.bank.models.paging;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class PageArray {

    private List<List<String>> data;
    private int recordsFiltered;
    private int recordsTotal;
    private int draw;
}
