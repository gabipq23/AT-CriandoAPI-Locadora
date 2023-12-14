package br.com.gabrielapq.atlocadora.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data@AllArgsConstructor@NoArgsConstructor
public class Filme {
    private int id;
    private String titulo;
    private String ano;
    private Producao producao;
    private List<String> elenco;
}
