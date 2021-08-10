package com.evolution.ddasoop.service;

import com.evolution.ddasoop.domain.UserRepository;
import com.evolution.ddasoop.web.dto.UserMainResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserMainResponseDto getMain(Long userIdx){
        return new UserMainResponseDto();
    }
}
