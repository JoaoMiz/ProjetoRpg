package com.example.Rpg_Marnes.service;

import com.example.Rpg_Marnes.dto.PlayerDTO;
import com.example.Rpg_Marnes.model.Player;
import com.example.Rpg_Marnes.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerServiceTest {

    @InjectMocks
    private PlayerService playerService;

    @Mock
    private PlayerRepository playerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarPlayerComSucesso() {
        Player player = new Player();
        player.setEmail("player@example.com");

        when(playerRepository.save(player)).thenReturn(player);

        Player resultado = playerService.criarPlayer(player);

        assertNotNull(resultado);
        assertEquals("player@example.com", resultado.getEmail());
        verify(playerRepository, times(1)).save(player);
    }

    @Test
    void testCriarPlayerSemEmailDeveLancarExcecao() {
        Player player = new Player();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            playerService.criarPlayer(player);
        });

        assertEquals("O e-mail deve ser preenchido.", exception.getMessage());
        verify(playerRepository, never()).save(player);
    }

    @Test
    void testEditarPlayerComSucesso() {
        Long id = 1L;
        Player playerExistente = new Player();
        playerExistente.setId(id);
        playerExistente.setEmail("old@example.com");

        Player novosDados = new Player();
        novosDados.setEmail("new@example.com");

        when(playerRepository.findById(id)).thenReturn(Optional.of(playerExistente));
        when(playerRepository.save(any(Player.class))).thenReturn(playerExistente);

        Player resultado = playerService.editarPlayer(id, novosDados);

        assertNotNull(resultado);
        assertEquals("new@example.com", resultado.getEmail());
        verify(playerRepository, times(1)).findById(id);
        verify(playerRepository, times(1)).save(playerExistente);
    }

    @Test
    void testEditarPlayerNaoEncontrado() {
        Long id = 1L;
        Player novosDados = new Player();

        when(playerRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            playerService.editarPlayer(id, novosDados);
        });

        assertEquals("Player não encontrado.", exception.getMessage());
        verify(playerRepository, times(1)).findById(id);
        verify(playerRepository, never()).save(any(Player.class));
    }

    @Test
    void testBuscarPlayerComSucesso() {
        Long id = 1L;
        Player player = new Player();
        player.setId(id);
        player.setEmail("player@example.com");

        when(playerRepository.findById(id)).thenReturn(Optional.of(player));

        PlayerDTO resultado = playerService.buscarPlayer(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(playerRepository, times(1)).findById(id);
    }

    @Test
    void testBuscarPlayerNaoEncontrado() {
        Long id = 1L;

        when(playerRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            playerService.buscarPlayer(id);
        });

        assertEquals("Player não encontrado.", exception.getMessage());
        verify(playerRepository, times(1)).findById(id);
    }

    @Test
    void testExcluirPlayerComSucesso() {
        Long id = 1L;

        when(playerRepository.existsById(id)).thenReturn(true);

        playerService.excluirPlayer(id);

        verify(playerRepository, times(1)).existsById(id);
        verify(playerRepository, times(1)).deleteById(id);
    }

    @Test
    void testExcluirPlayerNaoEncontrado() {
        Long id = 1L;

        when(playerRepository.existsById(id)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            playerService.excluirPlayer(id);
        });

        assertEquals("Player não encontrado.", exception.getMessage());
        verify(playerRepository, times(1)).existsById(id);
        verify(playerRepository, never()).deleteById(id);
    }
}