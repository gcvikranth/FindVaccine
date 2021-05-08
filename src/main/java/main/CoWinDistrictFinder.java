package main;

import model.DistrictList;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class CoWinDistrictFinder extends BaseClient {
    public static final String KARNATAKA_STATE_CODE = "16";

    public static void main(String arg[]) {

        String stateCode = (arg.length == 1) ? arg[0] : KARNATAKA_STATE_CODE;
        System.out.println("Calling to get the districts list for stateCode=" + stateCode);
        CoWinDistrictFinder cWin = new CoWinDistrictFinder();
        try {
            cWin.districts(stateCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void districts(String state_id) throws IOException {
        check(state_id);
    }

    public void check(String stateId) throws IOException {
        Call<DistrictList> call = apiService.districts("en_US", stateId);

        Response<DistrictList> response = call.execute();
        DistrictList districtList = response.body();
        districtList.getDistricts().forEach(district -> {

            System.out.println("Found District Id = " + district.getDistrict_id() + ", District Name = " + district.getDistrict_name() +
                    ", State id = " + district.getState_id());

        });
    }
}
