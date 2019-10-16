
import HW4.KeyTable;
import HW4.Phrase;


public class Test
{
    public static void main(String [] args)
    {
        char c=67;
        
        System.out.println(c);
        
        String hi="playfairexample";
        
        if(c>65)
        {
            System.out.println("You got it");
        }
        
        //hi=hi.replaceAll("J", "l");
        
        KeyTable p1=KeyTable.buildFromString(hi);
        System.out.println(p1);
        
        Phrase toEncrypt=Phrase.buildPhraseFromStringforEnc("ppppppxxxxjkkkjjii");
        
        toEncrypt=toEncrypt.encrypt(p1);
        
        System.out.println(toEncrypt);
        
        char hello[][]=new char[6][6];
        
        for(int r=0;r<5;r++)
        {
            for(int col=0;col<5;col++)
            {
                System.out.println(hello[r][col]);
            }
        }
        
        
    }
}