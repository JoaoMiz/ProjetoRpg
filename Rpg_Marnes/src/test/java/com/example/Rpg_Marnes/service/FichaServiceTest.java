package com.example.Rpg_Marnes.service;

import com.example.Rpg_Marnes.model.Ficha;
import com.example.Rpg_Marnes.repository.FichaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FichaServiceTest {

    @Mock
    private FichaRepository fichaRepository;

    @InjectMocks
    private FichaService fichaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveFicha() {
        Ficha ficha = new Ficha();
        ficha.setVida(100);

        when(fichaRepository.save(ficha)).thenReturn(ficha);

        Ficha savedFicha = fichaService.saveFicha(ficha);

        assertNotNull(savedFicha);
        assertEquals(100, savedFicha.getVida());
        verify(fichaRepository, times(1)).save(ficha);
    }

    @Test
    void testGetAllFichas() {
        Ficha ficha1 = new Ficha();
        ficha1.setVida(100);

        Ficha ficha2 = new Ficha();
        ficha2.setVida(200);

        when(fichaRepository.findAll()).thenReturn(Arrays.asList(ficha1, ficha2));

        List<Ficha> fichas = fichaService.getAllFichas();

        assertNotNull(fichas);
        assertEquals(2, fichas.size());
        verify(fichaRepository, times(1)).findAll();
    }

    @Test
    void testGetFichaById() {
        Ficha ficha = new Ficha();
        ficha.setVida(100);

        when(fichaRepository.findById(1L)).thenReturn(Optional.of(ficha));

        Optional<Ficha> foundFicha = fichaService.getFichaById(1L);

        assertTrue(foundFicha.isPresent());
        assertEquals(100, foundFicha.get().getVida());
        verify(fichaRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteFicha() {
        doNothing().when(fichaRepository).deleteById(1L);

        fichaService.deleteFicha(1L);

        verify(fichaRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateFicha() {
        Ficha existingFicha = new Ficha();
        existingFicha.setVida(100);
        existingFicha.setCa(15);

        Ficha newFichaDetails = new Ficha();
        newFichaDetails.setVida(200);
        newFichaDetails.setCa(18);

        when(fichaRepository.findById(1L)).thenReturn(Optional.of(existingFicha));
        when(fichaRepository.save(existingFicha)).thenReturn(existingFicha);

        Ficha updatedFicha = fichaService.updateFicha(1L, newFichaDetails);

        assertNotNull(updatedFicha);
        assertEquals(200, updatedFicha.getVida());
        assertEquals(18, updatedFicha.getCa());
        verify(fichaRepository, times(1)).findById(1L);
        verify(fichaRepository, times(1)).save(existingFicha);
    }

    @Test
    void testUpdateFichaNotFound() {
        when(fichaRepository.findById(1L)).thenReturn(Optional.empty());

        Ficha newFichaDetails = new Ficha();
        Ficha result = fichaService.updateFicha(1L, newFichaDetails);

        assertNull(result);
        verify(fichaRepository, times(1)).findById(1L);
        verify(fichaRepository, never()).save(any(Ficha.class));
    }
}
