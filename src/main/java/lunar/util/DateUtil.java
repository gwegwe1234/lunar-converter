package lunar.util;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {

    private static final Pattern STRING_ISO_LOCAL_DATE_PATTERN = Pattern.compile("^(?<year>\\d{4})-(?<month>\\d{2})-(?<day>\\d{2})$");

    public static Integer getValueFromStringFormatDate(String date, String dateName) {
        Matcher matcher = STRING_ISO_LOCAL_DATE_PATTERN.matcher(date);

        return Optional.of(matcher)
                .filter(Matcher::find)
                .map(filteredMatcher -> filteredMatcher.group(dateName))
                .map(Integer::valueOf)
                .orElseThrow(() -> new RuntimeException("Invalid date pattern"));
    }
}
