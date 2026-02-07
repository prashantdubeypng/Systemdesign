package SolidPrinciples;
/**
 * In that we follow the open close principle
 * It means that a class should be open for extension and closed for modification
 * Example
 * we have a class of product it have the properties of product 
 * like: product id , price and quantity
 * we have one more class that is cart that used when user add any product in that cart
 * we have properties of cart like: cart id and list of products
 * we have to calculate the total price of the cart , and have savetodb and generateinvoice 
 * Follow the single responsibility principle
 * we need more features like save to the NoSQLdb and in file also , currebglty saving the SQL db
 */
public class openclosePrinciple {
    
}
/**
 * alredy exist
 */
public class saveToDB{
    public void saveToDBSQL(cart cart){
        
    }
}
/**
 * add new features save in nosql and file also
 */
/**
 * voilates the open close principle
 * modify the existing saveToDB class
 */
public class saveToDB{
    public void saveToDBSQL(cart cart){
        
    }
    public void saveToDBNoSQL(cart cart){
        
    }
    public void saveToFile(cart cart){
        
    }
}
/**
 * follow the open close principle
 */
/**
 * we need to make the SaveToDB interface and make the new class
 *  for savingSQL , NOSQL and file that follow single
 *  responsibility principle
 */
public interface SaveToDB{
    public void saveToDB(cart cart);
}
public class saveToDBSQL implements SaveToDB{
    public void saveToDB(cart cart){
        
    }
}
public class saveToDBNoSQL implements SaveToDB{
    public void saveToDB(cart cart){
        
    }
}
public class saveToFile implements SaveToDB{
    public void saveToDB(cart cart){
        
    }
}