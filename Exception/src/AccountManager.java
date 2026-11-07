import java.util.ArrayList;

class AccountManager {
    private ArrayList<Account> accounts = new ArrayList<>();
    public void register(Account account)
            throws DuplicateAccountException {
        for (Account a : accounts) {
            if (a.getUsername().equals(account.getUsername())) {
                throw new DuplicateAccountException(
                    "Username " + account.getUsername()
                    + " da ton tai!"
                );
            }
        }
        accounts.add(account);
        System.out.println(
            "Dang ky thanh cong: " + account.getUsername()
        );
    }
}