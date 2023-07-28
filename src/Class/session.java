package Class;

//@author Muhammad Ahsan Raziq Sulthany

public class session {
    private static String idpgn;
    private static String nmpgn;
    private static String usnmpgn;
    private static String pwpgn;
    private static String lvlpgn;
    
    public static String getidpgn(){
        return idpgn;
    }
    public static void setidpgn(String idpgn){
        session.idpgn = idpgn;
    }
    public static String getnmpgn(){
        return nmpgn;
    }
    public static void setnmpgn(String nmpgn){
        session.nmpgn = nmpgn;
    }
    public static String getusnmpgn(){
        return usnmpgn;
    }
    public static void setusnmpgn(String usnmpgn){
        session.usnmpgn = usnmpgn;
    }
    public static String getpwpgn(){
        return pwpgn;
    }
    public static void setpwpgn(String pwpgn){
        session.pwpgn = pwpgn;
    }
    public static String getlvlpgn(){
        return lvlpgn;
    }
    public static void setlvlpgn(String lvlpgn){
        session.lvlpgn = lvlpgn;
    }
    public static void del(){
        idpgn="";
        nmpgn="";
        usnmpgn="";
        pwpgn="";
        lvlpgn="";
    }
}
