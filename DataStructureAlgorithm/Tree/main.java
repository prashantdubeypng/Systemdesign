import java.io.*;
import java.util.*;

public class main {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        int t = fs.nextInt();  
        while (t-- > 0) {
            int n = fs.nextInt();   
            String s = "";

            for (int i = 0; i < n; i++) {
                String ai = fs.next();

                String front = ai + s;
                String back  = s + ai;

                if (front.compareTo(back) < 0) {
                    s = front;
                } else {
                    s = back;
                }
            }

            out.append(s).append('\n');
        }

        System.out.print(out.toString());
    }

    
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream in) {
            br = new BufferedReader(new InputStreamReader(in));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
