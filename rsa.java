import java.util.*;
import java.math.BigInteger;
public class rsa{

	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		Random rand = new Random();

		long p = primeGen();
		while(isPrime(p)==false)
			p = primeGen();
		long q = primeGen();
		while(isPrime(q)==false || ((isPrime(q)==true) && (q==p)))
			q = primeGen();

		long n = p * q;
		// long phi = (p-1) * (q-1);
		long phi = lcm((p-1), (q-1));

		long e = (long)(rand.nextDouble()* phi) + 1;
		while(gcd(e, phi) != 1)
			e = (long)(rand.nextDouble()* phi) + 1;

		System.out.println("Public key: (n, e) =  (" + n + ", " + e + ")");

		long k = 1;
		while(((k*phi)+1)%e != 0 && (k * phi) < Integer.MAX_VALUE)
			k++;

		long d = ((k*phi)+1)/e;
		
		System.out.println("Private key generated.");

		System.out.println("Enter a number to encrypt: ");
		long toEncrypt = input.nextLong();
		BigInteger a = BigInteger.valueOf(toEncrypt);
		BigInteger b = BigInteger.valueOf(e);
		BigInteger c = BigInteger.valueOf(n);
		BigInteger encrypted = a.modPow(b, c);
		System.out.println("Here is your encrypted number: " + encrypted);

		System.out.print("Enter a number to decrypt: ");
		long toDecrypt = input.nextLong();
		BigInteger x = BigInteger.valueOf(toDecrypt);
		BigInteger y = BigInteger.valueOf(d);
		BigInteger z = BigInteger.valueOf(n);
		BigInteger decrypted = x.modPow(y, z);
		System.out.println("Here is your decrypted number: " + decrypted);
	}

	public static long primeGen(){
		Random rand = new Random();
		return (long)(rand.nextDouble() * (127-2) + 2);
	}
	
	public static boolean isPrime(long n){
		if(n==1 || n==0)
			return false;
		for(int i=2;i <= Math.sqrt(n);i++){
			if(n%i==0)
				return false;
		}
		return true;
	}

	public static long gcd(long a, long b){
		if(a == 0)
			return b;
		return gcd(b % a, a);
	}
	
	public static long lcm(long a, long b){
		return (a*b)/gcd(a,b);
	}
	
}