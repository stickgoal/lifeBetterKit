package info.maizz.lifebetterkit.util;


import junit.framework.TestCase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;

/**
 * Created by Lucas on 2017-11-13.
 */
public class QRUtilTest extends TestCase {


    public void testNewQrCode(){
       String storePath = "c:/tmp/";
       String fileName = "qr.png";

        try {
            QRUtil.newQrCodeToFile("http://www.open-open.com/lib/view/open1355055725476.html", fileName,storePath);
            assertTrue(Files.exists( Paths.get(storePath+fileName), LinkOption.NOFOLLOW_LINKS));

        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }


    }



}
