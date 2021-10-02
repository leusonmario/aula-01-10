package banco.priv;

import banco.priv.exc.SaldoInvalidoException;
import banco.user.Conta;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;

public class TransferenciaTest {

  @Test(expected = SaldoInvalidoException.class)
  public void realizaTransferenciaEntreContasComSaldoInvalido() throws SaldoInvalidoException {
    Conta conta1 = new Conta();
    Conta conta2 = new Conta();

    Transferencia transferencia = new Transferencia();
    transferencia.realizarTransferencia(conta1, conta2, 10.0);
  }

  @Test
  public void realizaTransferenciaValidaEntreContas() throws SaldoInvalidoException {
    Conta conta1 = new Conta();
    Conta conta2 = new Conta();
    conta1.realizarDeposito(60.0);
    conta2.realizarDeposito(10.0);

    Transferencia transferencia = new Transferencia();
    //Assume.assumeTrue(conta1.getSaldo() >= 70);

    transferencia.realizarTransferencia(conta1, conta2, 50.0);
    Assert.assertEquals(60.0, conta2.getSaldo(), 0.0);
  }

  @Test
  public void verificaValidadeDeValorTest(){
    Transferencia transferencia = new Transferencia();
    Assert.assertFalse(transferencia.verificaValidadeDeValorDeTransferencia(-5.0));
  }

  @Ignore
  public void verificaValorValidoTest(){
    Transferencia transferencia = new Transferencia();
    Assert.assertTrue(transferencia.verificaValidadeDeValorDeTransferencia(15.0));
  }

}
