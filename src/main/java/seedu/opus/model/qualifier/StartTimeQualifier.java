package seedu.opus.model.qualifier;

import java.time.LocalDateTime;
import java.util.Optional;

import seedu.opus.logic.parser.DateTimeParser;
import seedu.opus.model.task.DateTime;
import seedu.opus.model.task.ReadOnlyTask;

/**
 * Compares and filters the startTime attribute of a task in the task manager
 */
public class StartTimeQualifier {

    private String startTime;

    public StartTimeQualifier(String endTime) {
        this.startTime = endTime;
    }

    public boolean run(ReadOnlyTask task) {
        Optional<LocalDateTime> inputStartTime = DateTimeParser.parse(this.startTime);
        Optional<DateTime> taskStartTime = task.getEndTime();
        if(!inputStartTime.isPresent() || !taskStartTime.isPresent()) {
            return false;
        }
        return inputStartTime.get().isAfter(taskStartTime.get().dateTime);
    }
}
