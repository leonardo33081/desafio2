package com.api.accountmanagement.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.account.management.controllers.TransacaoController;
import com.api.account.management.dtos.TransacaoDTO;
import com.api.account.management.repositories.ContasRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransacaoUseCaseTest {

	  	@InjectMocks
	    TransacaoController transacaoController;

	    @Mock
	    private ContasRepository contaRepository;

	    @Test
	    void depositar(){
	        //Contas conta =  Mockito.mock(Contas.class, Mockito.withSettings().defaultAnswer(Mockito.RETURNS_DEEP_STUBS));
	        TransacaoDTO transacao =  Mockito.mock(TransacaoDTO.class, Mockito.withSettings().defaultAnswer(Mockito.RETURNS_DEEP_STUBS));
	        
	        Mockito.doCallRealMethod().when(transacaoController).depositoEmConta(transacao);
			
	        assertNotNull(transacaoController.depositoEmConta(transacao));
	    }
}
