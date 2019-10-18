package br.com.alura.escola;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.alura.escola.modelo.Curso;
import br.com.alura.escola.modelo.Turma;
import br.com.alura.escola.servico.AlunoServico;
import br.com.alura.escola.servico.TurmaServico;

public class Principal {

	public static void main(String...strings) {

		AlunoServico alunoServico = new AlunoServico();
		TurmaServico turmaServico = new TurmaServico();
		//alunoServico.listar().stream().forEach(System.out::println);
		
		List<String> alunos = alunoServico.listar().stream()
				.flatMap(aluno -> Stream.ofNullable(aluno.getNome()))
				.map(aluno -> aluno.toUpperCase())
				.collect(Collectors.toList());


		Map<Curso, List<Turma>> turmasPorCurso = turmaServico.listar().stream()
				.filter(a -> LocalDate.of(2019, 4,3).equals(a.getInicio()))
				.collect(Collectors.groupingBy(Turma::getCurso));

		Map<Curso, List<Turma>> turmasPorCurso2 = turmaServico.listar().stream()
				.collect(Collectors.groupingBy(Turma::getCurso,
						Collectors.filtering(a -> LocalDate.of(2019, 12,3).equals(a.getInicio()), Collectors.toList()) ) );


		System.out.println(turmasPorCurso2);
	}
}
