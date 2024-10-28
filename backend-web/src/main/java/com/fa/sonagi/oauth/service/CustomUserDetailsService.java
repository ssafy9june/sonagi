package com.fa.sonagi.oauth.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fa.sonagi.user.entity.Users;
import com.fa.sonagi.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findBySocialId(username);
		if (user == null) {
			throw new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다.");
		}
		return createUserDetails(user);
	}

	private UserDetails createUserDetails(Users users) {
		return new User(users.getSocialId(),
			users.getPassword(),
			users.getAuthorities());
		//  return UserPrincipal.create(user);
	}
}