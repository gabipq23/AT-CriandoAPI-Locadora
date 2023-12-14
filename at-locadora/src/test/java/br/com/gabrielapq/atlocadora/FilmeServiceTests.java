package br.com.gabrielapq.atlocadora;

import br.com.gabrielapq.atlocadora.controllers.FilmeController;
import br.com.gabrielapq.atlocadora.model.Filme;
import br.com.gabrielapq.atlocadora.service.FilmeService;
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
    public void testGetAll(){
        logger.info("Teste do método getAll");
        List<Filme> filmes = filmeService.getAll();
        assertEquals(14,filmes.size());
    }

    @Test
    public void testByIdNaoExistente(){
        logger.info("Teste do método getById");
        assertThrows(RuntimeException.class,()-> {
            filmeService.getById(15);
        });
    }

    @Test
    public void testAdd(){
        logger.info("Teste do método save");
        Filme filme = new Filme();
        Filme novoFilmeAdd = filmeService.save(filme);
        assertEquals(filme,novoFilmeAdd);
    }

    @Test
    public void testUpdate(){
        logger.info("Teste do método update");
        Filme filme = new Filme();
        filme.setId(50);
        assertThrows(RuntimeException.class,()-> {
            filmeService.update(50, filme);
        });
    }

    @Test
    public void testDelete(){
        logger.info("Teste do método delete");
        assertThrows(RuntimeException.class,()->{
            filmeService.delete(50);
        });
    }
}