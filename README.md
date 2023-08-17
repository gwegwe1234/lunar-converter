# Lunar-Converter

## 기능

- 음력 날짜 변환
  - 음력 -> 양력 변환 기능
  - 윤년 체크 포함
- 생일 여부 확인
  - 달력 타입에 따른 생일 여부 확인 기능

## 사용법

- 음력 날짜 변환
  - LunarCalendarUtil.getSolarDateFromLunar 호출
- 생일 여부 확인
  - BirthdayService birthdayService = new BirthdayService();
  - birthdayService.isBirthday(날짜, 달력 타입)