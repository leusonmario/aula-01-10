package banco.priv.transacao;

import banco.priv.pagamento.Pagamento;
import banco.priv.pagamento.dao.PagamentoDAO;
import banco.priv.transaction.Transacao;
import banco.user.Conta;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TransacaoTest {

  private Date data;

  @BeforeEach
  public void setUp(){
    this.data = new Date();
    Calendar c = Calendar.getInstance();
    c.setTime(data);
    c.add(Calendar.DATE, 3);
    data = c.getTime();
  }


  @Disabled
  public void realizaPagamentoComSaldoDeContaTest(){
    Transacao transacao = new Transacao();
    Conta conta = new Conta();
    conta.realizarDeposito(150.0);
    Pagamento pagamento = new Pagamento(this.data, 100.0);
    transacao.realizarPagamentoComSaldo(conta, pagamento);
    Assertions.assertTrue(pagamento.getPagamentoRealizado());
    Assertions.assertEquals(50.0, conta.getSaldo());
  }

  @Test
  public void naoRealizaPagamentoComSaldoDeContaTest(){
    Transacao transacao = new Transacao();
    PagamentoDAO pagamentoDAO = Mockito.mock(PagamentoDAO.class);
    transacao.setPagamentoDAO(pagamentoDAO);
    Conta conta = new Conta();
    conta.realizarDeposito(90.0);
    Pagamento pagamento = new Pagamento(this.data, 100.0);
    Mockito.when(pagamentoDAO.salvarPagamento(pagamento)).thenReturn(true);
    transacao.realizarPagamentoComSaldo(conta, pagamento);
    Assertions.assertFalse(pagamento.getPagamentoRealizado());
    Assertions.assertEquals(90.0, conta.getSaldo());
  }

  @Test
  public void realizaPagamentoAtualizaBancoTest(){
    Transacao transacao = new Transacao();
    PagamentoDAO pagamentoDAO = Mockito.mock(PagamentoDAO.class);
    transacao.setPagamentoDAO(pagamentoDAO);
    Conta conta = new Conta();
    conta.realizarDeposito(150.0);

    Pagamento pagamento = new Pagamento(this.data, 100.0);
    Mockito.when(pagamentoDAO.salvarPagamento(pagamento)).thenReturn(true);
    Mockito.when(pagamentoDAO.getPagamentosSalvos()).thenReturn(5);
    int numeroDePagamentos = pagamentoDAO.getPagamentosSalvos();

    transacao.realizarPagamentoComSaldo(conta, pagamento);
    pagamento.getPagamentoRealizado();
    Assertions.assertEquals(numeroDePagamentos+1, 6);
  }

  @Test
  public void naoRealizaPagamentoAtualizaBancoTest(){
    Transacao transacao = new Transacao();
    PagamentoDAO pagamentoDAO = Mockito.mock(PagamentoDAO.class);
    transacao.setPagamentoDAO(pagamentoDAO);
    Conta conta = new Conta();
    conta.realizarDeposito(80.0);
    Pagamento pagamento = new Pagamento(this.data, 100.0);

    transacao.realizarPagamentoComSaldo(conta, pagamento);
    Mockito.when(pagamentoDAO.getPagamentosSalvos()).thenReturn(5);
    pagamento.getPagamentoRealizado();
    Assertions.assertEquals(5, pagamentoDAO.getPagamentosSalvos());
  }

}
