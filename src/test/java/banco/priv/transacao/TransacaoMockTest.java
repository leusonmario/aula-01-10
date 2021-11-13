package banco.priv.transacao;

import banco.priv.pagamento.Pagamento;
import banco.priv.pagamento.dao.PagamentoDAO;
import banco.priv.transaction.Transacao;
import banco.user.Conta;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TransacaoMockTest {

  @Test
  public void realizaPagamentoComSaldoDeContaTest(){
    Transacao transacao = new Transacao();
    Conta conta = Mockito.mock(Conta.class);
    Mockito.when(conta.getSaldo()).thenReturn(150.0);
    Pagamento pagamento = Mockito.mock(Pagamento.class);
    Mockito.when(pagamento.realizarPagamento(200.0)).thenReturn(true);
    Mockito.when(pagamento.getPagamentoRealizado()).thenReturn(true);
    transacao.realizarPagamentoComSaldo(conta, pagamento);
    Assertions.assertTrue(pagamento.getPagamentoRealizado());
  }

  @Test
  public void realizaPagamentoComSaldoDeContaTwoTest(){
    Transacao transacao = new Transacao();
    Conta conta = Mockito.mock(Conta.class);
    Mockito.when(conta.getSaldo()).thenReturn(150.0);
    Pagamento pagamento = new Pagamento(new Date("2021/11/15"), 150);
    transacao.realizarPagamentoComSaldo(conta, pagamento);
    Assertions.assertTrue(pagamento.getPagamentoRealizado());

    Mockito.verify(conta, Mockito.times(1)).debitarValor(pagamento.getValor());
  }

  @Test
  public void realizaPagamentoComSaldoDeContaThreeTest(){
    Transacao transacao = new Transacao();
    Conta conta = new Conta();
    conta.realizarDeposito(150);
    Pagamento pagamento = Mockito.mock(Pagamento.class);
    Mockito.when(pagamento.realizarPagamento(conta.getSaldo())).thenReturn(false);
    transacao.realizarPagamentoComSaldo(conta, pagamento);

    Mockito.verify(pagamento, Mockito.times(1)).realizarPagamento(conta.getSaldo());
  }

  @Test
  public void realizaPagamentoComSaldoDeContaMockPagamentoDAOTest(){
    Transacao transacao = new Transacao();
    PagamentoDAO pagamentoDAO = Mockito.mock(PagamentoDAO.class);
    transacao.setPagamentoDAO(pagamentoDAO);
    Conta conta = new Conta();
    conta.realizarDeposito(200);
    Pagamento pagamento = new Pagamento(new Date("2021/11/19"), 120.0);
    transacao.realizarPagamentoComSaldo(conta, pagamento);

    Mockito.when(pagamentoDAO.salvarPagamento(pagamento)).thenReturn(true);
    //Mockito.verify(pagamentoDAO, Mockito.times(1)).salvarPagamento(pagamento);
    Mockito.verify(pagamentoDAO, Mockito.atMost(1)).salvarPagamento(pagamento);
    Mockito.verify(pagamentoDAO, Mockito.never()).getPagamentosSalvos();
    Assertions.assertTrue(pagamento.getPagamentoRealizado());
  }

}
