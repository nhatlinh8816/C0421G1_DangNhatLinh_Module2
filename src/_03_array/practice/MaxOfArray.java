package _03_array.practice;

import java.util.Scanner;

public class MaxOfArray {
    public static void main(String[] args) {
            int m,i;    //khai báo số nguyên m,i;
            Scanner input = new Scanner(System.in);
            do {
                System.out.println("Nhập độ dài mảng");
                m = input.nextInt();
            }while (m<=0);
            int[]array = new int[m];   //khai báo mảng array có độ dài m;
            for (i=0;i<m;i++){
                System.out.println("Nhập phần tử tại vị trí "+i+":");
                array[i] = input.nextInt();
            }
            int max = array[0];
            for (i=1;i<m;i++){
                if (array[i]>max){
                    max = array[i];
                }
            }System.out.println("Max of array is "+max);
        }
    }
