import java.io.IOException;
import java.net.URLEncoder;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class test {
	public String crawlFunc(String input) throws IOException {
		String val=URLEncoder.encode(input, "UTF-8");// ÀÎÄÚµù

		Connection.Response response = Jsoup.connect("https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query="+val)
                .method(Connection.Method.GET)
                .execute();
		
		Document googleDocument = response.parse();
		
		Element thumb = googleDocument.select("a[class=thumb]").first();
		if(thumb==null) {
			return "";
		}
		
		Elements img=thumb.select("img");
		if(img==null) {
			return "";
		} 
		
		String src = img.attr("src");
		if(src==null) {
			return "";
		}
	
		return src;
	}
}
