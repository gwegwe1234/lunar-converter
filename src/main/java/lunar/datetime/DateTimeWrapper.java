package lunar.datetime;

import java.time.LocalDate;

public interface DateTimeWrapper {
    LocalDate now(TimeSetter timeSetter);
}
