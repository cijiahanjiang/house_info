package edu.bupt.crawler.house.info.crawler;

import com.alibaba.fastjson.JSON;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * liujun 2018/5/4 下午4:44
 * liujun36@maoyan.com
 **/
@RestController
@RequestMapping("tianjin")
public class TianjinInfoOfLianjia {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static String regex = "<!--[\\s\\S]*?-->";
    private static String regex1 = "<!--\\w+-->";

    private static String baseUrl = "https://m.lianjia.com/tj/ershoufang/index/nankai/a2mw1pg";

    @RequestMapping("lianjia")
    public void test(String[] args) throws Exception {

        for (int i = 11; i < 100; i++) {
            HttpClient client = new HttpClient();
            HttpMethod getMethod = new GetMethod(baseUrl + i);
            client.executeMethod(getMethod);
            String webContent = getMethod.getResponseBodyAsString();
            Document document = Jsoup.parse(webContent);
            Elements elements = document.body().getElementsByClass("mod_cont");
            Elements lis = elements.get(0).getElementsByTag("ul").get(0)
                    .getElementsByTag("li");

            for (Element s : lis) {
                if (s.getElementsByClass("item_main").size() > 0) {
                    Map<String, Object> map = new HashMap<>();
                    String ids = s.getElementsByTag("a").attr("href").split("/")[3];
                    String id = "";
                    if (ids != null) {
                        System.out.println(ids);
                        id = ids.split("\\.")[0];
                    }
                    map.put("id", id);
                    String[] info = s.getElementsByClass("item_other text_cut").get(0).text().split("/");
                    map.put("layout", info[0]);
                    map.put("area", info[1].split("m")[0]);
                    map.put("position", info[3]);
                    System.out.println();
                    map.put("unit_price", s.getElementsByClass("unit_price").get(0).text().split("元")[0]);
                    map.put("total_price", s.getElementsByClass("price_total").get(0).getElementsByTag("em").get(0).text());
                    System.out.println(JSON.toJSONString(map));

                    jdbcTemplate.update("insert into tianjin_nankai_house_info VALUES (?,?,?,?,?,?,?)", new Object[]{
                            map.get("id"),
                            map.get("position"),
                            map.get("unit_price"),
                            map.get("total_price"),
                            map.get("area"),
                            map.get("layout"),
                            LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    });

                }
            }
        }
    }
}
