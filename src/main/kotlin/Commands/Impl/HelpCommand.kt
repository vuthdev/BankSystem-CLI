package Commands.Impl

import Commands.Command

class HelpCommand: Command {
    override val name = "help"
    override fun execute(args: List<String>) {
        println("==============================[ Bank System CLI ]==============================")
        println("Available Commands:")

        println("\n[ General ]")
        println("  help                                       Show this help menu")

        println("\n[ User Management ]")
        println("  create-user                                Create a new user")
        println("  remove-user <username>                     Remove an existing user")
        println("  list-user                                  List all users")

        println("\n[ Account Management ]")
        println("  add-account <username>                     Add a new account for a user")
        println("  delete-account <account-num>               Delete an account by number")
        println("  list-account <username>                    List all accounts for a user")
        println("  view-account <account-num>                 View details of an account")

        println("\n[ Transactions ]")
        println("  deposit <account-num> <amt>                Deposit money into an account")
        println("  withdraw <account-num> <amt>               Withdraw money from an account")
        println("  transfer <sender-num> <recv-num> <amt>     Transfer money between accounts")
        println("  list-transaction                           List all transactions")

        println("================================================================================")

    }
}