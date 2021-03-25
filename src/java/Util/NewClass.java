/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;
import java.util.*;

/**
 *
 * @author a1396582
 */
public class NewClass {

    public static void main(String[] args) {
//        Random r = new Random();
//        int a = 5;
//        System.out.println(r.nextInt(a));
//        forEach(i -> System.out.println(r.nextInt(a)))
//        ArrayList<Integer> al = new ArrayList<>();
//        al.add(100);
//        al.add(98);
//        al.add(103);
//        al.add(92);
//        al.add(105);

//        al.forEach(a -> {
//            if (a > 100) {
//                System.out.println(a);
//            }
//        });
//        Consumer<Integer> c=(Integer i)->{
//            System.out.println(r.nextInt(i));
//        };
//        
//        al.forEach(c);
//        al.stream().filter(a->a<100).forEach(System.out::println);
//        al.stream().filter(a->a<100).map(a->a).forEach(System.out::println);
//        for(int i=0;i<al.size();i++){
//            if(al.get(i)>100){
//                System.out.println(al.get(i));
//            }
//        }
//        al.stream().filter()
//        Scanner sc=new Scanner(System.in);
//        ArrayList<Float> al=new ArrayList<>();
//        while(sc.hasNextLine()){
//            String s=sc.nextLine();
//            if(s.equals("q"))
//                break;
//            if(!s.equals("42.195")){
//                al.add(Float.parseFloat(s));
//            }
//        }
//        Collections.sort(al,Collections.reverseOrder());
//        System.out.println(al.get(0)+" "+al.get(1)+" "+al.get(2));

        String s = "spring_boot_project";
        String res = "";
        if (s.indexOf("_") != s.length() - 1 && s.contains("_")) {
            System.out.println(1);
            for (int i = 0; i < s.length(); i++) {
                if (s.substring(i, i + 1).equals("_")) {
                    res += s.substring(i + 1, i + 2).toUpperCase();
                    i++;
                } else {
                    res += s.substring(i, i + 1);
                }
            }
        } else {
            System.out.println(2);
            for (int i = 0; i < s.length(); i++) {
                if (Character.isUpperCase(s.charAt(i))) {
                    res += "_" + s.substring(i, i + 1).toLowerCase();
//                    i++;
                } else {
                    res += s.substring(i, i + 1);
                }
            }
        }

        System.out.println(res);
    }
    public static int add(int n){
        return n+n;
    }

}

 class Person extends NewClass{
     NewClass n=new NewClass();
     static NewClass n1=new Person();
//     Person p=new NewClass();
     public static void main(String[] args) {
         System.out.println(n1.add(2));
     }
}
