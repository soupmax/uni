package Probleme;

//aufg1
public class NotNull {

    @SafeVarargs
    public static <T> T notNull(T... args) {
        for (T arg : args) {
            if (arg != null) {
                return arg;
            }
        }
        return null;
    }
}
