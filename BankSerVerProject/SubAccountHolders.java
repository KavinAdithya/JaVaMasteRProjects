import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public abstract class SubAccountHolders extends AccountHolders{
    @Override
    public ArrayList<String> getYourDataFromFile(long num) {
        String name=getAccountHolderName(num);
        ArrayList<String> memberDetail=new ArrayList<>();
        if(name!=null){
            BufferedReader dkReader=null;
            try{
                String s=name+".txt";
                dkReader=new BufferedReader(new FileReader(s));
                String k=dkReader.readLine();

                while(k!=null){
                    memberDetail.add(k);
                    k=dkReader.readLine();
                }    
            }
            catch(IOException e){
                e.printStackTrace();
                return memberDetail;
            }
            finally{
                try{
                    dkReader.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        else{
            System.out.println("ReEnter the Account Number correctly...");
            return memberDetail;
        }
        return memberDetail;

    }
    protected String getAccountHolderName(long number) {
        String name[]=null;
        if(MainBank.accounts<=number){
            BufferedReader dkReader=null;
            try{
                dkReader=new BufferedReader(new FileReader("ListOfAccountHolders.txt"));
                String s=dkReader.readLine();
                s=dkReader.readLine();
                for(int k=1;k<(number%710722104);k++){
                    s=dkReader.readLine();
                }
                /*int noLen=Long.toString(number).length();
                int n=Long.toString(number%710722104).length();
                 name=s.substring(n+1,(s.length()-noLen+1));*/
                 name=s.split(" ");
                 return name[2];
            }
            catch(IOException e){
                e.printStackTrace();
                return "empty";
            }
            finally{
                try{
                    dkReader.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        else{
            System.out.println("Invalid Account Number..");
            return "Invalid Account Number";
        }
    }
    @Override
    public void showDetails(long n){
        ArrayList<String> dk=getYourDataFromFile(n) ;
        for(String s:dk){
            System.out.println(s);
        }
    }
    public enum Datas{
        NAMECHANGE,AGECHANGE,CONTACTCHANGE,ADDRESSCHANGE,PANNUMBERCHANGE,ADHAARCHANGE,FATHERNAMECHANGE,MOTHERNAMECHANGE;
    }
    private void updateProfile(long accountNumber,Datas type){
        ArrayList<String> dk=getYourDataFromFile(accountNumber);
        Scanner sk=new Scanner(System.in);
        String data=sk.nextLine();
        sk.close();
        BankOperation bank=new BankOperation();
        switch(type){
            case NAMECHANGE:{
                String name="Name = "+data;
                dk.set(1,name);
                bank.updateData(dk);
                ArrayList<String> members=bank.getMembers();
               // members.remove(0);
                for(int k=1;k<members.size();k++){
                    String s[]=members.get(k).split(" ");
                    long no=Long.parseLong(s[1]);
                    if(no==accountNumber){
                        members.set(k,(accountNumber%710722104)+" "+accountNumber+" "+data);
                    }
                }
                writeMemeberNewly(members);
                System.out.println("SuccessFully Name has been Updated...");
                break;
            }
            case AGECHANGE:{
                String ageUpdate="Age = "+data;
                dk.set(2,ageUpdate);
                bank.updateData(dk);
                System.out.println("SuccessFully Age has been Updated...");
                break;
            }
            case CONTACTCHANGE:{
                String contactUpdate="Contact Number = "+data;
                dk.set(9,contactUpdate);
                bank.updateData(dk);
                System.out.println("SuccessFully Contact Number has been Updated...");
                break;
            }
            case ADDRESSCHANGE:{
                String addressUpdate="Residential Address = "+data;
                dk.set(10,addressUpdate);
                bank.updateData(dk);
                System.out.println("SuccessFully Address has been Updated...");
                break;
            }
            case PANNUMBERCHANGE:{
                String panUpdate="PAN Card Number = "+data;
                dk.set(8,panUpdate);
                bank.updateData(dk);
                System.out.println("SuccessFully PAN Number has been Updated...");
                break;
            }
            case ADHAARCHANGE:{
                String adhaarUpdate="Aadhaar Number = "+data;
                dk.set(7,adhaarUpdate);
                bank.updateData(dk);
                System.out.println("SuccessFully Aadhaar Number has been Updated...");
                break;
            }
            case FATHERNAMECHANGE:{
                String nameChange="Father Name = "+data;
                dk.set(3,nameChange);
                bank.updateData(dk);
                System.out.println("SuccesFully Father Name Has been Updated...");
                break;
            }
            case MOTHERNAMECHANGE:{
                String nameChange="Mother Name = "+data;
                dk.set(4,nameChange);
                bank.updateData(dk);
                System.out.println("SuccessFully Mother Name has been Updated...");
                break;
            }
            default:{
                System.out.println(" Not Able upDate your Data...");
            } 
        }
    }
    protected void writeMemeberNewly(ArrayList<String> dk){
        BufferedWriter file=null;
        try{
            file=new BufferedWriter(new FileWriter("ListOfAccountHolders.txt"));
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
    public void upDate(long accountNumber){
        try (Scanner dk = new Scanner(System.in)) {
            System.out.println("Select Any Option : ");
            System.out.println("1.Updating Your Name      : 43");
            System.out.println("2.Updating Father Name    : 44");
            System.out.println("3.Updating Mother Name    : 45");
            System.out.println("4.Updating Age            : 46");
            System.out.println("5.Updating Aadhaar Number : 19");
            System.out.println("6.Updating PAN number     : 20");
            System.out.println("7.Updating Address        : 21");
            System.out.println("8.Updating Contact Number : 22");
            int n=dk.nextInt();
      // dk.close();
            Datas k=null;
            switch(n){
                case 43:
                    k=Datas.NAMECHANGE;
                    break;
                case 44:
                    k=Datas.FATHERNAMECHANGE;
                    break;
                case 45:
                    k=Datas.MOTHERNAMECHANGE;
                    break;
                case 46:
                    k=Datas.AGECHANGE;
                    break;
                case 19:
                    k=Datas.ADHAARCHANGE;
                    break;
                case 20:
                    k=Datas.PANNUMBERCHANGE;
                    break;
                case 21:
                    k=Datas.ADDRESSCHANGE;
                    break;
                case 22:
                    k=Datas.CONTACTCHANGE;
                    break;
                default:
                    System.out.println("Invalid Updation ...");
                    return;
            }
            updateProfile(accountNumber,k);
        }
    }
}