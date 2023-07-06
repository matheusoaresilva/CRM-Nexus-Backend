package com.matheus.crm.order.entity;


import com.matheus.crm.order.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "requested_date")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT-3")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime requestedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_order")
    private Status status;

    @Column(name = "shipping_company_id")
    @NotNull
    private Long shippingCompanyId;

    @Column(nullable = false)
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<SaleItem> items = new ArrayList<>();

}
