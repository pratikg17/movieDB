package gozero.com.moviedb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<POJO> arrayList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        arrayList = new ArrayList<>();
        listView =(ListView) findViewById(R.id.list_view);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("http://api.themoviedb.org/3/movie/popular?api_key=ed4b53075dfd1c0b419f9b2180562cbe");
            }
        });

    }


    class ReadJSON extends AsyncTask<String,Integer,String>
    {

        @Override
        protected String doInBackground(String... params) {


            return  readUrl(params[0]);

        }

        @Override
        protected void onPostExecute(String content) {

            try {
                JSONObject jsonObject = new JSONObject(content);
                JSONArray jsonArray =jsonObject.getJSONArray("results");

                for(int i =0;i<jsonArray.length();i++)
                {
                    JSONObject resultObject = jsonArray.getJSONObject(i);


                    arrayList.add(new POJO("http://image.tmdb.org/t/p/w500/"+resultObject.getString("poster_path")));



                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            CustomGrid adapter =new CustomGrid(getApplicationContext(),R.layout.single_grid,arrayList);
            listView.setAdapter(adapter);

        }
    }



    private  static String readUrl(String theUrl)
    {
        StringBuilder content =new StringBuilder();
        try{
            URL url = new URL(theUrl);

            URLConnection urlConnection =url.openConnection();

            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while((line=bufferedReader.readLine())!=null)
            {
                content.append(line + "\n");
            }

            bufferedReader.close();

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return  content.toString();
    }


}
