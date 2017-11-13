package neosoft.training.neostore.data.remote;

/**
 * Created by webwerks on 9/11/17.
 */

public class ApiUtils {

    private ApiUtils(){

    }

    public static final String BASE_URL="http://staging.php-dev.in:8844/trainingapp/api/";

    public static APIService getAPIService(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);

    }


}
