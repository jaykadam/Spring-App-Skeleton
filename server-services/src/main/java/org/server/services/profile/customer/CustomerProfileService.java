package org.server.services.profile.customer;

import java.util.List;

import org.server.core.dtos.social.SocialDetailDTO;
import org.server.core.entities.profile.common.Profile;
import org.server.core.entities.profile.customer.CustomerProfile;
import org.server.core.entities.social.SocialUser;
import org.server.core.entities.user.User;
import org.server.core.enums.profile.CustomerProfileType;
import org.server.core.repositories.profile.CustomerProfileRepository;
import org.server.services.abstracts.DefaultJpaServiceImpl;
import org.server.services.abstracts.JpaService;
import org.server.services.profile.ProfileService;
import org.server.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerProfileService extends DefaultJpaServiceImpl<CustomerProfile, Long, CustomerProfileRepository>
		implements JpaService<CustomerProfile, Long> {
	@Autowired
	private CustomerProfileRepository customerProfileRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private ProfileService profileService;

	public ProfileService getProfileService() {
		return profileService;
	}

	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public CustomerProfileRepository getCustomerProfileRepository() {
		return customerProfileRepository;
	}

	public void setCustomerProfileRepository(CustomerProfileRepository customerProfileRepository) {
		this.customerProfileRepository = customerProfileRepository;
	}

	public List<CustomerProfile> findByProfileUser(User user) {
		return getCustomerProfileRepository().findByProfileUser(user);
	}

	public List<CustomerProfile> findByProfile(Profile profile) {
		return getCustomerProfileRepository().findByProfile(profile);
	}

	public List<CustomerProfile> findByUsername(String username) {
		User user = getUserService().getLoggedInUser();
		return getCustomerProfileRepository().findByProfileUser(user);
	}

	public CustomerProfile createNewCustomerProfile(CustomerProfile customerProfile) {
		Profile profile = getProfileService().createNewProfile(customerProfile.getProfile());
		customerProfile.setProfile(profile);
		customerProfile = getCustomerProfileRepository().save(customerProfile);
		return customerProfile;
	}

	public CustomerProfile updateCustomerProfile(CustomerProfile customerProfile) {
		Profile profile = getProfileService().updateProfile(customerProfile.getProfile());
		customerProfile.setProfile(profile);
		customerProfile = getCustomerProfileRepository().save(customerProfile);
		return customerProfile;
	}

	public CustomerProfile createNewCustomerProfileFromSocialUser(SocialUser socialUser,
			SocialDetailDTO socialDetailDTO) {
		Profile profile = getProfileService().createNewProfileForSocialUser(socialUser, socialDetailDTO);
		CustomerProfile customerProfile = new CustomerProfile();
		customerProfile.setProfile(profile);
		customerProfile.setCustomerProfileType(CustomerProfileType.RETAIL_CUSTOMER_PROFILE);
		customerProfile = create(customerProfile);
		return customerProfile;
	}
}
