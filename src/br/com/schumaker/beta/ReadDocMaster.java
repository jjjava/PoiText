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

/**
 *
 * @author hudsonschumaker
 * 
 * The good one
 */
public class ReadDocMaster {

    public static void main(String[] args) {
        try {

            File file = new File("/users/hudsonschumaker/downloads/Guisi01206us - Jira Guide for P3 PECB enhancement requests.doc");
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            HWPFDocument doc = new HWPFDocument(fis);
            WordExtractor extractor = new WordExtractor(doc);
            
            for (String rawText : extractor.getParagraphText()) {
                String text = extractor.stripFields(rawText);
                if(text.length()>10)
                System.out.println(text.trim());
            }
        } catch (Exception exep) {
        }
    }
}
