package my.mds2index.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.pegdown.PegDownProcessor;

public class PageGenerator {
    
    public void generateHtml(File mdFile) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(mdFile), "UTF-8"));
        String line = null;
        String mdContent = "";
        while ((line = br.readLine()) != null) {
            mdContent += line + "\r\n";
        }
        PegDownProcessor pdp = new PegDownProcessor(Integer.MAX_VALUE);
        String htmlContent = pdp.markdownToHtml(mdContent);
        System.out.println(htmlContent);
    }
    
    
    public static void main(String[] args) throws IOException {
        PageGenerator pageGenerator = new PageGenerator();
        pageGenerator.generateHtml(new File("E:\\gitregistry\\mds2index.html\\target\\classes\\markdowns\\09_pic.md"));
    }
    
}