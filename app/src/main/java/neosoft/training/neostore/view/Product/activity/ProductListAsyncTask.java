package neosoft.training.neostore.view.Product.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import neosoft.training.neostore.common.base.LoginAsyncTask;
import neosoft.training.neostore.model.ProductListModel;
import neosoft.training.neostore.model.RegistrationModel;
import neosoft.training.neostore.view.Product.adapter.ProductListingAdapter;
import neosoft.training.neostore.view.login.activity.LoginActivity;

/**
 * Created by webwerks1 on 26/10/17.
 */

public class ProductListAsyncTask extends AsyncTask<String, String, String> {
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    private Context context;
    private ProductListingAdapter mCustomRecyclerAdapter;
    private String msgResponse;
    private int statusCode;
    private StringBuilder result = new StringBuilder("");
    private Map<String, Object> productData;
    private static final String TAG = ProductListAsyncTask.class.getSimpleName();

    public ProductListAsyncTask(Map<String, Object> productData, Context context) {
        this.productData = productData;
        this.context = context;

    }

    @Override
    protected String doInBackground(String... strings) {

        URL url = null;
        try {
            url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("GET");

            if (this.productData != null) {
                OutputStream outputStream = urlConnection.getOutputStream();
                OutputStreamWriter writer = new OutputStreamWriter(outputStream);
                writer.write(getQuery(productData));
                writer.flush();
                writer.close();
                outputStream.close();
            }
            urlConnection.connect();

            statusCode = urlConnection.getResponseCode();
            msgResponse = urlConnection.getResponseMessage();
            if (statusCode == 200) {
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                //response = inputStream.toString();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                Log.e(TAG, String.valueOf(statusCode) + " message" + result.toString());
            } else {
                Log.e(TAG, String.valueOf(statusCode));

            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return result.toString();

    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
        Log.e(TAG, "onPostExecute: " + aVoid);
        List<ProductListModel> data = new ArrayList<>();
        if (statusCode == 200) {
            try {
                JSONObject jsonObject = new JSONObject(aVoid);
                int statusid = jsonObject.optInt("status");
                JSONArray jsonArray = jsonObject.optJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject dataObject = jsonArray.getJSONObject(i);
                    ProductListModel productListModel = new ProductListModel();
                    productListModel.setId(dataObject.optInt("id"));
                    productListModel.setProduct_category_id(dataObject.optInt("product_category_id"));
                    productListModel.setName(dataObject.optString("name"));
                    productListModel.setProducer(dataObject.optString("producer"));
                    productListModel.setDescription(dataObject.optString("description"));
                    productListModel.setCost(dataObject.optInt("cost"));
                    productListModel.setRating(dataObject.optInt("rating"));
                    productListModel.setView_count(dataObject.optInt("view_count"));
                    productListModel.setCreated(dataObject.optInt("created"));
                    productListModel.setModified(dataObject.optInt("modified"));
                    productListModel.setProduct_images(dataObject.optString("product_images"));
                    data.add(productListModel);

                    Log.e("json", "" + statusid);

                    mRecyclerView.setHasFixedSize(true);
                    mLayoutManager = new LinearLayoutManager(context);
                    mRecyclerView.setLayoutManager(mLayoutManager);

                    //add divider in recycler view
                    DividerItemDecoration mDividerItemDecoration =
                            new DividerItemDecoration(mRecyclerView.getContext(),mLayoutManager.getOrientation());
                    mRecyclerView.addItemDecoration(mDividerItemDecoration);

                    mCustomRecyclerAdapter = new ProductListingAdapter(context,data);
                    mRecyclerView.setAdapter(mCustomRecyclerAdapter);

                }

            } catch (JSONException e) {

                e.printStackTrace();
            }
            Intent intent = new Intent(context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }
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
}
