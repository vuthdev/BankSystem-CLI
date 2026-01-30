package Commands.Account

import Commands.Command
import Service.Bank

class DeleteAccountCommand(
    val bank: Bank,
): Command {
    override val name = "delete-account"
    override fun execute(args: List<String>) {
        if (args.isEmpty()) {
            println("delete-account <account-num>")
            return
        }
        if (args.size != 1) {
            println("delete-account <account-num>")
            return
        }

        val number = args[0].uppercase()

        print("Are you sure you want to delete this account? (y/n): ")
        val isDelete = readln()
        if (isDelete == "y" || isDelete == "yes") {
            bank.removeAccount(number)
        } else {
            return
        }
    }
}