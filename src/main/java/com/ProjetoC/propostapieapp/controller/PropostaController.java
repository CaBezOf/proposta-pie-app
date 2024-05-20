package com.ProjetoC.propostapieapp.controller;

import com.ProjetoC.propostapieapp.dto.PropostaRequestDto;
import com.ProjetoC.propostapieapp.dto.PropostaResponseDto;
import com.ProjetoC.propostapieapp.service.PropostaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/proposta")
public class PropostaController {

    private PropostaService propostaService;

    @PostMapping
    public ResponseEntity<PropostaResponseDto> criar(@RequestBody PropostaRequestDto requestDto){
        PropostaResponseDto response = propostaService.criar(requestDto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri()).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PropostaResponseDto>> obterResposta() {
        return ResponseEntity.ok(propostaService.obterProposta());
    }

}
