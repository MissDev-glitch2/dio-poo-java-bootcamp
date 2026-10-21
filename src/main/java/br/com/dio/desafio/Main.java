package br.com.dio.desafio;

import br.com.dio.desafio.dominio.Bootcamp;
import br.com.dio.desafio.dominio.Conteudo;
import br.com.dio.desafio.dominio.Curso;
import br.com.dio.desafio.dominio.Dev;
import br.com.dio.desafio.dominio.Mentoria;

import java.time.LocalDate;

/**
 * Ponto de entrada do projeto.
 *
 * <p>O {@code main} foi escrito para percorrer as cinco etapas do desafio na ordem em que
 * elas aparecem no enunciado, de modo que a saída do console serve de "prova de vida" de cada
 * conceito de POO aplicado:
 *
 * <ol>
 *   <li><strong>Abstração e encapsulamento</strong> — {@link Curso} e {@link Mentoria} com
 *       atributos privados, getters/setters e {@code toString};</li>
 *   <li><strong>Herança e polimorfismo</strong> — {@link Conteudo} abstrata, e a mesma
 *       variável de tipo {@code Conteudo} comportando-se ora como curso, ora como mentoria;</li>
 *   <li><strong>Bootcamp e Dev</strong> — as duas classes que fecham o domínio;</li>
 *   <li><strong>Regras de negócio</strong> — inscrição, progressão e cálculo de XP;</li>
 *   <li><strong>Interligação</strong> — tudo junto, do zero ao XP final.</li>
 * </ol>
 *
 * @author MissDev-glitch2
 */
public final class Main {

    private Main() {
        // classe utilitária: não instanciável
    }

    /**
     * Executa a demonstração completa do domínio.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        System.out.println("=".repeat(72));
        System.out.println("  PARTE 1 - Abstraindo e encapsulando o dominio");
        System.out.println("=".repeat(72));

        final var cursoJava = new Curso();
        cursoJava.setTitulo("Curso Java");
        cursoJava.setDescricao("Formacao completa em Java e Programacao Orientada a Objetos");
        cursoJava.setCargaHoraria(8);

        final var cursoJavaScript = new Curso();
        cursoJavaScript.setTitulo("Curso JavaScript");
        cursoJavaScript.setDescricao("Fundamentos de JavaScript para a web");
        cursoJavaScript.setCargaHoraria(4);

        final var mentoriaJava = new Mentoria();
        mentoriaJava.setTitulo("Mentoria de Java");
        mentoriaJava.setDescricao("Sessao ao vivo de duvida e revisao");
        mentoriaJava.setData(LocalDate.now());

        System.out.println(cursoJava);
        System.out.println(cursoJavaScript);
        System.out.println(mentoriaJava);

        System.out.println();
        System.out.println("=".repeat(72));
        System.out.println("  PARTE 2 - Heranca e polimorfismo");
        System.out.println("=".repeat(72));

        // A mesma variavel, dois tipos concretos: e o polimorfismo em acao.
        final Conteudo conteudo = cursoJava;
        System.out.println("Conteudo (variavel do tipo Conteudo)...");
        System.out.println("  instanceof Curso    -> " + (conteudo instanceof Curso));
        System.out.println("  calcularXp()        -> " + conteudo.calcularXp());

        final var mentoriaComoConteudo = mentoriaJava;
        System.out.println("Mentoria (mesma variavel, outro tipo concreto)...");
        System.out.println("  instanceof Mentoria -> " + (mentoriaComoConteudo instanceof Mentoria));
        System.out.println("  calcularXp()        -> " + mentoriaComoConteudo.calcularXp());

        System.out.println();
        System.out.println("=".repeat(72));
        System.out.println("  PARTE 3 e 4 - Bootcamp, Dev e regras de negocio");
        System.out.println("=".repeat(72));

        final var bootcamp = new Bootcamp();
        bootcamp.setNome("Bootcamp Java Developer");
        bootcamp.setDescricao("Trilha completa de Java com orientacao a objetos");
        bootcamp.getConteudos().add(cursoJava);
        bootcamp.getConteudos().add(cursoJavaScript);
        bootcamp.getConteudos().add(mentoriaJava);

        System.out.println(bootcamp);
        System.out.println("  Periodo: " + bootcamp.getDataInicial() + " -> " + bootcamp.getDataFinal());
        System.out.println("  Catalogo de conteudos: " + bootcamp.getConteudos().size());

        System.out.println();
        System.out.println("=".repeat(72));
        System.out.println("  PARTE 5 - Interligando as classes de dominio");
        System.out.println("=".repeat(72));

        final var devCamila = new Dev();
        devCamila.setNome("Camila");
        devCamila.setEmail("camila@dio.me");
        devCamila.inscreverBootcamp(bootcamp);

        final var devRodolfo = new Dev();
        devRodolfo.setNome("Rodolfo");
        devRodolfo.setEmail("rodolfo@dio.me");
        devRodolfo.inscreverBootcamp(bootcamp);

        System.out.println("Conteudos inscritos Camila: " + devCamila.getConteudosInscritos());
        System.out.println("Conteudos inscritos Rodolfo: " + devRodolfo.getConteudosInscritos());

        devCamila.progredir();
        devCamila.progredir();

        devRodolfo.progredir();
        devRodolfo.progredir();
        devRodolfo.progredir();

        System.out.println("-".repeat(72));
        System.out.println("Conteudos inscritos Camila: " + devCamila.getConteudosInscritos());
        System.out.println("Conteudos concluidos Camila: " + devCamila.getConteudosConcluidos());
        System.out.println("XP Camila: " + devCamila.calcularTotalXp());

        System.out.println("-".repeat(72));
        System.out.println("Conteudos inscritos Rodolfo: " + devRodolfo.getConteudosInscritos());
        System.out.println("Conteudos concluidos Rodolfo: " + devRodolfo.getConteudosConcluidos());
        System.out.println("XP Rodolfo: " + devRodolfo.calcularTotalXp());

        System.out.println("-".repeat(72));
        System.out.println("Devs inscritos no bootcamp: " + bootcamp.getDevsInscritos().size());
    }
}
