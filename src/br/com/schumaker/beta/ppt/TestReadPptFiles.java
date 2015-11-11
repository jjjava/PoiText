package br.com.schumaker.beta.ppt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.extractor.PowerPointExtractor;

/**
 *
 * @author hudson schumaker
 */
public class TestReadPptFiles {

    static HSLFSlideShow ppt;

    public TestReadPptFiles() {

    }

    public static void main(String args[]) {
        File f = new File("/Volumes/swap/HStudio/Project S@murai/Temp/test/_ppt");
        String names[] = f.list();
        for (String n : names) {
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream("/Volumes/swap/HStudio/Project S@murai/Temp/test/_ppt/" + n);
             
                readPPT(inputStream);
            } catch (FileNotFoundException ex) {
                System.err.println(ex);
            } catch (IOException ex) {
                System.err.println(ex);
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }
    }

    public static void readPPT(FileInputStream inputStream) {
        try {
            PowerPointExtractor ex = new PowerPointExtractor(inputStream);
            System.out.println(ex.getText());
        } catch (IOException ex) {
           System.err.println(ex);
        }
    }
}
