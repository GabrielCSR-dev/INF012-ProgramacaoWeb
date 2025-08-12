package br.edu.ifba.admindisciplinasenotas.servicos;

import org.springframework.stereotype.Service;

import br.edu.ifba.admindisciplinasenotas.dtos.DisciplinaForm;
import br.edu.ifba.admindisciplinasenotas.dtos.NotaDTO;
import br.edu.ifba.admindisciplinasenotas.dtos.NotaForm;
import br.edu.ifba.admindisciplinasenotas.entidades.Nota;
import br.edu.ifba.admindisciplinasenotas.repositorios.DisciplinaRepository;
import br.edu.ifba.admindisciplinasenotas.repositorios.NotaRepository;

@Service
public class NotaService {

	private NotaRepository notaRepository;
	private DisciplinaRepository disciplinaRepository;
	
	public NotaService(NotaRepository notaRepository, DisciplinaRepository disciplinaRepository) {
		super();
		this.notaRepository = notaRepository;
		this.disciplinaRepository = disciplinaRepository;
	}

	public NotaForm atualizarNota(Long ID, NotaForm form) {
		Nota nota = this.notaRepository.getById(ID);
		DisciplinaForm disciplina = form.disciplina();
		nota.setNota(form.nota());
		if(form.aluno() != null) 
			nota.setAluno(form.aluno());
		if(disciplina != null && this.disciplinaRepository.existsById(disciplina.ID()))
			nota.setDisciplina(this.disciplinaRepository.getById(form.disciplina().ID()));
		return new NotaForm(nota);
	}
	
	public NotaDTO deletarNota(Long ID) {
		NotaDTO notaDTO = new NotaDTO(this.notaRepository.getById(ID));
		this.notaRepository.deleteById(ID);
		return notaDTO;
	}
}
