package banco.user;

import java.util.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ContaTest {
  private Conta conta;
  private Logger logger = Logger.getLogger("my-logger");

  @Before
  public void setUp(){
    this.conta = new Conta();
  }

  @After
  public void printMessage(){
    this.logger.info("Saldo atual (conta) : " +this.conta.getSaldo());
  }

  @Test
  public void verificaDepositoEmContaTest(){
    conta.realizarDeposito(50.0);
    Assert.assertEquals(50.0, conta.getSaldo(),0.0);
  }

  @Test
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
