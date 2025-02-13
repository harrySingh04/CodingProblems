package November2024;

import java.util.HashMap;
import java.util.Map;

public class CreditCardProvider {

    Map<String, CreditCard> creditMap;
    Map<String, String> creditCardErrors;
    int limit;

    public CreditCardProvider() {
        this.creditMap = new HashMap<>();
        this.limit = 1000;
    }

    public void addCreditCard(String name, String number, int limit) {
        if (isValid(number)) {
            this.creditMap.put(name, new CreditCard(number, limit));
        } else {
            this.creditCardErrors.put(name, "Invalid credit card number , please provide the current one");
        }
    }

    public void addCharges(String name, int charges) {

        CreditCard creditCard = this.creditMap.get(name);

        if (creditCard == null)
            return;

        if (creditCard.limit <= creditCard.currentBalance + charges)
            return;

        creditCard.setCurrentBalance(creditCard.currentBalance + charges);

    }

    public void creditCard(String name, int charges) {

        CreditCard creditCard = this.creditMap.get(name);

        if (creditCard == null)
            return;

        int exitBal = creditCard.currentBalance;

        int curr = exitBal - charges;

        creditCard.setCurrentBalance(curr);

    }



    public boolean isValid(String number) {

        if (number.length() >= 12 && number.length() <= 16) {

            for (char c : number.toCharArray()) {

                if (!Character.isDigit(c))
                    return false;

            }
        } else
            return false;

        return true;
    }

    public class CreditCard {

        String number;
        int currentBalance;
        int limit;

        public CreditCard(String number, int limit) {
            this.number = number;
            this.currentBalance = 0;
            this.limit = limit;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public int getCurrentBalance() {
            return currentBalance;
        }

        public void setCurrentBalance(int currentBalance) {
            this.currentBalance = currentBalance;
        }


    }


    public static void main(String[] args) {

        String[][] operations = {
                {"Add", "Tom", "4111111111111111", "$1000"},
                {"Add", "Lisa", "5454545454545454", "$3000"},
                {"Add", "Quincy", "12345678901234", "$2000"},
                {"Charge", "Tom", "$500"},
                {"Charge", "Tom", "$800"},
                {"Charge", "Lisa", "$7"},
                {"Credit", "Lisa", "$100"},
                {"Credit", "Quincy", "$200"}};

        CreditCardProvider card = new CreditCardProvider();

        for(String[] op: operations){
            int amount  = Integer.parseInt(op[op.length - 1].substring(1));
            switch (op[0]){

                case "Add":
                    card.addCreditCard(op[1],op[2],amount);
                    break;
                case "Charge":
                    card.addCharges(op[1], amount);
                    break;
                case "Credit":
                    card.creditCard(op[2], amount);
                default:
                    System.out.println("Incorrect operation");

            }
        }




    }
}




