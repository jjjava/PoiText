/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.schumaker.beta.doc;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

public class ReadFile {

    public static void main(String[] args) {
        WordExtractor extractor = null;
        try {

            File file = new File("/users/hudsonschumaker/downloads/Guisi01206us - Jira Guide for P3 PECB enhancement requests.doc");
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            HWPFDocument doc = new HWPFDocument(fis);
            String text = doc.getDocumentText();
            System.out.println(text);
        } catch (Exception exep) {
        }
    }
}
