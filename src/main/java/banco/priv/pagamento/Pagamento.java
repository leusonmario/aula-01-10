package banco.priv.pagamento;

import java.util.Date;

public class Pagamento {
  private Date vencimento;
  private double valor;
  private boolean pagamentoRealizado;

  public Pagamento(Date vencimento, double valor){
    this.vencimento = vencimento;
    this.valor = valor;
    this.pagamentoRealizado = false;
  }

  public boolean realizarPagamento(double v) {
    if (v >= calculaValorPagamento()){
      this.pagamentoRealizado = true;
      return true;
    }
    return false;
  }

  private double calculaValorPagamento(){
    if (pagamentoVencido()){
      return this.valor + this.valor * 0.1;
    }
    return this.valor;
  }

  public boolean pagamentoVencido() {
    if (this.vencimento.compareTo(new Date(System.currentTimeMillis())) > 0){
      return false;
    }else{
      return true;
    }
  }

  public double getValor(){
    return this.valor;
  }

  public Date getVencimento(){
    return this.vencimento;
  }

  public boolean getPagamentoRealizado() {
    return this.pagamentoRealizado;
  }
}
