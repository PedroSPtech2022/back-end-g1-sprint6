package com.g1.back_end.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "centro_de_custos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CenterCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome do centro de custo não pode ser nulo")
    private String name;
    @NotNull
    private Integer annualBudget;
    @NotBlank(message = "O tipoe não pode ser nulo")
    private String typeCostCenter;

    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    private AreaDomain area;

    @ManyToOne
    @JoinColumn(name= "area_id", referencedColumnName="area_id"  )
    private Employee responsible;

    @OneToMany(mappedBy = "employee")
    @JoinColumn(name= "area_id", referencedColumnName="area_id")
    private List<Employee> employees;

}