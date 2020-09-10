package converter;

import java.util.Scanner;

public class Main {

    public static String toBase1(String num) {
        StringBuilder sb = new StringBuilder();
        sb.append("1".repeat(Integer.parseInt(num)));
        return sb.toString();
    }

    public static int fromBase1(String num) { //to base 10
        return num.length();
    }

    public static String convertFractal(String fracPart, int srcRadix, int trgRadix) {
        StringBuilder sb = new StringBuilder();
        double number = 0.0;
        double newNum;
        if (srcRadix != 10) {
            double dividend;
            for (int i = 1; i <= fracPart.length(); i++) {
               dividend = Integer.parseInt(fracPart.substring(i - 1, i), srcRadix);
               number += dividend / Math.pow(srcRadix, i);
            }
            fracPart = number + "";
        } else {
            fracPart = "0." + fracPart;
        }

        number = Double.parseDouble(fracPart);
        for (int i = 0; i < 5; i++) {
             newNum = number * (double)trgRadix;
             number = newNum - (int)newNum;
             sb.append(Integer.toString((int)(newNum - number), trgRadix));
        }
        return String.format("%.7s", sb);
    }

    public static String convertInteger(String integPart, int srcRadix, int trgRadix) {
        StringBuilder sb = new StringBuilder();

        if (srcRadix == 1) {
            integPart = fromBase1(integPart) + "";
        } else if (srcRadix != 10) {
            integPart = Integer.parseInt(integPart, srcRadix) + "";
        }

        if (trgRadix == 1) {
            sb.append(toBase1(integPart));
        } else {
            sb.append(Integer.toString(Integer.parseInt(integPart), trgRadix));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        boolean isError = false;
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int sourceRadix = 0;
        int targetRadix = 0;
        String number = "";

        if (sc.hasNextInt()) {
             sourceRadix = sc.nextInt();
            if (sourceRadix < 1 || sourceRadix > 36) isError = true;
        } else isError = true;
        if (sc.hasNext()) {
             number = sc.next();
        } else isError = true;
        if (sc.hasNextInt()) {
            targetRadix = sc.nextInt();
            if (targetRadix < 1 || targetRadix > 36) isError = true;
        } else isError = true;

        if (isError) {
            System.out.println("error");
        } else {
            if (number.contains(".")) {
                String integerPart = number.split("\\.")[0];
                String fractionPart = number.split("\\.")[1];
                sb.append(convertInteger(integerPart, sourceRadix, targetRadix)).append(".");
                sb.append(convertFractal(fractionPart, sourceRadix, targetRadix));
            } else {
                sb.append(convertInteger(number, sourceRadix, targetRadix));
            }
            System.out.println(sb);
        }
    }
}