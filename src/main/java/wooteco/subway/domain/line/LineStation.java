package wooteco.subway.domain.line;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wooteco.subway.domain.station.Station;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
public class LineStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "LINE")
    private Line line;

    @ManyToOne
    @JoinColumn(name = "PRE_STATION")
    private Station preStation;

    @ManyToOne
    @JoinColumn(name = "NEXT_STATION")
    private Station station;

    private int distance;
    private int duration;

    public LineStation(Line line, Station preStation, Station station, int distance, int duration) {
        this.line = line;
        this.preStation = preStation;
        this.station = station;
        this.distance = distance;
        this.duration = duration;
    }

    public void updatePreLineStation(Station preStation) {
        this.preStation = preStation;
    }

    public boolean isLineStationOf(Long preStationId, Long stationId) {
        return this.preStation.getId().equals(preStationId) && this.station.getId().equals(stationId)
                || this.preStation.getId().equals(stationId) && this.station.getId().equals(preStationId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineStation that = (LineStation) o;
        return distance == that.distance &&
                duration == that.duration &&
                Objects.equals(preStation, that.preStation) &&
                Objects.equals(station, that.station);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, preStation, station, distance, duration);
    }

    public Long getId() {
        return id;
    }

    public Station getPreStation() {
        return preStation;
    }

    public Station getStation() {
        return station;
    }

    public int getDistance() {
        return distance;
    }

    public int getDuration() {
        return duration;
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//        LineStation that = (LineStation)o;
//        return distance == that.distance &&
//                duration == that.duration &&
//                Objects.equals(preStationId, that.preStationId) &&
//                Objects.equals(stationId, that.stationId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(preStationId, stationId, distance, duration);
//    }
}
