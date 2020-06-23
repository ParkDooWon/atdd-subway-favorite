package wooteco.subway.domain.station;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@Sql("/truncate.sql")
@TestPropertySource(locations = "classpath:application-test.properties")
@DataJdbcTest
public class StationRepositoryTest {
    @Autowired
    private StationRepository stationRepository;

    @Test
    void findAllByIds() {
        stationRepository.save(new Station("서울"));
        stationRepository.save(new Station("강남"));
        stationRepository.save(new Station("잠실"));
        List<Long> ids = Arrays.asList(1L, 3L);

        List<Station> stations = stationRepository.findAllById(ids);

        assertThat(stations.size()).isEqualTo(2);
    }
}
