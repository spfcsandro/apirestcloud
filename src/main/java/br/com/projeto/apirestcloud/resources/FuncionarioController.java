package br.com.projeto.apirestcloud.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.apirestcloud.model.dto.FuncionarioDTO;
import br.com.projeto.apirestcloud.service.FuncionarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "Funcionario Endpoint", tags = {"Funcionario-Endpoint"},  description = "Crud Funcionario")
@RestController
@RequestMapping("/api")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;
	
	@ApiOperation(value = "Listar todos os Funcionários")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "page", dataType = "string", paramType = "query",
                value = "Results page you want to retrieve (0..N)"),
        @ApiImplicitParam(name = "size", dataType = "string", paramType = "query",
                value = "Number of records per page."),
        @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                value = "Sorting criteria in the format: property(,asc|desc). " +
                        "Default sort order is ascending. " +
                        "Multiple sort criteria are supported.")
	})
	@GetMapping("/funcionario")
    public Page<FuncionarioDTO> listarFuncionario(@RequestParam(required = false) String nome, 
    		@PageableDefault(sort="nome", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		
		if(nome == null) {
			return FuncionarioDTO.converter(funcionarioService.findAll(paginacao));
		}else {
			return FuncionarioDTO.converter(funcionarioService.findByNome(nome,paginacao));
		}
    }
	
	@ApiOperation(value = "Buscar um Funcionário")
	@GetMapping("funcionario/{id}")
	public ResponseEntity<FuncionarioDTO> retornaFuncionarioPorId(@PathVariable Long id) {
		return ResponseEntity.ok(new FuncionarioDTO(funcionarioService.getOne(id)));
	}
	
	@ApiOperation(value = "Salvar Funcionário")
	@PostMapping("/funcionario")
	public ResponseEntity<FuncionarioDTO> salvarFuncionario(@RequestBody @Valid FuncionarioDTO funcionarioDTO) {
		return ResponseEntity.ok(new FuncionarioDTO(funcionarioService.salvar(funcionarioDTO)));
	}
	
	@ApiOperation(value = "Atualizar Funcionário")
	@PutMapping("/funcionario/{id}")
	public ResponseEntity<FuncionarioDTO> atualizarFuncionario(@PathVariable Long id, @RequestBody @Valid FuncionarioDTO funcionarioDTO) {
		return ResponseEntity.ok(new FuncionarioDTO(funcionarioService.atualizar(id, funcionarioDTO)));
	}
	
	@ApiOperation(value = "Excluir Funcionário")
	@DeleteMapping("/funcionario/{id}")
	public ResponseEntity<?> deletarFuncionario(@PathVariable Long id){
		funcionarioService.deletar(id);
		return ResponseEntity.ok().build();
	}
	
}
