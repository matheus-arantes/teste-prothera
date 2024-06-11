package entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

public class Funcionario extends Pessoa {
  private BigDecimal salario;
  private String funcao;

  public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
    super(nome, dataNascimento);
    this.salario = salario;
    this.funcao = funcao;
  }

  public BigDecimal getSalario() {
    return salario;
  }

  public String getFuncao() {
    return funcao;
  }

  public void aumentarSalario(BigDecimal percentual) {
    this.salario = this.salario.add(this.salario.multiply(percentual));
  }

  @Override
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DecimalFormat df = new DecimalFormat("#,###.00");
    return super.toString() + ", Salário: R$ " + df.format(salario) + ", Função: " + funcao;
  }
}

