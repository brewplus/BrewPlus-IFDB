package jmash.utils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;

import javax.imageio.ImageIO;

public class Utility {

	public static void main(String[] args) throws IOException {
		System.out.println(getCurrentPeriods());
		System.out.println(getPathImageByPeriod("/jmash/images/bkgrnd.jpg"));
	}

	public static Periods getCurrentPeriods() {
		return getPeriods(LocalDate.now());
	}

	public static Periods getPeriods(LocalDate date) {
		Period period = Period.NORMAL;
		Period alternativePeriod = null;

		int currentYear = date.getYear();

		LocalDate capodanno = LocalDate.of(currentYear, Month.JANUARY, 1);
		LocalDate epifania = LocalDate.of(currentYear, Month.JANUARY, 6);
		LocalDate inizioCarnevale = epifania.plusDays(1);

		LocalDate pasqua = getEaster(currentYear);
		LocalDate domenicaPalme = pasqua.minusDays(7);
		LocalDate pasquetta = pasqua.plusDays(1);
		LocalDate mercolediCeneri = pasqua.minusDays(39);

		LocalDate inizioSettimanaCarnevale = mercolediCeneri.minusDays(7);
		LocalDate fineCarnevale = mercolediCeneri.minusDays(1);

		LocalDate immacolata = LocalDate.of(currentYear, Month.DECEMBER, 8);
		LocalDate ultimoDellAnno = LocalDate.of(currentYear, Month.DECEMBER, 31);

		if (isOnDate(date, capodanno, epifania) || isOnDate(date, immacolata, ultimoDellAnno)) {
			period = Period.CHRISTMAS;
		} else if (isOnDate(date, inizioCarnevale, fineCarnevale)) {
			period = Period.CARNIVAL;
			if (isOnDate(date, inizioSettimanaCarnevale, fineCarnevale)) {
				period = Period.CARNIVAL_WEEK;
				alternativePeriod = Period.CARNIVAL;
			}
		} else if (isOnDate(date, mercolediCeneri, pasquetta)) {
			period = Period.EASTER;
			if (isOnDate(date, domenicaPalme, pasquetta)) {
				period = Period.EASTER_WEEK;
				alternativePeriod = Period.EASTER;
			}
		}

		return new Periods(period, alternativePeriod);
	}

	public static boolean todayIsOnDate(LocalDate from, LocalDate to) {
		return isOnDate(LocalDate.now(), from, to);
	}

	public static boolean isOnDate(LocalDate date, LocalDate from, LocalDate to) {
		return (date.equals(from) || date.isAfter(from)) && ((date.equals(to) || date.isBefore(to)));
	}

	public static LocalDate getEaster(int year) {

		int a = year % 19;
		int b = year / 100;
		int c = year % 100;
		int d = b / 4;
		int e = b % 4;
		int g = (8 * b + 13) / 25;
		int h = (19 * a + b - d - g + 15) % 30;
		int j = c / 4;
		int k = c % 4;
		int m = (a + 11 * h) / 319;
		int r = (2 * e + 2 * j - k - h + m + 32) % 7;
		int month = (h - m + r + 90) / 25;
		int dayOfMonth = (h - m + r + month + 19) % 32;

		return LocalDate.of(year, month, dayOfMonth);
	}

	public static String getPathImageByPeriod(String path) throws IOException {
		Periods periods = new Periods(Period.CARNIVAL_WEEK, Period.CARNIVAL);
		periods = getCurrentPeriods();

		Path p = Paths.get(path);

		String name = p.getFileName().toString();

		String basicName = name;
		String ext = "";

		int pos = name.lastIndexOf(".");
		if (pos > 0) {
			basicName = name.substring(0, pos);
			ext = name.substring(pos);
		}
		String parent = "";
		if (name.length() < path.length()) {
			parent = path.substring(0, path.lastIndexOf(name));
		}

		String imagePath = "";

		if (periods.getAlternative() != null) {
			imagePath = parent + basicName + "_" + periods.getAlternative().getCode() + ext;
			if (existResourceFile(imagePath)) {
				return imagePath;
			}
		}

		if (periods.getPeriod() != null) {
			imagePath = parent + basicName + "_" + periods.getPeriod().getCode() + ext;
			if (existResourceFile(imagePath)) {
				return imagePath;
			}
		}

		return path;

	}

	public static boolean existResourceFile(String path) {
		boolean exists = false;
		try {
			ImageIO.read(Class.class.getResourceAsStream(path));
			exists = true;
		} catch (Exception e) {
			exists = false;
		}
		return exists;
	}

}
