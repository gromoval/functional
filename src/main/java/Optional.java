import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

class FindUserQuiz {

    public static Optional<UserO> findUserByAccountId(Set<UserO> users, String id) {
        return Optional.of(users.stream().filter(x -> Objects.equals(x.getAccount().get().getId(), id)).findAny()).orElse(Optional.empty());
        // write your code here
    }
}

class AccountO {
    private final String id;

    public AccountO(String id) {
        this.id = id;

    }

    public String getId() {
        return id;
    }
}

class UserO {
    private final String login;
    private final AccountO account;

    public UserO(String login, AccountO account) {
        this.login = login;
        this.account = account;
    }

    public String getLogin() {
        return login;
    }

    public Optional<AccountO> getAccount() {
        return Optional.ofNullable(account);
    }
}

class MainO {
    public static void main(String[] args) {
        AccountO account1 = new AccountO("Account1");
        AccountO account2 = new AccountO("Account2");
        AccountO account3 = new AccountO("Account3");
        Set usersSet = new HashSet();
        usersSet.add(new UserO("User1", account1));
        usersSet.add(new UserO("User2", account2));
        usersSet.add(new UserO("User3", account3));
        System.out.println(FindUserQuiz.findUserByAccountId(usersSet, "Account1"));//return Optional user object
        System.out.println(FindUserQuiz.findUserByAccountId(usersSet, "Account22"));//return Optional.empty

    }
}