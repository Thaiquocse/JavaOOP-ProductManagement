/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import static java.lang.String.format;
import java.util.Scanner;

/**
 *
 * @author thaiq
 */
public class MyTool {

    private static Scanner sc = new Scanner(System.in);

    public static String regexString(String inputMsg, String errorMsg, String format) {
        String id;
        boolean match;
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim();
            match = id.matches(format);
            if (id.length() == 0 || id.isEmpty() || match == false) {
                System.out.println(errorMsg);
            } else {
                return id;
            }
        }
    }

    public static int getAnInteger(String inputMsg, String errorMsg, int minNumber, int maxNumber) {
        int n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < minNumber || n > maxNumber) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }public static double getADouble(String inputMsg, String errorMsg, double minNumber, double maxNumber) {
        double n, tmp;
        
        if (minNumber > maxNumber) {
            tmp = minNumber;
            minNumber = maxNumber;
            maxNumber = tmp;
        }
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                if (n < minNumber || n > maxNumber)
                    throw new Exception();                
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
    
    public static String getString(String inputMsg, String errorMsg) {
        String id;        
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim();            
            if (id.length() == 0 || id.isEmpty())
                System.out.println(errorMsg);
            else 
                return id;
        }
    }
}



