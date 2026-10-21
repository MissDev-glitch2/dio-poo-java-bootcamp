package br.com.dio.desafio.dominio;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Um <strong>bootcamp</strong>: o programa que junta conteúdos e devs.
 *
 * <p>O bootcamp é o ponto de interligação do domínio. Ele guarda o catálogo de
 * {@link Conteudo}s e o conjunto de {@link Dev}s inscritos, e é ele que fornece a esses devs
 * o que eles vão estudar (via {@link Dev#inscreverBootcamp(Bootcamp)}).
 *
 * @author MissDev-glitch2
 * @see Conteudo
 * @see Dev
 */
public class Bootcamp {

    /** Duração padrão de um bootcamp, em dias. */
    private static final int DURACAO_EM_DIAS = 45;

    private String nome;
    private String descricao;
    private final LocalDate dataInicial = LocalDate.now();
    private final LocalDate dataFinal = dataInicial.plusDays(DURACAO_EM_DIAS);
    private Set<Dev> devsInscritos = new LinkedHashSet<>();
    private Set<Conteudo> conteudos = new LinkedHashSet<>();

    /** Construtor vazio, mantido para uso com {@code new} + {@code setters}. */
    public Bootcamp() {
        // uso fluente via setters
    }

    /**
     * Construtor convenientemente parametrizado.
     *
     * @param nome      nome do bootcamp
     * @param descricao descrição do bootcamp
     */
    public Bootcamp(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    /** @return o nome do bootcamp */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do bootcamp.
     *
     * @param nome novo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /** @return a descrição do bootcamp */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do bootcamp.
     *
     * @param descricao nova descrição
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /** @return a data de início do bootcamp */
    public LocalDate getDataInicial() {
        return dataInicial;
    }

    /** @return a data de término do bootcamp */
    public LocalDate getDataFinal() {
        return dataFinal;
    }

    /** @return os devs inscritos no bootcamp */
    public Set<Dev> getDevsInscritos() {
        return devsInscritos;
    }

    /**
     * Substitui o conjunto de devs inscritos.
     *
     * @param devsInscritos novo conjunto de devs
     */
    public void setDevsInscritos(Set<Dev> devsInscritos) {
        this.devsInscritos = devsInscritos;
    }

    /** @return os conteúdos oferecidos pelo bootcamp */
    public Set<Conteudo> getConteudos() {
        return conteudos;
    }

    /**
     * Substitui o conjunto de conteúdos do bootcamp.
     *
     * @param conteudos novo conjunto de conteúdos
     */
    public void setConteudos(Set<Conteudo> conteudos) {
        this.conteudos = conteudos;
    }

    /**
     * Adiciona um conteúdo ao catálogo do bootcamp.
     *
     * @param conteudo conteúdo a adicionar
     * @return {@code true} se o conteúdo ainda não estava no catálogo
     */
    public boolean adicionarConteudo(Conteudo conteudo) {
        return conteudos.add(conteudo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var bootcamp = (Bootcamp) o;
        return Objects.equals(nome, bootcamp.nome)
                && Objects.equals(descricao, bootcamp.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, descricao);
    }

    @Override
    public String toString() {
        return "Bootcamp{"
                + "nome='" + nome + '\''
                + ", descricao='" + descricao + '\''
                + ", dataInicial=" + dataInicial
                + ", dataFinal=" + dataFinal
                + '}';
    }
}
