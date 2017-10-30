package neosoft.training.neostore.common.base;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by webwerks1 on 30/10/17.
 */

public class BaseAsyncTask extends AsyncTask<Object, Object, Object> {
    onAsyncRequestComplete caller;
    Context context;
    String method = "GET";
    Map<String, Object> mapData;
    int statusCode;
    String msgResponse;
    private HttpURLConnection urlConnection;
    private static final String TAG = BaseAsyncTask.class.getSimpleName();
    private StringBuilder result = new StringBuilder("");

    public interface onAsyncRequestComplete {
        public void asyncResponse(Object response);
    }

    public BaseAsyncTask(Activity context, String method, Map<String, Object> mapData) {
        caller = (onAsyncRequestComplete) context;
        this.context = context;
        this.method = method;
        this.mapData = mapData;

    }

    public BaseAsyncTask(Context context, Map<String, Object> mapData) {
        caller = (onAsyncRequestComplete) context;
        this.context = context;
        this.mapData = mapData;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        String address = objects[0].toString();
        if (method == "GET")
                return get(address);
        if (method == "POST")
                return post(address);
        return null;

    }

    private Object post(String address) {
       try {
           URL url = new URL(address);
           urlConnection = (HttpURLConnection) url.openConnection();
           urlConnection.setDoInput(true);
           urlConnection.setDoOutput(true);
           urlConnection.setRequestProperty("Content_Type", "application/form-data");
           urlConnection.setRequestMethod("POST");
           urlConnection.setRequestProperty("Authorization", "someAuthString");

           if (this.mapData != null) {
               OutputStream outputStream = urlConnection.getOutputStream();
               OutputStreamWriter writer = new OutputStreamWriter(outputStream);
               writer.write(getQuery(mapData));
               writer.flush();
               writer.close();
               outputStream.close();
           }
           urlConnection.connect();

           statusCode = urlConnection.getResponseCode();
           msgResponse = urlConnection.getResponseMessage();
           return read(statusCode);
       }
       catch (Exception e){
           e.printStackTrace();
       }
       return null;
    }


    private Object get(String address) {
        try {
            StringBuilder stringBuilder = new StringBuilder(address);
            stringBuilder.append("?" + getQuery(mapData));
            URL url = new URL(stringBuilder.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            statusCode = urlConnection.getResponseCode();
            msgResponse = urlConnection.getResponseMessage();
            return read(statusCode);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private Object read(int statusCode) {
        BufferedReader reader;
        try {
            if (statusCode == 200) {
                reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));
            }
            //response = inputStream.toString();
            String line = "";
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            Log.e(TAG, String.valueOf(statusCode) + " message" + result.toString());
            return result.toString();
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }

       return null;

    }

    private String getQuery(Map<String, Object> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (Map.Entry<String, Object> pair : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue().toString(), "UTF-8"));
        }

        return result.toString();

    }

    @Override
    protected void onPostExecute(Object object) {
        caller.asyncResponse(object);
    }


}