import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KataCalculator {
    final static int INPUT_DATA = 3;
    final static int ORDINAL_FIRST_NUMBER = 0;
    final static int ORDINAL_SECOND_NUMBER = 2;
    final static int OPERATION_SIGN = 1;

    public static void main(String[] args) throws Exception {
        String[] operations = new String[]{"+", "-", "*", "/"};
        String line = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            line = reader.readLine();
        }

        String[] lineSplit = line.split(" ");

        if (lineSplit.length != INPUT_DATA) {
            throw new Exception("Введенные данные не удовлетворяет условию: 2 числа и знак арифметической операции в одну строку");
        }

        if (isArabic(lineSplit[ORDINAL_FIRST_NUMBER]) && isArabic(lineSplit[ORDINAL_SECOND_NUMBER])) {
            System.out.println(performAnAction(lineSplit[ORDINAL_FIRST_NUMBER], lineSplit[OPERATION_SIGN], lineSplit[ORDINAL_SECOND_NUMBER]));
        } else if (!isArabic(lineSplit[ORDINAL_FIRST_NUMBER]) && !isArabic(lineSplit[ORDINAL_SECOND_NUMBER])) {
            int resultArabic = performAnAction(romeToArabic(lineSplit[ORDINAL_FIRST_NUMBER]), lineSplit[OPERATION_SIGN], romeToArabic(lineSplit[ORDINAL_SECOND_NUMBER]));
            if (resultArabic < 1) {
                throw new Exception("В римской системе нет чисел меньше 1");
            }
            System.out.println(arabicToRome(resultArabic));
        } else if (!(isArabic(lineSplit[ORDINAL_FIRST_NUMBER]) && isArabic(lineSplit[ORDINAL_SECOND_NUMBER]))) {
            throw new Exception("Используются одновременно разные системы счисления");
        }
    }

    public static boolean isArabic(String operand) throws Exception {
        boolean flag = false;
        if (operand.matches("\\d*")) {
            flag = true;
        }
        return flag;
    }

    public static String romeToArabic(String romeNumber) {
        String[] romeNumberList = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] arabicNumberList = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        StringBuilder arabicNumber = new StringBuilder();
        for (int i = 0; i < romeNumberList.length; i++) {
            if (romeNumber.equals(romeNumberList[i])) {
                arabicNumber.append(arabicNumberList[i]);
                break;
            }
        }
        return arabicNumber.toString();
    }

    public static String arabicToRome(int arabicNumber) {
        String[] listRomeNumbers = new String[]{"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] listArabicNumbers = new int[]{100, 90, 50, 40, 10, 9, 5, 4, 1};
        int count = 0;
        int remainder;
        StringBuilder romeNumber = new StringBuilder();

        while (count < listRomeNumbers.length) {
            while (arabicNumber >= listArabicNumbers[count]) {
                remainder = arabicNumber / listArabicNumbers[count];
                arabicNumber %= listArabicNumbers[count];
                for (int i = 0; i < remainder; i++) {
                    romeNumber.append(listRomeNumbers[count]);
                }
            }
            count++;
        }
        return romeNumber.toString();
    }

    public static int performAnAction(String operator1, String operations, String operator2) throws Exception {
        Operation op;
        switch (operations) {
            case ("+"):
                op = Operation.SUM;
                break;
            case ("-"):
                op = Operation.SUBTRACT;
                break;
            case ("*"):
                op = Operation.MULTIPLY;
                break;
            case ("/"):
                op = Operation.DIVISION;
                break;
            default:
                throw new Exception("Неверный знак операции");
        }
        return op.perform(Integer.parseInt(operator1), Integer.parseInt(operator2));
    }
}
