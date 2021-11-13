package banco.priv.pagamento.dao;

import banco.priv.pagamento.Pagamento;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PagamentoDAOTest {

  @Test
  public void salvaObjetoPagamento(){
    PagamentoDAO pagamentoDAO = new PagamentoDAO();
    Pagamento pagamento = new Pagamento(new Date("2021/11/20"), 60.0);
    Assertions.assertTrue(pagamentoDAO.salvarPagamento(pagamento));
  }
}
