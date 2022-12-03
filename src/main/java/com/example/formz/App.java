package com.example.formz;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner scan = new Scanner(System.in);

        try{
            System.out.println("PINJOL SEMOGA SUKSES\n================================");
            System.out.print("Masukkan nama peminjam: ");
            String name = scan.nextLine();
            System.out.print("Masukkan umur peminjam: ");
            int age = Integer.parseInt(scan.nextLine());
            System.out.print("Masukkan pendapatan peminjam: ");
            int income = Integer.parseInt(scan.nextLine());

            VerificatorPinjol vp = new VerificatorPinjol();
            Pinjol pinjol = new Pinjol(name, age, income);
            String info = vp.getFormInfo(pinjol);
            System.out.println(info);
        }catch(Exception e){
           System.out.println("Form tidak valid: " + e.getMessage());;
        }

        scan.close();
    }
}
