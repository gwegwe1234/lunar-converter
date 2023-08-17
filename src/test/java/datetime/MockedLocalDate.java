package datetime;

import lunar.datetime.DateTimeWrapper;
import lunar.datetime.TimeSetter;

import java.time.LocalDate;

public class MockedLocalDate implements DateTimeWrapper {
    @Override
    public LocalDate now(TimeSetter timeSetter) {
        return LocalDate.of(timeSetter.getYear(), timeSetter.getMonth(), timeSetter.getDay());
    }
}
