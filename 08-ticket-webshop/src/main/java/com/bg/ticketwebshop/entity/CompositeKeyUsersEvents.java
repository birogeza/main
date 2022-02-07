package com.bg.ticketwebshop.entity;

import java.io.Serializable;

/**
 * 
 * CompositeKey class need to create the composite class with more ID-s, to avoid
 * to get from the sql database a repeated record instead of all records. This
 * composite key used by the UsersEvents Entity class, what is the POJO for the 
 * users_events view query records.
 * 
 * @author birog
 *
 */

public class CompositeKeyUsersEvents implements Serializable {

	private Long customerId;
	
	private Long providerId;
	
	private Long eventId;
	
	private Long seatId;
	
}
