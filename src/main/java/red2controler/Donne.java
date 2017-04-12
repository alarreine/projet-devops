package red2controler;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alarreine on 11/04/2017.
 */
public class Donne {
    byte[] bytesOfMessage = "sd".getBytes("UTF-8");
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] thedigest = md.digest(bytesOfMessage);

    Map<String,String> a = new HashMap<>();

    public Donne() throws NoSuchAlgorithmException, UnsupportedEncodingException {
    }
}
