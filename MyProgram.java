import java.util.*;
import java.lang.*;
import java.math.*;
/**
 WELCOME TO THE RSA ENCRYPTION AND DECRYPTION PLAYGROUND!

 DEFINITION:
 Cryptography is the practice of encoding information so only authorized people can read it.
 Cryptography scrambles information into an unreadable form, so that only authorized people with a password or key can access it.

 Have you ever wondered how encryption and decryption works? Don't hackers look so cool in those movies,
 cracking into high profile government database systems and stealing confidential information?
 Well, in the scope of this 'Fundamentals of Cybersecurity' class, we won't be able to look into
 exactly how these hackers infiltrate into governments. What we can take a look at, however, are some basic
 encryption and decryption methods throughout history and examine how they work. We will also explore some
 of the historical context between some of these encryption algorithms.

 The point of this project is to demonstrate the ingenious methods that people have come up with in order to try to send their messages to others without it being intercepted.

 The encryption algorithm that will be covered in this playground is "RSA" (Rivest–Shamir–Adleman) Crypto-system
 **/

public class MyProgram {

    // ** RSA Supporting Methods **

    // this program will return a random prime from within the bounds [lower_bound, upper_bound]
    public static List<Integer> randomPrimeGeneration(int lower_bound, int upper_bound) {
        // creating an arraylist (this is important, as an array has a predefined size, but we don't know how many primes we will end up having)
        List<Integer> ret = new ArrayList<Integer>();

        // looping through each integer falling in the range [lower_bound, upper_bound], adding to the arraylist
        for (int i = lower_bound; i <= upper_bound; i++) {
            if (isPrime(i)) { // because we only want prime numbers, we are only adding to the arraylist if i is prime.
                ret.add(i);
            }
        }
        // we return the arraylist
        return ret;
    }

    // This function will help pick two completely randomly chosen primes from the arraylist of primes that we have generated
    public static Pair<Integer, Integer> pickTwoPrimes(ArrayList<Integer> primes) {
        Collections.shuffle(primes); // shuffling the order of the primes (randomness created)
        int prime1 = primes.get(0); // taking prime 1
        int prime2 = primes.get(1); // taking prime 2
        return new Pair<>(prime1, prime2); // returns the pair of the numbers we want
    }

