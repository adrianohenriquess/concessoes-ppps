package br.gov.sp.tce.concessoes.ppps.rest.dto;

import java.time.LocalDate;

import javax.json.bind.annotation.JsonbDateFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConcessaoDTO {

    private Long id;

    private String descricao;

    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate dataInicial;

    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate dataFinal;
}
