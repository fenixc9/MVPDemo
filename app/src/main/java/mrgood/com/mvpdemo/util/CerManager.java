package mrgood.com.mvpdemo.util;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.Collection;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import mrgood.com.mvpdemo.constent.MyApp;
import okhttp3.OkHttpClient;
import okio.Buffer;

/**
 * Created by Administrator on 2017/1/26 0026.
 * 自签名证书类
 */

public class CerManager {

    /**
     * 将证书存放在asset中导入
     *
     * @param builder 所需的builder
     * @param str     证书名
     * @return
     */
    public static OkHttpClient.Builder addCerFile(OkHttpClient.Builder builder, String str) {
        final InputStream inputStream;
        try {
            inputStream = MyApp.getContext().getAssets().open(str); // 得到证书的输入流
            try {

                X509TrustManager trustManager = trustManagerForCertificates(inputStream);//以流的方式读入证书
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, new TrustManager[]{trustManager}, null);
                SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
                builder.sslSocketFactory(sslSocketFactory, trustManager);
            } catch (GeneralSecurityException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder;
    }

    /**
     * 将证书以base64导入
     *
     * @param builder 所需的builder
     * @param str     rfc导出  new Buffer().writeUtf8(key).inputStream()
     * @return
     */
    public static OkHttpClient.Builder addCerStr(OkHttpClient.Builder builder, String str) {
        final InputStream inputStream;
        inputStream = new Buffer().writeUtf8(str).inputStream(); // 得到证书的输入流
        try {

            X509TrustManager trustManager = trustManagerForCertificates(inputStream);//以流的方式读入证书
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{trustManager}, null);
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            builder.sslSocketFactory(sslSocketFactory, trustManager);
        } catch (GeneralSecurityException e) {
        }
        return builder;
    }


    /**
     * 导入证书
     *
     * @param in
     * @return
     * @throws GeneralSecurityException
     */
    private static X509TrustManager trustManagerForCertificates(InputStream in)
            throws GeneralSecurityException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        Collection<? extends Certificate> certificates = certificateFactory.generateCertificates(in);
        if (certificates.isEmpty()) {
            throw new IllegalArgumentException("expected non-empty set of trusted certificates");
        }

        // Put the certificates a key store.
        char[] password = "password".toCharArray(); // Any password will work.
        KeyStore keyStore = newEmptyKeyStore(password);
        int index = 0;
        for (java.security.cert.Certificate certificate : certificates) {
            String certificateAlias = Integer.toString(index++);
            keyStore.setCertificateEntry(certificateAlias, certificate);
        }

        // Use it to build an X509 trust manager.
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(
                KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, password);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:"
                    + Arrays.toString(trustManagers));
        }
        return (X509TrustManager) trustManagers[0];
    }

    /**
     * 生成空key
     *
     * @param password
     * @return
     * @throws GeneralSecurityException
     */
    private static KeyStore newEmptyKeyStore(char[] password) throws GeneralSecurityException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType()); // 这里添加自定义的密码，默认
            InputStream in = null; // By convention, 'null' creates an empty key store.
            keyStore.load(in, password);
            return keyStore;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
