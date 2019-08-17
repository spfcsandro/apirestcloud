package br.com.projeto.apirestaws.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projeto.apirestaws.ApirestawsApplicationTests;
import br.com.projeto.apirestcloud.model.dto.FuncionarioDTO;
import br.com.projeto.apirestcloud.resources.FuncionarioController;

public class FuncionarioControllerTest extends ApirestawsApplicationTests {

	private static final String ENDPOINT_FUNCIONARIO = "/api/funcionario";

	private MockMvc mvcMock;
	
	@Autowired
	private FuncionarioController funcionarioController;
	
	@Before
	public void setUp() {
		this.mvcMock = MockMvcBuilders.standaloneSetup(funcionarioController).build();
	}

	@Test
	public void cadastrarFuncionario_Sucesso_DeveriaCadastrarFuncionario() throws JsonProcessingException, Exception {
		FuncionarioDTO funcionario = criaRequest();
		
		mvcMock
		.perform(post(ENDPOINT_FUNCIONARIO)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(funcionario))
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().is2xxSuccessful())
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.nome").value(funcionario.getNome()))
		.andExpect(jsonPath("$.cpf").value(funcionario.getCpf().replaceAll("\\D", "")))
		.andExpect(jsonPath("$.endereco").value(funcionario.getEndereco()))
		.andExpect(jsonPath("$.matricula").exists())
		.andDo(print());
	}

	private FuncionarioDTO criaRequest() {
		return new FuncionarioDTO(null, "Sandro Andrade", "437.116.880-98", "Rua da Alvorada",null);
	}

}
