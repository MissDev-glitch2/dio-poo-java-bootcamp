package br.com.dio.desafio;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Teste de fumaca do {@link Main}: garante que a demonstracao completa roda de ponta a ponta
 * e produz as etapas esperadas.
 */
@DisplayName("Main - demonstracao completa do dominio")
class MainTest {

    @Test
    @DisplayName("O main executa sem excecao e imprime todas as etapas do desafio")
    void mainExecutaComTodasAsEtapas() {
        final var output = new ByteArrayOutputStream();
        final var original = System.out;

        try {
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
            assertDoesNotThrow(() -> Main.main(new String[0]));
        } finally {
            System.setOut(original);
        }

        final var saida = output.toString(StandardCharsets.UTF_8);

        assertTrue(saida.contains("PARTE 1"), "A etapa 1 (abstracao/encapsulamento) deve aparecer");
        assertTrue(saida.contains("PARTE 2"), "A etapa 2 (heranca/polimorfismo) deve aparecer");
        assertTrue(saida.contains("PARTE 3"), "A etapa 3 (Bootcamp e Dev) deve aparecer");
        assertTrue(saida.contains("PARTE 5"), "A etapa 5 (interligacao) deve aparecer");
        assertTrue(saida.contains("XP Camila"), "O XP da Camila deve ser impresso");
        assertTrue(saida.contains("XP Rodolfo"), "O XP do Rodolfo deve ser impresso");
        assertTrue(saida.contains("80.0"), "O curso de 8 horas deve render 80 XP");
        assertTrue(saida.contains("150.0"), "Os tres conteudos somam 150 XP");
    }
}
