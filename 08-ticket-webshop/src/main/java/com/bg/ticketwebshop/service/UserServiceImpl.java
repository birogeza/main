package com.bg.ticketwebshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bg.ticketwebshop.config.IAuthenticationFacade;
import com.bg.ticketwebshop.entity.Role;
import com.bg.ticketwebshop.entity.User;
import com.bg.ticketwebshop.reguser.RegUser;
import com.bg.ticketwebshop.repo.RoleRepository;
import com.bg.ticketwebshop.repo.UserRepository;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	// Variables and SETTER injections
	
	private IAuthenticationFacade iaf;
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public void setIAuthenticationFacade(IAuthenticationFacade iaf){
		this.iaf = iaf;
	}
	
	//METHODS:
	
	@Override
	@Transactional
	public User findByUserName(String username) {
		// check the database if the user already exists
		return userRepo.findByUsername(username);
	}

	@Override
	@Transactional
	public void save(RegUser regUser) {
		User user = new User();
		 // assign user details to the user object
		user.setUsername(regUser.getUsername());
		user.setPassword(passwordEncoder.encode(regUser.getPassword()));
		user.setFirstName(regUser.getFirstName());
		user.setLastName(regUser.getLastName());
		user.setEmail(regUser.getEmail());
		user.setAge(regUser.getAge());
		user.setSex(regUser.getSex());

		// give user default role of "employee"
		user.setRoles(Arrays.asList(roleRepo.findRoleByName("ROLE_USER")));

		 // save user in the database
		userRepo.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
	/**
	 * Public method what helps to identify the logged user id.
	 * First get the userName from IAuthenticationFacade (Spring Security)
	 * Then get the user based on userName.
	 * Finally identify the userId, and return it back.
	 */
	
	@Override
	public Long getLoggedUserId() {
		String userName = iaf.loggedUserName();
		User loggedUser = userRepo.findByUsername(userName); 
		return loggedUser.getId();
	}
	
	@Override
	@Transactional
	public User findById(Integer userId) {
		Optional<User> result = userRepo.findById(userId); 
		User theUser = null;
		if(result.isPresent()) {
			theUser = result.get();
		}else {
			throw new RuntimeException("There is no user for this userId: " + userId);
		}
		return theUser; 
	}
	
	public User getLoggedUser() {
		long lid = getLoggedUserId();
		int i = (int)lid;
		return findById(i);
	}
}
