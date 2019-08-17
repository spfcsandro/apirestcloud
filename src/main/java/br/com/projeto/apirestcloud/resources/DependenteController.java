package br.com.projeto.apirestcloud.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.apirestcloud.model.dto.DependenteDTO;
import br.com.projeto.apirestcloud.service.DependenteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Dependente Endpoint", tags = {"Dependente-Endpoint"},  description = "Crud Dependente")
@RestController
@RequestMapping("/api")
public class DependenteController{

	@Autowired
	private DependenteService dependenteService;
	
	@ApiOperation(value = "Listar todos os Dependentes")
	@GetMapping("/funcionario/{idFuncionario}/dependentes")
    public List<DependenteDTO> listarDependentesPorFuncionario(@PathVariable Long idFuncionario) {
		return DependenteDTO.converter(dependenteService.findByFuncionarioId(idFuncionario));
    }

	@ApiOperation(value = "Salvar Dependente")
	@PostMapping("/funcionario/{idFuncionario}/dependentes")
	public ResponseEntity<DependenteDTO> salvarDependente(@PathVariable Long idFuncionario,@RequestBody @Valid DependenteDTO dependenteDTO) {
		return ResponseEntity.ok(new DependenteDTO(dependenteService.salvar(idFuncionario, dependenteDTO)));
	}
	
	@ApiOperation(value = "Atualizar Dependente")
	@PutMapping("/funcionario/{idFuncionario}/dependentes/{idDependente}")
	public ResponseEntity<DependenteDTO> atualizarDependente(@PathVariable Long idFuncionario, @PathVariable Long idDependente, @RequestBody @Valid DependenteDTO dependenteDTO) {
		return ResponseEntity.ok(new DependenteDTO(dependenteService.atualizar(idFuncionario, idDependente, dependenteDTO)));
	}
	
	@ApiOperation(value = "Excluir Dependente")
	@DeleteMapping("/funcionario/{idFuncionario}/dependentes/{idDependente}")
	public ResponseEntity<?> deletarDependente(@PathVariable Long idFuncionario, @PathVariable Long idDependente){
		dependenteService.deletar(idFuncionario, idDependente);
		return ResponseEntity.ok().build();
	}
}
