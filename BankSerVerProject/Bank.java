package JaVaMasteR.BankSerVerProject;

import java.util.Scanner;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.BufferedReader;
import java.io.IOException;
public class Bank {
    private static Scanner dk=new Scanner(System.in);
    protected static String bankname="TechCrack World bank";
    protected static String Address="Nehru nagar,Coimbatore 635205";
    private   BankOperation bank=new BankOperation();
    private  long accountNumber=0;
    public  void start(){
        System.out.println("                          TechCrack UniVerse");
        System.out.println("                         "+bankname);  
        System.out.println("                            Coimbatore");
        System.out.print("Are you a existing a Customer(yes/no) ? - ");
        String s=dk.nextLine();
        if(s.compareToIgnoreCase("no")==0){
            justBeginIt();
        }
        else if(s.compareToIgnoreCase("yes")==0){
            System.out.print("Enter Your Account Number - ");
            try{
                accountNumber=dk.nextLong();
                if(presentAccount()){
                     justNeededMadeAnyUpdate();
                }
                else
                    System.out.println("Incorrect Account Number...");
            }
            catch(InputMismatchException e){
                System.out.println("Invalid Format Of Account Number...");
            }
        }
        else{
            System.out.println("Its Wrong Choice say(Yes/No)...");
        }
    }
    public  boolean presentAccount() {
        ArrayList<String> dk=bank.getMembers();
        dk.remove(0);
        if(dk.isEmpty()){
            return false;
        }
        for(int k=0;k<dk.size();k++){
            String s[]=dk.get(k).split(" ");
            if(Long.parseLong(s[1])==accountNumber)
                return true;
        }
        return false;
    }
    public  void justNeededMadeAnyUpdate(){
            System.out.println("Select Your Choice -  ");
            System.out.println("Credit/Debit - 19");
            System.out.println("Statement - 43");
            System.out.println("Updation - 1943");
            int m=dk.nextInt();
            switch(m){
                case 19:{
                    System.out.println("You are trying to make update on your transaction..\nSelect your Type of transcation...");
                    System.out.print("Depositing Amount - 1\nWithdrawn Amount - 9");
                    int n=dk.nextInt();
                    if(n==1)
                        bank.amountdeposit(accountNumber);
                    else if(n==9)
                        bank.withDrawalAmount(accountNumber);
                    else
                        System.out.println("Invalid Opertion...\nRestart it Again...");
                    break;
                }
                case 43:{
                    System.out.println("You Selected Your Statement...");
                    System.out.println("Full Statement -3\nFrom to Particular End Statement - 4\nFrom to Last Transaction Statement - 4343");
                    int n=dk.nextInt();
                    if(n==3){
                        bank.statement(accountNumber);
                    }
                    else if(n==4){
                        System.out.print("Enter Your Start Date (yyyy-mm-dd) - ");
                        try(BufferedReader sk=new BufferedReader(new InputStreamReader(System.in))){
                            String sd=sk.readLine();
                            System.out.print("Enter Your End Date (yyyy-mm-dd) - ");
                            String ed=sk.readLine();
                            bank.statement(accountNumber,sd,ed);
                        }
                        catch(IOException e){
                            e.printStackTrace();
                        }
                        break;
                    }
                    else if(n==4343){
                        System.out.print("Enter Your Start Date (yyyy-mm-dd) - ");
                        try(BufferedReader sk=new BufferedReader(new InputStreamReader(System.in))){
                        String jd=sk.readLine();
                        bank.statement(accountNumber,jd);
                        }
                        catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                    else{
                        System.out.println("Invalid Operation...\nRestart it Again...");
                    }
                    break;
                }
                case 1943:
                    bank.upDate(accountNumber);
                    break;
                default :
                    System.out.println("Invalid Operation...\nRestart it Again...");
            }
           
    }
    public void justBeginIt(){
        bank.setYourInformation();
    }
}
