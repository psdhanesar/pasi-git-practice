//package com.company;
//
//
//
//import java.math.BigDecimal;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AccountDaoImp  {
//
//
//    public static final String INSERT_ACCOUNT = "INSERT INTO accounts(customer_id,type,balance) VALUES (?,?,?)";
//    public static final String FIND_ACCOUNT = "SELECT * FROM accounts where account_id = ?";
//    public static final String UPDATE_BALANCE = "UPDATE accounts SET balance = ? where account_id =?";
//    public static final String GET_ACCOUNTS = "SELECT * FROM accounts";
//    private Connection conn;
//
//    private PreparedStatement insertAccount;
//    private PreparedStatement findAccount;
//    private PreparedStatement updateBalance;
//    private PreparedStatement getAccounts;
//
//
//    public boolean open() {
//        try {
//            conn = ConnectionManager.getConnection();
//
//            insertAccount = conn.prepareStatement(INSERT_ACCOUNT, Statement.RETURN_GENERATED_KEYS);
//            findAccount = conn.prepareStatement(FIND_ACCOUNT);
//            updateBalance = conn.prepareStatement(UPDATE_BALANCE);
//            getAccounts = conn.prepareStatement(GET_ACCOUNTS);
//            return true;
//        } catch (SQLException e) {
//            System.out.println("Couldn't connect to database: " + e.getMessage());
//            return false;
//        }
//
//    }
//
//    public void close() {
//        try {
//
//            if (insertAccount != null) {
//                insertAccount.close();
//            }
//            if (findAccount != null) {
//                findAccount.close();
//            }
//            if (updateBalance != null) {
//                updateBalance.close();
//            }
//            if (getAccounts != null) {
//                getAccounts.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        } catch (SQLException e) {
//            System.out.println("Couldn't close connection: " + e.getMessage());
//        }
//    }
//
//
//    public int saveAccount(int customer_id, Account account) {
//        open();
//        try {
//            insertAccount.setInt(1, customer_id);
//            insertAccount.setString(2, account.getAccountType());
//            insertAccount.setBigDecimal(3, BigDecimal.valueOf(account.getAccountBalance()));
//            int affectedRows = insertAccount.executeUpdate();
//            if (affectedRows == 1) {
//                ResultSet generatedKeys = insertAccount.getGeneratedKeys();
//                if (generatedKeys.next()) {
//                    return generatedKeys.getInt(1);
//                } else {
//                    throw new SQLException("Couldn't get _id for Account");
//                }
//            } else {
//                throw new SQLException("The Account insert failed");
//
//            }
//
//        } catch (Exception e) {
//            System.out.println("Insert Account exception: " + e.getMessage());
//        } finally {
//            close();
//
//        }
//        return 0;
//    }
//
//    @Override
//    public Account validateAccount(int account_id) {
//        open();
//        try {
//            findAccount.setInt(1, account_id);
//            ResultSet results = findAccount.executeQuery();
//            Account account = new Account();
//            if (results.next()) {
//                account.setAccount_id(results.getInt(1));
//                account.setCustomer_id(results.getInt(2));
//                account.setAccountType(results.getString(3));
//                account.setAccountBalance(results.getDouble(4));
//                return account;
//            } else {
//                return null;
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } finally {
//            close();
//        }
//        return null;
//    }
//
//    @Override
//    public List<Account> getAllAccounts() {
//        try {
//            open();
//            ResultSet resultSet = getAccounts.executeQuery();
//            List<Account> accountList = new ArrayList<>();
//            while (resultSet.next()) {
//                Account account = new Account();
//                account.setAccount_id(resultSet.getInt(1));
//                account.setCustomer_id(resultSet.getInt(2));
//                account.setAccountType(resultSet.getString(3));
//                account.setAccountBalance(resultSet.getDouble(4));
//                accountList.add(account);
//            }
//            return accountList;
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } finally {
//            close();
//        }
//        return null;
//    }
//
//    @Override
//    public void updateBalance(Account account) {
//        try {
//            open();
//            updateBalance.setBigDecimal(1, BigDecimal.valueOf(account.getAccountBalance()));
//            updateBalance.setInt(2, account.getAccount_id());
//            int resultSet = updateBalance.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } finally {
//            close();
//        }
//    }
//
//}
