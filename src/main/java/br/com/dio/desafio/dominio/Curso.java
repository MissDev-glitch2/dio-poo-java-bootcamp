package br.com.dio.desafio.dominio;

/**
 * Um <strong>curso</strong> do bootcamp.
 *
 * <p>Curso é um {@link Conteudo} cujo XP é proporcional à carga horária: quanto mais horas de
 * conteúdo, mais XP o dev recebe por concluí-lo. Essa regra própria é a implementação de
 * {@link #calcularXp()} — o mesmo método que a {@link Mentoria} implementa de outro jeito,
 * e é aí que aparece o <strong>polimorfismo</strong>.
 *
 * @author MissDev-glitch2
 * @see Conteudo
 */
public class Curso extends Conteudo {

    private int cargaHoraria;

    /** Construtor vazio, mantido para uso com {@code new} + {@code setters}. */
    public Curso() {
        super();
    }

    /**
     * Construtor convenientemente parametrizado.
     *
     * @param titulo       título do curso
     * @param descricao    descrição do curso
     * @param cargaHoraria carga horária do curso
     */
    public Curso(String titulo, String descricao, int cargaHoraria) {
        super(titulo, descricao);
        this.cargaHoraria = cargaHoraria;
    }

    /**
     * {@inheritDoc}
     *
     * <p>No curso, o XP é {@value #XP_PADRAO} pontos por hora de carga horária.
     */
    @Override
    public double calcularXp() {
        return XP_PADRAO * cargaHoraria;
    }

    /** @return a carga horária do curso */
    public int getCargaHoraria() {
        return cargaHoraria;
    }

    /**
     * Define a carga horária do curso.
     *
     * @param cargaHoraria nova carga horária
     */
    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    @Override
    public String toString() {
        return "Curso{"
                + "titulo='" + getTitulo() + '\''
                + ", descricao='" + getDescricao() + '\''
                + ", cargaHoraria=" + cargaHoraria
                + '}';
    }
}
