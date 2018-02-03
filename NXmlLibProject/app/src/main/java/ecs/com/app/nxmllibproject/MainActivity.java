package ecs.com.app.nxmllibproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import ecs.com.app.adapter.NewsAdapter;
import ecs.com.app.model.Item;
import ecs.com.app.rest.ApiClient;
import ecs.com.app.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.news_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiservice = ApiClient.getClient().create(ApiInterface.class);


        Call<String> call = apiservice.getChannel();
        // String url = call.request().url().toString();
        //System.out.print("URL-> " + url);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //  String channel = response.body();

                String xml = response.body();
                System.out.print("XML---->" + xml);

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = null;
                try {
                    dBuilder = dbFactory.newDocumentBuilder();

                    Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
                    // Document doc = dBuilder.parse(xml);

                    doc.getDocumentElement().normalize();

                    System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

                    NodeList nList = doc.getElementsByTagName("item");
                    List<Item> itemList = new ArrayList<>();
                    for (int i = 0; i < nList.getLength(); i++) {
                        Node nNode = nList.item(i);
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Item item = new Item();

                            Element eElement = (Element) nNode;
                            String title = eElement.getElementsByTagName("title").item(0).getTextContent();
                            String description = eElement.getElementsByTagName("description").item(0).getTextContent();
                            String link = eElement.getElementsByTagName("link").item(0).getTextContent();
                            String guid = eElement.getElementsByTagName("guid").item(0).getTextContent();
                            String pubDate = eElement.getElementsByTagName("pubDate").item(0).getTextContent();
                            item.setTitle(title);
                            item.setDescription(description);
                            item.setLink(link);
                            item.setGuid(guid);
                            item.setPubDate(pubDate);
                            itemList.add(item);
                        }
                    }

                    NewsAdapter newsAdapter = new NewsAdapter(itemList, R.layout.list_item_news, getApplicationContext());
                    recyclerView.setAdapter(newsAdapter);

                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });


    }
}
