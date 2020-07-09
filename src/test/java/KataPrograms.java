public class KataPrograms {

    public static void main(String[] args) {
        String str = "The quick brown fox jumps over the lazy dog";
        int num = 987;
        String str1 = "zpzpzp";
        if (pangramCheck(str)) {
            System.out.println(str + " is Pangram");
        } else {
            System.out.println(str + " is not Pangram");
        }

        System.out.println("Digital root is " + digitalRoot(num));

        System.out.println("String is " + getXO(str1));

        System.out.println("Longest string is " + longest("loopingisfunbutdangerous", "lessdangerousthancoding"));

        System.out.println("Readable Time is " + timeReadable(34556));
    }

    public static boolean pangramCheck(String sentence) {
        return sentence.chars().map(Character::toLowerCase).filter(Character::isAlphabetic).distinct().count() == 26;
    }

    public static int digitalRoot(int num) {
        return (1 + ((num - 1) % 9));
    }

    public static boolean getXO(String str) {
        return str.replace("x", "").length() == str.replace("o", "").length();
    }

    public static String longest(String s1, String s2) {
        String s = s1 + s2;
        return s
                .chars()
                .distinct()
                .sorted()
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String timeReadable(int seconds) {
        return String.format(
                "%02d:%02d:%02d",
                seconds / 3600,
                seconds / 3600 % 60,
                seconds % 60);
    }
}
