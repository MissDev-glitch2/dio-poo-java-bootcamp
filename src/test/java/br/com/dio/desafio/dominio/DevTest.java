package br.com.dio.desafio.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Testes das regras de negocio da classe {@link Dev}.
 */
@DisplayName("Dev - inscricao, progressao e calculo de XP")
class DevTest {

    private static Bootcamp bootcampDeTresConteudos() {
        final var bootcamp = new Bootcamp("Bootcamp Java Developer", "Trilha Java");
        bootcamp.adicionarConteudo(new Curso("Curso Java", "POO", 8));   // 80 XP
        bootcamp.adicionarConteudo(new Curso("Curso JS", "Web", 4));     // 40 XP
        bootcamp.adicionarConteudo(new Mentoria("Mentoria", "Revisao", LocalDate.now())); // 30 XP
        return bootcamp;
    }

    @Test
    @DisplayName("Progredir move o conteudo de inscritos para concluidos")
    void progredirMoveOConteudo() {
        final var dev = new Dev("Camila", "camila@dio.me");
        dev.inscreverBootcamp(bootcampDeTresConteudos());

        assertEquals(3, dev.getConteudosInscritos().size());
        assertEquals(0, dev.getConteudosConcluidos().size());

        dev.progredir();

        assertEquals(2, dev.getConteudosInscritos().size());
        assertEquals(1, dev.getConteudosConcluidos().size());
    }

    @Test
    @DisplayName("O XP total e a soma do XP dos conteudos concluidos")
    void xpTotalSomaOsConcluidos() {
        final var dev = new Dev("Camila", "camila@dio.me");
        dev.inscreverBootcamp(bootcampDeTresConteudos());

        assertEquals(0d, dev.calcularTotalXp(), "Nada foi concluido ainda");

        dev.progredir(); // curso Java  -> 80
        dev.progredir(); // curso JS    -> 40
        dev.progredir(); // mentoria    -> 30

        assertEquals(150d, dev.calcularTotalXp());
        assertTrue(dev.getConteudosInscritos().isEmpty());
    }

    @Test
    @DisplayName("Progredir sem inscricao nao lanca excecao")
    void progredirSemInscricao() {
        final var dev = new Dev("Camila", "camila@dio.me");
        dev.progredir();
        assertTrue(dev.getConteudosConcluidos().isEmpty());
        assertEquals(0d, dev.calcularTotalXp());
    }

    @Test
    @DisplayName("Inscricao em um conteudo especifico")
    void inscricaoEmConteudoEspecifico() {
        final var dev = new Dev("Rodolfo", "rodolfo@dio.me");
        final var curso = new Curso("Curso Java", "POO", 8);

        dev.inscreverConteudo(curso);

        assertEquals(1, dev.getConteudosInscritos().size());
        assertTrue(dev.getConteudosInscritos().contains(curso));
        assertFalse(dev.getConteudosConcluidos().contains(curso));
    }

    @Test
    @DisplayName("equals/hashCode usam apenas o nome, evitando instabilidade de hash")
    void equalsUsaApenasONome() {
        final var dev = new Dev("Camila", "camila@dio.me");
        final var homonimo = new Dev("Camila", "outro@dio.me");

        assertEquals(dev, homonimo, "Dois devs com o mesmo nome sao a mesma pessoa");
        assertEquals(dev.hashCode(), homonimo.hashCode());

        dev.inscreverConteudo(new Curso("X", "Y", 1));
        assertEquals(dev.hashCode(), homonimo.hashCode(), "Mudar o progresso nao muda o hash");
    }
}
