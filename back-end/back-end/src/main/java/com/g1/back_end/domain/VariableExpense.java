package com.g1.back_end.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "variable_expense")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VariableExpense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_variable")
    private String type;
    @NotNull(message = "a categoria não pode estar vazio")
    private String category;
    @NotNull(message = "o metodo de pagamento não pode estar vazio")
    private String paymentMethod;
    @NotNull(message = "nome do funcionario não pode estar vazio")
    private Boolean approval;
    @NotBlank
    private BigDecimal value;
    @DateTimeFormat
    private LocalDate date;
    private String describer;
    private String observation;

    @ManyToOne
    @JoinColumn(name = "cost_center_id", nullable = false)
    private CenterCost costCenter;

    @OneToOne
    private Employee responsibile;
}
