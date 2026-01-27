package Commands.Impl

import Commands.Command
import Model.User
import Service.Bank

class CreateUserCommand(
    val bank: Bank,
): Command {
    override val name = "create-user"
    override fun execute(args : List<String>) {
        if (args.size > 1) {
            println("Error: 'create-user' should not have an arguments.")
            return
        }

        while (true) {
            print("Enter the name of the new user: ")
            val username = readln()
            print("Enter email of the new user: ")
            val email = readln()
            print("Enter password of the new user: ")
            val password = readln()

            if (password.isEmpty()) {
                println("Password cannot be empty.")
                continue
            }

            val user = User(
                username = username,
                email = email,
                password = password
            )

            bank.createUser(user)

            print("Do you want to continue create new user? (y/n): ")
            val again = readln().lowercase()
            if (again != "y" && again != "yes") {
                break
            }
        }
    }
}