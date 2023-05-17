package ru.betboom.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.betboom.pojos.Book;
import ru.betboom.pojos.Library;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class JsonTest {
    private ClassLoader cl = JsonTest.class.getClassLoader();
    @DisplayName("Чтение и проверка содержимого json файла")
    @Test
    void jsonParser() throws Exception {
        try (InputStream is = cl.getResourceAsStream("books.json");
             InputStreamReader isr = new InputStreamReader(is)) {
            Library library = new ObjectMapper().readValue(isr, Library.class);
            List<Book> books = library.getBooks();
            Assertions.assertEquals(3, books.size());

            Book firstBook = books.get(0);
            Assertions.assertEquals("Изучаем Java", firstBook.getTitle());
            Assertions.assertEquals("Кэти Сьерра, Берт Бейтс", firstBook.getAuthor());
            Assertions.assertEquals(1999, firstBook.getYear());

            Book secondBook = books.get(1);
            Assertions.assertEquals("Философия Java", secondBook.getTitle());
            Assertions.assertEquals("Брюс Эккель", secondBook.getAuthor());
            Assertions.assertEquals(1998, secondBook.getYear());

            Book thirdBook = books.get(2);
            Assertions.assertEquals("Effective Java", thirdBook.getTitle());
            Assertions.assertEquals("Джошуа Блох", thirdBook.getAuthor());
            Assertions.assertEquals(2001, thirdBook.getYear());
        }

    }
}
