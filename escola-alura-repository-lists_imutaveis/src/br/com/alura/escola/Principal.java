package br.com.alura.escola;

import br.com.alura.escola.dao.TestHttp2DAO;
import br.com.alura.escola.dao.TestHttpDAO;
import br.com.alura.escola.modelo.Aluno;
import br.com.alura.escola.modelo.Curso;
import br.com.alura.escola.modelo.Turma;
import br.com.alura.escola.servico.AlunoServico;
import br.com.alura.escola.servico.TurmaServico;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Principal {

    public static void main(String... strings) throws IOException, URISyntaxException, InterruptedException {

        AlunoServico alunoServico = new AlunoServico();
        TurmaServico turmaServico = new TurmaServico();
        TestHttpDAO testHttpDAO = new TestHttpDAO();
        TestHttp2DAO testHttp2DAO = new TestHttp2DAO();

        //alunoServico.listar().stream().forEach(System.out::println);

        List<String> alunos = alunoServico.listar().stream()
                .flatMap(aluno -> Stream.ofNullable(aluno.getNome()))
                .map(aluno -> aluno.toUpperCase())
                .collect(Collectors.toList());


        Map<Curso, List<Turma>> turmasPorCurso = turmaServico.listar().stream()
                .filter(a -> LocalDate.of(2019, 4, 3).equals(a.getInicio()))
                .collect(Collectors.groupingBy(Turma::getCurso));

        Map<Curso, List<Turma>> turmasPorCurso2 = turmaServico.listar().stream()
                .collect(Collectors.groupingBy(Turma::getCurso,
                        Collectors.filtering(a -> LocalDate.of(2019, 12, 3).equals(a.getInicio()), Collectors.toList())));


        Optional<Aluno> aluno = alunoServico.listarAlunoPorCPF(1111L);
        //aluno.ifPresentOrElse(System.out::println, () -> System.out.println("Aluno not present"));

        Optional<Aluno> alunoRec = alunoServico.listarAlunoPorCPF(0000L)
                .or(() -> alunoServico.listarAlunoPorCPF(1111L))
                .or(() -> alunoServico.listarAlunoPorCPF(82757618083L));

        //System.out.println(alunoRec.get());

        //testHttpDAO.testeConnexaoHTTP();

        testHttp2DAO.testeConnexaoHTTP();
    }
}
