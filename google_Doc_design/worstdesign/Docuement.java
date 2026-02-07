// it is the basic application not scaleable it is teh worst design that i have made till now
package google_Doc_design.worstdesign;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
public class Docuement {
    List<String>elements  = new ArrayList<>();
    String renderedString;
    boolean ischanged = false;
    public  void addText(String text){
        if(text == null){
            return;
        }
        this.elements.add(text);
        ischanged = true;
    }
    public void addImage(String path){
        if(path == null){
            return;
        }
        this.elements.add(path);
        ischanged = true;
    }
    public String renderDocuements(){
        if(this.renderedString == null || ischanged){
            String result = "";
           for(String element:this.elements){
            if(element.length()>4&&(element.substring(element.length()-4)==".jpg"||element.substring(element.length()-4)==".png")){
                result += "Image"+element+"\n";
            }
            result += element +"\n";
           }
        this.renderedString = result;
        ischanged = false;
    }
    return this.renderedString;
    }
    public void savetofile(){
        if (this.renderedString == null || ischanged) {
            renderDocuements();
        }
        String fileName = "document_" + System.currentTimeMillis() + ".txt";
        Path outputPath = Paths.get(fileName);
        try {
            Files.write(outputPath, this.renderedString.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Failed to save document to file: " + outputPath, e);
        }
    }

    public static void main(String[] args) {
        Docuement doc = new Docuement();

        doc.addText("Hello, world");
        doc.addText("This is a demo document.");
        doc.addImage("photo.jpg");
        doc.addImage("diagram.png");

        String rendered = doc.renderDocuements();
        System.out.println("Rendered content:\n" + rendered);

        doc.savetofile();
        System.out.println("Saved document to file in the current working directory.");
    }
}
