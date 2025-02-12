package com.github.msufred.sms.data;

public final class DBTables {

    public static String[] createTableSql() {
        return new String[] {
                createUsersTable(),
                createDataPlansTable(),
                createWifiVendosTable(),
                createProductsTable(),
                createServicesTable(),
                createAccountsTable(),
                createSubscriptionsTable(),
                createTowersTable(),
                createBillingsTable(),
                createBillingStatementsTable(),
                createPurchaseBillingsTable(),
                createPaymentsTable(),
                createPaymentItemsTable(),
                createBalancesTable(),
                createExpensesTable(),
                createRevenuesTable(),
                createCashTransactionsTable(),
                createDailySummariesTable(),
                createSchedulesTable(),
        };
    }

    public static String[] updatesSql() {
        return new String[] {
                //updateTowersTable(),
                // updateTowersTable_050224(),
                // updatePaymentsTable_112524(),
                // updateRevenuesTable_112724(),
                // updateUsersTable_112724(),
                // updateAccountsTable_112724(),
                // updateExpensesTable_112824(),
                // updateCashTransactionsTable_120124(),
                //updateDailySummariesTable_120224(),
                //updatePaymentsTable_010125(),
                //updatePaymentItemsTable_010125(),
                //updatePaymentItemsTable_010225()
        };
    }

    private static String createUsersTable() {
        return "CREATE TABLE IF NOT EXISTS users (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "username VARCHAR(255) NOT NULL, " +
                "password VARCHAR(255) NOT NULL, " +
                "fullname VARCHAR(255) DEFAULT '', " +
                "designation VARCHAR(255) DEFAULT 'Employee', " +
                "role VARCHAR(8) DEFAULT 'guest', " +
                "tag VARCHAR(16) DEFAULT 'normal', " +
                "date_created TIMESTAMP, " +
                "date_updated TIMESTAMP, " +
                "date_deleted TIMESTAMP, " +
                "PRIMARY KEY (id)" +
                ")";
    }

    private static String createDataPlansTable() {
        return "CREATE TABLE IF NOT EXISTS data_plans (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(255) NOT NULL, " +
                "speed INT DEFAULT '0', " +
                "monthly_fee DOUBLE DEFAULT '0.0', " +
                "tag VARCHAR(16) DEFAULT 'normal', " +
                "date_created TIMESTAMP, " +
                "date_updated TIMESTAMP, " +
                "date_deleted TIMESTAMP, " +
                "PRIMARY KEY (id)" +
                ")";
    }

    private static String createWifiVendosTable() {
        return "CREATE TABLE IF NOT EXISTS wifi_vendos (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(255) NOT NULL, " +
                "ip_address VARCHAR(24), " +
                "status VARCHAR(16) DEFAULT 'Active', " +
                "tag VARCHAR(16) DEFAULT 'normal', " +
                "date_created TIMESTAMP, " +
                "date_updated TIMESTAMP, " +
                "date_deleted TIMESTAMP, " +
                "PRIMARY KEY (id)" +
                ")";
    }

    private static String createProductsTable() {
        return "CREATE TABLE IF NOT EXISTS products (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(255) NOT NULL, " +
                "price DOUBLE DEFAULT '0.0', " +
                "stock INT DEFAULT '0', " +
                "serial VARCHAR(255), " +
                "tag VARCHAR(16) DEFAULT 'normal', " +
                "date_created TIMESTAMP, " +
                "date_updated TIMESTAMP, " +
                "date_deleted TIMESTAMP, " +
                "PRIMARY KEY (id)" +
                ")";
    }

    private static String createServicesTable() {
        return "CREATE TABLE IF NOT EXISTS services (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(255) NOT NULL, " +
                "price DOUBLE DEFAULT '0.0', " +
                "description VARCHAR(255), " +
                "tag VARCHAR(16) DEFAULT 'normal', " +
                "date_created TIMESTAMP, " +
                "date_updated TIMESTAMP, " +
                "date_deleted TIMESTAMP, " +
                "PRIMARY KEY (id)" +
                ")";
    }

