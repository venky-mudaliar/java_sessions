import java.math.BigDecimal;

public class Ch05_Datatypes_in_Java {
	
	public static void main(String[] args) {
		
		System.out.println("\n<==== Data Types in Java ====>");
		
		// 1. byte - Useful for saving memory in large arrays, mainly in place of integers.
		// It can hold values between -128 to 127.
		byte byteValue = 100;
		System.out.println("byte value\t: " + byteValue);

		// 2. short - Similar to 'byte', it also saves memory but with a larger range.
		// It can hold values between -32,768 to 32,767.
		short shortValue = 30000;
		System.out.println("short value\t: " + shortValue);

		// 3. int - The most commonly used data type for storing integer values.
		// It can hold values between -2^31 to 2^31-1.
		int intValue = 100000;
		System.out.println("int value\t: " + intValue);

		// 4. long - Used when a wider range than 'int' is needed.
		// It can hold very large values, from -2^63 to 2^63-1.
		long longValue = 10000000000L; // Notice the 'L' suffix to indicate long literal
		System.out.println("long value\t: " + longValue);

		// 5. float - Used to save memory in large arrays of floating-point numbers.
		// It is a single-precision 32-bit IEEE 754 floating point, less precise than double.
		float floatValue = 10.5f; // Notice the 'f' suffix to indicate float literal
		System.out.println("float value\t: " + floatValue);

		// 6. double - The default data type for decimal values.
		// It is a double-precision 64-bit IEEE 754 floating point, more precise than float.
		double doubleValue = 99.99;
		System.out.println("double value\t: " + doubleValue);

		// 7. char - Used to store a single character.
		// It holds a single 16-bit Unicode character.
		char charValue = 'A';
		System.out.println("char value\t: " + charValue);

		// 8. boolean - Used for simple flags that track true/false conditions.
		// It can hold only two values: true or false.
		boolean booleanValue = true;
		System.out.println("boolean value\t: " + booleanValue);
		
		// 9. String - Used to represent a sequence of characters.
		// Strings are immutable objects in Java, meaning their values cannot be changed after they are created.
		String stringValue = "Lazy Coffee Code Tutorials";
		System.out.println("String value\t: " + stringValue);
		
		System.out.println("\n<==== Java is Strongly Typed ====>");
		// Java is strongly typed, meaning each variable must be declared with a specific data type.
		// Once declared, a variable can only hold values of that data type.
		// Example: 'myNumber' cannot hold a decimal value if declared as an int.
		
		double myDouble = 9.78;  // Declaring a double variable
		System.out.println("myDouble value\t: " + myDouble);
		int myInt = (int) myDouble;  // Explicitly casting from double to int, truncating the decimal part
		System.out.println("myInt value\t: " + myInt);  // Casting results in the loss of precision (only 9 remains)

		System.out.println("\n<==== Best Practices and Tips while working with Datatypes in Java ====>");
		// Best practices and tips to avoid pitfalls in Java data types.

		// Using BigDecimal for precision in financial calculations.
		// BigDecimal is preferred for precise calculations involving decimal numbers, such as currency.
		BigDecimal bigDecimalAmount = new BigDecimal("100.25");
		System.out.println("bigDecimalAmount value\t: " + bigDecimalAmount);

		// Be cautious when downcasting large numbers to smaller data types.
		// Here, casting from long to int may cause data loss.
		long largeNumber = 9876543210L;  // A large long value
		System.out.println("\nlargeNumber value\t: " + largeNumber);
		int smallerNumber = (int) largeNumber;  // Potential loss of data due to downcasting (loses higher bits)
		System.out.println("smallerNumber value\t: " + smallerNumber);  // Outputs a truncated value
	}

}
