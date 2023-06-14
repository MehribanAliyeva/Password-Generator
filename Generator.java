import java.util.Scanner;

public class Generator {
    public static Scanner keyboard;
    Alphabet alphabet;
    public Generator(Scanner keyboard){
      this.keyboard = keyboard;
    }

     public Generator(boolean IncludeUpper, boolean IncludeLower, boolean IncludeNum, boolean IncludeSym) {
        alphabet = new Alphabet(IncludeUpper, IncludeLower, IncludeNum, IncludeSym);
    }

    public void mainLoop(){
        System.out.println("Welcome!");
        printMenu();

        String userOption = "-1";

        while (!userOption.equals("4")) {

            userOption = keyboard.next();

            switch (userOption) {
                case "1" -> {
                    requestPassword();
                    printMenu();
                }
                case "2" -> {
                    checkPassword();
                    printMenu();
                }
                case "3" -> {
                    printUsefulInfo();
                    printMenu();
                }
                case "4" -> printQuitMessage();
                default -> {
                    System.out.println();
                    System.out.println("Kindly select one of the available commands");
                    printMenu();
                }
            }
        }
    }
     private void checkPassword() {
        String input;

        System.out.print("\nEnter your password:");
        input = keyboard.next();

        final Password p = new Password(input);

        System.out.println(p.calculateStrength());
    }

    private void requestPassword(){
          boolean usedUpper = false;
        boolean usedLower = false;
        boolean usedNumber = false;
        boolean usedSymbol = false;
        boolean cont;
        do{
            String input;
            cont = false;
            do {
                System.out.println("Do you want Lowercase letters \"abcd...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));
            if(include(input)) usedLower = true;
            do{
                 System.out.println("Do you want Uppercase letters \"abcd...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            }while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));
            if(include(input)) usedUpper = true;
            do {
                System.out.println("Do you want Numbers \"abcd...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));
            if (include(input)) usedNumber = true;
             do {
                System.out.println("Do you want Symbols \"abcd...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));
            if (include(input)) usedSymbol = true;
            if(!usedLower && !usedNumber && !usedUpper && !usedSymbol){
                  System.out.println("You have selected no characters to generate your " +
                        "password, at least one of your answers should be Yes\n");
                        cont = true;
            }
        }while(cont);
         System.out.println("Great! Now enter the length of the password");
        int length = keyboard.nextInt();
        final Generator generator = new Generator(usedUpper, usedLower, usedNumber, usedSymbol);
        final Password password = generator.generate(length);
           System.err.println("Your generated password -> " + password);
    }
    private void PasswordRequestError(String input) {
        if(!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no")){
            System.out.println("You have entered something incorrect let's go over it again \n");
        }
    }

    private boolean include(String input){
        return input.equalsIgnoreCase("yes");
    }
   private void printMenu() {
        System.out.println();
        System.out.println("Enter 1 - Password Generator");
        System.out.println("Enter 2 - Password Strength Check");
        System.out.println("Enter 3 - Useful Information");
        System.out.println("Enter 4 - Quit");
        System.out.print("Choice:");
    }

    private void printQuitMessage() {
        System.out.println("Closing the program bye bye!");
    } 
     private void printUsefulInfo() {
        System.out.println();
        System.out.println("Use a minimum password length of 8 or more characters if permitted");
        System.out.println("Include lowercase and uppercase alphabetic characters, numbers and symbols if permitted");
        System.out.println("Generate passwords randomly where feasible");
        System.out.println("Avoid using the same password twice (e.g., across multiple user accounts and/or software systems)");
        System.out.println("Avoid character repetition, keyboard patterns, dictionary words, letter or number sequences," +
                "\nusernames, relative or pet names, romantic links (current or past) " +
                "and biographical information (e.g., ID numbers, ancestors' names or dates).");
        System.out.println("Avoid using information that the user's colleagues and/or " +
                "acquaintances might know to be associated with the user");
        System.out.println("Do not use passwords which consist wholly of any simple combination of the aforementioned weak components");
    }

    public Password generate(int generatedLen){
      StringBuilder password = new StringBuilder("");
      int len = alphabet.getAlphabet().length();
      int max = len-1;
      int min = 0;
      int range = max-min +1;
      for(int i =0 ;i<generatedLen;i++){
        int randIndex = (int)  (Math.random() * range) + min;
        password.append(alphabet.getAlphabet().charAt(randIndex));
      }
      return new Password(password.toString());
    }
}