    private static String createAccountsTable() {
        return "CREATE TABLE IF NOT EXISTS accounts (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "account_no VARCHAR(32) NOT NULL, " +
                "name VARCHAR(255) NOT NULL, " +
                "address VARCHAR(255), " +
                "phone VARCHAR(15), " +
                "email VARCHAR(255), " +
                "bank_account_name VARCHAR(255) DEFAULT '', " +
                "bank_account_no VARCHAR(255) DEFAULT '', " +
                "status VARCHAR(16) DEFAULT 'Active', " +
                "tag VARCHAR(16) DEFAULT 'normal', " +
                "date_created TIMESTAMP, " +
                "date_updated TIMESTAMP, " +
                "date_deleted TIMESTAMP, " +
                "PRIMARY KEY (id)" +
                ")";
    }

    private static String createSubscriptionsTable() {
        return "CREATE TABLE IF NOT EXISTS subscriptions (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "account_no VARCHAR(32) NOT NULL, " +
                "plan_type VARCHAR(255) DEFAULT 'Standard', " +
                "speed INT DEFAULT '0', " +
                "monthly_fee DOUBLE DEFAULT '0.0', " +
                "ip_address VARCHAR(24), " +
                "start_date DATE NOT NULL, " +
                "end_date DATE NOT NULL, " +
                "status VARCHAR(16) DEFAULT 'Active', " +
                "tag VARCHAR(16) DEFAULT 'normal', " +
                "date_created TIMESTAMP, " +
                "date_updated TIMESTAMP, " +
                "date_deleted TIMESTAMP, " +
                "PRIMARY KEY (id)" +
                ")";
    }

    private static String createTowersTable() {
        return "CREATE TABLE IF NOT EXISTS towers (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "account_no VARCHAR(32) NOT NULL, " +
                "type VARCHAR(24) DEFAULT 'Default', " +
                "name VARCHAR(255) DEFAULT '', " +
                "latitude FLOAT DEFAULT '0.0', " +
                "longitude FLOAT DEFAULT '0.0', " +
                "elevation FLOAT DEFAULT '0.0', " +
                "tower_height DOUBLE DEFAULT '0.0', " +
                "ip_address VARCHAR(24), " +
                "parent_tower_id INT DEFAULT '-1', " +
                "parent_name VARCHAR(255) DEFAULT '', " +
                "status VARCHAR(16) DEFAULT 'Active', " +
                "tag VARCHAR(16) DEFAULT 'normal', " +
                "date_created TIMESTAMP, " +
                "date_updated TIMESTAMP, " +
                "date_deleted TIMESTAMP, " +
                "PRIMARY KEY (id)" +
                ")";
    }

    private static String createBillingsTable() {
        return "CREATE TABLE IF NOT EXISTS billings (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "billing_no VARCHAR(10) NOT NULL, " +
                "account_no VARCHAR(32) NOT NULL, " +
                "from_date DATE NOT NULL, " +
                "to_date DATE NOT NULL, " +
                "due_date DATE NOT NULL, " +
                "to_pay DOUBLE DEFAULT '0.0', " +
                "status VARCHAR(16) DEFAULT 'For Payment', " +
                "payment_no VARCHAR(16), " +
                "tag VARCHAR(16) DEFAULT 'normal', " +
                "date_created TIMESTAMP, " +
                "date_updated TIMESTAMP, " +
                "date_deleted TIMESTAMP, " +
                "PRIMARY KEY (id)" +
                ")";
    }

