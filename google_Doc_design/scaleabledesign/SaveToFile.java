package google_Doc_design.scaleabledesign;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveToFile implements Savingfile {
    @Override
    public void save(String data){
       String fileName = "document_" + System.currentTimeMillis() + ".txt";
        Path outputPath = Paths.get(fileName);
        try {
            Files.write(outputPath, data.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Failed to save document to file: " + outputPath, e);
        }finally{
            System.out.println("the file saving function is runned");
        }
    }
}
