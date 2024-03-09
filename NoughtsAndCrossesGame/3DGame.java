import java.util.Random;
import java.util.Scanner;
class NotRangeValue extends Throwable{
    String contentToBeDisplay;
    NotRangeValue(String contentToBeDisplay){
  this.contentToBeDisplay=contentToBeDisplay;
 }
    @Override
    public String toString(){
        return contentToBeDisplay;
    }
}
class StartUp{
    char array[][]=new char[3][3];
    Scanner scan=new Scanner(System.in);
    char winner=' ';
    Random generateNumber=new Random();
    char computer='X';
    char user='0';
    int counter=0;
    String userName;
    //Initializing The 3D arrat as empty
    public void emptyArray(){
        for(int r=0;r<array.length;r++){
            for(int c=0;c<array[r].length;c++){
                array[r][c]=' ';
            }
        }
    }
    //Getting New Position From User
    public void userSelection(){
        int row=0,column=0;
        do{
            try{
                System.out.print("Enter The Row : ");
                int r=Integer.parseInt(scan.nextLine());
                System.out.print("Enter the column : ");
                int c=Integer.parseInt(scan.nextLine());
                r--;
                c--;
                if(r>2||c>2)
                    throw new NotRangeValue("Be In Range!");
                if(r==-1)
                    r++;
                if(c==-1)
                    c++;
                row=r;
                column=c;
                if(isEmptyPlace(row,column))
                    System.out.println("Good Choice! "+userName);
                else
                    System.out.println("Space Is Not Empty! "+userName);
                if(array[row][column]==' ')
                    break;
            }
            catch(NotRangeValue e){
                System.out.println(e);
            }
            catch(Exception e){
                System.out.println("Invalid Type Of Input!");
            }
        }while(true);
        array[row][column]=user;
        counter++;
    }
    //Checking The Position Is Empty;
    public boolean isEmptyPlace(int row,int column){
        return array[row][column]==' ';
    }
    //Generating The New Position for Computer
    public void computerSelection(){
        int row=0,column=0;
        do{
        row=generateNumber.nextInt(3);
        column=generateNumber.nextInt(3);
        }while(array[row][column]!=' ');
        array[row][column]=computer;
        counter++;
    }
    //Display The Board 
    public void printArray(){
        System.out.println("--------------");
        for(int r=0;r<3;r++){
            System.out.println("| "+array[r][0]+" | "+array[r][1]+" | "+array[r][2]+" |");
            System.out.println("--------------");
        }
    }
    //Checking The Winner Is Present
    public void isWinnerIsPresent(){
        if(array[0][0]==array[0][1]&&array[0][1]==array[0][2]&&winner==' ')
            winner=array[0][1];
        if(array[0][0]==array[1][1]&&array[1][1]==array[2][2]&&winner==' ')
            winner=array[0][0];
        if(array[0][2]==array[1][1]&&array[2][0]==array[0][2]&&winner==' ')
            winner=array[0][2];
        if(array[0][0]==array[1][0]&&array[1][0]==array[2][0]&&winner==' ')
            winner=array[0][0];
        if(array[0][1]==array[1][1]&&array[2][1]==array[0][1]&&winner==' ')
            winner=array[0][1];
        if(array[0][2]==array[1][2]&&array[2][2]==array[1][2]&&winner==' ')
            winner=array[0][2];
        if(array[1][0]==array[1][1]&&array[1][1]==array[1][2]&&winner==' ')
            winner=array[1][0];
        if(array[2][0]==array[2][1]&&array[2][1]==array[2][2]&&winner==' ')
            winner=array[2][0];
    }
    //Driver Mode For Game
    public void startGame(){
        int totalWinned=0;
        int mySelfWinned=0;
        String mySelf="KaVin";
        String desicion="No";
        showDev();
        System.out.println("My Name Is KaVin!");
        System.out.print("May I Know Your Name - ");
        userName=scan.nextLine();
        System.out.println("Let we Begin The Game!!!");
        do{
            winner=' ';
            emptyArray();
            printArray();
            do{
                userSelection();
                printArray();
                if(counter>=9){
                    winner='T';
                    break;
                }
                isWinnerIsPresent();
                System.out.println(mySelf+" Selected!");
                computerSelection();
                printArray();
                isWinnerIsPresent();
            }while(winner==' ');
            if(winner=='X'){
                System.out.println("You Lost The Game!");
                mySelfWinned++;
            }
            else if(winner=='T'){
                System.out.println("Game Tie!");
            }
            else{
                System.out.println("You Won The Game!");
                totalWinned++;
            }
            System.out.print("Are YOU Want To Continue The Game (Yes/No) - ");
            desicion=scan.nextLine();
            counter=0;
        }while((desicion.compareToIgnoreCase("Yes"))==0);
        if(totalWinned > mySelfWinned){
            System.out.println(userName+" You Are All Winmer!\nCongratulation "+userName+" !!!");
        }
        else if(totalWinned<mySelfWinned){
            System.out.println("I am The Overall Winnerr !!!! \nBetter Luck ! "+userName+" NectTime");
        }
        else{
            System.out.println("The Game Has Been Tie "+userName+"\nWe Both Are Winners!!");
        }
    }
 //Developer Detail
    public void showDev(){
        System.out.println("TechCrack UniVerSe!");
        System.out.println("^----___-----___-----__^");
    }
}
class Game{
     //Driver Mode Which was invoked by JVM
    public static void main(String[] args) {
        StartUp game=new StartUp();
        game.startGame();
    }
}
