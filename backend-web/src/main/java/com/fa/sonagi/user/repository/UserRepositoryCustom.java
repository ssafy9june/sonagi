package com.fa.sonagi.user.repository;

import com.fa.sonagi.user.dto.NameDto;

public interface UserRepositoryCustom {
	NameDto findName(Long userId);
}
