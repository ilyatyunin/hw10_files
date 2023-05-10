package ru.betboom;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipExtractorTest {
    private ClassLoader cl = ZipExtractorTest.class.getClassLoader();

    @DisplayName("Чтение и проверка содержимого pdf файла")
    @Test
    void checkPdf() throws Exception {
        try (InputStream is = cl.getResourceAsStream("files.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zs.getNextEntry()) != null) { // возвращает файлы по очереди
                if (entry.getName().contains("pdf") && !entry.getName().contains("MACOSX")) {
                    PDF pdf = new PDF(zs);
                    Assertions.assertTrue(pdf.text.contains(EXPECTED_PDF_TEXT));
                }
            }
        }
    }
    private static final String EXPECTED_PDF_TEXT = "Assertions позволяют сравнить ожидаемые значения с \n" + "фактическими.";


    @DisplayName("Чтение и проверка содержимого xls файла")
    @Test
    void checkXls() throws Exception {
        try (InputStream is = cl.getResourceAsStream("files.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zs.getNextEntry()) != null) { // возвращает файлы по очереди
                if ((entry.getName().contains("xlsx") || (entry.getName().contains("xlsx"))) && !entry.getName().contains("MACOSX")) {
                    XLS xls = new XLS(zs);
//                    Assertions.assertTrue(xls.text.contains(EXPECTED_PDF_TEXT));
                }
            }
        }
    }
//    private static final String EXPECTED_PDF_TEXT = "Assertions позволяют сравнить ожидаемые значения с \n" + "фактическими.";
}
