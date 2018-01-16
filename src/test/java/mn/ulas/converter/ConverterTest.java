package mn.ulas.converter;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import mn.ulas.converter.util.Converter;

public class ConverterTest {

  // @Test
  /**
   * test conversation.
   */
  public void testSuccessfulConversion() throws IOException {
    List<File> files = new ArrayList<>();
    for (int i = 1; i <= 2; i++) {
      files.add(new File(ClassLoader.getSystemResource(i + ".png").getFile()));
    }

    Converter test = new Converter();
    test.setInputFiles(files);
    test.setPdfOutput("C:\\dada.pdf");
    test.convert();
  }
}
