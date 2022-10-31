import java.nio.file.Path;
import java.nio.file.Paths;

public class ContactsApplication {
    static Path p = Paths.get("src","text", "text.txt");

    public static void main(String[] args) {

        ApplicationMethods app = new ApplicationMethods();

        app.promptUser();




    }
}
