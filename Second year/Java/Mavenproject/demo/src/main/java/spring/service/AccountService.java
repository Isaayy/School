package spring.service;

import spring.dao.AccountDao;
import spring.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
/**
 * Account service
 */
@Component
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    /** Class constructor*/
    public AccountService()	{}

    /** setAccountDao setter */
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao	= accountDao;
    }

    /**
     * findDeliquentAccounts method
     * This methods finds account that matches certain conditions
     * @return delinquentAccounts
     */
    public List<Account> findDeliquentAccounts() throws	Exception {
        List<Account> delinquentAccounts = new ArrayList<Account>();
        List<Account> accounts = accountDao.findAll();
        Date thirtyDaysAgo = daysAgo(30);

        for	(Account account : accounts) {
            boolean	owesMoney =	account.getBalance().compareTo(BigDecimal.ZERO) > 0;
            boolean	thirtyDaysLate = account.getLastPaidOn()
                    .compareTo(thirtyDaysAgo) <= 0;

            if (owesMoney && thirtyDaysLate) delinquentAccounts.add(account);
        }

        return delinquentAccounts;
    }

    /**
     * daysAgo
     * This methods calculates how many days ago
     * @return date time
     */
    private	static Date daysAgo(int	days) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.DATE, -days);
        return	gc.getTime();
    }
}