    // this method will check if a number "num" is prime or not.
    public static boolean isPrime(int num) {
        // by definition, 1 is not prime, and no numbers smaller than 1 can be prime either.
        if(num <= 1) {
            return false;
        }
        /* Starting from the next largest integer (2, which happens to be a prime),
        we will check all integers 2... up to the square root of num. There is no point in
        checking numbers larger than the square root of num, since we already cover all pairs
        of factors a * b = N. This helps us reduce the redundancy in our code and makes it faster */
        for(int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    // This method switches a capital letter (A-Z) into its corresponding number (1-26)
    public static int convertToNumber(char letter) {
        // Throwing an exception so that I don't get an error
        int number;
        switch (letter) {
            case 'A':
                number = 1;
                break;
            case 'B':
                number = 2;
                break;
            case 'C':
                number = 3;
                break;
            case 'D':
                number = 4;
                break;
            case 'E':
                number = 5;
                break;
            case 'F':
                number = 6;
                break;
            case 'G':
                number = 7;
                break;
            case 'H':
                number = 8;
                break;
            case 'I':
                number = 9;
                break;
            case 'J':
                number = 10;
                break;
            case 'K':
                number = 11;
                break;
            case 'L':
                number = 12;
                break;
            case 'M':
                number = 13;
                break;
            case 'N':
                number = 14;
                break;
            case 'O':
                number = 15;
                break;
            case 'P':
                number = 16;
                break;
            case 'Q':
                number = 17;
                break;
            case 'R':
                number = 18;
                break;
            case 'S':
                number = 19;
                break;
            case 'T':
                number = 20;
                break;
            case 'U':
                number = 21;
                break;
            case 'V':
                number = 22;
                break;
            case 'W':
                number = 23;
                break;
            case 'X':
                number = 24;
                break;
            case 'Y':
                number = 25;
                break;
            case 'Z':
                number = 26;
                break;
            default:
                throw new IllegalArgumentException("Input is not an uppercase letter A-Z.");
        }
        return number;
    }

    // This method switches a number (1-26) into its corresponding capital letter (A-Z)
    public static char convertToLetter(int num) {
        if (num < 1 || num > 26) {
            throw new IllegalArgumentException("Input is not a number between 1 and 26 inclusive: " + num);
        }
        return (char) ('A' + num - 1);
    }

    // this method returns the greatest common divisor of any two integers a, b.
    public static int gcd(int a, int b) {
        int t;
        while(true) {
            t = a % b;
            // crucial: we are using a temporary variable t in order to do the following simplification:
            // gcd(a, b) = gcd(a, a mod b)
            if(t == 0) {
                return b;
            }
            // doing a switch
            a = b;
            b = t;
        }
    }

    // main function: here is where most of the functioning code related to the playground is operating at
    public static void main(String[] args) {

        // In order to begin taking in input from the user, we must first create a Scanner
        // System.in basically just means that we will be taking in user input from the output window

        Scanner sc = new Scanner(System.in);

        // friendly beginner message
        System.out.println("Welcome to the Encryption and Decryption Playground! Please begin by entering in your input that will be used for RSA: ");
        // Input your initials (A-Z, A-Z)
        System.out.println("Please type your initials (A-Z)");

        // taking an input message from the user (plaintext)
        String input = sc.nextLine();

        /*
        1. RSA (Rivest-Shamir-Adleman) Crypto-system

        History: RSA was a widely used asymmetric encryption algorithm with the intent of sending
        secure messages to others over a potentially vulnerable network or connection.

        Quick overview of Asymmetric Encryption:
        First, a client, such as a browser, forwards its public key to the server while requesting specific data.
        Then, the server encrypts the requested data using the client's provided public key and sends back the encrypted data.
        Thirdly, the client receives the encrypted data and proceeds to decrypt it.
          [NOTE: Here, client can also mean a different person. Asymmetric encryption does not have to be done online; It can also be done between two people
          or from one messenger to multiple intended receivers]

        Well, how does it work?

        The overall idea is that a large integer is extremely difficult to factorize.
        The RSA Public key consists of two numbers, where one number is the product of two other prime numbers.

        The private key also relies on these two prime numbers.

        The weakness of the RSA encryption algorithm is if a person figures out what the two prime numbers are.
        In other words, if someone is able to factorize this large number, the encryption method would be compromised.

        The strength of this encryption therefore depends entirely on the key size. With a large enough product of two prime numbers, RSA can be virtually impossible to crack.

        ***PUBLIC KEY***: (n, e)

        The public key is sent from the recipient to everyone that would like to send a message.
        This public key is in the form of two numbers (n, e)

        n = P * Q, which is the first number we need for the public key.

        Then, we need another integer e, which will be the exponent.

        ***PRIVATE KEY***: (d)
        THe private key d is kept secret. Only the recipient knows this.

        --How SRA Works:--

        Key generation: First, the sender and receiver agree on three large prime numbers, p, q, and n = pq. The sender also selects another prime number, e, and calculates its modular multiplicative inverse with respect to n, denoted as d.

        Key distribution: The sender shares its public key (n, e) with the receiver. The receiver keeps the private key (n, d) secret.

        Encryption: To encrypt a plaintext message m, the sender calculates c = m^e mod n. This ciphertext is then sent to the receiver.

        Decryption: To decrypt the ciphertext c, the receiver calculates m = c^d mod n. This decrypted message is the original plaintext.
        */

        // Here is the string form of the two letters stored
        // Note: We want to represent the first letter in the alphabet A as 1, and Z as 26.

        String RSA_plaintext = input; // redundant variable, but this just helps me see it better

        // Here, we are splitting up the 2 character string that we got into two separate characters
        // NOTE: Strings are similar to arrays in Java
        // The first character index is 0, just like how the first element index of an array is also 0
        char char1 = RSA_plaintext.charAt(0); // takes character at position 0
        char char2 = RSA_plaintext.charAt(1); // takes character at position 1

        // We are converting the two characters into numbers. this will help us do our RSA computations.
        int num1 = convertToNumber(char1);
        int num2 = convertToNumber(char2);

        //debugging
        System.out.println("Number plaintext for character 1: " + num1);
        System.out.println("Number plaintext for character 2: " + num2);

        // First step:
        // Now we need to prompt the user to give us an upper and lower bound for the generation of primes
        System.out.println("Great, now that you have entered in your plaintext message, enter in two numbers [a,b] as the lower and upper bounds of the prime generation range, inclusive.");
        // Setting a limit on the size of these numbers to reduce the likelihood of an integer overflow occurring
        // NOTE: Changed code at the bottom to use BigInteger which will no longer have an overflow issue
        System.out.println("Please make sure that the two numbers {a,b} you picked satisfy the condition 1 <= a < b <= 10^7");
        System.out.println("Enter the first number a: ");
        int lower = sc.nextInt();
        System.out.println("Enter the second number b: ");
        int upper = sc.nextInt();

        // casting the list type into an arraylist
        ArrayList<Integer> primes = (ArrayList<Integer>) randomPrimeGeneration(lower, upper);

        // picking two primes from the prime list randomly, namely (p,q)
        Pair<Integer, Integer> p_and_q = pickTwoPrimes(primes);

        // Here are the values for the p and q as needed in RSA
        int p = p_and_q.getFirst();
        int q = p_and_q.getSecond();

        // n is calculated as p * q in RSA as it is done here too
        int n = p * q;

        // ɸ(n) is the Euler's Totient Function, which gives the number of integers
        // smaller than n that are relatively prime to n.
        int phi_n = (p-1) * (q-1); // ɸ(n) not to get confused

        // Now, we need to determine our exponent e, which has to obey the following two properties
        // It follows that in RSA, e must be within the bounds 1 < e < ɸ(n)
        // e must be coprime with ɸ(n)
        int e = 2; // e starts at 2
        while(e < phi_n) {
            if(gcd(e, phi_n) == 1) // we have found the desired e!
                break;
            else
                e += 1; // keep going up
        }

        // finally, the private key d has to satisfy the following condition in RSA
        // d * e (mod ɸ(n)) = 1

        // finding the modular multiplicative inverse from solving this equation is quite hard in Java
        // I will rewrite this equation into something more manageable.

        // Here, let's define some constant k.
        // look below for the getD() function, line 292
        int d = getD(phi_n, e);

        // debugging
        System.out.println("p: " + p);
        System.out.println("q: " + q);
        System.out.println("n: " + n);
        System.out.println("Before encryption - num1: " + num1);
        System.out.println("Before encryption - e: " + e);
        System.out.println("Before encryption - n: " + n);

        // <--- CHARACTER 1 --->
        System.out.println("Number plaintext for character 1: " + num1);

        // The encryption: c = m^e mod n
        BigInteger c1 = BigInteger.valueOf(num1).modPow(BigInteger.valueOf(e), BigInteger.valueOf(n));
        System.out.println("Number ciphertext for character 1: " + c1);

        // The decryption: m = c^d mod n
        BigInteger m1 = c1.modPow(BigInteger.valueOf(d), BigInteger.valueOf(n));
        System.out.println("Number decrypted for character 1: " + m1);

        // <--- CHARACTER 2 --->
        System.out.println("Number plaintext for character 2: " + num2);

        // The encryption: c = m^e mod n
        BigInteger c2 = BigInteger.valueOf(num2).modPow(BigInteger.valueOf(e), BigInteger.valueOf(n));
        System.out.println("Number ciphertext for character 2: " + c2);

        // The decryption: m = c^d mod n
        BigInteger m2 = c2.modPow(BigInteger.valueOf(d), BigInteger.valueOf(n));
        System.out.println("Number decrypted for character 2: " + m2);
        // dchar1 and dchar2 are the results we expect after decrypting the ciphertext.
        // These two characters concatenated should result in our original two character String
        char dchar1 = convertToLetter(m1.intValue()); // casting to make the type fit
        char dchar2 = convertToLetter(m2.intValue()); // casting to make the type fit

        //debugging
        System.out.println("Decrypted character 1: " + dchar1);
        System.out.println("Decrypted character 2: " + dchar2);


        String res = String.valueOf(dchar1) + dchar2; // concatenation
        System.out.println("Hehe, let me take a guess... your original plaintext of two characters is...: " + res);

        System.out.println("Thanks for playing! :) Now you see how cool RSA can be!");
        System.out.println("Made by: Johnny Zheng");

    }

    // This method is used to calculate the d (private key) for RSA, given ɸ(n) and e
    //d know has to satisfy the equation d = (1 + (k * ɸ(n))) / e
    // essentially, all we have done here is just using the Euclidean algorithm,
    // iteratively simplifying the values
    private static int getD(int phi_n, int e) {
        int k = 3; // You can choose any non-negative integer here

        // Calculate the extended gcd (Greatest Common Divisor) of e and ɸ(n)
        int[] extendedGCDResult = extendedGCD(e, phi_n);

        int d = extendedGCDResult[1]; // This is the modular multiplicative inverse

        // Ensure d is positive
        while (d < 0) {
            d += phi_n;
        }

        return d;
    }

    // Extended Euclidean Algorithm to find coefficients x, y such that ax + by = gcd(a, b)
    // Source: https://en.wikipedia.org/wiki/Extended_Euclidean_algorithm
    private static int[] extendedGCD(int a, int b) {
        // Here, we are tackling the base case of the extendedGCD
        // if b is 0, return a and coefficients x=1, y=0

        if(b == 0) {
            return new int[]{a, 1, 0};
        }

        // this is known as recursion. we are calling the same function within itself
        // we keep doing this until we find valid coefficients a, b, and gcd
        int[] euclidExtendResult = extendedGCD(b, a % b);

        int gcd = euclidExtendResult[0]; // GCD of a and b
        int x = euclidExtendResult[2]; // Coefficient for a
        int y = euclidExtendResult[1] - (a / b) * euclidExtendResult[2]; // coefficient for b
        // these formulas came from wikipedia and other online sources

        return new int[]{gcd, x, y};
    }

}

// Creation of a Pair class for some methods, ease of use as a return type
// Alternate method would be returning an array, which is more tedious
class Pair<First, Second> {
    // Here, we constructed the two instance variables we need for the class Pair, which are first and second respectively
    private final First first;
    private final Second second;

    // this is known as a constructor. Whenever we call this method, we are creating a Pair object with
    // a initial First parameter "first" and an initial Second parameter "second"
    public Pair(First first, Second second) {
        this.first = first;
        this.second = second;
    }

    // this is a get method. we simply get the value "first" when we call it
    public First getFirst() {
        return first;
    }

    // this is a get method. we simply get the value "second" when we call it
    public Second getSecond() {
        return second;
    }
}