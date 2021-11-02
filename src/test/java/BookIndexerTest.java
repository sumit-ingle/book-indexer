import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static constant.ExceptionMessage.BOOK_EMPTY_OR_BLANK;
import static org.junit.jupiter.api.Assertions.*;

public class BookIndexerTest {

    private BookIndexer bookIndexer;

    @BeforeEach
    void beforeEach() {
        bookIndexer = new BookIndexer();
    }

    @Nested
    @DisplayName("Indexing a book")
    class IndexingABook {
        @Test
        void should_index_a_book() {
            assertDoesNotThrow(() -> bookIndexer.index("Chapter 1 Once upon a time"));
        }

        @Test
        void throws_an_exception_if_book_is_empty() {
            assertThrows(IllegalArgumentException.class, () -> bookIndexer.index(""), BOOK_EMPTY_OR_BLANK);
        }

        @Test
        void throws_an_exception_if_book_is_blank() {
            assertThrows(IllegalArgumentException.class, () -> bookIndexer.index(" "), BOOK_EMPTY_OR_BLANK);
        }
    }

    @Nested
    @DisplayName("Searching a book")
    class SearchingABook {
        @Test
        void returns_1_for_first_word() {
            bookIndexer.index("Chapter");
            int result = bookIndexer.search("Chapter");
            assertEquals(1, result);
        }

        @Test
        void returns_minus_1_if_word_is_not_found() {
            bookIndexer.index("Chapter");
            int result = bookIndexer.search("word");
            assertEquals(-1, result);
        }

        @Test
        void returns_2_for_second_word() {
            bookIndexer.index("Chapter 1");
            int result = bookIndexer.search("1");
            assertEquals(2, result);
        }

        @Test
        void should_work_for_two_spaces_between_two_words() {
            bookIndexer.index("Chapter  1");
            int result = bookIndexer.search("1");
            assertEquals(2, result);
        }

        @Test
        void should_work_for_newline_character_between_two_words() {
            bookIndexer.index("Chapter\n1");
            int result = bookIndexer.search("1");
            assertEquals(2, result);
        }

        @Test
        void returns_position_of_first_occurrence_if_there_are_duplicate_words() {
            bookIndexer.index("Chapter 1 Once upon a time. Chapter 2");
            int result = bookIndexer.search("Chapter");
            assertEquals(1, result);
        }

        @Test
        void should_ignore_full_stop_after_a_word() {
            bookIndexer.index("word. word");
            int result = bookIndexer.search("word");
            assertEquals(1, result);
        }

        @Test
        void should_ignore_double_quotes() {
            bookIndexer.index("\"word\". word");
            int result = bookIndexer.search("word");
            assertEquals(1, result);
        }

        @Test
        void should_search_for_word_with_single_quote() {
            bookIndexer.index("Chapter 1 He's a software engineer.");
            int result = bookIndexer.search("He's");
            assertEquals(3, result);
        }

        @Test
        void should_consider_non_ascii_word() {
            bookIndexer.index("Chapter 1 àöç");
            int result = bookIndexer.search("àöç");
            assertEquals(3, result);
        }
    }
}
