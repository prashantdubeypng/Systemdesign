/**
 * In that we follow the single responsibility principle
 * It means that a class should have exact single responsibility
 * That make the code more maintainable and testable
 * And if we do changes in the class it will not hammper the other responsibilty
 */
/**
 * Example
 * we have a class of product it have the properties of product 
 * like: product id , price and quantity
 * we have one more class that is cart that used when user add any product in that cart
 * we have properties of cart like: cart id and list of products
 * we have to calculate the total price of the cart
 * we need more features like invoice of the cart , save to the db
 */
import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * voilate the single responsibility principle
 * 
 */
class product{
    private int id;
    private BigDecimal price;
    private int quantity;
}
class cart{
    private int id;
    private List<product> products;
    public BigDecimal calculateTotalPrice(){
        return products.stream().map(product -> product.getPrice().multiply(new BigDecimal(product.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    private void saveToDB(){
        
    }
    private void generateInvoice(){
        
    }
}
/**
 * follow the single responsibility principle
 */
class cart{
    private int id;
    private List<product> products;
    public BigDecimal calculateTotalPrice(){
        return products.stream().map(product -> product.getPrice().multiply(new BigDecimal(product.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
class craeteinvoice{
    public void createInvoice(cart cart){
        
    }
}
class saveToDB{
    public void saveToDB(cart cart){
        
    }
}
