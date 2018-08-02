package gradletest;

public class BinarySearch {
    
    public double interestRate(double price, double monthlyPayment, int loanTerm) {
        
        double balance;
        double ret = 50;
        double top = 100;
        double low = 0;
        
        while(true) {
            
            balance = price;
            
            for (int i = 0; i< loanTerm; i++) {
                balance *= ret/1200 + 1;
                balance -= monthlyPayment;
            }
            
            if (balance < 1e-9 && balance > -(1e-9)) {
                break;
            } else if (balance > 0) {
                top = ret;
                ret -= (ret-low)/2;
            } else {
                low = ret;
                ret += (top-ret)/2;
            }
            
        }
        
        return ret;        
    }
    
    public static void main(String[] args) {
        System.out.println(new BinarySearch().interestRate(2000, 510, 4));
    }
}
