package wooteco.subway.domain.line;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wooteco.subway.domain.station.Station;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.*;

@Embeddable
@NoArgsConstructor
@Getter
public class LineStations {
    @OneToMany(mappedBy = "line", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LineStation> stations;

    public LineStations(Set<LineStation> stations) {
        this.stations = stations;
    }

    public static LineStations empty() {
        return new LineStations(new HashSet<>());
    }

    public Set<LineStation> getStations() {
        return stations;
    }

    public void add(LineStation targetLineStation) {
        updatePreStationOfNextLineStation(targetLineStation.getPreStation(), targetLineStation.getStation());
        stations.add(targetLineStation);
    }

    private void remove(LineStation targetLineStation) {
        updatePreStationOfNextLineStation(Station.toStation(targetLineStation.getStation().getId()), Station.toStation(targetLineStation.getPreStation().getId()));
        stations.remove(targetLineStation);
    }

    public void removeById(Long targetStationId) {
        extractByStationId(targetStationId)
                .ifPresent(this::remove);
    }

    public List<Station> getSortedStations() {
        List<Station> result = new ArrayList<>();
        extractNext(null, result);
        return result;
    }

    private void extractNext(Station preStation, List<Station> stations) {
        this.stations.stream()
                .filter(lineStation -> Objects.equals(lineStation.getPreStation(), preStation))
                .findFirst()
                .ifPresent(lineStation -> {
                    Station nextStation = lineStation.getStation();
                    stations.add(nextStation);
                    extractNext(nextStation, stations);
                });
    }

    private void updatePreStationOfNextLineStation(Station targetStation, Station newPreStation) {
        extractByPreStation(targetStation)
                .ifPresent(lineStation -> lineStation.updatePreLineStation(newPreStation));
    }

    private Optional<LineStation> extractByStationId(Long stationId) {
        return stations.stream()
                .filter(lineStation -> Objects.equals(lineStation.getStation().getId(), stationId))
                .findFirst();
    }

    private Optional<LineStation> extractByPreStation(Station preStation) {
        return stations.stream()
                .filter(lineStation -> Objects.equals(lineStation.getPreStation(), preStation))
                .findFirst();
    }
}
