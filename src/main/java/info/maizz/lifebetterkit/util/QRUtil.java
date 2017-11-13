package info.maizz.lifebetterkit.util;

import com.google.common.io.Files;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by Lucas on 2017-11-13.
 */
public abstract class QRUtil {


    public static File newQrCode(String message,String fileName){
        return newQrCode(message,fileName,0xFFFF0000,0xFFFFFFAA);
    }
    public static File newQrCode(String message,String fileName,int onColor,int offColor){
        return QRCode.from(message).to(ImageType.PNG).withColor(onColor,offColor).file(fileName);
    }

    public static void newQrCodeToFile(String message,String fileName,String path) throws IOException {
        Files.copy(newQrCode(message,fileName),new File(path+fileName));
    }

}
