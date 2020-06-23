package wooteco.subway.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
import java.util.stream.Stream;

@Entity
@NoArgsConstructor
@Getter
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long departStationId;
    private Long arriveStationId;

    public Favorite(Long departStationId, Long arriveStationId) {
        this.departStationId = departStationId;
        this.arriveStationId = arriveStationId;
    }

    public Favorite(Long id, Long departStationId, Long arriveStationId) {
        this.id = id;
        this.departStationId = departStationId;
        this.arriveStationId = arriveStationId;
    }

    public Stream<Long> getStationIdsStream() {
        return Stream.of(departStationId, arriveStationId);
    }

    public boolean isSameId(Long favoriteId) {
        return this.id.equals(favoriteId);
    }

    public Long getId() {
        return id;
    }

    public Long getDepartStationId() {
        return departStationId;
    }

    public Long getArriveStationId() {
        return arriveStationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Favorite favorite = (Favorite)o;
        return Objects.equals(departStationId, favorite.departStationId) &&
                Objects.equals(arriveStationId, favorite.arriveStationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departStationId, arriveStationId);
    }
}
