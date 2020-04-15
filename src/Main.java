import connection.HibernateUtil;
import pages.Login;

public class Main {

    public static void main(final String[] args) {
        HibernateUtil.init();

        Login loginWindow = new Login();
        loginWindow.toogle();
    }
}
