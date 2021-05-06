package main;

import model.Mobile;
import model.Txn;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class TestClient extends BaseClient {
  public static void main(String sp[]) {

      TestClient testClient = new TestClient();
      Call<Txn> call = testClient.apiService.generateOTP("en_US", new Mobile("9XXX33X3X1"));
      try {
          Response<Txn> response = call.execute();
          if (response.isSuccessful()) {
              Txn txn = response.body();
              System.out.println("txn" + txn.getTrxId());
          }

      } catch (IOException e) {
          e.printStackTrace();

      }
  }
}
