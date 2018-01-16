package mn.ulas.converter.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JProgressBar;

public class Converter {

  private static final Logger LOG = Logger.getLogger(Converter.class.getName());

  private JProgressBar progressBar;

  private ImageInputStream imageStream;
  private List<File> inputFiles;
  private String pdfOutput;

  private String extentions;
  private Rectangle pageSize;
  private Float quality;

  /**
   * Converts images to pdf.
   */
  public void convert() {
    try {
      OutputStream file = new FileOutputStream(new File(pdfOutput));
      ImageReader reader = ImageIO.getImageReadersByFormatName(extentions).next();
      Rectangle documentPageSize = pageSize;
      Document document = new Document(documentPageSize, 0, 0, 0, 0);
      PdfWriter writer = PdfWriter.getInstance(document, file);

      writer.setFullCompression();
      writer.setCompressionLevel(9);
      writer.setStrictImageSequence(true);

      document.setPageSize(documentPageSize);
      document.open();

      for (File inputFile : inputFiles) {
        imageStream = ImageIO.createImageInputStream(inputFile);
        reader.setInput(imageStream);

        int pages = reader.getNumImages(true);
        // LOG.log(Level.INFO, "Number of Images in File: {0}", pages);
        for (int i = 0; i < pages; i++) {
          BufferedImage bufferedImage = reader.read(i);
          Image image = Image.getInstance(writer, bufferedImage, quality);

          image.scaleToFit(documentPageSize.getWidth(), documentPageSize.getHeight());
          // image.setAlignment(Image.ALIGN_CENTER);
          image.setCompressionLevel(9);

          // image.setAbsolutePosition(0, 0);
          document.add(image);
          document.newPage();
        }
        // LOG.info("PDF Conversion in Java Completed");
      }

      document.close();
    } catch (DocumentException | IOException e) {
      LOG.log(Level.INFO, "Error {0}", e);
    }
  }

  public List<File> getInputFiles() {
    return inputFiles;
  }

  public void setInputFiles(List<File> inputFiles) {
    this.inputFiles = inputFiles;
  }

  public String getPdfOutput() {
    return pdfOutput;
  }

  public void setPdfOutput(String pdfOutput) {
    this.pdfOutput = pdfOutput;
  }

  public JProgressBar getProgressBar() {
    return progressBar;
  }

  public void setProgressBar(JProgressBar progressBar) {
    this.progressBar = progressBar;
  }

  public String getExtentions() {
    return extentions;
  }

  public void setExtentions(String extentions) {
    this.extentions = extentions;
  }

  public Rectangle getPageSize() {
    return pageSize;
  }

  public void setPageSize(Rectangle pageSize) {
    this.pageSize = pageSize;
  }

  public Float getQuality() {
    return quality;
  }

  public void setQuality(Float quality) {
    this.quality = quality;
  }

}
