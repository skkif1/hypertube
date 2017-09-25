package com.omotyliu.videoProvider;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.*;

//run programm
//
//      javac Intentics.java
//      java Intentics.class
//

public class Intentics {

    private static final TreeSet<Integer> primeNumbers = new TreeSet<>();
    private ArrayList<ResultNode> results = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException, InvalidArgumentException {

        Intentics intentics = new Intentics();
        intentics.getPrimeNumbers(10_000, 99_000);

        int lastPrimeNum = primeNumbers.last();
        int number = primeNumbers.higher(0);

        while (number != lastPrimeNum)
        {
            intentics.findPrime(number);
            number = primeNumbers.higher(number);
            primeNumbers.remove(number);
        }
        intentics.displayPrime();
    }

    private void findPrime(int number)
    {
        int last = primeNumbers.last();
        int multyplier = primeNumbers.higher(number);

        while (multyplier != last)
        {
            String potentialPolyndrom  =  String.valueOf((long)number * (long)multyplier);
            String tempReverse = new String(new StringBuilder(potentialPolyndrom).reverse());
            if (potentialPolyndrom.equals(tempReverse))
                results.add(new ResultNode(number, multyplier));
            multyplier = primeNumbers.higher(multyplier);
        }
    }

    private void getPrimeNumbers(int start, int end)
    {
        for (int i = start; i < end; i++) {
            if (isPrime(i))
                primeNumbers.add(i);
        }
    }

    private boolean isPrime(int n)
    {

        if (n % 2 == 0) return false;

        for(int i = 3; i * i <= n; i += 2) {
            if(n % i == 0)
                return false;
        }
        return true;
    }

    private static class ResultNode
    {
        int multiplierFirst;
        int multiplierSecond;

         ResultNode(int multiplierFirst, int multiplierSecond) {
            this.multiplierFirst = multiplierFirst;
            this.multiplierSecond = multiplierSecond;
        }
    }


    private void displayPrime() throws InvalidArgumentException {
        TreeSet<Long> set = new TreeSet<>();

        for (ResultNode num : results) {
            set.add((long)num.multiplierFirst * (long)num.multiplierSecond);
        }
        Long temp = set.last();
        System.out.println(set.last());
        System.out.println("number of Polyndroms = " + results.size() + "\n");

        for (ResultNode node : results) {
            System.out.print(node.multiplierFirst + " * ");
            System.out.print(node.multiplierSecond + " = ");
            System.out.print((long)node.multiplierFirst * (long)node.multiplierSecond + "\n");
            if ((long)node.multiplierFirst * (long)node.multiplierSecond > temp)
                throw new InvalidArgumentException(new String[]{"Lolll"});
        }
    }
}
