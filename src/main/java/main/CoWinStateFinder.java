package main;

import model.StateList;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class CoWinStateFinder extends BaseClient {
    public static void main(String arg[]) {
        System.out.println("Calling to get the states list ");
        CoWinStateFinder cWin = new CoWinStateFinder();
        try {
            cWin.states();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void states() throws IOException {
        check();
    }

    public void check() throws IOException {
        Call<StateList> call = apiService.states("en_US");

        Response<StateList> response = call.execute();
        StateList stateList = response.body();
        stateList.getStates().forEach(state -> {

            System.out.println("Found State Id = " + state.getState_id() + ", State Name = " + state.getState_name() +
                    ", State name_1 = " + state.getState_name_1());

        });
    }
}
