package com.evolution.ddasoop.service;

import com.evolution.ddasoop.domain.Forest;
import com.evolution.ddasoop.domain.ForestRepository;
import com.evolution.ddasoop.web.dto.MyForestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ForestService {

    private final ForestRepository forestRepository;

    public List<Forest> findAll(){
        return forestRepository.findAll();
    }



}
