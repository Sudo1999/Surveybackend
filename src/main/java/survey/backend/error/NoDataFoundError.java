package survey.backend.error;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class NoDataFoundError extends RuntimeException {

    public NoDataFoundError(String message) {
        super(message);
    }

    public static NoDataFoundError noResults(String itemType, String message) {
        return new NoDataFoundError(itemType + " return 0 results with " + message);
    }

    public static NoDataFoundError withId(String itemType, Long id) {
        return new NoDataFoundError(itemType + " " + id + " not found");
    }

    public static NoDataFoundError withIds(String itemTypes, long... ids) {
    //public static NoDataFoundError withIds(String itemTypes, Collection<Long> ids) {
//        return new NoDataFoundError(
//                itemTypes
//                        + " with ids "
//                        + ids.stream()
//                        .map(id -> "" + id)
//                        .collect(Collectors.joining(", "))
//                        + " not found");
        return new NoDataFoundError(
                itemTypes
                        + " with ids "
                        + Arrays.stream(ids).mapToObj(id -> "" + id)
                        .collect(Collectors.joining(", "))
                        + " not found");
    }

    public static NoDataFoundError withValues(String itemType, String values) {
        return new NoDataFoundError(
                itemType
                        + " with values [ "
                        + values
                        + " ] not found");
    }
}