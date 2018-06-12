package com.example.user.food;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AmbilDataJSON {

   public String passClosestPathJSON(String reqUrl) {

        // String response = null;
        StringBuilder resultStr = new StringBuilder();
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null){
                resultStr.append(line);
            }
        } catch (Exception e) {
            Log.e("yee", "msg", e);
            // Log.e("yee","failed my dude");
        } finally {
            //ye
        }
        return resultStr.toString();
    }

    public int[] getData() {
        int[] result = {0,0,0,0};

        String isi1=passClosestPathJSON("http://104.215.190.135/getUltrasonic");
        String isi2=passClosestPathJSON("http://104.215.190.135/getLoadcell");
        String isi3 =passClosestPathJSON("http://104.215.190.135/getDataInfrared");

        try {
            JSONArray jsonArray = new JSONArray(isi1);
            Double percent_ultra = jsonArray.getJSONObject(0).getDouble("percent_tb_ultra");
            result[0] = percent_ultra.intValue();
            jsonArray = new JSONArray(isi2);
            Double weight_load = jsonArray.getJSONObject(0).getDouble("weight_tb_load");
            result[1] = weight_load.intValue();
            jsonArray = new JSONArray(isi3);
            Double percent_infra = jsonArray.getJSONObject(0).getDouble("percent_tb_infra");
            result[2] = percent_infra.intValue();
            Double high_infra = jsonArray.getJSONObject(0).getDouble("total_tb_infra");
            result[3] = high_infra.intValue();

            Log.d("result", String.valueOf(result));
            Log.d("yee", high_infra.toString());
        } catch (JSONException e) {
            Log.e("e", "error", e);
        }

        return result;
    }
}
