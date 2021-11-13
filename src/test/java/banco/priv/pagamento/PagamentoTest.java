package banco.priv.pagamento;

import banco.priv.pagamento.Pagamento;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PagamentoTest {

  private Date data;

  @BeforeEach
  public void setUp(){
    this.data = new Date();
    Calendar c = Calendar.getInstance();
    c.setTime(data);
    c.add(Calendar.DATE, 3);
    data = c.getTime();
  }

  @Test
  public void realizaPagamentoTest(){
    Pagamento pagamento = new Pagamento(data, 50.0);
    Assertions.assertTrue(pagamento.realizarPagamento(60.0));
    Assertions.assertTrue(pagamento.getPagamentoRealizado());
  }

  @Test
  public void realizaPagamentoEmATrasoTest(){
    Pagamento pagamento = new Pagamento(new Date("2021/11/09"), 50.0);
    Assertions.assertFalse(pagamento.realizarPagamento(52.0));
    Assertions.assertFalse(pagamento.getPagamentoRealizado());
  }

  @Test
  public void verificaVencimentoPagamentoTest(){
    Pagamento pagamento = new Pagamento(new Date("2021/11/09"), 50.0);
    Assertions.assertTrue(pagamento.pagamentoVencido());
  }

  @Test
  public void verificaPagamentoEmAtrasoTest(){
    Pagamento pagamento = new Pagamento(data, 50.0);
    Assertions.assertFalse(pagamento.pagamentoVencido());
  }

}
