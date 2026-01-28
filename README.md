# 🏦 Bank System (CLI Application)

A command-line banking system built to practice backend fundamentals such as system design, business logic, and transaction flow.

---

## 📌 Available Commands

### 👤 User Commands

| Command                  | Description                |
| ------------------------ | -------------------------- |
| `create-user`            | Create a new user          |
| `list-users`             | Show all users             |
| `delete-user <username>` | Delete an existing user    |

---

### 💳 Account Commands

| Command                               | Description                                 |
| ------------------------------------- | ------------------------------------------- |
| `create-account <username>`           | Create a new bank account for selected user |
| `list-account <username>`             | Show all accounts of selected user          |
| `delete-account <account-num>`        | Delete an account by number                 |
| `balance <account-num>`               | Show current account balance                |

---

### 💸 Transaction Commands

| Command                                           | Description                       |
| ------------------------------------------------- | --------------------------------- |
| `deposit <account-num> <amt>`                     | Deposit money into account        |
| `withdraw <account-num> <amt>`                    | Withdraw money from account       |
| `transfer <sender-num> <recv-num> <amt>`          | Transfer money between account    |

---
