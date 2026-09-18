package com.mycompany.main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * JUnit 5 tests for the static validation/login-status methods in Main.java.
 *
 * NOTE: loginUser() reads repeatedly from System.in (via the shared Scanner)
 * whenever the credentials don't match, so it cannot be unit-tested for the
 * "incorrect credentials" path without hanging/blocking on stdin. Only the
 * "correct credentials" path (which returns immediately, before any read)
 * is exercised here. registerUser() is not tested because it is driven
 * entirely by console input and has no return value to assert against.
 */
class MainTest {

    @BeforeEach
    void resetRegisteredDetails() {
        // Reset shared static state before each test so tests don't
        // interfere with one another.
        Main.registeredUsername = null;
        Main.registeredPassword = null;
        Main.registeredCellphone = null;
    }

    @AfterEach
    void cleanUp() {
        Main.registeredUsername = null;
        Main.registeredPassword = null;
        Main.registeredCellphone = null;
    }

    // ---------- checkUserName ----------

    @Test
    @DisplayName("Valid username: 5 chars, contains underscore")
    void checkUserName_valid() {
        assertTrue(Main.checkUserName("ab_cd"));
    }

    @Test
    @DisplayName("Invalid username: correct length but no underscore")
    void checkUserName_noUnderscore() {
        assertFalse(Main.checkUserName("abcde"));
    }

    @Test
    @DisplayName("Invalid username: contains underscore but wrong length")
    void checkUserName_wrongLength() {
        assertFalse(Main.checkUserName("ab_"));
        assertFalse(Main.checkUserName("abc_defg"));
    }

    @Test
    @DisplayName("Invalid username: empty string")
    void checkUserName_empty() {
        assertFalse(Main.checkUserName(""));
    }

    // ---------- checkPasswordComplexity ----------

    @Test
    @DisplayName("Valid password: meets all complexity rules")
    void checkPasswordComplexity_valid() {
        assertTrue(Main.checkPasswordComplexity("Abcdef1!"));
    }

    @Test
    @DisplayName("Invalid password: too short")
    void checkPasswordComplexity_tooShort() {
        assertFalse(Main.checkPasswordComplexity("Ab1!"));
    }

    @Test
    @DisplayName("Invalid password: missing uppercase")
    void checkPasswordComplexity_missingUppercase() {
        assertFalse(Main.checkPasswordComplexity("abcdef1!"));
    }

    @Test
    @DisplayName("Invalid password: missing lowercase")
    void checkPasswordComplexity_missingLowercase() {
        assertFalse(Main.checkPasswordComplexity("ABCDEF1!"));
    }

    @Test
    @DisplayName("Invalid password: missing digit")
    void checkPasswordComplexity_missingDigit() {
        assertFalse(Main.checkPasswordComplexity("Abcdefg!"));
    }

    @Test
    @DisplayName("Invalid password: missing special character")
    void checkPasswordComplexity_missingSpecialChar() {
        assertFalse(Main.checkPasswordComplexity("Abcdefg1"));
    }

    @Test
    @DisplayName("Invalid password: empty string")
    void checkPasswordComplexity_empty() {
        assertFalse(Main.checkPasswordComplexity(""));
    }

    // ---------- checkCellPhoneNumber ----------

    @Test
    @DisplayName("Valid cellphone number: +27 followed by 9 digits")
    void checkCellPhoneNumber_valid() {
        assertTrue(Main.checkCellPhoneNumber("+27821234567"));
    }

    @Test
    @DisplayName("Invalid cellphone number: missing +27 prefix")
    void checkCellPhoneNumber_missingPrefix() {
        assertFalse(Main.checkCellPhoneNumber("0821234567"));
    }

    @Test
    @DisplayName("Invalid cellphone number: too few digits after +27")
    void checkCellPhoneNumber_tooFewDigits() {
        assertFalse(Main.checkCellPhoneNumber("+2782123456"));
    }

    @Test
    @DisplayName("Invalid cellphone number: too many digits after +27")
    void checkCellPhoneNumber_tooManyDigits() {
        assertFalse(Main.checkCellPhoneNumber("+278212345678"));
    }

    @Test
    @DisplayName("Invalid cellphone number: contains non-digit characters")
    void checkCellPhoneNumber_nonDigitChars() {
        assertFalse(Main.checkCellPhoneNumber("+2782a234567"));
    }

    @Test
    @DisplayName("Invalid cellphone number: empty string")
    void checkCellPhoneNumber_empty() {
        assertFalse(Main.checkCellPhoneNumber(""));
    }

    // ---------- returnloginStatus ----------

    @Test
    @DisplayName("Login status message when login succeeded")
    void returnloginStatus_success() {
        assertEquals("Welcome, it is great to sse you again.", Main.returnloginStatus(true));
    }

    @Test
    @DisplayName("Login status message when login failed")
    void returnloginStatus_failure() {
        assertEquals("Username or password incorrect, please try again.", Main.returnloginStatus(false));
    }

    // ---------- loginUser (success path only) ----------

    @Test
    @DisplayName("loginUser returns true immediately when credentials match registered details")
    void loginUser_correctCredentials_returnsTrue() {
        Main.registeredUsername = "ab_cd";
        Main.registeredPassword = "Abcdef1!";

        assertTrue(Main.loginUser("ab_cd", "Abcdef1!"));
    }
}
