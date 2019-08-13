import java.util.*;
import java.math.BigInteger;
public class rsa{

	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		Random rand = new Random();

		long p = (long)(rand.nextDouble() * (127-2) + 2);
		while(isPrime(p)==false){
			p = (long)(rand.nextDouble() * (127-2) + 2);
		}
		long q = (long)(rand.nextDouble() * (127-2) + 2);
		while(isPrime(q)==false || ((isPrime(q)==true) && (q==p))){
			q = (long)(rand.nextDouble() * (127-2) + 2);
		}

		long n = p * q;
		// long phi = (p-1) * (q-1);
		long phi = lcm((p-1), (q-1));

		long e = (long)(rand.nextDouble()* phi) + 1;
		while(gcd(e, phi) != 1){
			e = (long)(rand.nextDouble()* phi) + 1;
		}
		System.out.println(e);
		while(prime(e) == false){
			e = (long)(rand.nextDouble()* phi) + 1;
		}
		long k = 1;
		while(((k*phi)+1)%e != 0 && (k * phi) < Integer.MAX_VALUE){
			k++;
		}
		long d = ((k*phi)+1)/e;
		System.out.println("Your public key is - n:  " + pk + " e: " + e);
		System.out.println("Your private key is - d: " + d + " n: " + pk);
		System.out.println("Enter a number to encrypt: ");
		long encrypt = input.nextLong();
		BigInteger a = BigInteger.valueOf(encrypt);
		BigInteger b = BigInteger.valueOf(e);
		BigInteger c = BigInteger.valueOf(pk);
		BigInteger encrypted = a.modPow(b, c);
		//long c = (long)(Math.pow(encrypt, e)) % pk;
		System.out.println("Here is your encrypted number: " + encrypted);
		System.out.print("Enter a number to decrypt: ");
		long decrypt = input.nextLong();
		BigInteger x = BigInteger.valueOf(decrypt);
		BigInteger y = BigInteger.valueOf(d);
		BigInteger z = BigInteger.valueOf(pk);
		BigInteger decrypted = x.modPow(y, z);
		//long original_num = (long)(Math.pow(encrypted, d) % pk);
		System.out.println("Here is your decrypted number: " + decrypted);
		String public_key = Integer.toString(pk);
		String private_key = Integer.toString(d);
	}
	
	public static boolean isPrime(long n){
		if(n==1 || n==0){
			return false;
		}
		for(int i=2;i <= Math.sqrt(n);i++){
			if(n%i==0){
				return false;
			}
		}
		return true;
	}

	public static long gcd(long a, long b){
		if(a == 0){
			return b;
		}
		return gcd(b % a, a);
	}
	
	public static long lcm(long a, long b){
		return (a*b)/gcd(a,b);
	}
	
}