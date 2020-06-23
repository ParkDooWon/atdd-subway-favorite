package wooteco.subway.service.member;

import org.springframework.stereotype.Service;
import wooteco.subway.domain.member.Favorite;
import wooteco.subway.domain.member.Member;
import wooteco.subway.domain.member.MemberRepository;
import wooteco.subway.domain.station.Station;
import wooteco.subway.domain.station.StationRepository;
import wooteco.subway.service.member.dto.FavoriteCreateRequest;
import wooteco.subway.service.member.dto.FavoriteResponse;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FavoritesService {
    private final MemberRepository memberRepository;
    private final StationRepository stationRepository;

    public FavoritesService(MemberRepository memberRepository, StationRepository stationRepository) {
        this.memberRepository = memberRepository;
        this.stationRepository = stationRepository;
    }

    @Transactional
    public void addFavorite(Member member, FavoriteCreateRequest favoriteCreateRequest) {
        member.addFavorite(favoriteCreateRequest.toEntity());
//        memberRepository.save(member);
    }

    public List<FavoriteResponse> getFavorites(Member member) {
        List<Long> favoriteStationIds = member.getFavoriteStationIds();
        List<Station> stations = stationRepository.findAllById(favoriteStationIds);

        Map<Long, String> stationIds = stations.stream()
                .collect(Collectors.toMap(Station::getId, Station::getName));

        return FavoriteResponse.listOf(member.getFavorites(), stationIds);
    }

    @Transactional
    public void deleteFavorite(Member member, Long favoriteId) {
        Favorite targetFavorite = member.findFavoriteById(favoriteId);
        member.deleteFavorite(targetFavorite);
//        memberRepository.save(member);
    }
}
