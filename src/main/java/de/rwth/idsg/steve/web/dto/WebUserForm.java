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
package de.rwth.idsg.steve.web.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;


/**
 * @author fnkbsi
 * @since 01.04.2022
 */
@Getter
public class WebUserForm extends WebUserBaseForm {

    // @NotNull(message = "Password is required")
    // @Size(min = 8, message = "Password requires 8 or more characters")
    private String password = "";

    // @NotNull(message = "Password repetition is required")
    // @Size(min = 8, message = "The repeated password also requires 8 or more characters")
    private String passwordComparison;

    @AssertTrue(message = "The repeated password did not match!")
    private Boolean pwError;

    private String apiPassword = "";

    // @NotNull(message = "API Password repetition is required")
    private String apiPasswordComparison;

    @AssertTrue(message = "The repeated API password did not match!")
    private Boolean apiPwError;

    public void setPassword(String password) {
        validatePassword();
        this.password = password;
    }

    public void setPasswordComparison(String passwordComparison) {
        this.passwordComparison = passwordComparison;
        if (passwordComparison != null) {
            this.pwError = passwordComparison.equals(this.password);
        }
    }

    public void setApiPassword(String apiPassword) {
        validatePassword();
        this.apiPassword = apiPassword;
    }

    public String getApiPassword() {
        return apiPassword;
    }

    public void setApiPasswordComparison(String apiPasswordComparison) {
        this.apiPasswordComparison = apiPasswordComparison;
        if (apiPasswordComparison != null) {
            this.apiPwError = apiPasswordComparison.equals(this.apiPassword);
        }
    }

    public String getApiPasswordComparison() {
        return apiPasswordComparison;
    }

    private void validatePassword() {
        if (this.password != null && !this.password.isEmpty()) {
            if (this.password.length() < 8) {
                throw new IllegalArgumentException("Password requires 8 or more characters");
            }
            if (this.passwordComparison == null || this.passwordComparison.isEmpty()) {
                throw new IllegalArgumentException("Password repetition is required");
            }
            this.pwError = this.password.equals(this.passwordComparison);
            if (!this.pwError) {
                throw new IllegalArgumentException("The repeated password did not match!");
            }
        }
    }
    
    private void validateApiPassword() {
        if (this.apiPassword != null && !this.apiPassword.isEmpty()) {
            if (this.apiPassword.length() < 8) {
                throw new IllegalArgumentException("ApiPassword requires 8 or more characters");
            }
            if (this.apiPasswordComparison == null || this.apiPasswordComparison.isEmpty()) {
                throw new IllegalArgumentException("ApiPassword repetition is required");
            }
            this.apiPwError = this.apiPassword.equals(this.apiPasswordComparison);
            if (!this.apiPwError) {
                throw new IllegalArgumentException("The repeated ApiPassword did not match!");
            }
        }
    }
}