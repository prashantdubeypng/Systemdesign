package SolidPrinciples;
/**
 * In that we follow the liskov substitution principle
 * It means that a class should be substitutable for its subtypes
 * Example
 *we have a class account it have the properties of account 
 *like: account id , balance and account type
 *we have the clases like saving account and current account
 we have to implemet the new class FDclass that does not allow to withdraw the amount
 voilates the liskov substitution principle 
 */
public class Liskovsubstitutionprinciple {
    
}
/**
 * follow the liskov substitution principle
 */
public interface withoutWithdraw{
    deposite();
}
public interface withWithdraw extends withoutWithdraw{
    withdraw();
}
public class savingAccount implements withWithdraw{
    /** it can do both withdraw and deposite */
}
public class fdAccount implements withoutWithdraw{
    /** it can do only deposite */
}
