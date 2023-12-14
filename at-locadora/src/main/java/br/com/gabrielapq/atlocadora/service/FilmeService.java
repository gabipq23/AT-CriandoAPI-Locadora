package br.com.gabrielapq.atlocadora.service;

import br.com.gabrielapq.atlocadora.controllers.FilmeController;
import br.com.gabrielapq.atlocadora.model.Filme;
import br.com.gabrielapq.atlocadora.model.Producao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class FilmeService {
    Logger logger = LoggerFactory.getLogger(FilmeController.class);
    private List<Filme> filmes = initFilmes();
    private List<Filme> initFilmes() {
        logger.info("Get all");
        Producao producao1 = new Producao("Matthew Diamond","The Walt Disney Company");
        Filme filme1 = new Filme(1,"Camp Rock", "2008", producao1, Arrays.asList("Joe Jonas", "Demi Lovato"));

        Producao producao2 = new Producao("Kenny Ortega","The Walt Disney Company");
        Filme filme2 = new Filme(2,"High School Musical","2006", producao2, Arrays.asList("Zac Efron", "Ashley Tisdale"));

        Producao producao3 = new Producao("James Cameron","Lightstorm Entertainment");
        Filme filme3 = new Filme(3,"Avatar","2009", producao3, Arrays.asList("Zoe Saldaña", "Sam Worthington") );

        Producao producao4 = new Producao("Greta Gerwig","LuckyChap Entertainment");
        Filme filme4 = new Filme(4,"Barbie","2023", producao4, Arrays.asList("Margot Robbie", "Ryan Gosling") );

        Producao producao5 = new Producao("Christopher Nolan","Syncopy");
        Filme filme5 = new Filme(5,"Inception","2010", producao5, Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt"));

        Producao producao6 = new Producao("Patty Jenkins","Atlas Entertainment");
        Filme filme6 = new Filme(6,"Wonder Woman","2017", producao6, Arrays.asList("Gal Gadot", "Chris Pine"));

        Producao producao7 = new Producao("Steven Spielberg","Amblin Entertainment");
        Filme filme7 = new Filme(7,"Jurassic Park","1993", producao7, Arrays.asList("Sam Neill", "Laura Dern"));

        Producao producao8 = new Producao("Quentin Tarantino","A Band Apart");
        Filme filme8 = new Filme(8,"Pulp Fiction","1994", producao8, Arrays.asList("John Travolta", "Uma Thurman"));

        Producao producao9 = new Producao("Denis Villeneuve","Alcon Entertainment");
        Filme filme9 = new Filme(9,"Blade Runner 2049","2017", producao9, Arrays.asList("Ryan Gosling", "Harrison Ford"));

        Producao producao10 = new Producao("Chris Columbus", "Warner Bros. Pictures");
        Filme filme10 = new Filme(10, "Harry Potter and the Sorcerer's Stone", "2001", producao10, Arrays.asList("Daniel Radcliffe", "Emma Watson"));

        Producao producao11 = new Producao("Catherine Hardwicke", "Summit Entertainment");
        Filme filme11 = new Filme(11, "Twilight", "2008", producao11, Arrays.asList("Kristen Stewart", "Robert Pattinson"));

        Producao producao12 = new Producao("Chris Buck, Jennifer Lee", "Walt Disney Animation Studios");
        Filme filme12 = new Filme(12, "Frozen", "2013", producao12, Arrays.asList("Kristen Bell", "Idina Menzel"));

        Producao producao13 = new Producao("James Cameron", "20th Century Fox");
        Filme filme13 = new Filme(13, "Titanic", "1997", producao13, Arrays.asList("Leonardo DiCaprio", "Kate Winslet"));

        ArrayList<Filme> filmes = new ArrayList<>();

        filmes.add(filme1);
        filmes.add(filme2);
        filmes.add(filme3);
        filmes.add(filme4);
        filmes.add(filme5);
        filmes.add(filme6);
        filmes.add(filme7);
        filmes.add(filme8);
        filmes.add(filme9);
        filmes.add(filme10);
        filmes.add(filme11);
        filmes.add(filme12);
        filmes.add(filme13);
        return filmes;
    }

    public List<Filme> getAll() {
        return filmes;
    }

    public List<Filme> getAll(int size) {
        List<Filme> list = filmes.stream().toList();
        return list.subList(0,size);
    }

    public List<Filme> getAll(int size, String sort, String order) {
        if(sort.equals("")){
            return getAll(size);
        }else {
            List<Filme> filme = getAll(size);
            Comparator<Filme> comparator = Comparator.comparing(Filme::getTitulo);
            if(order.equals("desc")){
                comparator = comparator.reversed();
            }
            return filme.stream().sorted(comparator).toList();
        }
    }

    public Filme getById(int id){
        return filmes.get(id);
    }

    public Filme save(Filme filme){
        filmes.add(filme);
        return filme;
    }

    public Filme delete(int id){
        return filmes.remove(id);
    }

    public void update(int id, Filme filme){
        Filme filmeDaLista = getById(id);
        if (filmeDaLista != null){
            filmeDaLista.setId(filme.getId());
            filmeDaLista.setTitulo(filme.getTitulo());
            filmeDaLista.setAno(filme.getAno());
            filmeDaLista.setProducao(filme.getProducao());
            filmeDaLista.setElenco(filme.getElenco());

        } else {
            try{
                throw new FileNotFoundException("Filme não encontrado");
            } catch (FileNotFoundException e){
                throw new RuntimeException(e);
            }
        }
    }
}