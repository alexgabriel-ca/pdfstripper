package main.java;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PDFParserTextStripper {
	public static void getText(File pdfFile) throws IOException {
		PDDocument doc = PDDocument.load(pdfFile);
		String text = new PDFTextStripper().getText(doc);
		FileWriter myFileWriter = new FileWriter("C:/PDFCopy/output/text/pdftext.txt", true);
		myFileWriter.write(text);
		myFileWriter.close();
	}
}