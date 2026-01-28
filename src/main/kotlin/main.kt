import Commands.Command
import Commands.Account.CreateAccountCommand
import Commands.User.CreateUserCommand
import Commands.Account.DeleteAccountCommand
import Commands.Transaction.DepositCommand
import Commands.General.HelpCommand
import Commands.Account.ListAccountCommand
import Commands.Transaction.ListTransactionsCommand
import Commands.User.ListUserCommand
import Commands.User.RemoveUserCommand
import Commands.Transaction.TransferCommand
import Commands.Account.BalanceCommand
import Commands.Transaction.WithdrawCommand
import Repository.AccountRepository
import Repository.TransactionRepository
import Repository.UserRepository
import Service.Bank

fun main() {
    val bank = Bank(
        userRepo = UserRepository(),
        accountRepo = AccountRepository(),
        transactionRepo = TransactionRepository()
    )

    val commands: Map<String, Command> = listOf(
        HelpCommand(),
        CreateUserCommand(bank),
        RemoveUserCommand(bank),
        CreateAccountCommand(bank),
        DeleteAccountCommand(bank),
        ListUserCommand(bank),
        ListAccountCommand(bank),
        DepositCommand(bank),
        WithdrawCommand(bank),
        TransferCommand(bank),
        ListTransactionsCommand(bank),
        BalanceCommand(bank)
    ).associateBy { it.name }

    println("Welcome to Bank CLI. Type 'help' for commands.")

    while (true) {
        print("> ")
        val input = readln() ?: break
        val parts = input.trim().split(" ")
        val commandName = parts.firstOrNull() ?: continue
        val args = parts.drop(1)

        if (commandName == "exit") {
            println("Goodbye, command-exit!")
            break
        }

        commands[commandName]?.execute(args) ?: println("Invalid command: $commandName.")
    }
}

