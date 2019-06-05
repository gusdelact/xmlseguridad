package aws.sat.mx;



import javax.crypto.SecretKey;

import org.apache.xml.security.encryption.XMLCipher;
import org.w3c.dom.Document;


public class App {
 public static void main(String args[]) throws Exception {
  String xmlFile = "./mensaje.xml";
  String encryptedFile = "./encrypted.xml";
  String decryptedFile = "./decrypted.xml";
  String llaveSecreta = "./llave.pem";

  SecretKey secretKey = SecretKeyUtil.getSecretKey("AES");
  SecretKeyUtil.saveSecretKey(secretKey,llaveSecreta);
  Document document = XMLUtil.getDocument(xmlFile);

  Document encryptedDoc = XMLUtil.encryptDocument(document, secretKey,
    XMLCipher.AES_256);
  XMLUtil.saveDocumentTo(encryptedDoc, encryptedFile);

  Document decryptedDoc = XMLUtil.decryptDocument(encryptedDoc,
    secretKey, XMLCipher.AES_256);
  XMLUtil.saveDocumentTo(decryptedDoc, decryptedFile);

  System.out.println("Done");
 }
}