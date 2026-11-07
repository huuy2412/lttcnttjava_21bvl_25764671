public class Main {
    public static void main(String[] args) {
        AccountManager manager = new AccountManager();
        try {
            manager.register(new Account("huuy", "123"));
            manager.register(new Account("admin", "456"));
            manager.register(new Account("huuy", "789"));

        } catch (DuplicateAccountException e) {
            System.out.println("Loi: " + e.getMessage());
        }
    }
}