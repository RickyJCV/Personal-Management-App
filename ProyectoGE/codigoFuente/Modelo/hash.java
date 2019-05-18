package Modelo;

/**
 * Esta clase cifra las claves
 * 
 * @author Ricardo Jesús Cabrera Valero
 *
 */

public class hash {
	/**
	 * Codifica cualquier texto (Se usa para cifrar claves)
	 * 
	 * @param txt
	 * @param hashType
	 * @return null
	 */
	public static String getHash(String txt, String hashType) {
		/**
		 * Función que codifica el password convirtiendolo en un Hash para que aparezca
		 * cifrada en la BBDD y nadie pueda verla
		 */
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
			byte[] array = md.digest(txt.getBytes());
			StringBuffer sb = new StringBuffer();

			for (int i = 0; i < array.length; i++) {
				sb.append(Integer.toHexString((array[i] & 0xff) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * Convierte el texto en un hash cifrado
	 * 
	 * @param txt
	 * @return
	 */
	public static String md5(String txt) {
		return hash.getHash(txt, "MD5");
	}

	/**
	 * Convierte el texto en un hash cifrado
	 * 
	 * @param txt
	 * @return
	 */
	public static String sha1(String txt) {
		return hash.getHash(txt, "SHA1");
	}
}
