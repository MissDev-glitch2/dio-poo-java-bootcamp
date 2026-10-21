package br.com.dio.desafio.dominio;

import java.time.LocalDate;

/**
 * Uma <strong>mentoria</strong> do bootcamp.
 *
 * <p>Mentoria também é um {@link Conteudo}, mas a sua recompensa não depende de carga horária:
 * toda mentoria vale {@value #XP_PADRAO} + 20 pontos, independentemente da data. Esse é o outro
 * lado do <strong>polimorfismo</strong> — mesma assinatura, implementação diferente.
 *
 * @author MissDev-glitch2
 * @see Conteudo
 */
public class Mentoria extends Conteudo {

    /** Bônus fixo concedido por mentoria, somado ao {@link #XP_PADRAO}. */
    private static final double BONUS_MENTORIA = 20d;

    private LocalDate data;

    /** Construtor vazio, mantido para uso com {@code new} + {@code setters}. */
    public Mentoria() {
        super();
    }

    /**
     * Construtor convenientemente parametrizado.
     *
     * @param titulo    título da mentoria
     * @param descricao descrição da mentoria
     * @param data      data da mentoria
     */
    public Mentoria(String titulo, String descricao, LocalDate data) {
        super(titulo, descricao);
        this.data = data;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Na mentoria, o XP é fixo: {@value #XP_PADRAO} + {@value #BONUS_MENTORIA}.
     */
    @Override
    public double calcularXp() {
        return XP_PADRAO + BONUS_MENTORIA;
    }

    /** @return a data da mentoria */
    public LocalDate getData() {
        return data;
    }

    /**
     * Define a data da mentoria.
     *
     * @param data nova data
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Mentoria{"
                + "titulo='" + getTitulo() + '\''
                + ", descricao='" + getDescricao() + '\''
                + ", data=" + data
                + '}';
    }
}
