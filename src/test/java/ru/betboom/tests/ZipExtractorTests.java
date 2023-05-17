package ru.betboom.tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipExtractorTests {
    private ClassLoader cl = ZipExtractorTests.class.getClassLoader();
    private static final String EXPECTED_PDF_TEXT = "Assertions позволяют сравнить ожидаемые значения с \n" + "фактическими.";
    private static final String EXPECTED_XLS_TEXT = "Kathleen";
    private static final String[] EXPECTED_CSV_TEST = new String[]{
            "3", "Philip", "Gent", "Male", "France", "36", "21/05/2015", "2587"
    };

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

    @DisplayName("Чтение и проверка содержимого xls файла")
    @Test
    void checkXls() throws Exception {
        try (InputStream is = cl.getResourceAsStream("files.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zs.getNextEntry()) != null) { // возвращает файлы по очереди
                if ((entry.getName().contains("xls") || (entry.getName().contains("xlsx"))) && !entry.getName().contains("MACOSX")) {
                    XLS xls = new XLS(zs);
                    Assertions.assertTrue(
                            xls.excel.getSheetAt(0).getRow(4).getCell(1).getStringCellValue().equals(EXPECTED_XLS_TEXT)
                    );
                }
            }
        }
    }

    @DisplayName("Чтение и проверка содержимого csv файла")
    @Test
    void checkCsv() throws Exception {
        try (InputStream is = cl.getResourceAsStream("files.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zs.getNextEntry()) != null) { // возвращает файлы по очереди
                if ((entry.getName().contains("csv")) && !entry.getName().contains("MACOSX")) {
                    InputStreamReader isr = new InputStreamReader(zs);
                    CSVReader csvReader = new CSVReader(isr);
                    List<String[]> content = csvReader.readAll();
                    Assertions.assertArrayEquals(EXPECTED_CSV_TEST, content.get(3));
                }
            }
        }
    }
}