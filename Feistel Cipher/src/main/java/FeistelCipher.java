import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
        String result = "";
        char[] InputArray = input.toCharArray();
        for (int i = 0; i < InputArray.length; i++) {
            result += Integer.toBinaryString(InputArray[i]);
        }
        return result;
    }


    public static String F_function(String input) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a 48 bit Key: ");
        String key=br.readLine();

        //Expansion

        //XOR of Key and Expanded input

        //Substitution(8 Blocks - 6 bits to 4 bits each)

        //Permutation(combining 4 bits from 8 substitution blocks to get 32 bit)

        //returning that 32 bit

        return input;
    }


    public static void Feistel(String input) throws IOException {
        System.out.println("64 Bit Block");
        System.out.println(input);
        String string1=input.substring(0, 32);
        String string2=input.substring(32, 64);
        System.out.println("L0: "+string1);
        System.out.println("R0: "+string2);
        System.out.println("Round1:");
        System.out.println("L1: "+string2);
        String string3=FeistelCipher.F_function(string2);
        System.out.println("R2: "+Do_XOR(string1,string3));
    }




    public static void main(String []args) throws IOException {
        System.out.println("This is Feistel Cipher\n");
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please Specify the input string:\n");
        String plaintext=br.readLine();
        String BinaryInput=FeistelCipher.ConvertToBinary(plaintext);
        System.out.println(BinaryInput);
        ArrayList<String>Blocks=new ArrayList<String>();
        char[] InputArray = BinaryInput.toCharArray();
        String s="";
        int count=0;
        for (int i = 0; i < InputArray.length; i++) {
            s=s+InputArray[i];
            if(i%64==0)
            {
                Blocks.add(count,s);
                s="";
                count++;
            }
            if(i==InputArray.length-1 && i%64!=0)
            {
                for(int j=s.length();j<64;j++)
                    s=s+'0';
                Blocks.add(count,s);
            }
        }

        for(int i=1;i<Blocks.size();i++)
            FeistelCipher.Feistel(Blocks.get(i).toString());

    }

}
