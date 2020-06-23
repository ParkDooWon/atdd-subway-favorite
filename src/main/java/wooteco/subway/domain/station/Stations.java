//package wooteco.subway.domain.station;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.OneToMany;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@NoArgsConstructor
//@Getter
//public class Stations {
//    @OneToMany
//    private List<Station> stations;
//
//    public Stations(List<Station> stations) {
//        this.stations = stations;
//    }
//
//	public Map<Long, String> createStationIdNameMap() {
//		return stations.stream()
//			.collect(Collectors.toMap(Station::getId, Station::getName));
//	}
//}
