package br.com.schumaker.beta;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

/**
 *
 * @author hudsonschumaker
 */
public class HsReadWordFiles {

    private static final int READ_SIZE = 3095; // 3Mb  
   

    public static String readDocFile(String fileName) {
        File file = new File(fileName);
        StringBuilder content = new StringBuilder();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
//            int len = fis.available() < READ_SIZE ? fis.available() : READ_SIZE;
//            byte[] buffer = new byte[len];
//            fis.read(buffer, 0, len);
            HWPFDocument doc = new HWPFDocument(fis);
            WordExtractor we = new WordExtractor(doc);
          //  System.out.println(we.getText());
            String[] paragraphs = we.getParagraphText();
            System.out.println("Total no of paragraph " + paragraphs.length);
            for (String para : paragraphs) {
                content.append(para.trim());
                
            }
            String aux= content.toString();
            
            if(aux.length()>3095){
                aux = aux.substring(0, 3095);
            }
            System.out.println(aux);
        } catch (Exception ex) {
            System.err.println("readWordFile::HsDocFile: " + ex.getMessage());
            
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
                System.err.println("readWordFile::HsDocFile: " + ex.getMessage());
               
            }
        }
        return content.toString();
    }

    public static String readDocxFile(String fileName) {
        File file = new File(fileName);
        StringBuilder content = new StringBuilder();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file.getAbsolutePath());
            XWPFDocument document = new XWPFDocument(fis);
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            System.out.println("Total no of paragraph " + paragraphs.size());
            for (XWPFParagraph para : paragraphs) {
                content.append(para.toString().trim());
                System.out.println(para);
            }
        } catch (Exception ex) {
            System.err.println("readWordFile::HsDocxFile: " + ex.getMessage());
            
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
                System.err.println("readWordFile::HsDocxFile: " + ex.getMessage());
                
            }
        }
        return content.toString();
    }
}
