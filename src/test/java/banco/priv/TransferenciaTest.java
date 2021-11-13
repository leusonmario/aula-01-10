package banco.priv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

import banco.priv.exc.SaldoInvalidoException;
import banco.user.Conta;
import java.io.Closeable;
import java.io.IOException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TransferenciaTest {

  @Test
  public void realizaTransferenciaEntreContasComSaldoInvalido() {
    Conta conta1 = new Conta();
    Conta conta2 = new Conta();

    Transferencia transferencia = new Transferencia();
    assertThrows(SaldoInvalidoException.class, () -> {
      transferencia.realizarTransferencia(conta1, conta2, 10.0);
    });
  }

  @Test
  public void realizaTransferenciaEntreContasComSaldoInvalidoMessagem() {
    Conta conta1 = new Conta();
    Conta conta2 = new Conta();

    Transferencia transferencia = new Transferencia();
    Throwable exception = assertThrows(SaldoInvalidoException.class, () -> {
      transferencia.realizarTransferencia(conta1, conta2, 10.0);
    });
    assertEquals("Saldo Inválido", exception.getMessage());
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

  @Disabled
  public void verificaValorValidoTest(){
    Transferencia transferencia = new Transferencia();
    Assert.assertTrue(transferencia.verificaValidadeDeValorDeTransferencia(15.0));
  }

  @Test
  public void excecaoComMock() throws SaldoInvalidoException {
    Transferencia transferencia = Mockito.mock(Transferencia.class);
    Conta conta1 = new Conta();
    Conta conta2 = new Conta();

    doThrow(new SaldoInvalidoException("Saldo Inválido com Mock")).
        when(transferencia).realizarTransferencia(conta1, conta2, 150);
    Throwable exception = assertThrows(SaldoInvalidoException.class, () -> {
      transferencia.realizarTransferencia(conta1, conta2, 150.0);
    });
    Assertions.assertEquals("Saldo Inválido com Mock", exception.getMessage());
  }

}
