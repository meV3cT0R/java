import java.io.IOException;

public class StringTest {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String a = reader.readLine();
        String b = reader.readLine();
        
        System.out.println(a.length()+b.length());
        for(int i=0;i<a.length();i++) {
          if(a.charAt(i) >b.charAt(i)) {
            System.out.println("Yes");
            break;
          }else if(a.charAt(i) < b.charAt(i)) {
            System.out.println("No");
            break;
          }else {
            continue;
          }
        }
        
        String c = a.substring(0,1).toUpperCase();
        a = c+a.substring(1);
        c = b.substring(0,1).toUpperCase();
        b = c + b.substring(1);
        System.out.printf("%s %s",a,b);
        reader.close();
    }
}
