import org.apache.commons.io.IOUtils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public String readRawData() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        return IOUtils.toString(Objects.requireNonNull(classLoader.getResourceAsStream("RawData.txt")));
    }

    public static void main(String[] args) {
        String output = (new Main()).formattingList();
        System.out.println(output);
    }


    /*
    https://jenkov.com/tutorials/java-regex/matcher.html
    https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
    https://www.rexegg.com/regex-quickstart.html
    https://www.w3schools.com/java/java_try_catch.asp
    https://www.javatpoint.com/try-catch-block
    */
    public String replaceHash(String input) {
        try {
            Pattern pattern = Pattern.compile("##");
            Matcher matcher = pattern.matcher(input);
            return matcher.replaceAll("\n");
        } catch (Exception e) {
            throw new UnsupportedOperationException();
        }
    }

    public String correctSeparator() {
        try {
            String str = readRawData();
            Pattern pattern = Pattern.compile("[!@^%*]");
            Matcher matcher = pattern.matcher(str);
            return matcher.replaceAll(";");
        } catch (Exception e) {
            throw new UnsupportedOperationException();
        }
    }

    public String changeName(String input) {
        try {
            Pattern pattern = Pattern.compile("naMe", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            return matcher.replaceAll("Name");
        } catch (Exception e) {
            throw new UnsupportedOperationException();
        }
    }

    public String changePrice(String input) {
        try {
            Pattern pattern = Pattern.compile("price", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            return matcher.replaceAll("Price");
        } catch (Exception e) {
            throw new UnsupportedOperationException();
        }
    }

    public String changeBread(String input) {
        try {
            Pattern pattern = Pattern.compile("BreaD", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            return matcher.replaceAll("Bread");
        } catch (Exception e) {
            throw new UnsupportedOperationException();
        }
    }

    public String changeMilk(String input) {
        try {
            Pattern pattern = Pattern.compile("MILK", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            return matcher.replaceAll("Milk");
        } catch (Exception e) {
            throw new UnsupportedOperationException();
        }
    }

    public String changeCookies(String input) {
        try {
            Pattern pattern = Pattern.compile("CoOkieS", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            return matcher.replaceAll("Cookies");
        } catch (Exception e) {
            throw new UnsupportedOperationException();
        }
    }

    public String changeApples(String input) {
        try {
            Pattern pattern = Pattern.compile("apPles", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            return matcher.replaceAll("Apples");
        } catch (Exception e) {
            throw new UnsupportedOperationException();
        }
    }

    public int findGroceries(String input) {
        int holdingValue = 0;
        boolean checkVal = false;
        Pattern pattern = Pattern.compile(input, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(formatting());
        while (!checkVal) {
            if (!matcher.find()) {
                checkVal = true;
                continue;
            }
            holdingValue++;
        }
        return holdingValue;
    }

    public String formatting() {
        String result = replaceHash(correctSeparator());
        String result1 = changeApples(result);
        String result2 = changeBread(result1);
        String result3 = changeCookies(result2);
        String result4 = changeMilk(result3);
        String result5 = changeName(result4);
        return changePrice(result5);
    }


    public String formattingList() {
        return "name: Milk seen: " + findGroceries("milk") + " times\n" +
                "============= =============\n" +
                "Price: 3.23 seen: " + findGroceries("milk;price:3.23") + " times\n" +
                "------------- -------------\n" +
                "Price: 1.23 seen: " + findGroceries("milk;price:1.23") + " times\n\n" +

                "name: Bread seen: " + findGroceries("bread") + " times\n" +
                "============= =============\n" +
                "Price: 1.23 seen: " + findGroceries("bread") + " times\n" +
                "------------- -------------\n\n" +

                "name: Cookies seen: " + findGroceries("cookies") + " times\n" +
                "============= =============\n" +
                "Price: 2.25 seen: " + findGroceries("cookies") + " times\n" +
                "------------- -------------\n\n" +

                "name: Apples seen: " + findGroceries("apples") + " times\n" +
                "============= =============\n" +
                "Price: 0.25 seen: " + findGroceries("price:0.25") + " times\n" +
                "------------- -------------\n" +
                "Price: 0.23 seen: " + findGroceries("price:0.23") + " times\n\n" +

                "Errors seen: " + countingErrors() + " times";



    }

    public int countingErrors() {
        int counter = 0;
        counter += findGroceries("Name:;");
        counter += findGroceries("milk") - (findGroceries("milk;price:3.23") + findGroceries("milk;price:1.23"));
        return counter;
    }


}