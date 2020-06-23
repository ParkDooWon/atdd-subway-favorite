package wooteco.subway.domain.line;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import wooteco.subway.domain.station.Station;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LineTest {
    private Line line;
    Station station1 = new Station(1L, "aa");
    Station station2 = new Station(2L, "bb");
    Station station3 = new Station(3L, "cc");
    Station station4 = new Station(4L, "dd");

    @BeforeEach
    void setUp() {
        line = new Line(1L, "2호선", LocalTime.of(05, 30), LocalTime.of(22, 30), 5, "bg-black-500");
        line.addLineStation(new LineStation(line, null, station1, 10, 10));
        line.addLineStation(new LineStation(line, station1, station2, 10, 10));
        line.addLineStation(new LineStation(line, station2, station3, 10, 10));
    }

    @Test
    void addLineStation() {
        line.addLineStation(new LineStation(line, null, station4, 10, 10));
        assertThat(line.getStations()).hasSize(4);
    }

    @Test
    void getLineStations() {
        List<Station> stationIds = line.getSortedStations();

        assertThat(stationIds.size()).isEqualTo(3);
        assertThat(stationIds.get(0)).isEqualTo(station1);
        assertThat(stationIds.get(1)).isEqualTo(station2);
        assertThat(stationIds.get(2)).isEqualTo(station3);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L})
    void removeLineStation(Long stationId) {
        line.removeLineStationById(stationId);

        assertThat(line.getStations()).hasSize(2);
    }
}
