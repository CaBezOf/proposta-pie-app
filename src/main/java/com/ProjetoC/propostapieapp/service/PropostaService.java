package com.ProjetoC.propostapieapp.service;

import com.ProjetoC.propostapieapp.dto.PropostaRequestDto;
import com.ProjetoC.propostapieapp.dto.PropostaResponseDto;
import com.ProjetoC.propostapieapp.entity.Proposta;
import com.ProjetoC.propostapieapp.mapper.PropostaMapper;
import com.ProjetoC.propostapieapp.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {

    private PropostaRepository propostaRepository;
    private NotificacaoRabbitService notificacaoRabbitService;
    private String exchange;

    public PropostaService(PropostaRepository propostaRepository,
                           NotificacaoRabbitService notificacaoRabbitService,
                           @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
        this.propostaRepository = propostaRepository;
        this.notificacaoRabbitService = notificacaoRabbitService;
        this.exchange = exchange;
    }

    public PropostaResponseDto criar(PropostaRequestDto requestDto){
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
        propostaRepository.save(proposta);

        notificarRabbitMQ(proposta);

        return PropostaMapper.INSTANCE.convertEntityToDto(proposta);
    }

    public void notificarRabbitMQ(Proposta proposta){
        try{
            notificacaoRabbitService.notificar(proposta, exchange);
        } catch (RuntimeException ex) {
            proposta.setIntegrada(false);
            propostaRepository.save(proposta);
        }
    }

    public List<PropostaResponseDto> obterProposta(){
        return PropostaMapper.INSTANCE.convertListEntityToListDto(propostaRepository.findAll());
    }

}
