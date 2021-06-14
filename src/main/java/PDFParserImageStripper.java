package main.java;

import org.apache.pdfbox.contentstream.PDFStreamEngine;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PDFParserImageStripper extends PDFStreamEngine {
	public int imageNumber = 1;

	public static void parsePDFFile(File pdfFile) throws IOException {
		try (PDDocument document = PDDocument.load(new File(String.valueOf(pdfFile)))) {
			PDFParserImageStripper printer = new PDFParserImageStripper();
			int pageNum = 0;
			for (PDPage page : document.getPages()) {
				pageNum++;
				printer.processPage(page);
			}
		}
	}

	public void processOperator(Operator operator, List<COSBase> operands) throws IOException {
		String operation = operator.getName();
		if ("Do".equals(operation)) {
			COSName objectName = (COSName) operands.get(0);
			PDXObject xobject = getResources().getXObject(objectName);
			if (xobject instanceof PDImageXObject) {
				PDImageXObject image = (PDImageXObject) xobject;
				BufferedImage bImage = image.getImage();
				ImageIO.write(bImage, "PNG", new File("C:/PDFCopy/output/images/image_" + imageNumber + ".png"));
				imageNumber++;
			} else if (xobject instanceof PDFormXObject) {
				PDFormXObject form = (PDFormXObject) xobject;
				showForm(form);
			}
		} else {
			super.processOperator(operator, operands);
		}
	}
}