package mn.ulas.converter;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mn.ulas.converter.model.CodedValue;
import mn.ulas.converter.model.ValueLabelComboBoxModel;
import mn.ulas.converter.model.ValueLabelPair;
import mn.ulas.converter.util.Converter;
import mn.ulas.converter.util.Finder;

import org.apache.commons.lang3.StringUtils;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(MainFrame.class.getName());

    private Finder finder;
    private Converter converter;

    private List<CodedValue> sizes;
    private List<CodedValue> extentions;
    private List<CodedValue> qualities;

    private Map<String, List<File>> filesToconvert;

    /**
     * Constructor.
     */
    public MainFrame() {
        initComponents();
        init();

        setComboboxValues();
        setModels();
        addListeners();
    }

    /**
     * Runnable.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    new MainFrame().setVisible(true);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                        | UnsupportedLookAndFeelException e) {
                    LOG.log(Level.INFO, "Error: {0}", e);
                }
            }
        });
    }

    private void init() {
        finder = new Finder(progress);
        converter = new Converter();

        sizes = new ArrayList<>();
        extentions = new ArrayList<>();
        qualities = new ArrayList<>();
    }

    private void setComboboxValues() {
        CodedValue a4 = new CodedValue(PageSize.A4, "A4");
        CodedValue a3 = new CodedValue(PageSize.A3, "A3");
        sizes.add(a4);
        sizes.add(a3);

        CodedValue png = new CodedValue("png", "PNG");
        CodedValue tiff = new CodedValue("tif", "TIF");
        extentions.add(png);
        extentions.add(tiff);

        CodedValue low = new CodedValue(0.05f, "Low (10%)");
        CodedValue medium = new CodedValue(0.30f, "Medium (30%)");
        CodedValue high = new CodedValue(0.6f, "Good (60%)");
        qualities.add(low);
        qualities.add(medium);
        qualities.add(high);
    }

    @SuppressWarnings("unchecked")
    private void setModels() {
        comboSize.setModel(new ValueLabelComboBoxModel(sizes));
        comboExtention.setModel(new ValueLabelComboBoxModel(extentions));
        comboQuality.setModel(new ValueLabelComboBoxModel(qualities));

        comboSize.setSelectedIndex(0);
        comboExtention.setSelectedIndex(0);
        comboQuality.setSelectedIndex(0);
    }

    private void addListeners() {
        comboSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFileMap();
            }
        });

        comboExtention.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFileMap();
            }
        });

        comboQuality.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFileMap();
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelInput = new javax.swing.JPanel();
        tfInput = new javax.swing.JTextField();
        buttonBrowse = new javax.swing.JButton();
        panelProgress = new javax.swing.JPanel();
        progress = new javax.swing.JProgressBar();
        panelButtons = new javax.swing.JPanel();
        buttonConvert = new javax.swing.JButton();
        buttonCanel = new javax.swing.JButton();
        panelConfig = new javax.swing.JPanel();
        labelExtention = new javax.swing.JLabel();
        labelSize = new javax.swing.JLabel();
        comboExtention = new javax.swing.JComboBox<>();
        comboSize = new javax.swing.JComboBox<>();
        labelQuality = new javax.swing.JLabel();
        comboQuality = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ulas Converter");

        panelInput.setBorder(javax.swing.BorderFactory.createTitledBorder("Input"));

        tfInput.setEditable(false);

        buttonBrowse.setText("Browse");
        buttonBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBrowseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInputLayout = new javax.swing.GroupLayout(panelInput);
        panelInput.setLayout(panelInputLayout);
        panelInputLayout.setHorizontalGroup(
            panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInputLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfInput, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonBrowse)
                .addContainerGap())
        );
        panelInputLayout.setVerticalGroup(
            panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(tfInput, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonBrowse))
        );

        panelProgress.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout panelProgressLayout = new javax.swing.GroupLayout(panelProgress);
        panelProgress.setLayout(panelProgressLayout);
        panelProgressLayout.setHorizontalGroup(
            panelProgressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProgressLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelProgressLayout.setVerticalGroup(
            panelProgressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProgressLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonConvert.setText("Convert");
        buttonConvert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConvertActionPerformed(evt);
            }
        });

        buttonCanel.setText("Close");
        buttonCanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCanelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelButtonsLayout = new javax.swing.GroupLayout(panelButtons);
        panelButtons.setLayout(panelButtonsLayout);
        panelButtonsLayout.setHorizontalGroup(
            panelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelButtonsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonConvert)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCanel)
                .addContainerGap())
        );
        panelButtonsLayout.setVerticalGroup(
            panelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCanel)
                    .addComponent(buttonConvert))
                .addGap(20, 20, 20))
        );

        panelConfig.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        labelExtention.setText("Extention:");

        labelSize.setText("Output size :");

        labelQuality.setText("Quality");

        javax.swing.GroupLayout panelConfigLayout = new javax.swing.GroupLayout(panelConfig);
        panelConfig.setLayout(panelConfigLayout);
        panelConfigLayout.setHorizontalGroup(
            panelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfigLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelQuality, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelExtention, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelConfigLayout.createSequentialGroup()
                        .addComponent(comboQuality, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelConfigLayout.createSequentialGroup()
                        .addComponent(comboExtention, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelSize)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboSize, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        panelConfigLayout.setVerticalGroup(
            panelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfigLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelExtention)
                    .addComponent(comboExtention, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSize)
                    .addComponent(comboSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelQuality)
                    .addComponent(comboQuality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelConfig, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelInput, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelProgress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonConvertActionPerformed(java.awt.event.ActionEvent evt) {
        final String selectedPath = tfInput.getText();
        final String extention = (String) ((ValueLabelPair) comboExtention.getSelectedItem())
                .getValue();
        final Rectangle pageSize = (Rectangle) ((ValueLabelPair) comboSize.getSelectedItem())
                .getValue();
        final Float quality = (Float) ((ValueLabelPair) comboQuality.getSelectedItem())
                .getValue();

        if (!StringUtils.isEmpty(selectedPath)) {
            makeBusy(true);

            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    filesToconvert = finder.find(selectedPath, "." + extention);
                    if (filesToconvert.size() > 0) {
                        converter.setProgressBar(progress);
                        converter.setPageSize(pageSize);
                        converter.setExtentions(extention);
                        converter.setQuality(quality);

                        for (Map.Entry<String, List<File>> entry : filesToconvert.entrySet()) {
                            String key = entry.getKey();
                            String[] spliitedKey = key.split(Pattern.quote(File.separator));
                            if (spliitedKey.length > 0) {
                                String fileName = spliitedKey[spliitedKey.length - 1];
                                String path = entry.getKey().replace(fileName, "");

                                converter.setInputFiles(entry.getValue());
                                converter.setPdfOutput(path + fileName + ".pdf");
                                converter.convert();
                            } else {
                                throw new FileNotFoundException("Wrong folder Archetecture");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Файл олдсонгүй",
                                "Алдаа",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }

                    return null;
                }

                @Override
                public void done() {
                    progress.setValue(100);
                    makeBusy(false);
                }
            };
            worker.execute();
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Choose folder!",
                    "Error",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    } // GEN-LAST:event_btn_convertActionPerformed

    private void buttonCanelActionPerformed(java.awt.event.ActionEvent evt) {
        // GEN-FIRST:event_btn_cancelActionPerformed
        this.dispose();
    } // GEN-LAST:event_btn_cancelActionPerformed

    private void buttonBrowseActionPerformed(java.awt.event.ActionEvent evt) {
        // GEN-FIRST:event_btn_inputActionPerformed
        clearFileMap();
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File directory = chooser.getSelectedFile();
            if (directory != null) {
                tfInput.setText(directory.getAbsolutePath());
            } else {
                JOptionPane.showMessageDialog(this, "Choose file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } // GEN-LAST:event_btn_inputActionPerformed

    private void makeBusy(boolean busy) {
        tfInput.setEnabled(!busy);
        buttonBrowse.setEnabled(!busy);
        buttonConvert.setEnabled(!busy);
        buttonCanel.setEnabled(!busy);

        comboExtention.setEnabled(!busy);
        comboSize.setEnabled(!busy);
        comboQuality.setEnabled(!busy);
    }

    private void clearFileMap() {
        if (filesToconvert != null && !filesToconvert.isEmpty()) {
            filesToconvert.clear();
        }

        finder.clearFileMap();
        progress.setValue(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBrowse;
    private javax.swing.JButton buttonCanel;
    private javax.swing.JButton buttonConvert;
    private javax.swing.JComboBox<String> comboExtention;
    private javax.swing.JComboBox<String> comboQuality;
    private javax.swing.JComboBox<String> comboSize;
    private javax.swing.JLabel labelExtention;
    private javax.swing.JLabel labelQuality;
    private javax.swing.JLabel labelSize;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelConfig;
    private javax.swing.JPanel panelInput;
    private javax.swing.JPanel panelProgress;
    private javax.swing.JProgressBar progress;
    private javax.swing.JTextField tfInput;
    // End of variables declaration//GEN-END:variables
}
