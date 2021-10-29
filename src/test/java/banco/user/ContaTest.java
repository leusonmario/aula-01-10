package banco.user;

import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ContaTest {
  private Conta conta;
  private Logger logger = Logger.getLogger("my-logger");

  @BeforeEach
  public void setUp(){
    this.conta = new Conta();
  }

  @AfterEach
  public void printMessage(){
    this.logger.info("Saldo atual (conta) : " +this.conta.getSaldo());
  }

  @RepeatedTest(value = 3,
  name = "{displayName} : {currentRepetition} de {totalRepetitions}")
  @DisplayName("Teste Repetido - Verificação de Débito")
  public void verificaDepositoEmContaTest(){
    conta.realizarDeposito(50.0);
    Assert.assertEquals(50.0, conta.getSaldo(),0.0);
  }

  @ParameterizedTest(name = "Entrada : {0}")
  @ValueSource(doubles = {50.0, 60.0, 70.0})
  @DisplayName("Teste Parametrizado - Verificação de Débito")
  public void verificaDepositoEmContaTestParametrizado(double deposito){
    conta.realizarDeposito(deposito);
    Assert.assertEquals(deposito, conta.getSaldo(),0.0);
  }

  @Test
  @DisplayName("Verificação de Disponibilidade de Empréstimo")
  public void verifica_disponibilidade_emprestimo_OK_test(){
    conta.realizarDeposito(50.0); //fixture
    conta.realizarDeposito(100); //fixture

    boolean retornoEmprestimo = conta.verificarEmprestimo(); // chamada ao método-alvo

    Assert.assertTrue(retornoEmprestimo);//assertion
  }

  @Test
  public void verifica_disponibilidade_emprestimo_Falso_test(){
    conta.realizarDeposito(50.0); //fixture

    boolean retornoEmprestimo = conta.verificarEmprestimo(); // chamada ao método-alvo

    Assert.assertFalse(retornoEmprestimo);//assertion
  }

}
