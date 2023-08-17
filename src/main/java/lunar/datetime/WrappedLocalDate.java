package lunar.datetime;

import java.time.LocalDate;

public class WrappedLocalDate implements DateTimeWrapper{
    @Override
    public LocalDate now(TimeSetter timeSetter) {
        return LocalDate.now();
    }
}
