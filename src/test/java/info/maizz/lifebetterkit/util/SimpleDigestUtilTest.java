package info.maizz.lifebetterkit.util;

import junit.framework.TestCase;

/**
 * Created by Administrator on 2017/7/19.
 */
public class SimpleDigestUtilTest extends TestCase {

    private static final String[] testData = {"123456","abcde","123abcde","123x&#$!@456"};


    public void testEncryptSHA(){

        for(String d : testData){
            encryptAndCompareWithSHA(d);
        }

        for(String d : testData){
            encryptAndCompareWithMD5(d);
        }

    }

    private void encryptAndCompareWithSHA(String data) {
        String f = SimpleDigestUtil.encryptSHA(data);
        String s = SimpleDigestUtil.encryptSHA(data);
        System.out.println(f);
        assertEquals(f,s);
    }

    private void encryptAndCompareWithMD5(String data) {
        String f = SimpleDigestUtil.encryptMD5(data);
        String s = SimpleDigestUtil.encryptMD5(data);
        System.out.println(f);
        assertEquals(f,s);
    }

}
