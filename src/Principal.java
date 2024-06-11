import entity.Funcionario;
import entity.Pessoa;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.math.RoundingMode;

public class Principal {
  public static void main(String[] args) {

    //3.1 Insere todos os funcionários na lista
    List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
            new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
            new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
            new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
            new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
            new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
            new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
            new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
            new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
            new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
            new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
    ));

    // 3.2 Remove o funcionário João da lista.
    funcionarios.removeIf(f -> f.getNome().equals("João"));

    // 3.3 Imprime todos os funcionários
    System.out.println("Todos os funcionários:");
    funcionarios.forEach(System.out::println);

    // 3.4 Aumenta o salário de todos os funcionários em 10%
    funcionarios.forEach(f -> f.aumentarSalario(new BigDecimal("0.10")));

    // 3.5 Agrupa os funcionários por função utilizando um MAP
    Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
            .collect(Collectors.groupingBy(Funcionario::getFuncao));

    // 3.6 Imprime os funcionários agrupados por função
    System.out.println("\n\nFuncionários agrupados por função:\n");
    funcionariosPorFuncao.forEach((funcao, lista) -> {
      System.out.println("Função: " + funcao);
      lista.forEach(System.out::println);
    });

    // 3.8 Imprime os funcionários que fazem aniversário no mês 10 e 12
    System.out.println("\n\nFuncionários que fazem aniversário em Outubro e Dezembro:\n");
    funcionarios.stream()
            .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
            .forEach(System.out::println);

    // 3.9 Imprime o funcionário com maior idade
    Funcionario maisVelho = Collections.max(funcionarios, Comparator.comparing(Pessoa::getDataNascimento));
    int idadeMaisVelho = LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear();
    System.out.println("\n\nFuncionário com maior idade:\n " + maisVelho.getNome() + ", Idade: " + idadeMaisVelho);

    // 3.10 Imprime a lista de funcionários por ordem alfabética
    System.out.println("\n\nFuncionários em ordem alfabética:\n");
    funcionarios.stream()
            .sorted(Comparator.comparing(Funcionario::getNome))
            .forEach(System.out::println);

    // 3.11 Imprime o total dos salários dos funcionários
    BigDecimal totalSalarios = funcionarios.stream()
            .map(Funcionario::getSalario)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    DecimalFormat df = new DecimalFormat("#,###.00");
    System.out.println("\n\nTotal dos salários:\n R$ " + df.format(totalSalarios));

    // 3.12 Imprime quantos salários mínimos ganha cada funcionário
    BigDecimal salarioMinimo = new BigDecimal("1212.00");
    System.out.println("\n\nSalários em relação ao salário mínimo:\n");
    funcionarios.forEach(f -> {
      BigDecimal salariosMinimos = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
      System.out.println(f.getNome() + ": " + salariosMinimos + " salários mínimos");
    });
  }
}
