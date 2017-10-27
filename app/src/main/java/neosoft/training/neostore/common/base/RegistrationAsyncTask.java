package neosoft.training.neostore.common.base;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

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
import neosoft.training.neostore.view.login.activity.LoginActivity;


public class RegistrationAsyncTask extends AsyncTask<String,Void,String>{
    String msgResponse;
   
    Map<String, Object> userData;
    Context context;
    private static final String TAG = RegistrationAsyncTask.class.getSimpleName();

    public RegistrationAsyncTask(Map<String, Object> userData, Context context) {
       this.userData=userData;
       this.context=context;

    }


    @Override
    protected String doInBackground(String... strings) {
        StringBuilder result = null;
        int statusCode;
        URL url= null;
        try {
            url = new URL(strings[0]);
            HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content_Type","application/form-data");
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Authorization","someAuthString");

            if (this.userData != null) {
                OutputStream outputStream = urlConnection.getOutputStream();
                OutputStreamWriter writer = new OutputStreamWriter(outputStream);
                writer.write(getQuery(userData));
                writer.flush();
                writer.close();
                outputStream.close();
            }
            urlConnection.connect();

            statusCode = urlConnection.getResponseCode();
            msgResponse=urlConnection.getResponseMessage();
            if (statusCode ==  200) {
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
//                response = inputStream.toString();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                result = new StringBuilder();
                String line;
                while((line = reader.readLine()) != null) {
                    result.append(line);
                }

                Log.e(TAG,String.valueOf(statusCode)+" message"+ result.toString() );
            }
            else {
                Log.e(TAG,String.valueOf(statusCode) );

            }
        }
        catch (Exception e) {
            Log.e(TAG,e.getMessage() );
        }
        return result.toString();
    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
        Log.e(TAG, "onPostExecute: "+aVoid);
        try {
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

            Log.e("json",""+statusid);

        }
        catch (JSONException e){

            e.printStackTrace();
        }
        Intent intent=new Intent(context,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);

    }


    private String getQuery(Map<String,Object> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (Map.Entry<String,Object> pair : params.entrySet())
        {
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
