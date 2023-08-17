package lunar.service;

import lunar.datetime.DateTimeWrapper;
import lunar.datetime.TimeSetter;
import lunar.datetime.WrappedLocalDate;
import lunar.model.YearType;
import lunar.util.LunarCalendarUtil;

import java.time.LocalDate;
import java.util.Optional;

import static lunar.util.DateUtil.getValueFromStringFormatDate;
import static lunar.util.LunarCalendarUtil.getSolarDateFromLunar;

public class BirthdayService {

    private static String MONTH = "month";
    private static String DAY = "day";
    private static int dummyNumber = 0;

    private DateTimeWrapper dateTimeWrapper = new WrappedLocalDate();
    private TimeSetter timeSetter = new TimeSetter(dummyNumber, dummyNumber, dummyNumber);

    public BirthdayService() {
    }

    public BirthdayService(DateTimeWrapper dateTimeWrapper, TimeSetter timeSetter) {
        this.dateTimeWrapper = dateTimeWrapper;
        this.timeSetter = timeSetter;
    }

    public Boolean isBirthday(String birthday, YearType yearType) {
        LocalDate now = dateTimeWrapper.now(timeSetter);

        switch (yearType) {
            case SUN:
                return compareMonthValueAndDayOfMonth(birthday, now);
            case LUNA:
            case LEAP:
            default:
                int lunarMonth = getValueFromStringFormatDate(birthday, MONTH);
                int lunarDay = getValueFromStringFormatDate(birthday, DAY);
                boolean isIntercalation = Optional.of(yearType)
                        .filter(type -> type.equals(YearType.LEAP))
                        .map(filteredYearType -> true)
                        .orElse(false);
                int lunarYear = LunarCalendarUtil.convertLunarYearForBirthday(now.getYear(), lunarMonth, lunarDay, isIntercalation);
                return compareMonthValueAndDayOfMonth(getSolarDateFromLunar(lunarYear, lunarMonth, lunarDay, isIntercalation), now);
        }
    }

    private boolean compareMonthValueAndDayOfMonth(String date, LocalDate now) {
        return getValueFromStringFormatDate(date, MONTH).equals(now.getMonthValue())
                && getValueFromStringFormatDate(date, DAY).equals(now.getDayOfMonth());
    }
}