    /**
     * Table billing_statements holds the records of billing statements created & printed. I have to preserve the
     * data of that billing statement, so that I can print it anytime.
     */
    private static String createBillingStatementsTable() {
        return "CREATE TABLE IF NOT EXISTS billing_statements (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "billing_no VARCHAR(10) NOT NULL, " +
                "include_balance BOOLEAN DEFAULT 'false', " +
                "prev_balance DOUBLE DEFAULT '0.0', " +
                "monthly_fee DOUBLE DEFAULT '0.0', " +
                "discount DOUBLE DEFAULT '0.0', " +
                "penalty DOUBLE DEFAULT '0.0', " +
                "vat DOUBLE DEFAULT '0.0', " +
                "total DOUBLE DEFAULT '0.0', " +
                "prepared_by VARCHAR(255), " +
                "designation VARCHAR(255), " +
                "received_by VARCHAR(255), " +
                "tag VARCHAR(16) DEFAULT 'normal', " +
                "date_created TIMESTAMP, " +
                "date_printed TIMESTAMP, " +
                "date_updated TIMESTAMP, " +
                "date_deleted TIMESTAMP, " +
                "PRIMARY KEY (id)" +
                ")";
    }

    private static String createPurchaseBillingsTable() {
        return "CREATE TABLE IF NOT EXISTS purchase_billings (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "billing_no VARCHAR(10) NOT NULL, " +
                "name VARCHAR(255) NOT NULL, " +
                "walk_in BOOLEAN DEFAULT 'false', " +
                "to_pay DOUBLE DEFAULT '0.0', " +
                "status VARCHAR(16) DEFAULT 'For Payment', " +
                "payment_no VARCHAR(16) DEFAULT '', " +
                "tag VARCHAR(16) DEFAULT 'normal', " +
                "date_created TIMESTAMP, " +
                "date_updated TIMESTAMP, " +
                "date_deleted TIMESTAMP, " +
                "PRIMARY KEY (id)" +
                ")";
    }

    private static String createPaymentsTable() {
        return "CREATE TABLE IF NOT EXISTS payments (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "payment_no VARCHAR(10) NOT NULL, " +
                "name VARCHAR(255) NOT NULL, " +
                "address VARCHAR(255) DEFAULT '', " +
                "contact VARCHAR(12) DEFAULT '', " +
                "payment_for VARCHAR(16) DEFAULT 'Billing', " +
                "extra_info VARCHAR(16), " +
                "prev_balance DOUBLE DEFAULT '0.0', " +
                "amount_to_pay DOUBLE DEFAULT '0.0', " +
                "discount DOUBLE DEFAULT '0.0', " +
                "vat DOUBLE DEFAULT '0.0', " +
                "surcharges DOUBLE DEFAULT '0.0', " +
                "amount_total DOUBLE DEFAULT '0.0', " +
                "amount_paid DOUBLE DEFAULT '0.0', " +
                "balance DOUBLE DEFAULT '0.0', " +
                "payment_date DATE NOT NULL, " +
                "mode VARCHAR(255) DEFAULT 'Cash', " +
                "ref VARCHAR(255) DEFAULT '', " +
                "prepared_by VARCHAR(255), " +
                "status VARCHAR(16) DEFAULT 'valid', " +
                "tag VARCHAR(16) DEFAULT 'normal', " +
                "date_created TIMESTAMP, " +
                "date_updated TIMESTAMP, " +
                "date_deleted TIMESTAMP, " +
                "PRIMARY KEY (id)" +
                ")";
    }

    private static String createPaymentItemsTable() {
        return "CREATE TABLE IF NOT EXISTS payment_items (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "payment_no VARCHAR(10) NOT NULL, " +
                "item_no VARCHAR(10), " +
                "item_name VARCHAR(255), " +
                "serial VARCHAR(255), " +
                "amount DOUBLE DEFAULT '0.0', " +
                "price DOUBLE DEFAULT '0.0', " +
                "quantity INTEGER DEFAULT '1', " +
                "tag VARCHAR(16) DEFAULT 'normal', " +
                "date_created TIMESTAMP, " +
                "date_updated TIMESTAMP, " +
                "date_deleted TIMESTAMP, " +
                "PRIMARY KEY (id)" +
                ")";
    }

    private static String createBalancesTable() {
        return "CREATE TABLE IF NOT EXISTS balances (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "account_no VARCHAR(32) NOT NULL, " +
                "amount DOUBLE DEFAULT '0.0', " +
                "status VARCHAR(16) DEFAULT 'Pending', " +
                "date_paid DATE, " +
                "tag VARCHAR(16) DEFAULT 'normal', " +
                "date_created TIMESTAMP, " +
                "date_updated TIMESTAMP, " +
                "date_deleted TIMESTAMP, " +
                "PRIMARY KEY (id)" +
                ")";
    }

