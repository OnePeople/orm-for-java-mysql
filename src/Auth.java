
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

 
public class Auth   {
    public ORM orm;
    
    public static String md5(String st) {
    MessageDigest messageDigest = null;
    byte[] digest = new byte[0];
 
    try {
        messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        messageDigest.update(st.getBytes());
        digest = messageDigest.digest();
    } catch (NoSuchAlgorithmException e) {
        // тут можно обработать ошибку
        // возникает она если в передаваемый алгоритм в getInstance(,,,) не существует
        e.printStackTrace();
    }
 
    BigInteger bigInt = new BigInteger(1, digest);
    String md5Hex = bigInt.toString(16);
 
    while( md5Hex.length() < 32 ){
        md5Hex = "0" + md5Hex;
    }
 
    return md5Hex;
}
    
      private static Auth _instance = null;

   

    public static synchronized Auth getInstance() {
        if (_instance == null)
            _instance = new Auth();
        return _instance;
    }
    
    
    
    private Auth() {
     
      this.orm = new ORM("users");
  
    }
    public String uid="0";
    
    public Boolean Access(String tabel,String operation  ) {
        if (this.uid.equals("0") ) return false;
     // String[] Userid =orm.where("login='"+ tabel+"' &&  password='"+md5(tabel)+"'").toArray (1);
    String[] can =  this.orm.executeQuery("SELECT  "+tabel+" FROM access a\n" +
        "  LEFT JOIN users u ON u.acess = a.level\n" +
        "  WHERE a.operation='"+operation+"' && u.id="+this.uid+" LIMIT 1 ").toArray(1) ;
     if (can.length==0) return false;
     if (can[0].equals("1")) return true;
     if (can[0].equals("0")) return false;
     
        return false ;
    }  
    
    
    public Boolean Check(String login, String password ) {
     
     String[] Userid =orm.where("login='"+ login+"' &&  password='"+md5(password)+"'").toArray (1);
      System.out.println("Check: Userid.length - "+Userid.length);
    if (Userid.length!=0)
    { 
        uid=Userid[0];
        this.orm.uid=this.uid;
        return true;
    }
     else 
        return false;//;
    }  
}
