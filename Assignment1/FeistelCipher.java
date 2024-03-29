import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by KunalSaini on 05-Sep-16.
 */
public class FeistelCipher {


    private static boolean bitOf(char in) {
        return (in == '1');
    }

    private static char charOf(boolean in) {
        return (in) ? '1' : '0';
    }

    public static String Do_XOR(String s1,String s2)
    {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s1.length(); i++) {
            sb.append(charOf(bitOf(s1.charAt(i)) ^ bitOf(s2.charAt(i))));
        }

        String result = sb.toString();
        return(result);
    }


    public static String ConvertToBinary(String input)
    {
        String result = new BigInteger(input.getBytes()).toString(2);
        //System.out.println("Binary String: "+'0'+result);
        return ('0'+result);
    }

    public static String ConvertToAscii(String s)
    {
        BigInteger bin=new BigInteger(s,2);
        return(new String(bin.toByteArray()));
    }


    public static String F_function(String input){

        String key="110000101011001011100010101100111001010100110010";
        System.out.println("48 bit Key: "+key);
        String out="";
        //Expansion
            for(int i=4;i<input.length()+4;i=i+4)
            {
                out=out+input.substring(i-4,i)+input.charAt(i-4)+input.charAt(i-3);
            }
        //System.out.println("Expanded : ");
        //System.out.println(out);
        //XOR of Key and Expanded input
        String xor=Do_XOR(out,key);
        //System.out.println("XOR key and input : "+xor);
        //Substitution(8 Blocks - 6 bits to 4 bits each)
        String s1=xor.substring(0,6).substring(1)+xor.substring(0,6).substring(0,1);
        String s2=xor.substring(6,12).substring(1)+xor.substring(6,12).substring(0,1);
        String s3=xor.substring(12,18).substring(1)+xor.substring(12,18).substring(0,1);
        String s4=xor.substring(18,24).substring(1)+xor.substring(18,24).substring(0,1);
        String s5=xor.substring(24,30).substring(1)+xor.substring(24,30).substring(0,1);
        String s6=xor.substring(30,36).substring(1)+xor.substring(30,36).substring(0,1);
        String s7=xor.substring(36,42).substring(1)+xor.substring(36,42).substring(0,1);
        String s8=xor.substring(42,48).substring(1)+xor.substring(42,48).substring(0,1);
        //Permutation(combining 4 bits from 8 substitution blocks to get 32 bit)
        String p1=""+s1.charAt(0)+s1.charAt(1)+s1.charAt(2)+s1.charAt(3);
        String p2=""+s1.charAt(5)+s1.charAt(4)+s1.charAt(3)+s1.charAt(2);;
        String p3=""+s1.charAt(1)+s1.charAt(2)+s1.charAt(3)+s1.charAt(4);;
        String p4=""+s1.charAt(1)+s1.charAt(3)+s1.charAt(4)+s1.charAt(5);;
        String p5=""+s1.charAt(0)+s1.charAt(2)+s1.charAt(4)+s1.charAt(5);;
        String p6=""+s1.charAt(2)+s1.charAt(3)+s1.charAt(4)+s1.charAt(5);;
        String p7=""+s1.charAt(1)+s1.charAt(3)+s1.charAt(5)+s1.charAt(0);;
        String p8=""+s1.charAt(0)+s1.charAt(2)+s1.charAt(4)+s1.charAt(1);;
        //returning that 32 bit
        String p=p1+p2+p3+p4+p5+p6+p7+p8;
        //System.out.println("Permuted 32 bit: "+p);
        return p;
    }


    public static String Feistel(String input) throws IOException {
        System.out.println("64 Bit Block");
        System.out.println(input);
        String string1=input.substring(0, 32);
        String string2=input.substring(32, 64);
        //System.out.println("L0: "+string1);
        //System.out.println("R0: "+string2);
        //System.out.println("Round1:");
        String string3=FeistelCipher.F_function(string2);
        //System.out.println("L1: "+string2);
        //System.out.println("R1: "+Do_XOR(string1,string3));
        //System.out.println("Cypher Output: "+string2+Do_XOR(string1,string3));
        System.out.println("Cypher Output in Ascii:"+FeistelCipher.ConvertToAscii(string2+Do_XOR(string1,string3)));
        return (string2+Do_XOR(string1,string3));
    }




    public static void main(String []args) throws IOException {
        System.out.println("This is Feistel Cipher\n");
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please Specify the input string:\n");
        String plaintext=br.readLine();
        String BinaryInput=FeistelCipher.ConvertToBinary(plaintext);
        String s;
        int l=64-BinaryInput.length()%64;
        String padding="";
        for(int i=0;i<l;i++)
            padding=padding+"0";
        BinaryInput=BinaryInput+padding;
        System.out.println(BinaryInput);
        for (int i = 64; i < BinaryInput.length()+64; i=i+64) {

          s="";
            s = BinaryInput.substring(i-64, i);
            System.out.println("Cyper:");
            String cyper=FeistelCipher.Feistel(s);
            System.out.println("Decypher:");
            FeistelCipher.Decypher(cyper);

        }




    }


    private static void Decypher(String cyper) throws IOException {
        //System.out.println("First Round: ");
        String L1=cyper.substring(0,32);
        String R1=cyper.substring(32,64);
        //System.out.println("L1: "+L1);
        //System.out.println("R1: "+R1);
        String L0=Do_XOR(R1,FeistelCipher.F_function(L1));
        String R0=L1;
        //System.out.println("L0: "+L0);
        //System.out.println("R0: "+R0);
        //System.out.println("Decypher OutPut:"+L0+R0);
        System.out.println("Decypher Output in Ascii:"+FeistelCipher.ConvertToAscii(L0+R0));
    }


}
