package com.adidaschallenge.newsletterpublicapi.beans;

import java.time.Instant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class NewsletterSubscription {
	
	@Email(message="Email is not valid")
	@NotNull(message="Email cannot be missing or empty")
	@ApiModelProperty(value = "User valid email", required = true)
	private String email;
	
	@ApiModelProperty(value = "User first name", required = false)
	private String firstName;
	
	@ApiModelProperty(value = "User gender (M|F)", required = false)
	private char gender;
	
    @NotNull(message="Date of birth cannot be missing or empty")
    @ApiModelProperty(value = "User date of birth", required = true)
	private Instant dateOfBirth;
    

    @NotNull(message="Consent is not checked")
    @ApiModelProperty(value = "User consent", required = true)
	private boolean consent;
    
    @NotNull(message="Newsletter id cannot be missing or empty")
    @ApiModelProperty(value = "Newsleter id that the user wants to subscribe", required = true)
	private int newsletterId;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public Instant getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Instant dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public boolean isConsent() {
		return consent;
	}
	public void setConsent(boolean consent) {
		this.consent = consent;
	}
	public int getNewsletterId() {
		return newsletterId;
	}
	public void setNewsletterId(int newsletterId) {
		this.newsletterId = newsletterId;
	}
}
