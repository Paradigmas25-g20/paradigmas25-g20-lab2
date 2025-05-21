package namedEntity.heuristic;

import java.util.List;
import java.util.Map;

public abstract class Heuristic {

	private static Map<String, List<String>> categoryMap = Map.of(
			"Microsoft", List.of("Company"),
			"Apple", List.of("Company"),
			"Google", List.of("Company"),
			"Musk", List.of("Person", "Surname"),
			"Biden", List.of("Person", "Surname"),
			"Trump", List.of("Person", "Surname"),
			"Messi", List.of("Person", "Surname"),
			"Federer", List.of("Person", "Surname"),
			"USA", List.of("Place", "Country"),
			"Russia", List.of("Place", "Country")
			);

	public List<String> getCategories(String entity) { return categoryMap.get(entity); }

	public abstract boolean isEntity(String word);
		
}
