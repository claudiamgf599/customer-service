package com.trade.customerportfolio.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Builder
public class Customer {
    @Id
    private Integer id;
    private String name;
    private Integer balance;
}
