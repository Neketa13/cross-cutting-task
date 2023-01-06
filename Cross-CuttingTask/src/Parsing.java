import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;
import java.util.Scanner;



public class Parsing {
    //private static Expression expression;
    public static void main(String[]args) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
       // IvParameterSpec iv = CryptoUtils.getIVSecureRandom("AES");
        //String string="3 * (sin (pi) - 2) / e/(-1)";
        Expression expression = new ExpressionBuilder(ReadFromTxt()).variables("pi", "e").build().setVariable("pi", Math.PI).setVariable("e", Math.E);
        try {
            System.out.println(expression.evaluate());
        } catch (Throwable cause) {
            if (cause instanceof ArithmeticException && "Division by zero!".equals(cause.getMessage())) {
                System.out.println(Double.POSITIVE_INFINITY);
            } else {
                System.out.println(Double.NaN);
            }
        }
        //byte[] key= "Bar12345Bar12345".getBytes();
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecretKey originalKey = keyGenerator.generateKey();
        WriteFromTxt(expression.evaluate());

    }

    public static String ReadFromTxt() throws IOException {
        FileReader fileReader= new FileReader("input.txt");
        Scanner scan = new Scanner(fileReader);
        String str=scan.nextLine();
        fileReader.close();
        return str;
    }

    public static void WriteFromTxt(double result) throws IOException {
        FileWriter fileWriter=new FileWriter("output.txt");
        fileWriter.write(String.valueOf(result));
        fileWriter.close();
    }



    }

