package seedu.address.model.task;

import java.util.Comparator;

public class EndTimeComparator implements Comparator<ReadOnlyTask> {
    public int compare(ReadOnlyTask p, ReadOnlyTask q) {
        if (p.getEndTime().get().dateTime.isBefore(q.getEndTime().get().dateTime)) {
            return -1;
        } else if (p.getEndTime().get().dateTime.isAfter(q.getEndTime().get().dateTime)) {
            return 1;
        } else {
            return 0;
        }
    }
}
