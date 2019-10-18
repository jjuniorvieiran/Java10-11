package br.com.alura.escola;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.alura.escola.servico.AlunoServico;

public class Principal {

	public static void main(String...strings) {

		AlunoServico alunoServico = new AlunoServico();
		//alunoServico.listar().stream().forEach(System.out::println);
		
		List<String> alunos = alunoServico.listar().stream()
				.flatMap(aluno -> Stream.of(aluno.getNome()))
				.map(aluno -> aluno.toUpperCase())
				.collect(Collectors.toList());

		System.out.println(alunos);

	}
}
