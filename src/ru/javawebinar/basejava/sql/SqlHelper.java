package ru.javawebinar.basejava.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
  private final ConnectionFactory connectionFactory;

  public SqlHelper(ConnectionFactory connectionFactory) {
    this.connectionFactory = connectionFactory;
  }

  public void execute(String sql) {
    execute(sql, PreparedStatement::execute);
  }

  public <T> T execute(String sql, SQLExecutor<T> executor) {
    try (Connection cf = connectionFactory.getConnection();
        PreparedStatement ps = cf.prepareStatement(sql)) {
      return executor.execute(ps);
    } catch (SQLException e) {
      throw ExceptionUtil.convertException(e);
    }
  }
}
