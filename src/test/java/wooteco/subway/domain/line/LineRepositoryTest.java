package wooteco.subway.domain.line;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import wooteco.subway.domain.station.Station;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
public class LineRepositoryTest {
    @Autowired
    private LineRepository lineRepository;

    @Test
    void addLineStation() {
        // given
        Station station1 = new Station(1L, "aa");
        Station station2 = new Station(2L, "bb");

        Line line = new Line("2호선", LocalTime.of(05, 30), LocalTime.of(22, 30), 5, "bg-black-500");
        Line persistLine = lineRepository.save(line);
        persistLine.addLineStation(new LineStation(persistLine, null, station1, 10, 10));
        persistLine.addLineStation(new LineStation(persistLine, station1, station2, 10, 10));

        // when
        Line resultLine = lineRepository.save(persistLine);

        // then
        assertThat(resultLine.getStations()).hasSize(2);
    }
}
