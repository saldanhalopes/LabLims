/*
 * Copyright (C) 2019 rafael.lopes
 *
 * Este programa é um software livre: você pode redistribuí-lo e / ou modificar
 * sob os termos da GNU General Public License, conforme publicado pela
 * a Free Software Foundation, seja a versão 3 da Licença, quanto
 * qualquer versão posterior.
 *
 * Este programa é distribuído na esperança de que seja útil,
 * mas SEM QUALQUER GARANTIA; sem a garantia implícita de
 * COMERCIALIZAÇÃO OU APTIDÃO PARA UM PROPÓSITO PARTICULAR. Veja o
 * GNU General Public License para obter mais detalhes.
 *
 * Você deve ter recebido uma cópia da GNU General Public License
 *  juntamente com este programa. Caso contrário, veja <http://www.gnu.org/licenses/>.
 */
package br.com.cristalia.biblioteca.util;

import br.com.cristalia.biblioteca.dao.ArquivosDAO;
import br.com.cristalia.biblioteca.model.Arquivos;
import com.itextpdf.barcodes.BarcodeEAN;
import java.io.FileInputStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import java.io.File;
import java.io.IOException;
import javax.swing.JDialog;

/**
 *
 * @author rafael.lopes
 */
public class Pdf {

    public static float FACTOR = 0.4f;

    public static File carregar() {
        JFrame parentFrame = new JFrame();
        parentFrame.setIconImage(new Frames().getIcon());
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecionar arquivo");
        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF(.pdf)", "pdf"));
        int userSelection = fileChooser.showOpenDialog(parentFrame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (file.getName().contains(".pdf") || file.getName().contains(".PDF")) {
                return file;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Arquivos salvar(File file, String descricao) {
        Arquivos arquivo = new Arquivos();
        try {
            String name = file.getName();
            arquivo.setNome(name);
            arquivo.setDescricao(descricao);
            arquivo.setTamanho((double) (file.length() / 1024));
            arquivo.setTipo("pdf");
            byte[] arqInBytes = new byte[(int) file.length()];
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                fileInputStream.read(arqInBytes);
            }
            arquivo.setVersion(0);
            arquivo.setArquivo(arqInBytes);
            arquivo = new ArquivosDAO().salvar(arquivo);
            return arquivo;
        } catch (Exception ex) {
            return null;
        }
    }

    public static void view(Long id) {
        try {
            Arquivos arquivo = new ArquivosDAO().findById(Arquivos.class, id);
            SwingController controller = new SwingController();
            SwingViewBuilder factory = new SwingViewBuilder(controller);
            JPanel viewerComponentPanel = factory.buildViewerPanel();
            ComponentKeyBinding.install(controller, viewerComponentPanel);
            controller.getDocumentViewController().setAnnotationCallback(
                    new org.icepdf.ri.common.MyAnnotationCallback(
                            controller.getDocumentViewController()));
            JFrame window = new JFrame("Viewer Component");
            window.setIconImage(new Frames().getIcon());
            window.getContentPane().add(viewerComponentPanel);
            window.pack();
            window.setLocationRelativeTo(null);
            window.setVisible(true);
            controller.openDocument(arquivo.getArquivo(), 0, arquivo.getArquivo().length,
                    arquivo.getDescricao(), null);
            window.toFront();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e);
        }
    }

    public static void view(Long id, JDialog window) {
        try {
            Arquivos arquivo = new ArquivosDAO().findById(Arquivos.class, id);
            SwingController controller = new SwingController();
            SwingViewBuilder factory = new SwingViewBuilder(controller);
            JPanel viewerComponentPanel = factory.buildViewerPanel();
            ComponentKeyBinding.install(controller, viewerComponentPanel);
            controller.getDocumentViewController().setAnnotationCallback(
                    new org.icepdf.ri.common.MyAnnotationCallback(
                            controller.getDocumentViewController()));
            JDialog frm = new JDialog(window, "Viewer Component", true);
            frm.setSize(600, 600);
            frm.setIconImage(new Frames().getIcon());
            frm.getContentPane().add(viewerComponentPanel);
            frm.pack();
            frm.setResizable(true);
            frm.setLocationRelativeTo(null);
            controller.openDocument(arquivo.getArquivo(), 0, arquivo.getArquivo().length,
                    arquivo.getDescricao(), null);
            frm.setVisible(true);
            frm.toFront();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e);
        }
    }

