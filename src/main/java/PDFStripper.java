package main.java;

import java.io.File;
import java.io.IOException;

public class PDFStripper {
	public static void main(String[] args) throws IOException {
		File rippingImages = new File("path to file");
		PDFParserImageStripper.parsePDFFile(rippingImages);
		File rippingText = new File("path to file");
		PDFParserTextStripper.getText(rippingText);
	}
}
