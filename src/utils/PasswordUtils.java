package utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class PasswordUtils {

    // Configuración de seguridad (ajusta estos valores según tus necesidades)
    private static final int ITERATIONS = 65536;  // Número de iteraciones
    private static final int KEY_LENGTH = 256;    // Longitud de clave en bits
    private static final int SALT_LENGTH = 16;    // Longitud del salt en bytes

    /**
     * Hashea una contraseña con PBKDF2 + Salt aleatorio
     * @param password Contraseña en texto plano
     * @return String con el hash (formato: salt:hash)
     * @throws Exception Si ocurre un error en el hashing
     */
    public static String hashPassword(String password) throws Exception {
        try {
            // 1. Generar salt aleatorio
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[SALT_LENGTH];
            random.nextBytes(salt);

            // 2. Configurar el algoritmo PBKDF2
            PBEKeySpec spec = new PBEKeySpec(
                    password.toCharArray(),
                    salt,
                    ITERATIONS,
                    KEY_LENGTH
            );

            // 3. Generar el hash
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();

            // 4. Combinar salt y hash para almacenamiento (formato: salt:hash)
            return Base64.getEncoder().encodeToString(salt) + ":" +
                    Base64.getEncoder().encodeToString(hash);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new Exception("Error al hashear la contraseña", e);
        }
    }

    /**
     * Verifica si una contraseña coincide con un hash almacenado
     * @param inputPassword Contraseña a verificar (texto plano)
     * @param storedHash Hash almacenado (formato salt:hash)
     * @return boolean true si coinciden
     * @throws Exception Si ocurre un error en la verificación
     */
    public static boolean verifyPassword(String inputPassword, String storedHash) throws Exception {
        try {
            // 1. Extraer salt y hash del storedHash
            String[] parts = storedHash.split(":");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Formato de hash inválido");
            }

            byte[] salt = Base64.getDecoder().decode(parts[0]);
            byte[] storedHashBytes = Base64.getDecoder().decode(parts[1]);

            // 2. Calcular hash de la contraseña ingresada con el mismo salt
            PBEKeySpec spec = new PBEKeySpec(
                    inputPassword.toCharArray(),
                    salt,
                    ITERATIONS,
                    KEY_LENGTH
            );

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] testHash = factory.generateSecret(spec).getEncoded();

            // 3. Comparar los hashes
            return slowEquals(storedHashBytes, testHash);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new Exception("Error al verificar contraseña", e);
        }
    }

    /**
     * Comparación segura contra ataques de timing
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }
}