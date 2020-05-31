package com.uni.redes;

public class PalindromeBreaker {

    String brake(String string) {
        String result = "";
        for (int i = 0; i<string.length(); i++) {
            if(string.charAt(i) != 'a') {
                String check = result + "a" + string.substring(i+1);
                if(!isPalindrome(check)) return check;
            }
            result.concat(String.valueOf(string.charAt(i)));
        }
        return "IMPOSSIBLE";
    }

    boolean isPalindrome(String string) {
        String firstHalf = string.substring(0,string.length()/2);
        String secondHalf = string.substring(string.length()/2, string.length());
        for(int i = 0; i<firstHalf.length(); i++){
            char normalChar = firstHalf.charAt(i);
            char reverseChar = secondHalf.charAt(secondHalf.length() - (i + 1));
            if(normalChar != reverseChar) return false;
        }
        return true;
    }
}
