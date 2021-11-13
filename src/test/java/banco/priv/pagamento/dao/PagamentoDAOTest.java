package banco.priv.pagamento.dao;

import banco.priv.pagamento.Pagamento;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PagamentoDAOTest {

  @Test
  public void salvaObjetoPagamento(){
    PagamentoDAO pagamentoDAO = Mockito.mock(PagamentoDAO.class);
    Pagamento pagamento = new Pagamento(new Date("2021/11/20"), 60.0);
    Mockito.when(pagamentoDAO.salvarPagamento(pagamento)).thenReturn(true);
    Assertions.assertTrue(pagamentoDAO.salvarPagamento(pagamento));
  }
}
