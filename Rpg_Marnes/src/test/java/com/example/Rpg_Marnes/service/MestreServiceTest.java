package com.example.Rpg_Marnes.service;

import com.example.Rpg_Marnes.model.Mestre;
import com.example.Rpg_Marnes.repository.MestreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MestreServiceTest {

    @InjectMocks
    private MestreService mestreService;

    @Mock
    private MestreRepository mestreRepository;

    @Mock
    private ChatService chatService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRolarDados() {
        int numLados = 6; // Dado com 6 lados
        int qntDados = 3; // Jogar 3 dados
        int bonus = 5; // Bônus de 5

        // Não precisamos mockar métodos de `Math.random` ou `StringBuilder`.

        doNothing().when(chatService).AdicionarMensagem(anyString());

        int resultado = mestreService.rolarDados(numLados, qntDados, bonus);

        // O valor mínimo é (1 * qntDados) + bonus
        int minExpected = qntDados + bonus;
        // O valor máximo é (numLados * qntDados) + bonus
        int maxExpected = (numLados * qntDados) + bonus;

        assertTrue(resultado >= minExpected, "O resultado deve ser maior ou igual ao valor mínimo esperado.");
        assertTrue(resultado <= maxExpected, "O resultado deve ser menor ou igual ao valor máximo esperado.");

        // Verifica se a mensagem foi enviada ao chat
        verify(chatService, times(1)).AdicionarMensagem(contains("Rolagem de Dados"));
    }

    @Test
    void testSaveMestre() {
        Mestre mestre = new Mestre();
        mestre.setEmail("Mestre Teste");

        when(mestreRepository.save(any(Mestre.class))).thenReturn(mestre);

        Mestre savedMestre = mestreService.saveMestre(mestre);

        assertNotNull(savedMestre, "O Mestre salvo não deve ser nulo.");
        assertEquals("Mestre Teste", savedMestre.getEmail(), "O nome do Mestre salvo deve corresponder.");
        verify(mestreRepository, times(1)).save(mestre);
    }
}
