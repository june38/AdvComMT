package topic5;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gdata.client.youtube.YouTubeQuery;
import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.util.ServiceException;

public class YouTubeSearch {
	public static VideoFeed search(String keyword) throws IOException, ServiceException{
		YouTubeService service = new YouTubeService("YouTubeSearch");
		YouTubeQuery query = new YouTubeQuery(new URL("http://gdata.youtube.com/feeds/api/videos"));
		query.setOrderBy(YouTubeQuery.OrderBy.VIEW_COUNT);
		query.setFullTextQuery(keyword);
		query.setSafeSearch(YouTubeQuery.SafeSearch.NONE);
		return service.query(query, VideoFeed.class);	
	}
}
