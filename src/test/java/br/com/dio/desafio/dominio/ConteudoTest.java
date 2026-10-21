package br.com.dio.desafio.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Testes da abstração {@link Conteudo} e do polimorfismo em {@link #calcularXp()}.
 */
@DisplayName("Conteudo - abstracao e polimorfismo")
class ConteudoTest {

    @Test
    @DisplayName("Conteudo e abstrata e nao pode ser instanciada")
    void conteudoEhAbstrata() {
        assertThrows(
                InstantiationException.class,
                () -> {
                    final var ctors = Conteudo.class.getDeclaredConstructors();
                    for (final var ctor : ctors) {
                        ctor.setAccessible(true);
                        ctor.newInstance();
                    }
                },
                "Conteudo e abstrata: nao deveria ser possivel instancia-la");
    }

    @Test
    @DisplayName("A mesma variavel do tipo Conteudo devolve XP diferente conforme o tipo concreto")
    void polimorfismoNoCalculoDeXp() {
        final var curso = new Curso("Curso Java", "Java e POO", 8);
        final var mentoria = new Mentoria("Mentoria Java", "Revisao ao vivo", LocalDate.now());

        final Conteudo[] conteudos = {curso, mentoria};

        assertEquals(80d, conteudos[0].calcularXp(), "Curso: 10 XP por hora x 8 horas");
        assertEquals(30d, conteudos[1].calcularXp(), "Mentoria: 10 + 20 de bonus");
        assertNotEquals(
                conteudos[0].calcularXp(),
                conteudos[1].calcularXp(),
                "Tipos diferentes devem ter regras de XP diferentes");
    }

    @Test
    @DisplayName("Encapsulamento: os atributos so saem por getters e entram por setters")
    void encapsulamentoDosAtributos() {
        final var conteudo = new Curso();
        conteudo.setTitulo("Titulo");
        conteudo.setDescricao("Descricao");

        assertEquals("Titulo", conteudo.getTitulo());
        assertEquals("Descricao", conteudo.getDescricao());
    }

    @Test
    @DisplayName("equals/hashCode distinguem tipos diferentes com os mesmos dados")
    void equalsDistingueTiposConcretos() {
        final var curso = new Curso("Mesmo", "Dado", 1);
        final var mentoria = new Mentoria("Mesmo", "Dado", LocalDate.now());

        assertNotEquals(curso, mentoria, "Curso e Mentoria nao sao o mesmo conteudo");
        assertEquals(curso, new Curso("Mesmo", "Dado", 99));
        assertEquals(curso.hashCode(), new Curso("Mesmo", "Dado", 99).hashCode());
    }

    @Test
    @DisplayName("Heranca: Curso e Mentoria sao Conteudo")
    void herancaComConteudo() {
        assertInstanceOf(Conteudo.class, new Curso());
        assertInstanceOf(Conteudo.class, new Mentoria());
    }
}
