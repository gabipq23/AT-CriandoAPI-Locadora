package br.com.gabrielapq.atlocadora;

import br.com.gabrielapq.atlocadora.controllers.FilmeController;
import br.com.gabrielapq.atlocadora.model.Filme;
import br.com.gabrielapq.atlocadora.service.FilmeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FilmeServiceTests {

    Logger logger = LoggerFactory.getLogger(FilmeController.class);
    @Autowired
    FilmeService filmeService;

    @Test
    @DisplayName("Teste do método getAll")
    public void testGetAll(){
        logger.info("Faz o teste a partir da quantidade de itens na lista de filmes.");
        List<Filme> filmes = filmeService.getAll();
        assertEquals(14,filmes.size());
    }

    @Test
    @DisplayName("Teste do método getById")
    public void testByIdNaoExistente(){
        logger.info("Faz o teste buscando um id que não existe na lista.");
        assertThrows(RuntimeException.class,()-> {
            filmeService.getById(15);
        });
    }

    @Test
    @DisplayName("Teste do método save")
    public void testAdd(){
        logger.info("Faz o teste pra conferir se o filme esta corretamente adicionado.");
        Filme filme = new Filme();
        Filme novoFilmeAdd = filmeService.save(filme);
        assertEquals(filme,novoFilmeAdd);
    }

    @Test
    @DisplayName("Teste do método update")
    public void testUpdate(){
        logger.info("Faz o teste para sinalizar que ocorre erro ao tentar atualizar um filme que não existe na lista, a partir do id.");
        Filme filme = new Filme();
        filme.setId(50);
        assertThrows(RuntimeException.class,()-> {
            filmeService.update(50, filme);
        });
    }

    @Test
    @DisplayName("Teste do método delete")
    public void testDelete(){
        logger.info("Faz o teste para sinalizar que ocorre erro ao tentar um filme que não existe na lista, a partir do id.");
        assertThrows(RuntimeException.class,()->{
            filmeService.delete(50);
        });
    }
}