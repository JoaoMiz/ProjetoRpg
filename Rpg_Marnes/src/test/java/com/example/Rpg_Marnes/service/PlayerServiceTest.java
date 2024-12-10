package com.example.Rpg_Marnes.service;

import com.example.Rpg_Marnes.dto.PlayerDTO;
import com.example.Rpg_Marnes.model.Ficha;
import com.example.Rpg_Marnes.model.Player;
import com.example.Rpg_Marnes.repository.FichaRepository;
import com.example.Rpg_Marnes.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @InjectMocks
    private PlayerService playerService;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private FichaRepository fichaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Testes para Player
    @Test
    public void testCriarPlayerComSucesso() {
        Player player = new Player();
        player.setEmail("player@example.com");

        when(playerRepository.save(player)).thenReturn(player);

        Player resultado = playerService.criarPlayer(player);

        assertNotNull(resultado);
        assertEquals("player@example.com", resultado.getEmail());
        verify(playerRepository, times(1)).save(player);
    }

    @Test
    public void testCriarPlayerSemEmailDeveLancarExcecao() {
        Player player = new Player();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            playerService.criarPlayer(player);
        });

        assertEquals("O e-mail deve ser preenchido.", exception.getMessage());
        verify(playerRepository, never()).save(player);
    }

    @Test
    public void testEditarPlayerComSucesso() {
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
    public void testBuscarPlayerComSucesso() {
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

    // Testes para Ficha
    @Test
    public void testCriarFichaComSucesso() {
        Ficha ficha = new Ficha();
        ficha.setDescricao("Nova Ficha");
        ficha.setVida(100);
        ficha.setCa(15);
        ficha.setProficiencia(2);

        when(fichaRepository.save(ficha)).thenReturn(ficha);

        Ficha resultado = playerService.criarFicha(ficha);

        assertNotNull(resultado);
        assertEquals("Nova Ficha", resultado.getDescricao());
        assertEquals(100, resultado.getVida());
        verify(fichaRepository, times(1)).save(ficha);
    }

    @Test
    public void testCriarFichaSemCamposObrigatoriosDeveLancarExcecao() {
        Ficha ficha = new Ficha();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            playerService.criarFicha(ficha);
        });

        assertEquals("A descrição da ficha deve ser preenchida.", exception.getMessage());
        verify(fichaRepository, never()).save(ficha);
    }

    @Test
    public void testEditarFichaComSucesso() {
        Long id = 1L;
        Ficha fichaExistente = new Ficha();
        fichaExistente.setId(id);
        fichaExistente.setDescricao("Descrição Antiga");
        fichaExistente.setVida(50);

        Ficha novosDados = new Ficha();
        novosDados.setDescricao("Descrição Nova");
        novosDados.setVida(100);

        when(fichaRepository.findById(id)).thenReturn(Optional.of(fichaExistente));
        when(fichaRepository.save(any(Ficha.class))).thenReturn(fichaExistente);

        Ficha resultado = playerService.editarFicha(id, novosDados);

        assertNotNull(resultado);
        assertEquals("Descrição Nova", resultado.getDescricao());
        assertEquals(100, resultado.getVida());
        verify(fichaRepository, times(1)).findById(id);
        verify(fichaRepository, times(1)).save(fichaExistente);
    }

    @Test
    public void testBuscarFichaComSucesso() {
        Long id = 1L;
        Ficha ficha = new Ficha();
        ficha.setId(id);
        ficha.setDescricao("Descrição da Ficha");

        when(fichaRepository.findById(id)).thenReturn(Optional.of(ficha));

        Ficha resultado = playerService.buscarFicha(id);

        assertNotNull(resultado);
        assertEquals("Descrição da Ficha", resultado.getDescricao());
        verify(fichaRepository, times(1)).findById(id);
    }

    @Test
    public void testExcluirFichaComSucesso() {
        Long id = 1L;

        when(fichaRepository.existsById(id)).thenReturn(true);

        playerService.excluirFicha(id);

        verify(fichaRepository, times(1)).existsById(id);
        verify(fichaRepository, times(1)).deleteById(id);
    }

    @Test
    public void testExcluirFichaNaoEncontrada() {
        Long id = 1L;

        when(fichaRepository.existsById(id)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            playerService.excluirFicha(id);
        });

        assertEquals("Ficha não encontrada.", exception.getMessage());
        verify(fichaRepository, times(1)).existsById(id);
        verify(fichaRepository, never()).deleteById(id);
    }
}
