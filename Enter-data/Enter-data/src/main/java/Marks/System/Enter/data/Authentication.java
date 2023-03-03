package Marks.System.Enter.data;

public class Authentication {
    static String[] users = {"admin", "malek", "mustafa"};
    static String[] passwords = {"admin@123", "malek@123", "mustafa@123"};

    public static boolean valid(String userName, String Password) {
        for (int i = 0; i < 3; i++) {
            if (userName.equals(users[i]) && Password.equals(passwords[i]))
                return true;
        }
        return false;
    }
}
