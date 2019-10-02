package com.service.impl;

import com.entity.Code;
import com.repository.CodeRepository;
import com.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CodeServiceImpl implements CodeService {

    private final CodeRepository codeRepository;

    @Autowired
    public CodeServiceImpl(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @Override
    public void addCode(Code code) {
        codeRepository.save(code);
    }

    @Override
    public Optional<Code> getCode(Long id) {
        return codeRepository.findById(id);
    }
}