    public static void view(Long id, JFrame window) {
        try {
            Arquivos arquivo = new ArquivosDAO().findById(Arquivos.class, id);
            SwingController controller = new SwingController();
            SwingViewBuilder factory = new SwingViewBuilder(controller);
            JPanel viewerComponentPanel = factory.buildViewerPanel();
            ComponentKeyBinding.install(controller, viewerComponentPanel);
            controller.getDocumentViewController().setAnnotationCallback(
                    new org.icepdf.ri.common.MyAnnotationCallback(
                            controller.getDocumentViewController()));
            JDialog frm = new JDialog(window, "Viewer Component", true);
            frm.setSize(600, 600);
            frm.setIconImage(new Frames().getIcon());
            frm.getContentPane().add(viewerComponentPanel);
            frm.pack();
            frm.setResizable(true);
            frm.setLocationRelativeTo(null);
            controller.openDocument(arquivo.getArquivo(), 0, arquivo.getArquivo().length,
                    arquivo.getDescricao(), null);
            frm.setVisible(true);
            frm.toFront();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e);
        }
    }

//    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
//        PdfName key = new PdfName("ITXT_SpecialId");
//        PdfName value = new PdfName("123456789");
//        // Read the file
//        PdfReader reader = new PdfReader(src);
//        int n = reader.getXrefSize();
//        PdfObject object;
//        PRStream stream;
//        // Look for image and manipulate image stream
//        for (int i = 0; i < n; i++) {
//            object = reader.getPdfObject(i);
//            if (object == null || !object.isStream()) {
//                continue;
//            }
//            stream = (PRStream) object;
//            // if (value.equals(stream.get(key))) {
//            PdfObject pdfsubtype = stream.get(PdfName.SUBTYPE);
//            System.out.println(stream.type());
//            if (pdfsubtype != null && pdfsubtype.toString().equals(PdfName.IMAGE.toString())) {
//                PdfImageObject image = new PdfImageObject(stream);
//                BufferedImage bi = image.getBufferedImage();
//                if (bi == null) {
//                    continue;
//                }
//                int width = (int) (bi.getWidth() * FACTOR);
//                int height = (int) (bi.getHeight() * FACTOR);
//                BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//                AffineTransform at = AffineTransform.getScaleInstance(FACTOR, FACTOR);
//                Graphics2D g = img.createGraphics();
//                g.drawRenderedImage(bi, at);
//                ByteArrayOutputStream imgBytes = new ByteArrayOutputStream();
//                ImageIO.write(img, "JPG", imgBytes);
//                stream.clear();
//                stream.setData(imgBytes.toByteArray(), false, PRStream.BEST_COMPRESSION);
//                stream.put(PdfName.TYPE, PdfName.XOBJECT);
//                stream.put(PdfName.SUBTYPE, PdfName.IMAGE);
//                stream.put(key, value);
//                stream.put(PdfName.FILTER, PdfName.DCTDECODE);
//                stream.put(PdfName.WIDTH, new PdfNumber(width));
//                stream.put(PdfName.HEIGHT, new PdfNumber(height));
//                stream.put(PdfName.BITSPERCOMPONENT, new PdfNumber(8));
//                stream.put(PdfName.COLORSPACE, PdfName.DEVICERGB);
//            }
//        }
//        // Save altered PDF
//        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
//        stamper.close();
//        reader.close();
//    }
//
//    public void modifyPdf() {
//
//        try {
//            //Create PdfReader instance.
//            PdfReader pdfReader = new PdfReader("C:\\Programa\\pdf\\ufo.pdf");
//
//            //Create PdfStamper instance.
//            PdfStamper pdfStamper = new PdfStamper(pdfReader,
//                    new FileOutputStream("C:\\Programa\\pdf\\ufo_ModifiedTestFile.pdf"));
//
//            //Create BaseFont instance.
//            BaseFont baseFont = BaseFont.createFont(
//                    BaseFont.TIMES_ROMAN,
//                    BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
//
//            //Get the number of pages in pdf.
//            int pages = pdfReader.getNumberOfPages();
//
//            //Iterate the pdf through pages.
//            for (int i = 1; i <= pages; i++) {
//                //Contain the pdf data.
//                PdfContentByte pageContentByte
//                        = pdfStamper.getOverContent(i);
//
//                pageContentByte.beginText();
//                //Set text font and size.
//                pageContentByte.setFontAndSize(baseFont, 14);
//
//                pageContentByte.setTextMatrix(50, 740);
//
//                //Write text
//                pageContentByte.showText("javawithease.com");
//                pageContentByte.endText();
//            }
//
//            //Close the pdfStamper.
//            pdfStamper.close();
//
//            System.out.println("PDF modified successfully.");
//        } catch (DocumentException | IOException e) {
//            JOptionPane.showMessageDialog(null, "Erro ao editar PDF - " + e);
//        }
//
//    }
    public void manipulate_2_Pdf(String src, String dest) throws IOException {

        //Initialize PDF document
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));

        Document document = new Document(pdfDoc);
        Rectangle pageSize;
        PdfCanvas canvas;
        int n = pdfDoc.getNumberOfPages();
        for (int i = 1; i <= n; i++) {
            PdfPage page = pdfDoc.getPage(i);
            pageSize = page.getPageSize();

            BarcodeEAN barcode = new BarcodeEAN(pdfDoc);
            barcode.setCodeType(BarcodeEAN.EAN13);
            barcode.setCode(createBarcodeNumber(i));

            PdfFormXObject barcodeXObject = barcode.createFormXObject(ColorConstants.BLACK, ColorConstants.BLACK, pdfDoc);

            canvas = new PdfCanvas(page);
            //Draw header text
            canvas.beginText().setFontAndSize(PdfFontFactory.createFont(StandardFonts.HELVETICA), 12)
                    .moveText(pageSize.getWidth() / 2 - 260, pageSize.getHeight() - 60)
                    .showText("Produto: " + "  Teste  ")
                    .endText();
            canvas.beginText().setFontAndSize(PdfFontFactory.createFont(StandardFonts.HELVETICA), 12)
                    .moveText(pageSize.getWidth() / 2 - 260, pageSize.getHeight() - 80)
                    .showText("Lote: " + "  123")
                    .endText();
            //Draw footer line
            canvas.setStrokeColor(ColorConstants.BLACK)
                    .setLineWidth(.2f)
                    .moveTo(pageSize.getWidth() / 2 - 250, 65)
                    .lineTo(pageSize.getWidth() / 2 + 250, 65).stroke();
            //Draw page number
            canvas.beginText().setFontAndSize(PdfFontFactory.createFont(StandardFonts.HELVETICA), 7)
                    .moveText(pageSize.getWidth() / 2 + 220, 25)
                    .showText("Page ")
                    .showText(String.valueOf(i))
                    .showText(" of ")
                    .showText(String.valueOf(n))
                    .addXObject(barcodeXObject, pageSize.getWidth() / 2 - 50, 25)
                    .endText();

            //Draw watermark
            Paragraph p = new Paragraph("CÓPIA CONTROLADA").setFontSize(60);
            canvas.saveState();
            PdfExtGState gs1 = new PdfExtGState().setFillOpacity(0.2f);
            canvas.setExtGState(gs1);
            document.showTextAligned(p,
                    pageSize.getWidth() / 2, pageSize.getHeight() / 2,
                    pdfDoc.getPageNumber(page),
                    TextAlignment.CENTER, VerticalAlignment.MIDDLE, 45);
            canvas.restoreState();
        }

        pdfDoc.close();

    }

    private static String createBarcodeNumber(int i) {
        String barcodeNumber = String.valueOf(i);
        barcodeNumber = "0000000000000".substring(barcodeNumber.length()) + barcodeNumber;

        return barcodeNumber;
    }

}
