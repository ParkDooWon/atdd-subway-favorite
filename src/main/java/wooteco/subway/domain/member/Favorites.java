package wooteco.subway.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
@NoArgsConstructor
@Getter
public class Favorites {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorite> favorites;

    public Favorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public static Favorites empty() {
        return new Favorites(new ArrayList<>());
    }

    public void addFavorite(Favorite favorite) {
        if (favorites.contains(favorite)) {
            throw new IllegalArgumentException("중복되는 즐겨찾기 항목입니다.");
        }
        favorites.add(favorite);
    }

    public List<Long> getStationIds() {
        return favorites.stream()
                .flatMap(Favorite::getStationIdsStream)
                .distinct()
                .collect(Collectors.toList());
    }

    public Favorite findFavoriteById(Long favoriteId) {
        return favorites.stream()
                .filter(favorite -> favorite.isSameId(favoriteId))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public int size() {
        return favorites.size();
    }

    public void delete(Favorite favorite) {
        if (!favorites.contains(favorite)) {
            throw new IllegalArgumentException("존재하지 않는 즐겨찾기 항목입니다.");
        }
        favorites.remove(favorite);
    }
}
