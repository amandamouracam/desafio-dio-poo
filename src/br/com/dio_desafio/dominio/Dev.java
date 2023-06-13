package br.com.dio_desafio.dominio;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Dev {

    private String nome;
    private Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();
    private Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();

    public void inscreverBootcamp(Bootcamp bootcamp){
        //adicionando os conteúdos do bootcamp
        this.conteudosInscritos.addAll(bootcamp.getConteudosBootcamp());

        //adicionando o dev ao bootcamp
        bootcamp.getDevsInscritos().add(this);
    }

    public void progredir(){
        //cada conteudo inscrito e finalizado, é adicionado a variável conteudo
        Optional<Conteudo> conteudo = this.conteudosInscritos.stream().findFirst();

        //se existire conteudos dentro da variável...
        if (conteudo.isPresent()){
            //... ele é adicionado na lista de conteudos concluidos...
            this.conteudosConcluidos.add(conteudo.get());
            //...e removido dos conteudos inscritos
            this.conteudosInscritos.remove(conteudo.get());
        }else {
            System.err.println("Você não está matriculado em nenhum conteúdo!");
        }

    }

    public double calcularTotalXP(){

        //cada conteudo + o XP de cada conteudo e soma
        return this.conteudosConcluidos.stream().mapToDouble(
                        Conteudo::calcularXP).sum();
    }

    //gets e sets

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Conteudo> getConteudosInscritos() {
        return conteudosInscritos;
    }

    public void setConteudosInscritos(Set<Conteudo> conteudosInscritos) {
        this.conteudosInscritos = conteudosInscritos;
    }

    public Set<Conteudo> getConteudosConcluidos() {
        return conteudosConcluidos;
    }

    public void setConteudosConcluidos(Set<Conteudo> conteudosConcluidos) {
        this.conteudosConcluidos = conteudosConcluidos;
    }


    //equals e hashcode


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dev dev = (Dev) o;
        return Objects.equals(nome, dev.nome) && Objects.equals(conteudosInscritos, dev.conteudosInscritos) && Objects.equals(conteudosConcluidos, dev.conteudosConcluidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, conteudosInscritos, conteudosConcluidos);
    }
}
