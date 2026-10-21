package br.com.dio.desafio.dominio;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Um <strong>dev</strong> que participa do bootcamp.
 *
 * <p>O dev carrega o seu próprio estado de progresso: o que está inscrito, o que já concluiu
 * e o XP acumulado. As regras de negócio ({@link #inscreverBootcamp(Bootcamp)},
 * {@link #progredir()} e {@link #calcularTotalXp()}) ficam concentradas aqui — é a parte
 * "finalizando regras de negócio" do desafio.
 *
 * @author MissDev-glitch2
 * @see Bootcamp
 * @see Conteudo
 */
public class Dev {

    private String nome;
    private String email;
    private Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();
    private Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();

    /** Construtor vazio, mantido para uso com {@code new} + {@code setters}. */
    public Dev() {
        // uso fluente via setters
    }

    /**
     * Construtor convenientemente parametrizado.
     *
     * @param nome  nome do dev
     * @param email e-mail do dev
     */
    public Dev(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    /**
     * Inscreve o dev no bootcamp, matriculando-o em todos os conteúdos do catálogo.
     *
     * @param bootcamp bootcamp ao qual o dev vai se inscrever
     */
    public void inscreverBootcamp(Bootcamp bootcamp) {
        this.conteudosInscritos.addAll(bootcamp.getConteudos());
        bootcamp.getDevsInscritos().add(this);
    }

    /**
     * Matricula o dev em um conteúdo específico.
     *
     * @param conteudo conteúdo a ser cursado
     */
    public void inscreverConteudo(Conteudo conteudo) {
        this.conteudosInscritos.add(conteudo);
    }

    /**
     * Conclui o próximo conteúdo em que o dev está inscrito.
     *
     * <p>O conteúdo concluído sai da lista de inscritos e entra na de concluídos, e é aí que
     * o XP passa a contar.
     */
    public void progredir() {
        final Optional<Conteudo> conteudo = this.conteudosInscritos.stream().findFirst();
        if (conteudo.isPresent()) {
            this.conteudosConcluidos.add(conteudo.get());
            this.conteudosInscritos.remove(conteudo.get());
        } else {
            System.out.println("Você ainda não se matriculou em nenhum conteúdo!");
        }
    }

    /**
     * Soma o XP de todos os conteúdos concluídos.
     *
     * @return o XP total acumulado pelo dev
     */
    public double calcularTotalXp() {
        return this.conteudosConcluidos.stream().mapToDouble(Conteudo::calcularXp).sum();
    }

    /** @return o nome do dev */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do dev.
     *
     * @param nome novo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /** @return o e-mail do dev */
    public String getEmail() {
        return email;
    }

    /**
     * Define o e-mail do dev.
     *
     * @param email novo e-mail
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /** @return os conteúdos em que o dev está inscrito */
    public Set<Conteudo> getConteudosInscritos() {
        return conteudosInscritos;
    }

    /**
     * Substitui o conjunto de conteúdos inscritos.
     *
     * @param conteudosInscritos novo conjunto de conteúdos inscritos
     */
    public void setConteudosInscritos(Set<Conteudo> conteudosInscritos) {
        this.conteudosInscritos = conteudosInscritos;
    }

    /** @return os conteúdos que o dev já concluiu */
    public Set<Conteudo> getConteudosConcluidos() {
        return conteudosConcluidos;
    }

    /**
     * Substitui o conjunto de conteúdos concluídos.
     *
     * @param conteudosConcluidos novo conjunto de conteúdos concluídos
     */
    public void setConteudosConcluidos(Set<Conteudo> conteudosConcluidos) {
        this.conteudosConcluidos = conteudosConcluidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var dev = (Dev) o;
        return Objects.equals(nome, dev.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString() {
        return "Dev{"
                + "nome='" + nome + '\''
                + ", email='" + email + '\''
                + ", xp=" + calcularTotalXp()
                + '}';
    }
}
