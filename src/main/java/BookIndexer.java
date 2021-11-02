import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static constant.ExceptionMessage.BOOK_EMPTY_OR_BLANK;
import static constant.Regex.ANY_WORD;
import static java.util.stream.Collectors.toMap;

public class BookIndexer {
    private Map<String, Integer> index;

    public void index(String book) {
        validateBlankInput(book);
        var wordsMatcher = Pattern.compile(ANY_WORD).matcher(book);
        List<String> words = wordsMatcher.results()
                .map(MatchResult::group)
                .collect(Collectors.toList());
        index = IntStream.range(0, words.size())
                .boxed()
                .collect(toMap(words::get, i -> i + 1, (first, second) -> first));
    }

    private void validateBlankInput(String book) {
        if (book.isBlank()) {
            throw new IllegalArgumentException(BOOK_EMPTY_OR_BLANK);
        }
    }

    public int search(String word) {
        return index.getOrDefault(word, -1);
    }
}
