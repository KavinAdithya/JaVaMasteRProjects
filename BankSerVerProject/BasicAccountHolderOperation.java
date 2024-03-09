package JaVaMasteR.BankSerVerProject;
import java.util.ArrayList;
interface BasicAccountHolderOperation{
    boolean setYourInformation();
   // boolean setYourDataOnFile();
   //ArrayList<String> addMembers;
    ArrayList<String> getYourDataFromFile(long num);
    //String getAccountHolderName(long number);
    ArrayList<String> getMembers();
    void showDetails(long n);
    boolean amountdeposit(long accountNumber);
    //void updateData(ArrayList<String dk);
    double checkBalance(long accountNumber);
    boolean withDrawalAmount(long accountNumber);
    void statement(long accountNumber);
    void statement(long accountNumber,String startDate,String endDate);
    void statement(long accountNumber,String startDate);
    boolean checkAnyTransactionAvailable(long accountNumber);
    //void writeMemberNewly(ArrayList<String> dk);
    //private void updateProfile(long accountNumber,Data k);
    public void upDate(long accountNumber);
}