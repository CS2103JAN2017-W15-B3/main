package seedu.address.model.task;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import seedu.address.commons.exceptions.IllegalValueException;

public class DateTime {

    /*
     * Represents a Task's dateTime in the task manager.
     * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
     */

    public static final String MESSAGE_DATETIME_CONSTRAINTS =
            "Task date time should be in the format of [Day]/[Month]/[Year] [Hour]:[Minute]";

    public final LocalDateTime dateTime;
    private static DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("dd/MM/uuuu HH:mm")
            .withResolverStyle(ResolverStyle.STRICT);
    private Clock clock;

    /**
     * Validates given dateTime.
     *
     * @throws IllegalValueException if given dateTime is invalid.
     */
    public DateTime(LocalDateTime dateTime) throws IllegalValueException {
        assert dateTime != null;

        this.clock = Clock.systemDefaultZone();
        this.dateTime = dateTime;
    }

    public DateTime(String dateTime) throws IllegalValueException {
        assert dateTime != null;

        this.clock = Clock.systemDefaultZone();
        if (!isValidDateTime(dateTime)) {
            throw new IllegalValueException(MESSAGE_DATETIME_CONSTRAINTS);
        }
        this.dateTime = LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Checks if the DateTime is in the current week.
     *
     * @return true if the DateTime is in the current week
     */
    public boolean isCurrentWeek() {
        LocalDateTime now = LocalDateTime.now(clock);

        // 1 for Monday, 7 for Sunday
        int dayOfCurrentWeek = now.getDayOfWeek().getValue();

        LocalDateTime startOfWeek = now.minusDays(dayOfCurrentWeek);
        LocalDateTime endOfWeek = now.plusDays(7 - dayOfCurrentWeek);

        return dateTime.isAfter(startOfWeek) && dateTime.isBefore(endOfWeek);
    }

    /**
     * Returns true if a given date is a valid task dateTime.
     * @throws IllegalValueException
     */
    public static boolean isValidDateTime(String test) {
        try {
            if (LocalDateTime.parse(test, formatter) != null) {
                return true;
            }
        } catch (DateTimeParseException dtpe) {

        }
        return false;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    @Override
    public String toString() {
        return formatter.format(dateTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTime // instanceof handles nulls
                && this.dateTime.equals(((DateTime) other).dateTime)); // state check

    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }
}
