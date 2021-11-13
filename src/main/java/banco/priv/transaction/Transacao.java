package banco.priv.transaction;

import banco.priv.pagamento.Pagamento;
import banco.priv.pagamento.dao.PagamentoDAO;
import banco.user.Conta;

public class Transacao {
  private PagamentoDAO pagamentoDAO;

  public Transacao(){
    this.pagamentoDAO = new PagamentoDAO();
  }

  public void realizarPagamentoComSaldo(Conta conta, Pagamento pagamento) {
    if (pagamento.realizarPagamento(conta.getSaldo())){
      conta.debitarValor(pagamento.getValor());
      pagamentoDAO.salvarPagamento(pagamento);
    }
  }

  public PagamentoDAO getPagamentoDAO() {
    return pagamentoDAO;
  }

  public void setPagamentoDAO(PagamentoDAO pagamentoDAO) {
    this.pagamentoDAO = pagamentoDAO;
  }
}
