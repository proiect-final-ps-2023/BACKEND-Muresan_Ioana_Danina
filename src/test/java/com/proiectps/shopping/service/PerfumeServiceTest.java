package com.proiectps.shopping.service;

import com.proiectps.shopping.model.Perfume;
import com.proiectps.shopping.repository.PerfumeRepository;
import com.proiectps.shopping.service.impl.PerfumeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PerfumeServiceTest {

    @Mock
    private PerfumeRepository perfumeRepository;

    @InjectMocks
    private PerfumeServiceImpl perfumeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        perfumeService = new PerfumeServiceImpl(perfumeRepository);
    }

    @Test
    public void savePerfumeTest() {
        Perfume perfume = new Perfume();
        perfume.setId(1L);
        perfume.setTitle("Chanel No. 5");
        perfume.setGender("Female");
        perfume.setBrand("Chanel");
        perfume.setFragrance_top_notes("Aldehydes, Bergamot, Lemon");
        perfume.setFragrance_middle_notes("Jasmine, Rose, Ylang-Ylang, Iris");
        perfume.setFragrance_base_notes("Vanilla, Sandalwood, Vetiver, Amber");
        perfume.setDescription("Iconic floral-aldehyde fragrance");
        perfume.setPrice(100);
        perfume.setVolume(50);
        perfume.setStock(10);

        Perfume savedPerfume = new Perfume();
        savedPerfume.setId(1L);
        savedPerfume.setTitle("Chanel No. 5");
        savedPerfume.setGender("Female");
        savedPerfume.setBrand("Chanel");
        savedPerfume.setFragrance_top_notes("Aldehydes, Bergamot, Lemon");
        savedPerfume.setFragrance_middle_notes("Jasmine, Rose, Ylang-Ylang, Iris");
        savedPerfume.setFragrance_base_notes("Vanilla, Sandalwood, Vetiver, Amber");
        savedPerfume.setDescription("Iconic floral-aldehyde fragrance");
        savedPerfume.setPrice(100);
        savedPerfume.setVolume(50);
        savedPerfume.setStock(10);

        when(perfumeRepository.save(perfume)).thenReturn(savedPerfume);

        Perfume result = perfumeService.savePerfume(perfume);

        assertEquals(savedPerfume.getId(), result.getId());
        assertEquals(savedPerfume.getTitle(), result.getTitle());
        assertEquals(savedPerfume.getGender(), result.getGender());
        assertEquals(savedPerfume.getBrand(), result.getBrand());
        assertEquals(savedPerfume.getFragrance_top_notes(), result.getFragrance_top_notes());
        assertEquals(savedPerfume.getFragrance_middle_notes(), result.getFragrance_middle_notes());
        assertEquals(savedPerfume.getFragrance_base_notes(), result.getFragrance_base_notes());
        assertEquals(savedPerfume.getDescription(), result.getDescription());
        assertEquals(savedPerfume.getPrice(), result.getPrice());
        assertEquals(savedPerfume.getVolume(), result.getVolume());
        assertEquals(savedPerfume.getStock(), result.getStock());
    }

    @Test
    public void testGetPerfumeById() {
        // Arrange
        Long perfumeId = 1L;
        Perfume perfume = new Perfume();
        perfume.setId(perfumeId);
        PerfumeRepository repositoryMock = mock(PerfumeRepository.class);
        when(repositoryMock.findById(perfumeId)).thenReturn(Optional.of(perfume));
        PerfumeServiceImpl service = new PerfumeServiceImpl(repositoryMock);

        // Act
        Perfume result = service.getPerfumeById(perfumeId);

        // Assert
        assertEquals(perfume, result);
    }

    @Test
    public void testDeletePerfume() {
        // Arrange
        Long perfumeId = 1L;
        Perfume perfume = new Perfume();
        perfume.setId(perfumeId);
        PerfumeRepository repositoryMock = mock(PerfumeRepository.class);
        when(repositoryMock.findById(perfumeId)).thenReturn(Optional.of(perfume));
        PerfumeServiceImpl service = new PerfumeServiceImpl(repositoryMock);

        // Act
        String result = service.deletePerfume(perfumeId);

        // Assert
        assertEquals("Perfume deleted successfully", result);
    }

    @Test
    public void testDeletePerfumeWithInvalidId() {
        // Arrange
        Long perfumeId = 1L;
        PerfumeRepository repositoryMock = mock(PerfumeRepository.class);
        when(repositoryMock.findById(perfumeId)).thenReturn(Optional.empty());
        PerfumeServiceImpl service = new PerfumeServiceImpl(repositoryMock);

        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> service.deletePerfume(perfumeId));
    }

    @Test
    public void testUpdatePrice() {
        // Arrange
        Long perfumeId = 1L;
        Perfume perfume = new Perfume();
        perfume.setId(perfumeId);
        perfume.setPrice(100);
        Integer newPrice = 150;
        PerfumeRepository repositoryMock = mock(PerfumeRepository.class);
        when(repositoryMock.findById(perfumeId)).thenReturn(Optional.of(perfume));
        PerfumeServiceImpl service = new PerfumeServiceImpl(repositoryMock);

        // Act
        Perfume result = service.updatePrice(perfumeId, newPrice);

        // Assert
        assertEquals(newPrice, result.getPrice());
    }

    @Test
    public void testFindByGender() {
        // Arrange
        String gender = "Female";
        List<Perfume> perfumes = new ArrayList<>();
        Perfume perfume = new Perfume();
        perfume.setId(1L);
        perfume.setGender(gender);
        perfumes.add(perfume);
        PerfumeRepository repositoryMock = mock(PerfumeRepository.class);
        when(repositoryMock.findFirstByGender(gender)).thenReturn(perfumes);
        PerfumeServiceImpl service = new PerfumeServiceImpl(repositoryMock);

        // Act
       // List<Perfume> result = service.findByGender(gender);

        // Assert
     //   assertEquals(perfumes, result);
    }
}
