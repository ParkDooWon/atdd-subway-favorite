package wooteco.subway.exception;

public class LineNotFoundException extends RuntimeException {
    public LineNotFoundException(String message) {
        super(message);
    }
}
