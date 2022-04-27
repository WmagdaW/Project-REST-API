package revision;

import javax.validation.constraints.Max;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static int max;


    public static long getCountEmptyStringUsingJava8(List<String> strings) {
        return strings.stream()
                .filter(text -> text.length() == 0)
                .count();
    }

    public static long getCountLength3UsingJava8(List<String> strings) {
        return strings.stream()
                .filter(text -> text.length()==3)
                .count();
    }

    public static List<String> deleteEmptyStringsUsingJava8(List<String> strings) {
        return strings.stream()
                .filter(text -> text.length()>0)
                .collect(Collectors.toList());
    }

    public static String getMergedStringUsingJava8(List<String> strings, String letters) {
        return strings.stream()
                .filter(text -> text.length()>0)
                .collect(Collectors.joining(letters));

    }

    public static List<Integer> getSquaresJava8 (List<Integer> numbers) {
        return numbers.stream()
                .map (n -> n*n)
                .distinct()
                .collect(Collectors.toList());
    }

    public static Integer getMaxJava8 (List<Integer> numbers) {
        return numbers.stream()
                .max(Comparator.comparing(Integer::valueOf))
                .get();

    }

    public static Integer getMinJava8 (List<Integer> numbers) {
        return numbers.stream()
                .min (Comparator.comparing(Integer::valueOf))
                .get();
    }

    public static Integer getSumJava8 (List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

//    public static Integer getAverageJava8 (List<Integer> numbers) {
//        return numbers.stream()
////                .mapToDouble(Integer::doubleValue)
//                .mapToInt(Integer::intValue)
//                .average();
//    }
}