    private static String createExpensesTable() {
        return "CREATE TABLE IF NOT EXISTS expenses (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "category VARCHAR(255) DEFAULT 'Operational', " +
                "type VARCHAR(255) DEFAULT 'Others', " +
                "description VARCHAR(255) DEFAULT '', " +
                "amount DOUBLE DEFAULT '0.0', " +
                "mode VARCHAR(255) DEFAULT 'Cash', " +
                "ref VARCHAR(255) DEFAULT '', " +
                "date DATE NOT NULL, " +
                "attachment VARCHAR(255) DEFAULT '', " +
                "tag VARCHAR(16) DEFAULT 'normal', " +
                "date_created TIMESTAMP, " +
                "date_updated TIMESTAMP, " +
                "date_deleted TIMESTAMP, " +
                "PRIMARY KEY (id)" +
                ")";
    }

    private static String createRevenuesTable() {
        return "CREATE TABLE IF NOT EXISTS revenues (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "type VARCHAR(255) DEFAULT 'Others', " +
                "description VARCHAR(255) DEFAULT '', " +
                "amount DOUBLE DEFAULT '0.0', " +
                "mode VARCHAR(255) DEFAULT 'Cash', " +
                "ref VARCHAR(255) DEFAULT '', " +
                "date DATE NOT NULL, " +
                "attachment VARCHAR(255) DEFAULT '', " +
                "tag VARCHAR(16) DEFAULT 'normal', " +
                "date_created TIMESTAMP, " +
                "date_updated TIMESTAMP, " +
                "date_deleted TIMESTAMP, " +
                "PRIMARY KEY (id)" +
                ")";
    }

    private static String createCashTransactionsTable() {
        return "CREATE TABLE IF NOT EXISTS cash_transactions (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "type VARCHAR(10) DEFAULT 'Cash In', " +
                "description VARCHAR(255) DEFAULT '', " +
                "amount DOUBLE DEFAULT '0.0', " +
                "mode VARCHAR(255) DEFAULT 'Cash', " +
                "ref VARCHAR(255) DEFAULT '', " +
                "date DATE NOT NULL, " +
                "attachment VARCHAR(255) DEFAULT '', " +
                "tag VARCHAR(16) DEFAULT 'normal', " +
                "date_created TIMESTAMP, " +
                "date_updated TIMESTAMP, " +
                "date_deleted TIMESTAMP, " +
                "PRIMARY KEY (id)" +
                ")";
    }

    private static String createDailySummariesTable() {
        return "CREATE TABLE IF NOT EXISTS daily_summaries (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "date DATE NOT NULL, " +
                "forwarded DOUBLE DEFAULT '0.0', " +
                "revenues DOUBLE DEFAULT '0.0', " +
                "expenses DOUBLE DEFAULT '0.0', " +
                "cash_in DOUBLE DEFAULT '0.0', " +
                "cash_out DOUBLE DEFAULT '0.0', " +
                "balance DOUBLE DEFAULT '0.0', " +
                "tag VARCHAR(16) DEFAULT 'normal', " +
                "date_created TIMESTAMP, " +
                "date_updated TIMESTAMP, " +
                "date_deleted TIMESTAMP, " +
                "PRIMARY KEY (id)" +
                ")";
    }

    private static String createSchedulesTable() {
        return "CREATE TABLE IF NOT EXISTS schedules (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "title VARCHAR(255) NOT NULL, " +
                "description VARCHAR(255) DEFAULT '', " +
                "date DATE NOT NULL, " +
                "status VARCHAR(16) DEFAULT 'pending', " +
                "tag VARCHAR(16) DEFAULT 'normal', " +
                "date_created TIMESTAMP, " +
                "date_updated TIMESTAMP, " +
                "date_deleted TIMESTAMP, " +
                "PRIMARY KEY (id)" +
                ")";
    }

    // Updates

