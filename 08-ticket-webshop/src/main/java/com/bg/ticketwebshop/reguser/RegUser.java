package com.bg.ticketwebshop.reguser;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.bg.ticketwebshop.validation.FieldMatch;

/**
 * @author birog
 *
 * Here we use the annotations and classes what we defined in the validation package.
 * @FieldMatch annotation checkes the password and matchingPassword fields, because they have to
 * match. If these are not match together, the message will appear.
 */
@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match!")
})
public class RegUser {

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String username;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String password;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String matchingPassword;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String firstName;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String lastName;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String email;
	
	@NotNull(message = "is required")
	@Min(value=18, message = "At least 18 years old age is required.")
	private Long age;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String sex;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public RegUser() {}

	public RegUser(@NotNull(message = "is required") @Size(min = 1, message = "is required") String username,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String password,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String matchingPassword,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String firstName,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String lastName,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String email,
			Long age,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String sex) {
		this.username = username;
		this.password = password;
		this.matchingPassword = matchingPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "\n>>>>> RegUser [username=" + username + ", password=" + password + ", matchingPassword=" + matchingPassword
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", age=" + age + ", sex="
				+ sex + "]\n";
	}
	
	
}
