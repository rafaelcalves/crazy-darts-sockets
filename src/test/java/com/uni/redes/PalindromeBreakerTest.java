package com.uni.redes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PalindromeBreakerTest {
    PalindromeBreaker palindromeBreaker = new PalindromeBreaker();

    @Test
    void shouldBrakeWhenPossible() {
        String result = palindromeBreaker.brake("bab");
        assertEquals("aab",result);
    }

    @Test
    void shouldReturnImpossible() {
        String result = palindromeBreaker.brake("aaa");
        assertEquals("IMPOSSIBLE", result);
    }

    @Test
    void shouldReturnTrueForPalidrome() {
        boolean result = palindromeBreaker.isPalindrome("aabaa");
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseForNotPalindromes() {
        boolean result = palindromeBreaker.isPalindrome("abab");
        assertFalse(result);
    }
}