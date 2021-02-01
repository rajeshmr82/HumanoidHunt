import java.io.IOException;
import java.util.regex.Pattern;

public class Android {

    public static String findPath() {
        Grid grid= new Grid();
        try {
            var input = Utility.readInput("android.txt");
            grid.parseInput(input);
            var f=grid.findPath();
            System.out.println(f);
            f.printPath();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }


}
