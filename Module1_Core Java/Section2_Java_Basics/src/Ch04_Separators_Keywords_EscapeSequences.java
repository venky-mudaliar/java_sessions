
public class Ch04_Separators_Keywords_EscapeSequences {

    // Method demonstrating parentheses (), semicolon ;, comma , and period .
    public static void main(String[] args) {
    	
    	// Comma , separates multiple variables in a declaration and multiple arguments in a method call
        // Here, a, b, and c are declared and initialized in a single statement using commas.
        int a = 5, b = 10, c = 15;
        
        // Parentheses () are used to group expressions and control precedence in arithmetic operations.
        // The + operator concatenates the strings and the sum of a, b, and c.
        System.out.println("\n<==== Parentheses () ====>");
        System.out.println("Sum of a, b, and c: " + (a + b + c));
        
        // Demonstrating the usage of parentheses () in control statements (if-else).
        // If 'a' is greater than 'b', the first block is executed, otherwise the second block.
        System.out.println("\n<==== Parentheses () ====>");
        if(a > b) {
        	// Period . is used to call the 'println' method of System.out
        	System.out.println("a > b");
        } else {
        	System.out.println("a <= b");
        }
        
        // Demonstrating Escape Sequences
        // \n: Inserts a newline character, moving the cursor to the next line.
        System.out.println("\n<==== Escape Sequences ====>");
        System.out.println("New Line: First Line\nSecond Line");
        
        // \t: Inserts a tab character, adding horizontal space in the output.
        System.out.println("Tab text: Tabbed\tText");
        
        // \\: Prints a single backslash in the output, as \ is an escape character in Java.
        System.out.println("Backslash: This is a backslash: \\");
        
        // \": Prints double quotes in the output, useful when quotes are needed inside a string literal.
        System.out.println("Quotes: \"Hello World\"");
        
        // \\uXXXX: Inserts a Unicode character using its hexadecimal code.
        // \u00A9 is the Unicode for the copyright symbol ©.
        System.out.println("Unicode Copyright character: \u00A9");
        
        // \u03A3 is the Unicode for the Greek letter Sigma (Σ).
        System.out.println("Unicode Sigma character: \u03A3");
        
        
    }
}