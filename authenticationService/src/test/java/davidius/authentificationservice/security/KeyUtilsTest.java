package davidius.shared.security;

import com.davidius.shared.security.KeyUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.interfaces.RSAPublicKey;

class KeyUtilsTest {

    @Test
    void testLoadPublicKeyFromClasspath_Valid() throws Exception {
        // Mets un vrai chemin de clé de test dans src/test/resources/keys/public_key.pem
        RSAPublicKey key = KeyUtils.loadPublicKeyFromClasspath("keys/public_key.pem");
        Assertions.assertNotNull(key);
        // Optionnel : assertions sur la taille, l'algorithme, etc.
    }

    @Test
    void testLoadPublicKeyFromClasspath_Invalid() {
        Assertions.assertThrows(Exception.class, () -> {
            KeyUtils.loadPublicKeyFromClasspath("keys/fake_key.pem");
        });
    }
}
