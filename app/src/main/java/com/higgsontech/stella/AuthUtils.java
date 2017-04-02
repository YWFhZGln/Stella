package com.higgsontech.stella;

import android.content.Context;
import android.util.Log;

import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.engines.AESEngine;
import org.spongycastle.crypto.paddings.PKCS7Padding;
import org.spongycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.encoders.Base64;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by aman on 28/3/17.
 */

public class AuthUtils{





    private static byte[] sessionkey;//=generateSessionKey();

    private static byte[] pidbytes;//=makePid().getBytes();

    private static final String JCE_PROVIDER = "SC";

    private static final String ASYMMETRIC_ALGO = "RSA/ECB/PKCS1Padding";
    private static final int SYMMETRIC_KEY_SIZE = 256;

    private static final String CERTIFICATE_TYPE = "X.509";

    private static PublicKey publicKey;
    private static Date certExpiryDate;

    static Context context;



    AuthUtils(Context c){
        this.context=c;
        getDataFromCer();
        try {
            sessionkey=generateSessionKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        try {
            pidbytes=makePid().getBytes();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }



    public static String createXmlInput(String uid)  {

        String xml="<Auth xmlns=\"http://www.uidai.gov.in/authentication/uid-auth-request/1.0\"  uid=\""+uid+"\" tid=\"public\" ac=\"public\" sa=\"public\" ver=\"1.6\" txn=\""+createTxn()+"\" lk=\"MBFWjkJHNF-fLidl8oOHtUwgL5p1ZjDbWrqsMEVEJLVEDpnlNj_CZTg\">\n" +
                "<Uses pi=\"y\" pa=\"n\" pfa=\"n\" bio=\"n\" bt=\"n\" pin=\"n\" otp=\"n\"/>\n" +
                "<Meta udc=\"1111122222\" />\n" +
                "<Skey ci=\"20200916\">"+createSkey(sessionkey)+"</Skey>\n" +
                "<Hmac>"+createHmac(sessionkey,pidbytes)+"</Hmac>\n" +
                "<Data type=\"X\">"+createData(sessionkey,pidbytes)+"</Data>\n" +"<signature></signature>"+
                "</Auth>";

        Log.e("authtils",xml);

        return xml;

    }

    private static String createData(byte[] sessionkey, byte[] pidbytes) {

        byte[] encXMLPIDData = new byte[0];
        try {
            encXMLPIDData = encryptUsingSessionKey(sessionkey, pidbytes);
        } catch (InvalidCipherTextException e) {
            e.printStackTrace();
        }
        return Base64.toBase64String(encXMLPIDData);


    }

    private static String createHmac(byte[] sessionkey, byte[] pidbytes) {
        byte[] hmac = generateSha256Hash(pidbytes);
        byte[] encryptedHmacBytes = new byte[0];
        try {
            encryptedHmacBytes = encryptUsingSessionKey(sessionkey, hmac);
        } catch (InvalidCipherTextException e) {
            e.printStackTrace();
        }
        return Base64.toBase64String(encryptedHmacBytes);



    }

    private static String createSkey(byte[] sessionkey) {

        byte[] encryptedSessionKey = new byte[0];
        try {
            encryptedSessionKey = encryptUsingPublicKey(sessionkey);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return Base64.toBase64String(encryptedSessionKey);


    }

    //Creates a AES key that can be used as session key (skey)
    public static byte[] generateSessionKey() throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyGenerator kgen = KeyGenerator.getInstance("AES", JCE_PROVIDER);
        kgen.init(SYMMETRIC_KEY_SIZE);
        SecretKey key = kgen.generateKey();
        byte[] symmKey = key.getEncoded();
        return symmKey;
    }

    /**
     * Encrypts given data using UIDAI public key
     * @param data Data to encrypt
     * @return Encrypted data
     */
    public static byte[] encryptUsingPublicKey(byte[] data) throws IOException, GeneralSecurityException {
        // encrypt the session key with the public key
        Cipher pkCipher = Cipher.getInstance(ASYMMETRIC_ALGO, JCE_PROVIDER);
        pkCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encSessionKey = pkCipher.doFinal(data);
        return encSessionKey;
    }

    /**
     * Encrypts given data using session key
     * @param skey Session key
     * @param data Data to encrypt
     * @return Encrypted data
     * @throws InvalidCipherTextException
     */
    public static byte[] encryptUsingSessionKey(byte[] skey, byte[] data) throws InvalidCipherTextException {
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new AESEngine(), new PKCS7Padding());

        cipher.init(true, new KeyParameter(skey));

        int outputSize = cipher.getOutputSize(data.length);

        byte[] tempOP = new byte[outputSize];
        int processLen = cipher.processBytes(data, 0, data.length, tempOP, 0);
        int outputLen = cipher.doFinal(tempOP, processLen);

        byte[] result = new byte[processLen + outputLen];
        System.arraycopy(tempOP, 0, result, 0, result.length);
        return result;

    }
    /**
     * Returns UIDAI certificate's expiry date in YYYYMMDD format using GMT time zone.
     * It can be used as certificate identifier
     * @return Certificate identifier for UIDAI public certificate
     */
    public static String getCertificateIdentifier() {
        SimpleDateFormat ciDateFormat = new SimpleDateFormat("yyyyMMdd");
        ciDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String certificateIdentifier = ciDateFormat.format(certExpiryDate);
        return certificateIdentifier;
    }


    private static String getTimeStampn()
    {
        SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return date.format(new Date());
    }
    private static String makePid() throws UnsupportedEncodingException {
        String pid="<Pid ts=\""+getTimeStampn()+"\" ver=\"1.0\">\n" +
                "<Demo lang=\"\">\n" +
                "<Pi ms=\"E\" mv=\"\" name=\"Kumar Agarwal\" lname=\"\" lmv=\"\" gender=\"M\" dob=\"1978-05-04\"\n" +
                "dobt=\"A\" age=\"\" phone=\"2314475929\" email=\"kma@mailserver.com\"/>\n" +
                "</Demo>\n" +
                "</Pid>";
        return pid;
    }

    public static byte[] generateSha256Hash(byte[] message) {
        String algorithm = "SHA-256";
        String SECURITY_PROVIDER = "SC";

        byte[] hash = null;

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(algorithm, SECURITY_PROVIDER);
            digest.reset();
            hash = digest.digest(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hash;
    }

    private static String createTxn() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        String txn = "AuthDemoClient" + ": public :" + dateFormat.format(new Date());
        return txn;
    }

    private static void getDataFromCer(){
        CertificateFactory certFactory = null;
        try {
            certFactory = CertificateFactory.getInstance(CERTIFICATE_TYPE, JCE_PROVIDER);
            //   AssetManager assetManager=c.getResources().getAssets();
            //InputStream inputStream=assetManager.open("uidai_auth_stage.cer");

            //   Resources resources=c.getResources();
            //InputStream inputStream=resources.openRawResource(R.raw.uidai_auth_stage);
            InputStream inputStream=context.getAssets().open("uidai_auth_stage.cer");
            X509Certificate cert = (X509Certificate) certFactory.generateCertificate(inputStream);
            publicKey = cert.getPublicKey();
            certExpiryDate = cert.getNotAfter();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getSignedXml(String uid){
        String xml=createXmlInput(uid);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);

        try {
            Document document=dbf.newDocumentBuilder().parse(new InputSource(new StringReader(xml)));


        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return xml;

    }



}
