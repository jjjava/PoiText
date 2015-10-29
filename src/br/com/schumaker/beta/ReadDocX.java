/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.schumaker.beta;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 *
 * @author hudsonschumaker
 */
public class ReadDocX {

    public static void main(String[] args) {
        try {

            File file = new File("/users/hudsonschumaker/downloads/PROCESSO - BPCS - Dar baixa ao CDR.docx");
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            XWPFDocument doc = new XWPFDocument(fis);
            XWPFWordExtractor ex = new XWPFWordExtractor(doc);
           
            
            for (String rawText : ex.) {
                String text = extractor.stripFields(rawText);
                if(text.length()>10)
                System.out.println(text.trim());
            }

//                if(text.length()>10)
            System.out.println(text.trim());

        } catch (Exception exep) {
        }
    }
}
