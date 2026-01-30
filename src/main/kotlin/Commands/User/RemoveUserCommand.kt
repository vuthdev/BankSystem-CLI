package Commands.User

import Commands.Command
import Service.Bank

class RemoveUserCommand(
    val bank: Bank,
): Command {
    override val name = "delete-user"
    override fun execute(args: List<String>) {
        if (args.isEmpty()) {
            println("Error: delete-user <username>")
            return
        }

        if (args.size != 1) {
            println("Error: delete-user <username>")
            return
        }

        val username = args[0].lowercase()

        print("Are you sure you want to delete this account? (y/n): ")
        val isDelete = readln()
        if (isDelete == "y" || isDelete == "yes") {
            bank.removeUser(username)
        } else {
            return
        }
    }
}