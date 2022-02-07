package com.bg.ticketwebshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="account")
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Pattern(regexp="[0-9]{8}-[0-9]{8}-[0-9]{8}")
	@NotNull(message="Account number is required!")
	@Column(name="account_number")
	private String accountNumber;
	
	@Min(value=1, message="Amount have to be a positive number!")
	@Column(name="account_amount")
	private Double accountAmount;
	
	@NotNull(message="Currency is required! (Pattern: 3 capital letters: XXX")
	@Pattern(regexp="^[A-Z]{3}")
	@Column(name="currency")
	private String currency;
	
	
	@Min(100)
	@Max(999)
	@Column(name="cvc_code")
	private Integer cvcCode;
	
	@Column(name="user_id")
	private Long userId;
	/**
	 *	Set up the 1:1 two way data-connection between the tables, but
	 *	we would like to avoide, if we delete an account then delete the user.
	 *	So if we delete an account, the user will remain unchanged.  
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(Double accountAmount) {
		this.accountAmount = accountAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getCvcCode() {
		return cvcCode;
	}

	public void setCvcCode(Integer cvcCode) {
		this.cvcCode = cvcCode;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
	
	public Account(
			@NotNull(message = "Account number field is required") @Size(min = 4) @Valid String accountNumber,
			@Min(value = 0, message = "Amount have to be positive") @Valid Double accountAmount,
			@NotNull(message = "Currency field is required") @Size(min = 1) @Valid String currency,
			@NotNull(message = "CVC code field is required") @Valid @Min(value = 0, message = "CVC have to be positive number") Integer cvcCode,
			Long userId) {
		this.accountNumber = accountNumber;
		this.accountAmount = accountAmount;
		this.currency = currency;
		this.cvcCode = cvcCode;
		this.userId = userId;
	}

	public Account() {}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountNumber=" + accountNumber + ", accountAmount=" + accountAmount
				+ ", currency=" + currency + ", cvcCode=" + cvcCode + ", userId=" + userId + "]";
	}
	
}
