public class OwnException extends Exception{
    String s;

    public OwnException(String s){
        this.s=s;
    }

    @Override
    public String toString(){
        return s;
    }
}

