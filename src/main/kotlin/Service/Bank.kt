package Service

import Config.BankConfig
import Model.Account
import Model.Enums.TransactionStatus
import Model.Enums.TransactionType
import Model.Transaction
import Model.User
import Repository.AccountRepository
import Repository.TransactionRepository
import Repository.UserRepository
import Util.BankValidator

class Bank(
    private val userRepo: UserRepository,
    private val accountRepo: AccountRepository,
    private val transactionRepo: TransactionRepository,
){
    fun createUser(user: User) {
        if (userRepo.findByUsername(user.username) != null) {
            println("Error: User ${user.username} already exists")
            return
        }

        if (userRepo.findByEmail(user.email) != null) {
            println("Error: User ${user.email} already exists")
            return
        }

        val newUser = User (
            username = user.username,
            password = user.password,
            email = user.email,
        )
        userRepo.save(newUser)
        val newAccount = Account(
            ownerId = newUser.id,
        )
        accountRepo.save(newAccount)
        println("User ${newUser.username} created with a Account ${newAccount.id}")
    }

    fun addAccount(username: String) {
        val user = userRepo.findByUsername(username)
        if (user == null) {
            println("User $username not found")
            return
        }

        val numAcc = accountRepo.countAllAccounts(username)
        if (numAcc >= BankConfig.ACCOUNT_LIMIT) {
            println("Account limited is 3!")
            return
        }

        val newAccount = Account(
            ownerId = user.id,
        )
        accountRepo.save(newAccount)
        println("Successfully added new account")
    }

    fun removeUser(username: String) {
        val userId = userRepo.findByUsername(username)
        if (userId == null) {
            println("Error: User $username not found")
            return
        }

        accountRepo.deleteByUserId(userId)
        userRepo.deleteByUsername(username)
        println("User $username removed successfully.")
    }

    fun removeAccount(accountNumber: String) {
        val account = accountRepo.findAccountByNumber(accountNumber)

        if (account == null) {
            println("Error: Account $accountNumber not found")
            return
        }

        val username = userRepo.findById(account.ownerId)?.username

        if (username == null) {
            println("Error: User $username not found")
            return
        }

        if (accountRepo.countAllAccounts(username) <= BankConfig.MIN_ACCOUNT) {
            println("Error: This user only has one account cannot be deleted.")
            return
        }

        accountRepo.deleteByAccountNumber(accountNumber)
        println("Account $accountNumber removed successfully.")
    }

    fun deposit(amount: Double, accountNumber: String) {
        val account = accountRepo.findAccountByNumber(accountNumber)

        if (account == null) {
            println("Error: Account $accountNumber not found.")
            return
        }

        if (!BankValidator.isValidAmount(amount)) {
            println("Error: Amount $amount is not valid")
            return
        }

        if (amount < BankConfig.MIN_BALANCE) {
            println("Error: Must deposit at lease 5 dollars")
            return
        }

        account.credit(amount)
        accountRepo.updateBalance(account, account.balance)
        println("Successfully deposited $amount.")

        val newTransaction = Transaction(
            sender = account.accountNumber,
            receiver = account.accountNumber,
            amount = amount,
            type = TransactionType.DEPOSIT,
            status = TransactionStatus.CONFIRMED,
        )
        transactionRepo.save(newTransaction)
        printTransactions(newTransaction)
    }

    fun withdraw(amount: Double, accountNumber: String) {
        val account = accountRepo.findAccountByNumber(accountNumber)

        if (account == null) {
            println("Error: Account $accountNumber not found.")
            return
        }

        if (amount > account.balance) {
            println("Error: You don't have enough balance to withdraw.")
            return
        }

        if (!BankValidator.isValidAmount(amount)) {
            println("Error: Amount $amount is not valid.")
            return
        }

        if (amount > BankConfig.MAX_WITHDRAWAL_AMOUNT) {
            println("Error: Exceeds maximum withdrawal amount.")
            return
        }

        account.debit(amount)
        accountRepo.updateBalance(account, account.balance)
        println("Successfully withdraw $amount.")

        val newTransaction = Transaction(
            sender = account.accountNumber,
            receiver = account.accountNumber,
            amount = amount,
            type = TransactionType.WITHDRAW,
            status = TransactionStatus.CONFIRMED,
        )
        transactionRepo.save(newTransaction)
        printTransactions(newTransaction)
    }

    fun transfer(amount: Double, from: String, to: String) {
        if (from == to) {
            println("Error: Error: You cannot transfer to your own account.")
            return
        }
        val fromAccount = accountRepo.findAccountByNumber(from)
        val toAccount = accountRepo.findAccountByNumber(to)

        if (fromAccount == null || toAccount == null) {
            println("Error: Account not found.")
            return
        }

        if (!BankValidator.isValidAmount(amount)) {
            println("Error: Amount $amount is not valid.")
            return
        }

        if (fromAccount.balance < amount) {
            println("Error: You don't have enough balance to transfer!")
            return
        }

        fromAccount.debit(amount)
        toAccount.credit(amount)
        accountRepo.updateBalance(fromAccount, fromAccount.balance)
        accountRepo.updateBalance(toAccount, toAccount.balance)

        println("Successfully transferred.")

        val newTransaction = Transaction(
            sender = fromAccount.accountNumber,
            receiver = toAccount.accountNumber,
            amount = amount,
            type = TransactionType.TRANSFER,
            status = TransactionStatus.CONFIRMED,
        )
        transactionRepo.save(newTransaction)
        printTransactions(newTransaction)
    }

    fun listTransactions() {
        transactionRepo.getAll().forEach {
            printTransactions(it)
        }
    }

    fun printTransactions(transaction: Transaction) {
        println("Transaction [${transaction.id}]")
        println("   type: ${transaction.type}")
        println("   status: ${transaction.status}")
        println("   amount: ${transaction.amount}")
        println("   sender: ${transaction.sender}")
        println("   receiver: ${transaction.receiver}")
        println("   currency: ${transaction.currency}")
    }

    fun listAccountsByUsername(username: String) {
        val user = userRepo.findByUsername(username)
        if(user == null) {
            println("User $username not found")
            return
        }
        val accounts = accountRepo.findAccountByUserId(user.id)
        println("================[ $username's account balances: ${ accountRepo.totalBalanceByUsername(username) } ]}] ]==============")
        accounts.forEach {
            println("-----------------------------------------------")
            println("Account Number: ${it.accountNumber}")
            println("Balance: ${it.balance}")
            println("Currency: ${it.currency}")
            println("-----------------------------------------------")
        }
        println("====================================================================")
    }

    fun checkAccount(accountNumber: String) {
        val account = accountRepo.findAccountByNumber(accountNumber)

        if (account == null) {
            println("Error: Account not found.")
            return
        }

        val username = userRepo.findById(account.ownerId)?.username

        if (username == null) {
            println("Error: Account not found.")
            return
        }

        println("===================================")
        println("Owner: $username")
        println("Account number: ${account.accountNumber}")
        println("balance: ${account.balance}$")
        println("===================================")
    }

    fun getAllUsers() {
        if(userRepo.findAll().isEmpty()) {
            return println("No users.")
        }
        println("=================================")
        userRepo.findAll().forEach {
            println("${it.username} has ${accountRepo.countAllAccounts(it.username)} accounts")
        }
        println("=================================")
    }
}