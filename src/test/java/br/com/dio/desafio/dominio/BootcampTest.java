package br.com.dio.desafio.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Testes da classe {@link Bootcamp}, que agrega conteudos e devs.
 */
@DisplayName("Bootcamp - catalogo de conteudos e devs inscritos")
class BootcampTest {

    private static Bootcamp bootcampComCatalogo() {
        final var bootcamp = new Bootcamp();
        bootcamp.setNome("Bootcamp Java Developer");
        bootcamp.setDescricao("Trilha completa de Java");
        bootcamp.adicionarConteudo(new Curso("Curso Java", "POO", 8));
        bootcamp.adicionarConteudo(new Mentoria("Mentoria Java", "Revisao", LocalDate.now()));
        return bootcamp;
    }

    @Test
    @DisplayName("O catalogo de conteudos guarda o que foi adicionado")
    void catalogoDeConteudos() {
        final var bootcamp = bootcampComCatalogo();
        assertEquals(2, bootcamp.getConteudos().size());
        assertTrue(bootcamp.adicionarConteudo(new Curso("Outro", "Curso", 2)));
        assertEquals(3, bootcamp.getConteudos().size());
    }

    @Test
    @DisplayName("O bootcamp dura 45 dias a partir da data de inicio")
    void periodoDeDuracao() {
        final var bootcamp = bootcampComCatalogo();
        assertEquals(bootcamp.getDataInicial().plusDays(45), bootcamp.getDataFinal());
        assertFalse(bootcamp.getDataInicial().isAfter(bootcamp.getDataFinal()));
    }

    @Test
    @DisplayName("A inscricao no bootcamp matricula o dev em todos os conteudos")
    void inscricaoMatriculaEmTodosOsConteudos() {
        final var bootcamp = bootcampComCatalogo();
        final var dev = new Dev("Camila", "camila@dio.me");

        dev.inscreverBootcamp(bootcamp);

        assertEquals(2, dev.getConteudosInscritos().size());
        assertTrue(bootcamp.getDevsInscritos().contains(dev));
    }

    @Test
    @DisplayName("equals/hashCode comparam nome e descricao, e nao o estado mutavel")
    void equalsIgnoraEstadoMutavel() {
        final var bootcamp = bootcampComCatalogo();
        final var outro = new Bootcamp("Bootcamp Java Developer", "Trilha completa de Java");

        assertEquals(bootcamp, outro, "Dois bootcamps com os mesmos dados sao iguais");
        assertEquals(bootcamp.hashCode(), outro.hashCode());

        outro.adicionarConteudo(new Curso("X", "Y", 1));
        assertEquals(
                bootcamp,
                outro,
                "Adicionar conteudo nao pode alterar a identidade do bootcamp");
    }
}
