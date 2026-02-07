package google_Doc_design.scaleabledesign;

import java.util.*;
public class Docuementmain {
    List<DocuemenetElement> element = new ArrayList<>();
    public void addElements(DocuemenetElement data){
        element.add(data);
    }
    // here i am try to render the data based on the type of it is if the data is the image then render the function of teh DocuemnetImage and if text then we are going to use the DocuemnetText
    public String render(){
        String result = "";
        for(DocuemenetElement ele :element){
            result += ele.render();
        }
        return result;
    }
    public void addImage(String path){
        
    }
    public static void main(String[] args) {
        Docuementmain doc = new Docuementmain();
        DocuementText doctext = new DocuementText();
        DocuemenetImage docimage = new DocuemenetImage();
        SaveToFile save = new SaveToFile();
        doctext.TextElement("text i am entrying");
        docimage.imageElements("/c/hp/users/prashant/image.jpg");
        doc.addElements(doctext);
        doc.addElements(docimage);
        String ans = doc.render();
        System.out.println(ans);
        save.save(ans);
    }
}
