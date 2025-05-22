package namedEntity.heuristic;

import java.util.List;
import java.util.Map;

public abstract class Heuristic {

	private static Map<String, String> categoryMap = Map.of(
			"Microsoft", "Company",
			"Apple", "Company",
			"Google", "Company",
			"Musk", "Surname",
			"Biden", "Surname",
			"Trump", "Surname",
			"Messi", "Surname",
			"Roger", "Name",
			"Barcelona", "City",
			"Russia", "Country"
			);

	public String getCategories(String entity) { return categoryMap.get(entity); }
	
	public String detectConcreteCategory(String entity) {
			for (String ent: categoryMap.keySet()) {
				if (entity.equals(ent)) {
					return categoryMap.get(entity);
				}
			}
			return "Other";
	}

	public abstract boolean isEntity(String word);

	public static void main(String[] args) {
		Heuristic h = new QuickHeuristic();
		System.out.println(h.detectConcreteCategory("Musk"));
	}
}
