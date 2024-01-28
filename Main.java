package proMini;
import java.util.Random;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
class RangeOutOfBoundException extends Exception{
}
class Person {
    String name;
    int count=0;
    Person(String name){
        this.name=name;
    }
    List<Integer> key=new LinkedList<>();
    List<Integer> value=new LinkedList<>();
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public boolean startGame(int gen,int limit) {
        System.out.print(name+" !!! Guess The Number 0 - "+limit+"  :");
        int n=0;
        boolean a = true;
        while (a) {
            try {
                n = Integer.parseInt(in.readLine());
                if(n>limit)
                    throw new RangeOutOfBoundException();
                a=false;
            } catch (NumberFormatException e) {
                System.out.println("Cannot ConVert Character To An a Number!!!");
            }
            catch(IOException e){
                System.out.println("Getting Error from input");
            }
            catch(RangeOutOfBoundException e){
                System.out.println("Guessing Number Is Not Valid For Your Given Range");
         }
         key.add(gen);
         value.add(n);
        }
        
        return gen==n;
    }
    public void showPerformance(){
        System.out.println("Hii !!! "+name+" #@");
        System.out.println("Number Generated    Number Guessed");
        for(int a=0;a<key.size();a++){
            System.out.println("    "+key.get(a)+"           :        "+value.get(a));
        }
    }
    public static int validName(String s,Person player[]){
        for(int u=0;u<player.length;u++){
            if((s.compareToIgnoreCase(player[u].name))==0){
                return u;
            }
        }
        return -1;
    }
    public static void finalWinner(Person player[]){
        int max=player[0].count;
        int index=0;
        for(int k=1;k<player.length;k++){
            if(max<player[k].count){
                max=player[k].count;
                index=k;
            }
        }
        if(max==0)
            System.out.println("No Winners In This Game");
        else{
            System.out.println(player[index].name+" Is The Winner ... Congratulation "+player[index].name+" 😇");
            System.out.print("Are you Needed to see Any Players Performance : ");
            try{
                String check=Main.in.readLine();
                if((check.compareToIgnoreCase("yes"))==0){
                    System.out.println("Enter the player name or no");
                    String checkName=Main.in.readLine();
                    int k;
                    try{
                        k=Integer.parseInt(checkName);
                        player[k].showPerformance();
                    }
                    catch(NumberFormatException e){
                        int returnSomething=validName(checkName,player);
                        if(returnSomething == -1)
                            System.out.println("Invalid Name That Your Entered!!!");
                        else
                            player[returnSomething].showPerformance();
                    }
                }
            }
            catch(IOException e){
                System.out.println("Input Error ");
            }
        }
    }
    public static void checkTheWinners(boolean result[],Person players[]){
        String nextMove=null;
        for(int k=0;k<result.length;k++){
            if(result[k])
                System.out.println(players[k].name+" Guessed Correctly...");
            else    
                System.out.println(players[k].name+" Wrongly Guessed ...");
        }
        System.out.print("Are You Interested To Continue The Playing :");
        try{
            nextMove=Main.in.readLine();
        }
        catch(IOException e){
            System.out.println("Input Error");
        }
        if((nextMove.compareToIgnoreCase("yes"))==0)
            justBeginTheGame(players);
        else{
            if(players.length!=0)
                finalWinner(players);
            System.out.println("Thank You....☺️");
        }
    }
    static Random gen=new Random();
    public static void justBeginTheGame(Person player[])
    {
        int generate=gen.nextInt(Main.range);
        boolean result[]=new boolean[player.length];
        for(int k=0;k<player.length;k++ ){
            result[k]=player[k].startGame(generate,Main.range);
            if(result[k])
                player[k].count++;
        }
        checkTheWinners(result,player);
    }
}
public class Main {
    static BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
    public static int repeatName(String s,Person player[],int m){
        for(int k=0;k<m;k++){
            if((s.compareToIgnoreCase(player[k].name))==0)
                return -1;
        }
        return 0;
    }
    static int range=0;
    public static void startGame() {
        try{
            System.out.print("Tell Me How Many Players Gona A Challenge Your self : ");
            int n=Integer.parseInt(in.readLine());
            Person players[]=new Person[n];
            System.out.print("Enter the Range For the Game : ");
            range=Integer.parseInt(in.readLine());
            String name;
            for(int m=0;m<n;m++){
                while(true){
                    System.out.print("Enter Player "+(m+1)+" Name : ");
                    if(m==0){
                        name=in.readLine();
                        players[m]=new Person(name);
                        break;
                    }
                    else{
                        name=in.readLine();
                        if((repeatName(name,players,m))==0){
                            players[m]=new Person(name);
                            break;
                        }
                        else{
                            System.out.println("The Name used Must be Unique From Other Names..ReEnter it AGAIN..");
                        }
                    }
                }
            }
            Person.justBeginTheGame(players);
        }
        catch(NumberFormatException e){
            System.out.println("Game Has Been Terminatred Due to UnExcepted InPut");
        }
        catch(Exception r){
            System.out.println("Unexcepted Error .. Restart The Game Again !!! "+r);
        }
    }
    public static void main(String[] args) {
        System.out.println("Tech$crack √••");
        startGame();
    }
}