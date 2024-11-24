import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class uzd3
{
    public static void main(String[] args) throws Exception {
        String file = "123.txt";
        String text = TextAnalize(file);
        int result = countWords(text);
        System.out.println(result);
    }
    public static String TextAnalize(String file) throws Exception
    {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }
    public static int countWords(String text)
    {
        String[] words = text.split(" ");
        int count = 0;
        Pattern capitalLetterPattern = Pattern.compile("[A-Z]");
        for (String word : words) {

            if (word.contains("e"))
            {
                continue;
            }
            Matcher matcher = capitalLetterPattern.matcher(word);
            if (matcher.find())
            {
                count++;
            }
        }
        return count;
    }
}