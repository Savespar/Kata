import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task {
    public static void main(String[] args) throws IOException {
        String n1;
        String n2;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
       n1 = reader.readLine();
       int a = Integer.parseInt(n1);
       n2 = reader.readLine();
       int b = Integer.parseInt(n2);
        System.out.println(sum(a, b));
        reader.close();
    }
    static int sum (int a, int b){
        return a + b;
    }
}
