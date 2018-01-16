package mn.ulas.converter.util;

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Raven.
 */
public class Finder {

  private static final Logger LOG = Logger.getLogger(Finder.class.getName());

  public static Map<String, List<File>> filesToconvert = new HashMap<>();

  private List<File> filesToAdd = new ArrayList<>();
  private String tmp = "";

  private final JProgressBar progressBar;

  public Finder(JProgressBar progressBar) {
    this.progressBar = progressBar;
  }

  /**
   * Find folders and files.
   */
  public Map<String, List<File>> find(String directoryName, String extention) {
    try {
      File directory = new File(directoryName);
      File[] files = directory.listFiles();

      progressBar.setValue(30);

      for (File file : files) {
        String fileName = file.getName();
        if (file.isFile() && fileName.toLowerCase().endsWith(extention)) {
          File parentDirectory = file.getParentFile();

          if (parentDirectory != null) {
            String parentPath = parentDirectory.getAbsolutePath();

            if (!StringUtils.isEmpty(tmp) && !StringUtils.equals(tmp, parentPath)) {
              filesToAdd = new ArrayList<>();
            }

            if (!filesToAdd.contains(file)) {
              filesToAdd.add(file);
            }
            tmp = parentPath;

            if (!StringUtils.isEmpty(tmp) && !filesToconvert.containsKey(tmp)) {
              filesToconvert.put(tmp, filesToAdd);
            }
          }
        } else if (file.isDirectory()) {
          find(file.getAbsolutePath(), extention);
        }
      }
    } catch (Exception e) {
      LOG.log(Level.SEVERE, "Error: {0}", e);
    }

    progressBar.setValue(50);

    return filesToconvert;
  }

  public void clearFileMap() {
    filesToconvert.clear();
  }
}
