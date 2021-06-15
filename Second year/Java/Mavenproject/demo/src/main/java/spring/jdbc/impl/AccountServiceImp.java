package spring.jdbc.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import spring.dao.AccountDao;
import spring.jdbc.AccountRowMapper;
import spring.model.Account;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;

/**
 * AccountServiceImp class
 */
@Service
public class AccountServiceImp implements AccountDao {
    /** SQL Query */
    private static final String FIND_ALL_SQL = "select account_no, balance, last_paid_on from account";

    /** Jdbc template */
    @Inject private JdbcTemplate jdbcTemplate;
//    @Inject NamedParameterJdbcTemplate jdbcTemplate;
    /** Row mapper */
    @Inject private AccountRowMapper accountRowMapper;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    @Override
//    public List<Account> findAll() {
//        return jdbcTemplate.query(FIND_ALL_SQL, new HashMap<String, Object>(),accountRowMapper);
//    }
    /**
     * finaAll method
     * This methods match query and row mapper to provide list of account
     * @param Account
     * @return jdbcTemplate
     */
    @Override
    public List<Account> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, accountRowMapper);
    }
}