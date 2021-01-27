import java.io.*;
import java.util.*;

public class Utility {

    static List<String> readInput(String fileName) throws IOException {
        File inputFile = new File(Objects.requireNonNull(Solution.class.getClassLoader().getResource(fileName)).getFile());
        InputStream inputStream = new FileInputStream(inputFile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        List<String> input = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            input.add(line);
        }

        return input;
    }

    public static HashMap<Character, Integer> sortDescByValue(HashMap<Character, Integer> map) {
        List<Map.Entry<Character, Integer>> list
                = new LinkedList<>(map.entrySet());

        list.sort((obj1, obj2) -> (obj2.getValue())
                .compareTo(obj1.getValue()));

        HashMap<Character, Integer> result
                = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> mapEntry : list) {
            result.put(mapEntry.getKey(), mapEntry.getValue());
        }

        return result;
    }
}
