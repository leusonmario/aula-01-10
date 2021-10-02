package banco.priv;

import banco.priv.exc.SaldoInvalidoException;
import banco.user.Conta;

public class Transferencia {

  public void realizarTransferencia(Conta conta1, Conta conta2, double valorTransferencia)
      throws SaldoInvalidoException {
    if (conta1.getSaldo() >= valorTransferencia) {
      conta1.debitarValor(valorTransferencia);
      conta2.realizarDeposito(valorTransferencia);
    }else{
      throw new SaldoInvalidoException("Saldo Inválido");
    }
  }

  public boolean verificaValidadeDeValorDeTransferencia(double valor){
    if (valor > 0 && valor > 10){
      return true;
    }else{
      return false;
    }
  }
}
