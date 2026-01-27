import Commands.Command
import Commands.Impl.AddAccountCommand
import Commands.Impl.CreateUserCommand
import Commands.Impl.DeleteAccountCommand
import Commands.Impl.DepositCommand
import Commands.Impl.HelpCommand
import Commands.Impl.ListAccountCommand
import Commands.Impl.ListTransactionsCommand
import Commands.Impl.ListUserCommand
import Commands.Impl.RemoveUserCommand
import Commands.Impl.TransferCommand
import Commands.Impl.ViewAccountCommand
import Commands.Impl.WithdrawCommand
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
        AddAccountCommand(bank),
        DeleteAccountCommand(bank),
        ListUserCommand(bank),
        ListAccountCommand(bank),
        DepositCommand(bank),
        WithdrawCommand(bank),
        TransferCommand(bank),
        ListTransactionsCommand(bank),
        ViewAccountCommand(bank)
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

