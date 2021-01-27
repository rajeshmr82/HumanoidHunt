import java.io.*;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Tattoo {
    public Tattoo() {
        System.out.println("Tattoo");
    }

    private static List<String> readInput() throws IOException {
        File inputFile = new File(Objects.requireNonNull(Solution.class.getClassLoader().getResource("tattoo.txt")).getFile());
        InputStream inputStream = new FileInputStream(inputFile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        List<String> input = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            input.add(line);
        }

        return input;
    }

    public static String getPassword() {
        StringBuilder password = new StringBuilder();

        try {
            var allChannels = readInput();
            for (var channel:
                    allChannels) {
                password.append(findPasswordCharacter(channel)) ;
                System.out.println(password.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

       return password.toString();
    }

    private static char findPasswordCharacter(String channel) {
        List<Integer> bytes = new ArrayList<>();
        while (channel.length()>0){
            var byteString = channel.substring(0,8);
            bytes.add(Integer.parseInt(byteString, 2));
            channel = channel.substring(8);
        }
        int currIndex = 0;
        boolean foundStart=false;

        while (true){
            int newIndex = bytes.get(currIndex);
            if(newIndex>=bytes.size()){
                if(!foundStart){
                    currIndex++;
                }else{
                    return (char)newIndex;
                }
            }else{
                currIndex = newIndex;
                foundStart = true;
            }
        }
    }

    public int getNumber(String binary){
        if(binary.length()!=8){
            return -1;
        }
        int result=0;
        for (int i = 0; i < binary.length(); i++) {
            if(binary.charAt(i)=='1'){
                result +=Math.pow(2,7-i);
            }
        }

        return result;
    }
}
