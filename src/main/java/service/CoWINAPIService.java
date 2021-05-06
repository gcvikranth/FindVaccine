package service;

import model.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface CoWINAPIService {
  //@Headers("Content-Type: application/json")
  @Headers({"Cache-Control: no-cache",
          "Cache-Control: no-store",
          "Cache-Control: no-transform",
          "Content-Type: application/json",
          "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36"
  })
  @GET("v2/admin/location/states")
  Call<StateList> states(@Header("Accept-Language") String lang);

  @Headers({"Cache-Control: no-cache",
          "Cache-Control: no-store",
          "Cache-Control: no-transform",
          "Content-Type: application/json",
          "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36"
  })
  @GET("v2/admin/location/districts/{state_id}")
  Call<DistrictList> districts(@Header("Accept-Language") String lang, @Path("state_id") String state_id );


  @Headers({"Cache-Control: no-cache",
          "Cache-Control: no-store",
          "Cache-Control: no-transform",
          "Content-Type: application/json",
          "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36"
  })
  @GET("v2/appointment/sessions/public/findByDistrict")
  Call<SessionList> session(@Header("Accept-Language") String lang, @QueryMap Map<String,String> options);


  @Headers({"Cache-Control: no-cache",
          "Cache-Control: no-store",
          "Cache-Control: no-transform",
          "Content-Type: application/json",
          "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36",
          "Content-Type: application/json"
  })
  @POST("v2/auth/public/generateOTP/")
  Call<Txn> generateOTP(@Header("Accept-Language") String lang, @Body Mobile mobile);
}

