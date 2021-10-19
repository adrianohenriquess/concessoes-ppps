package br.gov.sp.tce.utils;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Classe com metodos para criptografia e decriptografia utilizando AES<br/><br/>
 * 
 * O encoding padrao dos textos de entrada e saida so o UTF-8.<br/>
 * 
 * O formato do conteudo criptogrado:
 * <ul>
 * 	<li>Vetor de inicializacao (128 bits)</li>
 * 	<li>Mensagem</li>
 * </ul>
 * 
 * Apos a criptografia, o conteudo e codificado em Base64.<br/>
 * 
 * A chave de criptografia  um {@link UUID}, cuja representacao interna possui 128 bits,
 * atendendo a especificacao do algoritmo AES.
 * 
 * 
 * @author dvivencio
 * @author p-mvicente
 *
 */
public class AESUtil {
	private static final int TAMANHO_IV = 16;
	
	private static final int TAMANHO_CHAVE = 16;
	
	private static final String AES = "AES";
	private static final String CIPHER = "AES/CBC/PKCS5PADDING";
	
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");	

	/**
	 * Metodo estetico que encripta o conteudo de uma string com a chave fornecida
	 * 
	 * @param chave
	 * @param conteudo
	 * @return
	 */
    public static String encripta(final UUID chave, final String conteudo) {
        try {
            final byte[] iv = geraIV();
			final IvParameterSpec initVector = new IvParameterSpec(iv);
            final SecretKeySpec skeySpec = new SecretKeySpec(uuidEmBytes(chave), AES);
            final Cipher cipher = Cipher.getInstance(CIPHER);
            
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, initVector);
            
            final byte[] encrypted = cipher.doFinal(conteudo.getBytes(DEFAULT_CHARSET));
            
            final ByteBuffer bb = ByteBuffer.allocate(iv.length + encrypted.length);            
            bb.put(iv);
            bb.put(encrypted);
            
            return new String(Base64.getEncoder().encode(bb.array()), DEFAULT_CHARSET);
        } catch (Exception ex) {
        	throw new IllegalStateException("Erro no processo de encriptacao AES", ex);
        }
    }

    /**
     * Metodo estatico que decripta o conteudo de uma string com a chave fornecida
     * 
     * @param chave
     * @param conteudo
     * @return
     */
    public static String decripta(final UUID chave, final String conteudo) {
        try {
        	final byte[] decoded = Base64.getDecoder().decode(conteudo.getBytes(DEFAULT_CHARSET));
        	final ByteBuffer bb = ByteBuffer.wrap(decoded);
        	
        	final byte[] iv = new byte[TAMANHO_IV];
        	bb.get(iv);
        	
        	final byte[] encrypted = new byte[bb.remaining()];
        	bb.get(encrypted);
        	
        	final IvParameterSpec initVector = new IvParameterSpec(iv);
            final SecretKeySpec skeySpec = new SecretKeySpec(uuidEmBytes(chave), AES);
            final Cipher cipher = Cipher.getInstance(CIPHER);
            
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, initVector);
            
			final byte[] original = cipher.doFinal(encrypted);
            return new String(original, DEFAULT_CHARSET);
        } catch (Exception ex) {
            throw new IllegalStateException("Erro no processo de decriptacao AES", ex);
        }
    }
    
    /**
     * Gera um vetor de inicializacao de {@value #TAMANHO_IV} bytes para utilizacao na criptografia AES
     * 
     *  Ref.: http://www.cryptofails.com/post/70059609995/crypto-noobs-1-initialization-vectors
     *   
     * @return o vetor de incializacao, com 16 bytes
     */
    private static byte[] geraIV(){
    	final Random random = new SecureRandom();
    	final byte[] iv = new byte[TAMANHO_IV];
    	random.nextBytes(iv);
		return iv;
    }

    /**
     * Converte um UUID para um vetor de bytes para uso como chave para criptografia
     * 
     * A conversao utiliza um a representacao textual (hexadecimal) que eh consistente entre as plataformas
     * Cada par de caracteres torna-se um byte. Ex: 78ca -> {0x78,0xca} -> {120,-54}
     * 
     * @param uuid
     * @return sua representacao em um vetor de bytes
     */
    private static byte[] uuidEmBytes(final UUID uuid){
    	final ByteBuffer bb = ByteBuffer.wrap(new byte[TAMANHO_CHAVE]);
    	List<String> pares = Arrays.asList(uuid.toString().replaceAll("-", "").split("(?<=\\G.{2})"));

    	for (String hex : pares) {
    		bb.put((byte) Integer.parseInt(hex, 16));
		}

    	return bb.array();
    }
    
}
