/*
 * SteVe - SteckdosenVerwaltung - https://github.com/steve-community/steve
 * Copyright (C) 2013-2024 SteVe Community Team
 * All Rights Reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package de.rwth.idsg.steve.web.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Sevket Goekay <sevketgokay@gmail.com>
 * @since 01.08.2024
 */
public class ChargeBoxIdValidatorTest {

    ChargeBoxIdValidator validator = new ChargeBoxIdValidator();

    @Test
    public void testNull() {
        Assertions.assertTrue(validator.isValid(null, null));
    }

    @Test
    public void testAllLowercaseLetters() {
        Assertions.assertTrue(validator.isValid("test", null));
    }

    @Test
    public void testAllUppercaseLetters() {
        Assertions.assertTrue(validator.isValid("TEST", null));
    }

    @Test
    public void testMixedCaseLetters() {
        Assertions.assertTrue(validator.isValid("TesT", null));
        Assertions.assertTrue(validator.isValid("tEst", null));
    }

    @Test
    public void testLettersAndNumbers() {
        Assertions.assertTrue(validator.isValid("test12", null));
        Assertions.assertTrue(validator.isValid("89test", null));
        Assertions.assertTrue(validator.isValid("te9s0t", null));
    }

    @Test
    public void testDot() {
        Assertions.assertTrue(validator.isValid(".test", null));
        Assertions.assertTrue(validator.isValid("test.", null));
        Assertions.assertTrue(validator.isValid("te..st", null));
    }

    @Test
    public void testDash() {
        Assertions.assertTrue(validator.isValid("-test", null));
        Assertions.assertTrue(validator.isValid("test-", null));
        Assertions.assertTrue(validator.isValid("te--st", null));
    }

    @Test
    public void testUnderscore() {
        Assertions.assertTrue(validator.isValid("_test", null));
        Assertions.assertTrue(validator.isValid("test_", null));
        Assertions.assertTrue(validator.isValid("te__st", null));
    }

    @Test
    public void testColon() {
        Assertions.assertTrue(validator.isValid(":test", null));
        Assertions.assertTrue(validator.isValid("test:", null));
        Assertions.assertTrue(validator.isValid("te::st", null));
        Assertions.assertTrue(validator.isValid("VID:00XXXXXXXXXX", null));
    }

    @Test
    public void testPoundSign() {
        Assertions.assertTrue(validator.isValid("#test", null));
        Assertions.assertTrue(validator.isValid("test#", null));
        Assertions.assertTrue(validator.isValid("te##st", null));
        Assertions.assertTrue(validator.isValid("#FreeCharging", null));
    }

    @Test
    public void testCombined() {
        Assertions.assertTrue(validator.isValid("1t.E-S_:t20#", null));
    }

    @Test
    public void testEmpty() {
        Assertions.assertFalse(validator.isValid("", null));
    }

    @Test
    public void testSpace() {
        Assertions.assertFalse(validator.isValid("  ", null));
    }

    @Test
    public void testSpaceAtBeginning() {
        Assertions.assertFalse(validator.isValid(" test", null));
    }

    @Test
    public void testSpaceAtEnd() {
        Assertions.assertFalse(validator.isValid("test ", null));
    }

    @Test
    public void testOpeningParenthesis() {
        Assertions.assertFalse(validator.isValid("te(st", null));
    }

    @Test
    public void testClosingParenthesis() {
        Assertions.assertFalse(validator.isValid("te)st", null));
    }

    @Test
    public void testBiggerSymbol() {
        Assertions.assertFalse(validator.isValid("te>st", null));
    }

    @Test
    public void testSmallerSymbol() {
        Assertions.assertFalse(validator.isValid("te<st", null));
    }

    @Test
    public void testSlash() {
        Assertions.assertFalse(validator.isValid("te/st", null));
    }

    @Test
    public void testEquals() {
        Assertions.assertFalse(validator.isValid("te=st", null));
    }
}