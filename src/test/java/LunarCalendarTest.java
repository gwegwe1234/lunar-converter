import datetime.MockedLocalDate;
import lunar.datetime.TimeSetter;
import lunar.model.YearType;
import lunar.service.BirthdayService;
import lunar.util.LunarCalendarUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
public class LunarCalendarTest {

    MockedLocalDate localDate = new MockedLocalDate();
    BirthdayService birthdayService;

    @Test
    void lunaCalTest() throws InterruptedException {
        var numThreads = 200;
        var service = Executors.newFixedThreadPool(numThreads);

        IntStream.range(0, 1000).forEach((i) -> {
            service.execute(() -> {
                Assertions.assertEquals(LunarCalendarUtil.getSolarDateFromLunar(2020, 2, 28, true), "2020-03-22");
                Assertions.assertEquals(LunarCalendarUtil.getSolarDateFromLunar(2022, 10, 10, true), "2022-11-03");
                Assertions.assertEquals(LunarCalendarUtil.getSolarDateFromLunar(2021, 10, 25, true), "2021-11-29");
                Assertions.assertEquals(LunarCalendarUtil.getSolarDateFromLunar(2020, 4, 20, false), "2020-05-12");
                Assertions.assertEquals(LunarCalendarUtil.getSolarDateFromLunar(2020, 4, 20, true), "2020-06-11");
            });
        });

        TimeUnit.SECONDS.sleep(5);
    }

    @Test
    @DisplayName("윤년 생일 테스트 / 올해 윤년")
    void birthdayTest1() {
        //given
        TimeSetter now = new TimeSetter(2020, 6, 11);
        birthdayService = new BirthdayService(localDate, now);
        String birthday = "1991-04-20";
        YearType yearType = YearType.LEAP;

        Assertions.assertEquals(birthdayService.isBirthday(birthday, yearType), true);
    }

    @Test
    @DisplayName("음력 생일 테스트 / 올해 윤년")
    void birthdayTest2() {
        //given
        TimeSetter now = new TimeSetter(2020, 5, 12);
        birthdayService = new BirthdayService(localDate, now);
        String birthday = "1991-04-20";
        YearType yearType = YearType.LUNA;

        Assertions.assertEquals(birthdayService.isBirthday(birthday, yearType), true);
    }

    @Test
    @DisplayName("양력 생일 테스트 / 올해 윤년")
    void birthdayTest3() {
        //given
        TimeSetter now = new TimeSetter(2020, 4, 20);
        birthdayService = new BirthdayService(localDate, now);
        String birthday = "1991-04-20";
        YearType yearType = YearType.SUN;

        Assertions.assertEquals(birthdayService.isBirthday(birthday, yearType), true);
    }

    @Test
    @DisplayName("음력 생일 테스트 / 올해 윤년 아님")
    void birthdayTest4() {
        TimeSetter now = new TimeSetter(2022, 6, 22);
        birthdayService = new BirthdayService(localDate, now);
        String birthday = "1991-05-24";
        YearType yearType = YearType.LUNA;

        Assertions.assertEquals(birthdayService.isBirthday(birthday, yearType), true);
    }

    @Test
    @DisplayName("윤년 생일 테스트 / 올해 윤년 아님")
    void birthdayTest5() {
        TimeSetter now = new TimeSetter(2022, 6, 22);
        birthdayService = new BirthdayService(localDate, now);
        String birthday = "1991-05-24";
        YearType yearType = YearType.LEAP;

        Assertions.assertEquals(birthdayService.isBirthday(birthday, yearType), true);
    }
}
