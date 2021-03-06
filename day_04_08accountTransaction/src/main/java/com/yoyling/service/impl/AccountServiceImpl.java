package com.yoyling.service.impl;

import com.yoyling.dao.AccountDao;
import com.yoyling.domain.Account;
import com.yoyling.service.AccountService;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 账户的业务实现类
 *
 * 事务控制应该都是在业务层
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    private TransactionTemplate transactionTemplate;

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Account findAccountById(Integer accountId) {
        return transactionTemplate.execute(new TransactionCallback<Account>() {
            public Account doInTransaction(TransactionStatus status) {
                return accountDao.findAccountById(accountId);
            }
        });
    }

    public void transfer(String sourceName, String targetName, Float money) {
        transactionTemplate.execute(new TransactionCallback<Object>() {
            public Object doInTransaction(TransactionStatus status) {
                System.out.println("transfer...");
                //1.根据名称查询转出账户
                Account source = accountDao.findAccountByName(sourceName);
                //2.根据名称查询转入账户
                Account target = accountDao.findAccountByName(targetName);
                //3.转出账户减钱
                source.setMoney(source.getMoney() - money);
                //4.转入账户加钱
                target.setMoney(target.getMoney() + money);
                //5.更新转出账户
                accountDao.updateAccount(source);

                int i = 1 / 0;

                //6.更新转入账户
                accountDao.updateAccount(target);
                return null;
            }
        });
    }
}
