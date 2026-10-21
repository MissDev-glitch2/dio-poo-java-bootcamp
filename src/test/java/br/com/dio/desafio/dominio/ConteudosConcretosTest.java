package br.com.dio.desafio.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Testes das subclasses concretas {@link Curso} e {@link Mentoria}.
 */
@DisplayName("Curso e Mentoria - subclasses concretas de Conteudo")
class ConteudosConcretosTest {

    @Test
    @DisplayName("Curso: XP proporcional a carga horaria")
    void cursoCalculaXpPelaCargaHoraria() {
        assertEquals(80d, new Curso("Java", "POO", 8).calcularXp());
        assertEquals(40d, new Curso("JS", "Web", 4).calcularXp());
        assertEquals(0d, new Curso("Zero", "Sem carga", 0).calcularXp());
    }

    @Test
    @DisplayName("Mentoria: XP fixo, independente da data")
    void mentoriaCalculaXpFixo() {
        final var hoje = new Mentoria("M1", "Revisao", LocalDate.now());
        final var futuro = new Mentoria("M2", "Revisao", LocalDate.now().plusMonths(6));

        assertEquals(30d, hoje.calcularXp());
        assertEquals(futuro.calcularXp(), hoje.calcularXp(), "A data nao muda o XP da mentoria");
    }

    @Test
    @DisplayName("toString expoe os atributos relevantes de cada tipo")
    void toStringExpoeOsAtributos() {
        final var curso = new Curso("Curso Java", "Descricao do curso", 8);
        assertTrue(curso.toString().contains("Curso"));
        assertTrue(curso.toString().contains("Curso Java"));
        assertTrue(curso.toString().contains("8"));

        final var mentoria = new Mentoria("Mentoria Java", "Descricao da mentoria", LocalDate.now());
        assertTrue(mentoria.toString().contains("Mentoria"));
        assertTrue(mentoria.toString().contains("Mentoria Java"));
    }
}
