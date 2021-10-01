/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 *          Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie {
    /**
     * Get a default greeting
     *
     * @return a greeting
     */
    public String getGreeting() {
        return "Hello, let's talk.";
    }

    /**
     * Gives a response to a user statement
     *
     * @param statement the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement) {
        String response = "";
        if (statement.indexOf("no") >= 0) {
            response = "Why so negative?";
        } else if (findWord(statement, "mother") >= 0
                || statement.indexOf("father") >= 0
                || statement.indexOf("sister") >= 0
                || statement.indexOf("brother") >= 0) {
            response = "Tell me more about your family.";
        } else if (statement.indexOf("cat") >= 0
                || statement.indexOf("dog") >= 0) {
            response = "Tell me more about your pets";
        } else if (statement.indexOf("Nathan") >= 0) {
            response = "He sounds like a good teacher";
        } else if (statement.trim().length() == 0) {
            response = "Say something else please";
        } else if (statement.indexOf("sausage") >= 0) {
            response = "I love sausage with my eggs";
        } else if (statement.indexOf("phone") >= 0) {
            response = "technology is evil and will destroy mankind";
        } else if (statement.indexOf("parker") >= 0) {
            response = "that kid is so weird";
        } else if (statement.indexOf("I want") >= 0) {
            response = transformIWantStatement(statement);
        } else if (findWord(statement, "I") < findWord(statement, "you")) {
            response = transformIYouStatement(statement);
        }
        else if(statement.indexOf("I want to") >= 0) {
            response = transformIWantToStatement(statement);
        }
        else if(findWord(statement, "you") < findWord(statement, "me")) {
            response = transformYouMeStatement(statement);
        }
        else {
            response = getRandomResponse();
        }
        return response;
    }

    /**
     * Pick a default response to use if nothing else fits.
     *
     * @return a non-committal string
     */
    public String getRandomResponse() {
        final int NUMBER_OF_RESPONSES = 6;
        double r = Math.random();
        int whichResponse = (int) (r * NUMBER_OF_RESPONSES);
        String response = "";

        if (whichResponse == 0) {
            response = "Interesting, tell me more.";
        } else if (whichResponse == 1) {
            response = "Hmmm.";
        } else if (whichResponse == 2) {
            response = "Do you really think so?";
        } else if (whichResponse == 3) {
            response = "You don't say.";
        } else if (whichResponse == 4) {
            response = "Did I ask?";
        } else if (whichResponse == 5) {
            response = "Do you know Candace?";
        }

        return response;
    }

    // Checks to see if the String word appears as a whole word
    // in the String str (in this case, a "whole word" means that 
    // word is not just a substring of some larger word in str)

    // This method should work regardless of the capitalization 
    // of str or word

    // The method returns the index of the first character in word
    // if it is found, and returns -1 otherwise. 
    public int findWord(String str, String word) {
        str = str.toLowerCase();
        word = word.toLowerCase();
        int index = str.indexOf(word);
        if ( str.indexOf(word + " ") == 0 || str.indexOf(word) + word.length() == str.length() && str.indexOf(" " + word) >= 0 || str.indexOf(" " + word + " ") >= 0) {
            return index;
        } else {
            return -1;
        }
    }


    // We will work on the following methods later!

    /**
     * Take a statement with "I want <something>." and transform it into 
     * "Would you really be happy if you had <something>?"
     * @param statement the user statement, assumed to contain "I want"
     * @return the transformed statement
     */
    public String transformIWantStatement(String statement)
    {
        String want = statement.substring(6, statement.length());
        String ans = "Would you really be happy if you had" + want + "?";
        return ans;
    }

    /**
     * Take a statement with "I <something> you" and transform it into 
     * "Why do you <something> me?"
     * @param statement the user statement, assumed to contain "I" followed by "you"
     * @return the transformed statement
     */
    public String transformIYouStatement(String statement)
    {
        String want = statement.substring(2, findWord(statement, "you"));
        String ans = "Why do you " + want + "me?";
        return ans;
    }

    /**
     * Take a statement with "I want to <something>." and transform it into 
     * "What would it mean to <something>?"
     * @param statement the user statement, assumed to contain "I want to"
     * @return the transformed statement
     */
    public String transformIWantToStatement(String statement)
    {
        String word = statement.substring(9, statement.length());
        String ans = "What would it mean to" + word + "?";
        return ans;
    }




    /**
     * Take a statement with "you <something> me" and transform it into 
     * "What makes you think that I <something> you?"
     * @param statement the user statement, assumed to contain "you" followed by "me"
     * @return the transformed statement
     */
    public String transformYouMeStatement(String statement)
    {
        String word = statement.substring(7, findWord(statement, "me"));
        String ans = "What makes you think that I " + word + "you?";
        return ans;
    }
}
