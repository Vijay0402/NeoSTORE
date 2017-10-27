package neosoft.training.neostore.common.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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
import java.util.Map;

import neosoft.training.neostore.model.RegistrationModel;
import neosoft.training.neostore.view.home.activity.HomeActivity;

/**
 * Created by webwerks1 on 25/10/17.
 */

public class LoginAsyncTask extends AsyncTask<String, Void, String> {
    private Context context;
    private String msgResponse;
    private int statusCode;
    private StringBuilder result = new StringBuilder("");
    Map<String, Object> loginData;
    private static final String TAG = LoginAsyncTask.class.getSimpleName();

    public LoginAsyncTask(Map<String, Object> loginData, Context context) {
        this.loginData = loginData;
        this.context = context;

    }

    @Override
    protected String doInBackground(String... strings) {

        URL url = null;
        try {
            url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content_Type", "application/form-data");
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Authorization", "someAuthString");

            if (this.loginData != null) {
                OutputStream outputStream = urlConnection.getOutputStream();
                OutputStreamWriter writer = new OutputStreamWriter(outputStream);
                writer.write(getQuery(loginData));
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
        try {
            if (statusCode == 200) {


                JSONObject jsonObject = new JSONObject(aVoid);
                int statusid = jsonObject.optInt("status");
                JSONObject dataObject = jsonObject.optJSONObject("data");
                RegistrationModel sampleModel = new RegistrationModel();

                sampleModel.setId(dataObject.optInt("id"));
                sampleModel.setRole_id(dataObject.optInt("role_id"));
                sampleModel.setFirst_name(dataObject.optString("first_name"));
                sampleModel.setLast_name(dataObject.optString("last_name"));
                sampleModel.setEmail(dataObject.optString("email"));
                sampleModel.setUsername(dataObject.optString("username"));
                sampleModel.setProfile_pic(dataObject.optString("profile_pic"));
                sampleModel.setCountry_id(dataObject.optString("country_id"));
                sampleModel.setGender(dataObject.optString("gender"));
                sampleModel.setPhone_no(dataObject.optInt("phone_no"));
                sampleModel.setDob(dataObject.optString("dob"));
                sampleModel.setIs_active(dataObject.optBoolean("is_active"));
                sampleModel.setCreated(dataObject.optString("created"));
                sampleModel.setModified(dataObject.optString("modified"));
                sampleModel.setAccess_token(dataObject.optString("access_token"));

                SharedPreferences sharedPreferences = context.getSharedPreferences("Login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Username", String.valueOf(sampleModel.getUsername()));
                editor.putString("Email",String.valueOf(sampleModel.getEmail()));
                editor.commit();

                //to pass navigation textview
                Intent intent = new Intent(context, HomeActivity.class);
               /* intent.putExtra("username",sampleModel.getUsername());
                intent.putExtra("email", sampleModel.getEmail());*/
                context.startActivity(intent);
                ((Activity) context).finish();


                Log.e("json", "" + statusid);

            } else {
                Toast.makeText(context, "Email or password is wrong. try again", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
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
