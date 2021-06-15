package spring.jdbc;

import spring.model.Account;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * AccountRowMapper
 */
@Component
public class AccountRowMapper implements RowMapper<Account> {

    /**
     * mapRow
     * This methods maps rows form query into Account model
     * @param resultSet
     * @param rowNum
     * @return account
     */
    @Override
    public Account mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Account account = new Account();
        account.setAccountNo(resultSet.getString(1));
        account.setBalance(resultSet.getBigDecimal(2));
        account.setLastPaidOn(resultSet.getDate(3));

        return account;
    }
}
