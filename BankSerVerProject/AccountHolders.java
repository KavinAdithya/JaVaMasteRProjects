package JaVaMasteR.BankSerVerProject;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public abstract class AccountHolders implements BasicAccountHolderOperation {
    public String name;
    public byte age;
    public String address;
    public long accountNumberCurrent;
    public String fatherName;
    public String adhaarNumber;
    public String panNumber="NoPanCard";
    public String motherName;
    public double balanceEnquiry=0;
    public long phoneNumber;
    public String accountType="Zero Balance Account";
    public List<String> transactionHistory=new ArrayList<String>(); 
    @Override
    public boolean setYourInformation(){
        Scanner dkScan=null;
        try{
            dkScan=new Scanner(System.in);
            System.out.print("Name : ");
            name=dkScan.nextLine();
            System.out.print("Age : ");
            age=Byte.parseByte(dkScan.nextLine());
            System.out.print("Father Name : ");
            fatherName=dkScan.nextLine();
            System.out.print("Mother Name : ");
            motherName=dkScan.nextLine();
            System.out.print("Adhaar Number : ");
            adhaarNumber=dkScan.nextLine();
            if(adhaarNumber.length()<12)
                throw new OwnException("Invalid Aadhaar Number...");
            System.out.print("Do You have PAN card : ");
            String s=dkScan.nextLine();
            if((s.compareToIgnoreCase("yes"))==0){
                System.out.print("PAN number : ");
                panNumber=dkScan.nextLine();
                if(panNumber.length()<10)
                    throw new OwnException("Invalid PAN Number...");
            }
            System.out.print("Resditenial Address : ");
            address=dkScan.nextLine();
            System.out.print("Contact Number : ");
            phoneNumber=Long.parseLong(dkScan.nextLine());
            if(String.valueOf(phoneNumber).length()!=10){
                throw new OwnException("Invalid Phone Number...");
            }
            System.out.print("Are you depositing Amount : ");
            String k=dkScan.nextLine();
            if((k.compareToIgnoreCase("yes"))==0){
                System.out.print("Depositing Amount :");
                balanceEnquiry=Double.parseDouble(dkScan.nextLine());
                accountType="Not A Zero Balance Type Account";
            }
            setYourDataOnFile();
            return true;
        }
        catch(OwnException e){
            System.out.println(e);
            return false;
        }
        catch(Exception e){
            System.out.println("Something Unusal... Contact Bank Manager...\n");
            return false;
        }
        finally{
            dkScan.close();
        }

    }
    protected void addMembers(){
        BufferedWriter dkWriter=null;
        try{
            if(MainBank.accounts>710722105){
                dkWriter=new BufferedWriter(new FileWriter("ListOfAccountHolders.txt",true));
                dkWriter.write((MainBank.accounts%710722104)+" "+accountNumberCurrent+" "+name+"\n");
            }
            else{

                dkWriter=new BufferedWriter(new FileWriter("ListOfAccountHolders.txt"));
                dkWriter.write("No  Account  Name\n");
                dkWriter.write((MainBank.accounts%710722104)+" "+accountNumberCurrent+" "+name+"\n");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try {
                dkWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public ArrayList<String> getMembers() {
        BufferedReader dkReader=null;
        ArrayList<String> members=new ArrayList<>();
        int i=0;
        try{
            dkReader = new BufferedReader(new FileReader("ListOfAccountHolders.txt"));
            i=1;
            String d=dkReader.readLine();
            while(d!=null){
                members.add(d);
                d=dkReader.readLine();
            }
        }
        catch(IOException e){
            //return members;
        }
        catch(NullPointerException e){
            System.out.println("Something Unsual...");
        }
        finally{
            try{
                if(i==1)
                    dkReader.close();
            }
            catch(IOException e){
               // e.printStackTrace();
            }
        }
        return members;
    }
    protected boolean setYourDataOnFile(){
        String fileName=name+".txt";
        BufferedWriter dkWriter=null;
        try{
            dkWriter=new BufferedWriter(new FileWriter(fileName));
           ArrayList<String> dk=getMembers();
           MainBank.accounts=dk.size()+1+710722104;
           accountNumberCurrent=dk.size()+1+710722104;
            dkWriter.write("Account Number = "+accountNumberCurrent+"\n");
            dkWriter.write("Name = "+name+"\n");
            dkWriter.write("Age = "+age+"\n");
            dkWriter.write("Father Name = "+fatherName+"\n");
            dkWriter.write("Mother Name = "+motherName+"\n");
            dkWriter.write("AccountType = "+accountType+"\n");
            dkWriter.write("Account Balance = "+balanceEnquiry+"\n");
            dkWriter.write("Aadhaar Number = "+adhaarNumber+"\n");
            dkWriter.write("PAN Card Number = "+panNumber+"\n");
            dkWriter.write("Contact Number = "+phoneNumber+"\n");
            dkWriter.write("Residential Address = "+address+"\n");
            dkWriter.write("      Date            Time             Withdraw/Deposit           CurrentBalance");
            addMembers();
           System.out.println("Account Has been Succesfully Created..\nYour Account Number is "+accountNumberCurrent);
        }
        catch(IOException e){
            e.printStackTrace();
            return false;
        }
        finally{
            try{
                dkWriter.close();
            }
            catch(IOException e){
             //   e.printStackTrace();
            }
        }
        return true;
    }
}