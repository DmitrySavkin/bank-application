package org.bank.bank.models.paging;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Data
@NoArgsConstructor
public class Page<T> {

    private List<T> data;
    private int recordsFiltered;
    private int recordsTotal;
    private int draw;

    public Page(List<T> data) {
        this.data = data;
    }
}
