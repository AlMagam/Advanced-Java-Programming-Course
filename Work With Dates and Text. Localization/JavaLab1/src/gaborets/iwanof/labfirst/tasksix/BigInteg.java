package gaborets.iwanof.labfirst.tasksix;

import java.math.BigInteger;
import java.util.Random;

    public class BigInteg {
        public static void main(String[] args) {
            Random random = new Random();
            BigInteger ranNum = new BigInteger(100, random); // von 0 bis 10^100-1
            BigInteger exp = new BigInteger("5");//pow
            System.out.println("Random numer : " + ranNum + " going in pow(exponenta) " + exp);
            // func pow
            BigInteger res1 = ranNum.pow(exp.intValue());
            System.out.println("Result using exp: " + res1);
            // multipl
            BigInteger res2 = BigInteger.ONE;
            for (int i = 0; i < exp.intValue(); i++) {
                res2 = res2.multiply(ranNum);
            }
            System.out.println("Result using multiplication: " + res2);
        }
    }
