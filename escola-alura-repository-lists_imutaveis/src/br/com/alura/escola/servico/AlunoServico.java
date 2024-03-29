package br.com.alura.escola.servico;

import java.util.List;
import java.util.Optional;

import br.com.alura.escola.modelo.Aluno;

public class AlunoServico {

	Aluno priscila = new Aluno("Priscila", 7670832029L);
	Aluno fernando = new Aluno("Fernando", 3588807004L);
	Aluno rafael = new Aluno("Rafael", 49157745030L);
	Aluno renata = new Aluno("Renata", 82757618083L);

	public List<Aluno> listar() {
		List<Aluno> alunos = List.of(priscila, fernando, rafael, renata);
		return alunos;
	}

	public Optional<Aluno> listarAlunoPorCPF(Long cpf) {

		Optional<Aluno> aluno = listar().stream()
				.filter(a -> a.getCpf().equals(cpf))
				.findAny();

		return  aluno;

	}

}
