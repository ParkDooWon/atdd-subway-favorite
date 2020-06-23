package wooteco.subway.domain.line;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
@NoArgsConstructor
@Getter
public class Lines {
    private List<Line> lines;

    public Lines(List<Line> lines) {
        this.lines = lines;
    }

    public List<Long> getStationIds() {
        return lines.stream()
                .flatMap(line -> line.getStations().stream())
                .map(lineStation -> lineStation.getStation().getId())
                .collect(Collectors.toList());
    }

    public List<Line> getLines() {
        return lines;
    }
}
