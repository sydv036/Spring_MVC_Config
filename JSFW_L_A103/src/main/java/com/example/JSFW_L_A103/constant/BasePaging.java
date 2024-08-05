package com.example.JSFW_L_A103.constant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class BasePaging<T> {
    private int pageNumber;
    private int pageSize;
    private int totalPage;
    private List<T> data;

    public BasePaging(Page<T> page) {
        this.pageNumber = page.getNumber() + 1;
        this.pageSize = page.getSize();
        this.totalPage = page.getTotalPages();
        this.data = page.getContent();
    }
}
