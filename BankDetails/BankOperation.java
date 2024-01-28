package proMini.BankDetails;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.LocalDate;
public class BankOperation extends SubAccountHolders{
    Scanner sk=new Scanner(System.in);
    @Override
    public boolean amountdeposit(long accountNumber){
        System.out.print("Amount : ");
        double amt=Double.parseDouble(sk.nextLine());
        ArrayList<String> dk=getYourDataFromFile(accountNumber);
        String tal=dk.get(6);
        String sub=tal.substring(18);
        double n=Double.parseDouble(sub);
        amt+=n;
        dk.set(6,"Account Balance = "+amt);
        updateData(dk);
        updateTransaction(accountNumber,amt-n,amt,"Deposit   ");
        System.out.println("Amount has been credited SuccessFully...");
        return true;
    }
    protected void updateData(ArrayList<String> dk){
        String fullName=dk.get(1);
        String name=fullName.substring(7)+".txt";
        BufferedWriter file=null;
        try{
            file=new BufferedWriter(new FileWriter(name));
            for(int k=0;k<dk.size();k++){
                file.write(dk.get(k)+"\n");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                file.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public double checkBalance(long accountNumber){
        ArrayList<String> dk=getYourDataFromFile(accountNumber);
        String tal=dk.get(6);
        String sub=tal.substring(18);
        double amt=Double.parseDouble(sub);
        return amt;
    }
    @Override
    public boolean withDrawalAmount(long accountNumber){
        System.out.print("Amount Withdrawal : ");
        double n=Double.parseDouble(sk.nextLine());
        double m=checkBalance(accountNumber);
        ArrayList<String> dk=getYourDataFromFile(accountNumber);
        String that=dk.get(6).substring(0,18);
        if(m>=n){
            m-=n;
            dk.set(6,that+m);
            updateData(dk);
            updateTransaction(accountNumber,n,m,"WithDrawal");
            System.out.println("SuccessFully Amount has be Debited...");
            return true;
        }
        else{
            System.out.println("InSufficient Balance...");
            return false;
        }
    }
    private void updateTransaction(long accountNumber,double n,double m,String trans){
        String name=getAccountHolderName(accountNumber)+".txt";
        BufferedWriter file=null;
        try{
            file=new BufferedWriter(new FileWriter(name,true));
            file.write(LocalDate.now()+"       "+LocalTime.now()+"       "+trans+"  "+n+"        "+m+"\n");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                file.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public void statement(long accountNumber){
        String name=getAccountHolderName(accountNumber)+".txt";
        BufferedReader file=null;
        try{
            file=new BufferedReader(new FileReader(name));
            String s=file.readLine();
            int i=1;
            while(s!=null){
                if(i>11){
                    System.out.println(s);
                }
                s=file.readLine();
                i++;
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                file.close();
            }
            catch(Exception e){
            }
        }
    }
    @Override
    public void statement(long accountNumber,String startDate , String endDate ){
        String name=getAccountHolderName(accountNumber)+".txt";
        BufferedReader file=null;
        try{
            file=new BufferedReader(new FileReader(name));
            String s=file.readLine();
            int i=1;
            boolean knowPrint=false;
            while(s!=null){
                String []str=s.split("       ");
                if((i>12)&&(!knowPrint)){     
                    if(str[0].compareToIgnoreCase(startDate)==0){
                        knowPrint=true;
                    }
                }
                if(knowPrint){
                    System.out.println(s);
                }
                if(str[0].compareToIgnoreCase(endDate)==0){
                    break;
                }
                s=file.readLine();
                i++;
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                file.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public void statement(long accountNumber,String startDate){
        String name=getAccountHolderName(accountNumber)+".txt";
        BufferedReader file=null;
        try{
            file=new BufferedReader(new FileReader(name));
            String s=file.readLine();
            int i=1;
            boolean knowPrint=false;
            while(s!=null){
                String []str=s.split("       ");
                if((i>=12)&&(!knowPrint)){     
                    if(str[0].compareToIgnoreCase(startDate)==0){
                        knowPrint=true;
                    }
                }
                if(knowPrint){
                    System.out.println(s);
                }
                s=file.readLine();
                i++;
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                file.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public boolean checkAnyTransactionAvailable(long accountNumber){
        String name=getAccountHolderName(accountNumber)+".txt";
        BufferedReader file=null;
        int n=0;
        try{
            file=new BufferedReader(new FileReader(name));
            String s=file.readLine();
            while(s!=null){
                n++;
                s=file.readLine();
            }
            if(n>12)
                return true;
            else
                return false;
        }
        catch(IOException e){
            e.printStackTrace();
            return false;
        }
        finally{
            try{
                file.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}