    private static String updateTowersTable() {
        return "ALTER TABLE towers ADD COLUMN IF NOT EXISTS name VARCHAR(255) DEFAULT '' AFTER type";
    }

    private static String updateTowersTable_050224() {
        return "ALTER TABLE towers ADD COLUMN IF NOT EXISTS parent_name VARCHAR(255) DEFAULT '' AFTER parent_tower_id";
    }

    private static String updatePaymentsTable_112524() {
        return "ALTER TABLE payments ADD COLUMN IF NOT EXISTS mode VARCHAR(255) DEFAULT 'Cash' AFTER payment_date; " +
                "ALTER TABLE payments ADD COLUMN IF NOT EXISTS ref VARCHAR(255) DEFAULT '' AFTER mode;";
    }

    private static String updateRevenuesTable_112724() {
        return "ALTER TABLE revenues ADD COLUMN IF NOT EXISTS mode VARCHAR(255) DEFAULT 'Cash' AFTER amount; " +
                "ALTER TABLE revenues ADD COLUMN IF NOT EXISTS ref VARCHAR(255) DEFAULT '' AFTER mode; " +
                "ALTER TABLE revenues ADD COLUMN IF NOT EXISTS attachment VARCHAR(255) DEFAULT '' AFTER date;";
    }

    private static String updateUsersTable_112724() {
        return "ALTER TABLE users ADD COLUMN IF NOT EXISTS fullname VARCHAR(255) DEFAULT '' AFTER password; " +
                "ALTER TABLE users ADD COLUMN IF NOT EXISTS designation VARCHAR(255) DEFAULT 'Employee' AFTER fullname;";
    }

    private static String updateAccountsTable_112724() {
        return "ALTER TABLE accounts ADD COLUMN IF NOT EXISTS bank_account_name VARCHAR(255) DEFAULT '' AFTER email; " +
                "ALTER TABLE accounts ADD COLUMN IF NOT EXISTS bank_account_no VARCHAR(255) DEFAULT '' AFTER bank_account_name;";
    }

    private static String updateExpensesTable_112824() {
        return "ALTER TABLE expenses ADD COLUMN IF NOT EXISTS mode VARCHAR(255) DEFAULT 'Cash' AFTER amount; " +
                "ALTER TABLE expenses ADD COLUMN IF NOT EXISTS ref VARCHAR(255) DEFAULT '' AFTER mode; " +
                "ALTER TABLE expenses ADD COLUMN IF NOT EXISTS category VARCHAR(255) DEFAULT 'Operational' AFTER id; " +
                "ALTER TABLE expenses ADD COLUMN IF NOT EXISTS attachment VARCHAR(255) DEFAULT '' AFTER date;";
    }

    private static String updateCashTransactionsTable_120124() {
        return "ALTER TABLE cash_transactions ADD COLUMN IF NOT EXISTS attachment VARCHAR(255) DEFAULT '' AFTER date";
    }

    private static String updateDailySummariesTable_120224() {
        return "ALTER TABLE daily_summaries ADD COLUMN IF NOT EXISTS cash_in DOUBLE DEFAULT '0.0' AFTER expenses; " +
                "ALTER TABLE daily_summaries ADD COLUMN IF NOT EXISTS cash_out DOUBLE DEFAULT '0.0' AFTER cash_in;";
    }

    private static String updatePaymentsTable_010125() {
        return "ALTER TABLE payments ADD COLUMN IF NOT EXISTS address VARCHAR(255) DEFAULT '' AFTER name; " +
                "ALTER TABLE payments ADD COLUMN IF NOT EXISTS contact VARCHAR(12) DEFAULT '' AFTER address;";
    }

    private static String updatePaymentItemsTable_010125() {
        return "ALTER TABLE payment_items ADD COLUMN IF NOT EXISTS quantity INTEGER DEFAULT '1' AFTER amount;";
    }

    private static String updatePaymentItemsTable_010225() {
        return "ALTER TABLE payment_items ADD COLUMN IF NOT EXISTS price DOUBLE DEFAULT '0.0' AFTER amount;";
    }
}
