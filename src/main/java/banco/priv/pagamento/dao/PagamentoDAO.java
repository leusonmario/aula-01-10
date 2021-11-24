package banco.priv.pagamento.dao;

import banco.priv.pagamento.Pagamento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class PagamentoDAO {

  public boolean salvarPagamento(Pagamento pagamento) {
    try {
      Connection myConnection = this.getConnection();
      String sql = "insert into pagamento values (?, ?, ?)";
      PreparedStatement preparableStatement = myConnection.prepareStatement(sql);
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
      preparableStatement.setString(1, String.valueOf(pagamento.getValor()));
      preparableStatement.setString(2, simpleDateFormat.format(pagamento.getVencimento()));
      preparableStatement.setString(3, String.valueOf(pagamento.getPagamentoRealizado() ? 1 : 0));
      preparableStatement.execute();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public int getPagamentosSalvos(){
    try {
      Connection myConnection  = getConnection();
      Statement statement = myConnection.createStatement();
      String sql = "select * from pagamento";
      ResultSet resultSet = statement.executeQuery(sql);

      int size = 0;
      while(resultSet.next()){
        // essa string é referente à coluna da tabela no BD
        double valor = Double.valueOf(resultSet.getString("valor"));
        size++;
      }
      return size;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }

  private Connection getConnection() throws SQLException {
      Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banco-db",
          "leuson", "1234");
      return myConnection;
  }

}
