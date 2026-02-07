package google_Doc_design.scaleabledesign;

public class DocuemenetImage implements DocuemenetElement{
    private String imagepath;
    public void imageElements(String path){
        this.imagepath = path;
    }
    @Override
    public String render(){
        return "image"+this.imagepath;
    }
}
