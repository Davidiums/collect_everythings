package davidius.authentificationservice.security;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyUtils {
    public static PrivateKey loadPrivateKey() throws Exception {
        // Charge depuis le classpath
        InputStream is = KeyUtils.class.getClassLoader().getResourceAsStream("keys/private_key.pem");
        String key = new String(is.readAllBytes());
        key = key.replaceAll("-----BEGIN (.*)-----", "")
                .replaceAll("-----END (.*)----", "")
                .replaceAll("\\s", "");
        byte[] decoded = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
        return KeyFactory.getInstance("RSA").generatePrivate(keySpec);
    }

    public static PublicKey loadPublicKey() throws Exception {
        // Charge depuis le classpath
        InputStream is = KeyUtils.class.getClassLoader().getResourceAsStream("keys/public_key.pem");
        String key = new String(is.readAllBytes());
        key = key.replaceAll("-----BEGIN (.*)-----", "")
                .replaceAll("-----END (.*)----", "")
                .replaceAll("\\s", "");
        byte[] decoded = Base64.getDecoder().decode(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
        return KeyFactory.getInstance("RSA").generatePublic(keySpec);
    }
}