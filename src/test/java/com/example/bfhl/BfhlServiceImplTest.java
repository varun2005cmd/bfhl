package com.example.bfhl;

import com.example.bfhl.dto.BfhlRequestDto;
import com.example.bfhl.dto.BfhlResponseDto;
import com.example.bfhl.service.BfhlServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BfhlServiceImplTest {

    private BfhlServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new BfhlServiceImpl();
    }

    // ===== Example A from Requirements =====
    @Test
    void testExampleA() {
        BfhlRequestDto request = new BfhlRequestDto();
        request.setData(Arrays.asList("a", "1", "334", "4", "R", "$"));

        BfhlResponseDto response = service.process(request);

        assertTrue(response.isSuccess());
        assertEquals("varun_21092005", response.getUserId());
        assertEquals("varun.btech2023@sitpune.edu.in", response.getEmail());
        assertEquals("23070123149", response.getRollNumber());
        assertEquals(List.of("1"), response.getOddNumbers());
        assertEquals(List.of("334", "4"), response.getEvenNumbers());
        assertEquals(List.of("A", "R"), response.getAlphabets());
        assertEquals(List.of("$"), response.getSpecialCharacters());
        assertEquals("339", response.getSum());
        assertEquals("Ra", response.getConcatString());
    }

    // ===== Example B from Requirements =====
    @Test
    void testExampleB() {
        BfhlRequestDto request = new BfhlRequestDto();
        request.setData(Arrays.asList("2", "a", "y", "4", "&", "-", "*", "5", "92", "b"));

        BfhlResponseDto response = service.process(request);

        assertTrue(response.isSuccess());
        assertEquals("varun_21092005", response.getUserId());
        assertEquals("varun.btech2023@sitpune.edu.in", response.getEmail());
        assertEquals("23070123149", response.getRollNumber());
        assertEquals(List.of("5"), response.getOddNumbers());
        assertEquals(List.of("2", "4", "92"), response.getEvenNumbers());
        assertEquals(List.of("A", "Y", "B"), response.getAlphabets());
        assertEquals(List.of("&", "-", "*"), response.getSpecialCharacters());
        assertEquals("103", response.getSum());
        assertEquals("ByA", response.getConcatString());
    }

    // ===== Example C from Requirements =====
    @Test
    void testExampleC() {
        BfhlRequestDto request = new BfhlRequestDto();
        request.setData(Arrays.asList("A", "ABCD", "DOE"));

        BfhlResponseDto response = service.process(request);

        assertTrue(response.isSuccess());
        assertEquals("varun_21092005", response.getUserId());
        assertEquals("varun.btech2023@sitpune.edu.in", response.getEmail());
        assertEquals("23070123149", response.getRollNumber());
        assertEquals(List.of(), response.getOddNumbers());
        assertEquals(List.of(), response.getEvenNumbers());
        assertEquals(List.of("A", "ABCD", "DOE"), response.getAlphabets());
        assertEquals(List.of(), response.getSpecialCharacters());
        assertEquals("0", response.getSum());
        assertEquals("EoDdCbAa", response.getConcatString());
    }

    // ===== Additional Test Cases =====
    @Test
    void testOnlyNumbers() {
        BfhlRequestDto request = new BfhlRequestDto();
        request.setData(Arrays.asList("1", "2", "3", "4"));

        BfhlResponseDto response = service.process(request);

        assertTrue(response.isSuccess());
        assertEquals(List.of("1", "3"), response.getOddNumbers());
        assertEquals(List.of("2", "4"), response.getEvenNumbers());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("10", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    void testSpecialCharactersOnly() {
        BfhlRequestDto request = new BfhlRequestDto();
        request.setData(Arrays.asList("@", "#", "$"));

        BfhlResponseDto response = service.process(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertEquals(List.of("@", "#", "$"), response.getSpecialCharacters());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    void testEmptyInput() {
        BfhlRequestDto request = new BfhlRequestDto();
        request.setData(new ArrayList<>());

        BfhlResponseDto response = service.process(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    void testSingleOddNumber() {
        BfhlRequestDto request = new BfhlRequestDto();
        request.setData(Arrays.asList("5"));

        BfhlResponseDto response = service.process(request);

        assertTrue(response.isSuccess());
        assertEquals(List.of("5"), response.getOddNumbers());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertEquals("5", response.getSum());
    }

    @Test
    void testSingleEvenNumber() {
        BfhlRequestDto request = new BfhlRequestDto();
        request.setData(Arrays.asList("4"));

        BfhlResponseDto response = service.process(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertEquals(List.of("4"), response.getEvenNumbers());
        assertEquals("4", response.getSum());
    }

    @Test
    void testMultiCharacterAlphabets() {
        BfhlRequestDto request = new BfhlRequestDto();
        request.setData(Arrays.asList("hello", "world"));

        BfhlResponseDto response = service.process(request);

        assertTrue(response.isSuccess());
        assertEquals(List.of("HELLO", "WORLD"), response.getAlphabets());
        // HELLOWORLD reversed = DLROWOLLEH, alternating caps = DlRoWoLlEh
        assertEquals("DlRoWoLlEh", response.getConcatString());
    }

    @Test
    void testLargeNumbers() {
        BfhlRequestDto request = new BfhlRequestDto();
        request.setData(Arrays.asList("999999", "123456", "1"));

        BfhlResponseDto response = service.process(request);

        assertTrue(response.isSuccess());
        // 999999 is odd, 123456 is even, 1 is odd
        assertEquals(List.of("999999", "1"), response.getOddNumbers());
        assertEquals(List.of("123456"), response.getEvenNumbers());
        assertEquals("1123456", response.getSum()); // 999999 + 123456 + 1 = 1123456
    }

    @Test
    void testMixedCaseAlphabets() {
        BfhlRequestDto request = new BfhlRequestDto();
        request.setData(Arrays.asList("aBc", "DeF", "g"));

        BfhlResponseDto response = service.process(request);

        assertTrue(response.isSuccess());
        assertEquals(List.of("ABC", "DEF", "G"), response.getAlphabets());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
    }

    @Test
    void testAlphabeticStringReversal() {
        // Test: alphabets = ["A", "B"] -> combined = "AB" -> reversed = "BA" -> alternating = "Ba"
        BfhlRequestDto request = new BfhlRequestDto();
        request.setData(Arrays.asList("A", "B"));

        BfhlResponseDto response = service.process(request);

        assertTrue(response.isSuccess());
        assertEquals("Ba", response.getConcatString());
    }

    @Test
    void testSingleAlphabet() {
        BfhlRequestDto request = new BfhlRequestDto();
        request.setData(Arrays.asList("x"));

        BfhlResponseDto response = service.process(request);

        assertTrue(response.isSuccess());
        assertEquals(List.of("X"), response.getAlphabets());
        assertEquals("X", response.getConcatString());
    }

    @Test
    void testZeroValue() {
        BfhlRequestDto request = new BfhlRequestDto();
        request.setData(Arrays.asList("0", "1", "2"));

        BfhlResponseDto response = service.process(request);

        assertTrue(response.isSuccess());
        assertEquals(List.of("1"), response.getOddNumbers());
        assertEquals(List.of("0", "2"), response.getEvenNumbers());
        assertEquals("3", response.getSum());
    }

    @Test
    void testResponseMetadata() {
        BfhlRequestDto request = new BfhlRequestDto();
        request.setData(Arrays.asList("a", "1"));

        BfhlResponseDto response = service.process(request);

        assertTrue(response.isSuccess());
        assertEquals("varun_21092005", response.getUserId());
        assertEquals("varun.btech2023@sitpune.edu.in", response.getEmail());
        assertEquals("23070123149", response.getRollNumber());
    }
}
