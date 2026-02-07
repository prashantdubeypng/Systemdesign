package google_Doc_design.scaleabledesign;

public class DocuementText implements  DocuemenetElement{
    private String text;
    public void TextElement(String text){
        this.text = text;
    }
    @Override
    public String render(){
        return this.text;
    }
}
