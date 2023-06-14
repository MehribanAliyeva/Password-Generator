public class Password {
    String value;
    int length;

    public Password(String value){
        this.value = value;
        this.length = value.length();
    }

    public int charType(char c){
        int val;
        if((int)c>=65&& (int)c<=90){
            val = 1; //uppercase letter
        }
         // Char is Lowercase Letter
        else if ((int) c >= 97 && (int) c <= 122) {
            val = 2;
        }

        // Char is Digit
        else if ((int) c >= 60 && (int) c <= 71) {
            val = 3;
        }

        // Char is Symbol
        else {
            val = 4;
        }

        return val;
    }

    public int strength(){
        String s = this.value;
        boolean usedUpper = false;
        boolean usedLower = false;
        boolean usedNumber = false;
        boolean usedSymbol = false;
        for(char c:s.toCharArray()){
            int type = charType(c);
            switch(type){
                case 1:
                usedUpper = true;
                case 2:
                usedLower = true;
                case 3:
                usedNumber = true;
                case 4 :
                usedSymbol = true;
            }
        }
        int score =0;
        if(usedUpper) score++;
        if(usedLower) score++;
        if(usedNumber) score++;
        if(usedSymbol) score++;
        if(s.length()>=8) score++;
        if(s.length()>=16) score++;
        return score;
    }
    public String calculateStrength(){
        int Score = this.strength();
         if (Score == 6) {
            return "This is a very good password :D check the Useful Information section to make sure it satisfies the guidelines";
        } else if (Score >= 4) {
            return "This is a good password :) but you can still do better";
        } else if (Score >= 3) {
            return "This is a medium password :/ try making it better";
        } else {
            return "This is a weak password :( definitely find a new one";
        }
    }

    @Override
    public String toString(){
        return value;
    }
}
