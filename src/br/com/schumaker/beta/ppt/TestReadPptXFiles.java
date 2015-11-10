package br.com.schumaker.beta.ppt;

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

    static XMLSlideShow ppt;
    public static void main(String[] args) {
        String fileName = "/Volumes/swap/HStudio/Project S@murai/Temp/HStudio.pptx";
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(fileName);
             ppt = new XMLSlideShow(inputStream);
        } catch (FileNotFoundException e) {
            System.err.println(e);
            return;
        }
        catch (IOException e) {
            System.err.println(e);
            return;
        }
        readPPT(ppt);
    }

    public static void readPPT(XMLSlideShow ppt) {
        CoreProperties props = ppt.getProperties().getCoreProperties();
        String title = props.getTitle();
        System.out.println("Title: " + title);

        for (XSLFSlide slide : ppt.getSlides()) {
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
