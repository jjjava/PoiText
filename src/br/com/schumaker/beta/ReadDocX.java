package br.com.schumaker.beta;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

/**
 *
 * @author hudsonschumaker
 */
public class ReadDocX {

    public static void main(String[] args) {

            File file = new File("/users/hudsonschumaker/downloads/test/PROCESSO - BPCS - Dar baixa ao CDR.docx");
            StringBuilder content = new StringBuilder();
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file.getAbsolutePath());
                XWPFDocument document = new XWPFDocument(fis);
                List<XWPFParagraph> paragraphs = document.getParagraphs();
                System.out.println("Total no of paragraph " + paragraphs.size());
                for (XWPFParagraph para : paragraphs) {
                  //  content.append(para.toString().trim());
                    System.out.println(para.getText().trim());
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
          //  return content.toString();
        }
    }
