package br.edu.ifba.admindisciplinasenotas.servicos;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifba.admindisciplinasenotas.dtos.DisciplinaDTO;
import br.edu.ifba.admindisciplinasenotas.dtos.DisciplinaForm;
import br.edu.ifba.admindisciplinasenotas.dtos.NotaDTO;
import br.edu.ifba.admindisciplinasenotas.dtos.NotaForm;
import br.edu.ifba.admindisciplinasenotas.entidades.Disciplina;
import br.edu.ifba.admindisciplinasenotas.entidades.Nota;
import br.edu.ifba.admindisciplinasenotas.repositorios.DisciplinaRepository;
import br.edu.ifba.admindisciplinasenotas.repositorios.NotaRepository;

@Service
public class DisciplinaService {

	private DisciplinaRepository disciplinaRepository;
	private NotaRepository notaRepository;
	
	public DisciplinaService(DisciplinaRepository disciplinaRepository, NotaRepository notaRepository) {
		super();
		this.disciplinaRepository = disciplinaRepository;
		this.notaRepository = notaRepository;
	}

	public List<DisciplinaDTO> listar() {
		return this.disciplinaRepository.findAll().stream().map(DisciplinaDTO::new).toList();
	}
	
	public DisciplinaDTO listarPorID(Long ID) {
		return new DisciplinaDTO(this.disciplinaRepository.getById(ID));
	}
	
	public DisciplinaForm cadastrar(DisciplinaForm disciplinaForm) {
		Disciplina disciplina = this.disciplinaRepository.save(new Disciplina(disciplinaForm));
		return new DisciplinaForm(disciplina);
	}
	
	public DisciplinaDTO deletarPorID(Long ID) {
		DisciplinaDTO disciplinaDTO = listarPorID(ID);
		this.disciplinaRepository.deleteById(ID);
		return disciplinaDTO;
	}
	
	public DisciplinaForm atualizarPorID(Long ID, DisciplinaForm disciplinaForm) {
		Disciplina disciplina = this.disciplinaRepository.getById(ID);
		if(disciplinaForm.nome() != null)
			disciplina.setNome(disciplinaForm.nome());
		if(disciplinaForm.codigo() != null)
			disciplina.setCodigo(disciplinaForm.codigo());
		return new DisciplinaForm(disciplina);
	}
	
	public List<NotaDTO> listarNotasPorDisciplina(Long ID) {
		Disciplina disciplina = this.disciplinaRepository.getById(ID);
		return this.notaRepository.getByDisciplina(disciplina).stream().map(NotaDTO::new).toList();
	}
	
	public NotaForm cadastrarNota(Long ID, NotaForm form) {
		Disciplina disciplina = this.disciplinaRepository.getById(ID);
		Nota nota = this.notaRepository.save(new Nota(form, disciplina));
		return new NotaForm(nota);
	} 
}
