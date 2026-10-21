package br.com.dio.desafio.dominio;

import java.util.Objects;

/**
 * Superclasse abstrata que representa um <strong>conteúdo</strong> de um bootcamp.
 *
 * <p>É aqui que mora a <em>abstração</em> do domínio: {@link Curso} e {@link Mentoria} têm
 * coisas diferentes entre si (carga horária, data), mas compartilham o que realmente importa
 * para o bootcamp — um título, uma descrição e uma recompensa em XP. Essa parte comum é
 * modelada aqui, e o que diverge fica a cargo das subclasses.
 *
 * <p>A classe é abstrata justamente porque não existe "conteúdo genérico": só faz sentido
 * instanciar um {@link Curso} ou uma {@link Mentoria}. Por isso {@link #calcularXp()} é
 * abstrato — cada tipo de conteúdo tem a sua própria regra de recompensa, e o compilador
 * obriga cada subclasse a implementá-la.
 *
 * @author MissDev-glitch2
 * @see Curso
 * @see Mentoria
 */
public abstract class Conteudo {

    /** XP base hora de carga horária cursada. */
    protected static final double XP_PADRAO = 10d;

    private String titulo;
    private String descricao;

    /** Construtor vazio, mantido para uso com {@code new} + {@code setters}. */
    protected Conteudo() {
        // uso fluente via setters
    }

    /**
     * Construtor convenientemente parametrizado.
     *
     * @param titulo    título do conteúdo
     * @param descricao descrição do conteúdo
     */
    protected Conteudo(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    /**
     * Calcula a recompensa em XP deste conteúdo.
     *
     * <p>É o ponto de <strong>polimorfismo</strong> do domínio: o chamador não precisa saber
     * se está lidando com um curso ou uma mentoria, apenas pede o XP e recebe o valor correto
     * para o tipo concreto em tempo de execução.
     *
     * @return o XP concedido pela conclusão deste conteúdo
     */
    public abstract double calcularXp();

    /** @return o título do conteúdo */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Define o título do conteúdo.
     *
     * @param titulo novo título
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /** @return a descrição do conteúdo */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do conteúdo.
     *
     * @param descricao nova descrição
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var conteudo = (Conteudo) o;
        return Objects.equals(titulo, conteudo.titulo)
                && Objects.equals(descricao, conteudo.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, descricao);
    }
}
