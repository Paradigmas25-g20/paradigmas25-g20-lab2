//package parser;
//
//import feed.Article;
//import feed.Feed;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.json.JSONException;
//
//import java.util.Date;
//
//public class RedditParser extends GeneralParser {
//
//
//    public Feed parse(String redditToParse) {
//        Feed feed = new Feed(redditToParse);
//        try {
//
//            SONObject root = new JSONObject(jsonContent);
//            JSONObject data = root.getJSONObject("data");
//            JSONArray children = data.getJSONArray("children");
//            for (int i = 0; i < children.length(); i++) {
//                JSONObject postContainer = children.getJSONObject(i);
//                JSONObject postData = postContainer.getJSONObject("data");
//                String title = postData.getString("title");
//                ;
//                String link = "https://www.reddit.com" + postData.getString("permalink");
//                String descriptionOrText = postData.optString("selftext", "");
//                Date pubdate = null;
//                if (postData.has("created_utc")) {
//                    long createdUtcTimestamp = postData.getLong("created_utc");
//                    pubdate = new Date(createdUtcTimestamp * 1000L);
//
//                    Article article = new Article(title, descriptionOrText, pubdate, link);
//
//                    // Añadir el artículo al feed
//                    feed.addArticle(article);
//                }
//            }
//        }catch(JSONException e){
//                System.err.println("Error al parsear el JSON de Reddit: " + e.getMessage());
//            }
//            return feed;
//
//    }
//}
