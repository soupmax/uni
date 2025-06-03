package ElementareSprachelemente;

//aufgabe 7
public class Converter {

    // 1. Dezimal → Binär (Dual)
    public static String decimalToBinary(int number) {
        if (number == 0)
            return "0";
        StringBuilder binary = new StringBuilder();
        while (number > 0) {
            binary.insert(0, number % 2);
            number /= 2;
        }
        return binary.toString();
    }

    // 2. Binär → Dezimal
    public static int binaryToDecimal(String binary) {
        int decimal = 0;
        for (int i = 0; i < binary.length(); i++) {
            char c = binary.charAt(binary.length() - 1 - i);
            if (c == '1') {
                decimal += Math.pow(2, i);
            }
        }
        return decimal;
    }

    // 3. Dezimal → beliebige Basis (zwischen 2 und 36)
    public static String decimalToBase(int number, int base) {
        if (base < 2 || base > 36)
            throw new IllegalArgumentException("Basis muss zwischen 2 und 36 liegen.");
        return Integer.toString(number, base);
    }

    // 4. Beliebige Basis → Dezimal
    public static int baseToDecimal(String number, int base) {
        if (base < 2 || base > 36)
            throw new IllegalArgumentException("Basis muss zwischen 2 und 36 liegen.");
        return Integer.parseInt(number, base);
    }

    // Test
    public static void main(String[] args) {
        int dezimal = 10;
        String binary = decimalToBinary(dezimal);
        System.out.println("Dezimal " + dezimal + " → Binär: " + binary);

        String bin = "1010";
        int dez = binaryToDecimal(bin);
        System.out.println("Binär " + bin + " → Dezimal: " + dez);

        int num = 255;
        String hex = decimalToBase(num, 16);
        System.out.println("Dezimal " + num + " → Hexadezimal: " + hex);

        String base36 = "1z";
        int base10 = baseToDecimal(base36, 36);
        System.out.println("Basis36 " + base36 + " → Dezimal: " + base10);
    }
}
