import java.io.IOException;
import java.util.HashMap;

public class NanoBots {
    public static String getBaseValue() {
        StringBuilder baseValue = new StringBuilder();
        try {
            var signal = Utility.readInput("nanobots.txt").get(0);
            HashMap<Character, Integer> map = new HashMap<>();
            for (var c :
                    signal.toCharArray()) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            var sortedMap = Utility.sortDescByValue(map);
            Character currentChar = ' ';
            if(sortedMap.entrySet().stream().findFirst().isPresent()) {
                currentChar = sortedMap.entrySet().stream().findFirst().get().getKey();
            }

            baseValue.append(currentChar);
            while (!currentChar.equals(';')) {
                currentChar = getNextCharacter(signal, currentChar);
                if(currentChar.equals(';')){
                    break;
                }
                baseValue.append(currentChar);
                System.out.println(baseValue.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return baseValue.toString();
    }

    private static Character getNextCharacter(String signal, Character currentChar) {
        HashMap<Character, Integer> charMap = new HashMap<>();
        int skipIndex = 0;
        var index = signal.indexOf(currentChar, skipIndex);
        while (index>=0) {
            if (index < signal.length() - 1) {
                char nextChar = signal.charAt(index + 1);
                charMap.put(nextChar, charMap.getOrDefault(nextChar, 0) + 1);
            }
            skipIndex = index;
            index = signal.indexOf(currentChar, skipIndex+1);
        }
        charMap = Utility.sortDescByValue(charMap);
        if(charMap.entrySet().stream().findFirst().isPresent()){
            currentChar = charMap.entrySet().stream().findFirst().get().getKey();
        }
        return currentChar;
    }
}
