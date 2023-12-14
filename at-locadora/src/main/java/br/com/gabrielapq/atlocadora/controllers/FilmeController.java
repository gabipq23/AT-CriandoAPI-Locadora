package br.com.gabrielapq.atlocadora.controllers;

import br.com.gabrielapq.atlocadora.model.Filme;
import br.com.gabrielapq.atlocadora.service.FilmeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/filme")
public class FilmeController {
    @Autowired
    FilmeService filmeService;
    Logger logger = LoggerFactory.getLogger(FilmeController.class);

    @GetMapping
    public List<Filme> getAll(
            @RequestParam (required = false, defaultValue = "13") int size,
            @RequestParam (required = false, defaultValue = "") String sort,
            @RequestParam (required = false, defaultValue = "") String order
            ) {
        logger.info("Get all filmes");
        return filmeService.getAll(size, sort,order);
    }

    @GetMapping("/{id}")
    public ResponseEntity getFilmeById(@PathVariable int id) {
        try {
            logger.info("Get filme com id " + id);
            Filme filme = filmeService.getById(id);
            return ResponseEntity.ok(filme);
        } catch (Exception e){
            logger.error("Erro ao encontrar filme: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao encontrar filme. Código de erro 500.");
        }
    }

    @PostMapping
    public ResponseEntity<String> addFilme(@RequestBody Filme filme) {
        try {
            logger.info("Post filme " + filme);
            filmeService.save(filme);
            return ResponseEntity.ok("Filme adicionado com sucesso. Código 200.");
        } catch (Exception e) {
            logger.error("Erro ao adicionar filme: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar filme. Código de erro 500.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFilme(@PathVariable int id, @RequestBody Filme filme) {
        try {
            logger.info("Put filme " + id + " nome alterado " + filme);
            filmeService.update(id, filme);
            return ResponseEntity.ok("Filme atualizado com sucesso. Código 200.");
        } catch (Exception e) {
            logger.error("Erro ao atualizar filme: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar filme. Código de erro 500.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFilme(@PathVariable int id) {
        try {
            logger.info("Delete filme " + id);
            Filme removed = filmeService.delete(id);
            logger.info("Filme deletado " + removed);
            return ResponseEntity.ok("Filme deletado com sucesso.");
        } catch (Exception e) {
            logger.error("Erro ao excluir filme: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir filme. Código de erro 500.");
        }
    }
}