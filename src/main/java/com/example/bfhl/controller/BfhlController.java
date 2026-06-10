package com.example.bfhl.controller;

import com.example.bfhl.dto.BfhlRequestDto;
import com.example.bfhl.dto.BfhlResponseDto;
import com.example.bfhl.service.BfhlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping
    public ResponseEntity<BfhlResponseDto> handlePost(@RequestBody BfhlRequestDto request) {
        BfhlResponseDto response = bfhlService.process(request);
        return ResponseEntity.ok(response);
    }
}
