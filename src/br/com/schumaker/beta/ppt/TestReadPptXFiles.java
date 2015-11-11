package br.com.schumaker.beta.ppt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.apache.poi.POIXMLProperties.CoreProperties;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

/**
 *
 * @author hudson schumaker
 */
public class TestReadPptXFiles {

    static XMLSlideShow pptx;

    public static void main(String[] args) {
        File f = new File("/Volumes/swap/HStudio/Project S@murai/Temp/test/_ppt");
        String names[] = f.list();
        for (String n : names) {
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream("/Volumes/swap/HStudio/Project S@murai/Temp/test/_ppt/"+n);
                pptx = new XMLSlideShow(inputStream);
                readPPTX(pptx);
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

    public static void readPPTX(XMLSlideShow pptx) {
        CoreProperties props = pptx.getProperties().getCoreProperties();
        String title = props.getTitle();
        System.out.println("Title: " + title);

        for (XSLFSlide slide : pptx.getSlides()) {
            System.out.println("Starting slide...");
            List<XSLFShape> shapes = slide.getShapes();
            for (XSLFShape shape : shapes) {
                if (shape instanceof XSLFTextShape) {
                    XSLFTextShape textShape = (XSLFTextShape) shape;
                    String text = textShape.getText().trim();
                    System.out.println("Text: " + text);
                }
            }
        }
    }
}
