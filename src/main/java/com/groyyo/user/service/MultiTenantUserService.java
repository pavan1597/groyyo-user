package com.groyyo.user.service;


import com.groyyo.user.config.TenantIdentifierResolver;
import com.groyyo.user.model.Users;
import com.groyyo.user.model.UserRepository;
import com.groyyo.user.model.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;

//
//this class is used when multitenancy is configured
 class MultiTenantUserService {


	@Autowired
	UserRepository userRepository;

	@Autowired TransactionTemplate txTemplate;

	@Autowired TenantIdentifierResolver currentTenant;



	private Users createUser(String tenantId , UserRequest userRequest) {

		currentTenant.setCurrentTenant(tenantId);

		Users users = Users.builder().
				firstName(userRequest.getFirstName()).
				fullName(userRequest.getFullName()).
				emailId(userRequest.getEmailId()).
				phone(userRequest.getPhone()).build();

		return txTemplate.execute(tx -> {
			return userRepository.save(users);
		});
	}
}
