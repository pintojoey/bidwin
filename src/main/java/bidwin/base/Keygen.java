package bidwin.base;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class Keygen {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(512);
        KeyPair pair = keyGen.genKeyPair();
        byte[] publicKey = pair.getPublic().getEncoded();

        StringBuffer retString = new StringBuffer();
        for (int i = 0; i < publicKey.length; ++i) {
            retString.append(Integer.toHexString(0x0100 + (publicKey[i] & 0x00FF)).substring(1));
        }
        System.out.println("public key: " +retString);

        byte[] privateKey = pair.getPrivate().getEncoded();


         retString = new StringBuffer();
        for (int i = 0; i < privateKey.length; ++i) {
            retString.append(Integer.toHexString(0x0100 + (privateKey[i] & 0x00FF)).substring(1));
        }
        System.out.println("private key: " +retString);

        Cipher cipher = Cipher.getInstance(pair.getPrivate().getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, pair.getPrivate());
        System.out.println("sig: "+cipher.doFinal("{}".getBytes(Charset.defaultCharset())).toString());
        System.out.println(Hex.encodeHexString(cipher.doFinal(("Hello world").getBytes())));
    }
}
