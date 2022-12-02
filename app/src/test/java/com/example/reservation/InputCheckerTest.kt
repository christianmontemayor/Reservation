package com.example.reservation

import org.junit.Assert.*
import org.junit.Test


class InputCheckerTest {
    @Test
    fun canary() {
        assertTrue(true)
        assertFalse(false)
    }

    @Test
    fun isPhoneNumberValid_returnsTrue_onValidPhoneNumbers() {
        assertTrue(isPhoneNumberValid("8323505668"))
        assertTrue(isPhoneNumberValid("8323505668"))
        assertTrue(isPhoneNumberValid("18323505668"))
        assertTrue(isPhoneNumberValid("18001236789"))
        assertTrue(isPhoneNumberValid("6125322797"))
        assertTrue(isPhoneNumberValid("3127654321"))
    }

    @Test
    fun isPhoneNumberValid_returnsFalse_onInvalidPhoneNumbers() {
        assertFalse(isPhoneNumberValid("1"))
        assertFalse(isPhoneNumberValid("12"))
        assertFalse(isPhoneNumberValid("1-800-emp-toda"))
        assertFalse(isPhoneNumberValid("123456789010"))
        assertFalse(isPhoneNumberValid("1-800-366-4322"))
    }

    @Test
    fun isDateValid_returnsTrue_onValidDates() {
        assertTrue(isDateValid("12/24/02"))
        assertTrue(isDateValid("10/02/22"))
        assertTrue(isDateValid("11/2/22"))
        assertTrue(isDateValid("09/02/02"))
        assertTrue(isDateValid("9/2/02"))
    }

    @Test
    fun isDateValid_returnsFalse_onInvalidDates() {
        assertFalse(isDateValid("12/242/02"))
        assertFalse(isDateValid("140/02/22"))
        assertFalse(isDateValid("2/29/23"))
        assertFalse(isDateValid("09/34/02"))
        assertFalse(isDateValid("13/2/02"))
    }

    @Test
    fun isGuestsValid_returnsTrue_onValidGuestSize() {
        assertTrue(isGuestsValid("1"))
        assertTrue(isGuestsValid("2"))
        assertTrue(isGuestsValid("10"))
        assertTrue(isGuestsValid("20"))
        assertTrue(isGuestsValid("99"))
    }

    @Test
    fun isGuestsValid_returnsFalse_onInvalidGuestSize() {
        assertFalse(isGuestsValid("-1"))
        assertFalse(isGuestsValid("0"))
        assertFalse(isGuestsValid("100"))
        assertFalse(isGuestsValid("1012132143242131243254"))
        assertFalse(isGuestsValid("-100"))
    }
}