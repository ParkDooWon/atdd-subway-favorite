package wooteco.subway.service.line;

import org.springframework.stereotype.Service;
import wooteco.subway.domain.line.Line;
import wooteco.subway.domain.line.LineRepository;
import wooteco.subway.domain.line.Lines;
import wooteco.subway.domain.station.Station;
import wooteco.subway.domain.station.StationRepository;
import wooteco.subway.exception.LineNotFoundException;
import wooteco.subway.service.line.dto.LineDetailResponse;
import wooteco.subway.service.line.dto.WholeSubwayResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LineStationService {
    private final LineRepository lineRepository;
    private final StationRepository stationRepository;

    public LineStationService(LineRepository lineRepository, StationRepository stationRepository) {
        this.lineRepository = lineRepository;
        this.stationRepository = stationRepository;
    }

    public LineDetailResponse findLineWithStationsById(Long lineId) {
        Line line = lineRepository.findById(lineId)
                .orElseThrow(() -> new LineNotFoundException("노선을 찾을 수 없습니다."));
        List<Long> lineStationIds = line.getStationIds();
        List<Station> stations = stationRepository.findAllById(lineStationIds);

        return LineDetailResponse.of(line, mapStations(lineStationIds, stations));
    }

    public WholeSubwayResponse findLinesWithStations() {
        Lines lines = new Lines(lineRepository.findAll());
        List<Station> stations = stationRepository.findAllById(lines.getStationIds());

        List<LineDetailResponse> lineDetailResponses = lines.getLines().stream()
                .map(line -> LineDetailResponse.of(line, mapStations(line.getStationIds(), stations)))
                .collect(Collectors.toList());

        return WholeSubwayResponse.of(lineDetailResponses);
    }

    private List<Station> mapStations(List<Long> stationsIds, List<Station> stations) {
        return stations.stream()
                .filter(station -> stationsIds.contains(station.getId()))
                .collect(Collectors.toList());
    }

    public void deleteLineStationByStationId(Long stationId) {
        List<Line> lines = lineRepository.findAll();
        lines.forEach(line -> line.removeLineStationById(stationId));
        lineRepository.saveAll(lines);
    }
}
