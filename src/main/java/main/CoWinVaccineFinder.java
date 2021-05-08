package main;

import model.Mobile;
import model.SessionList;
import model.Txn;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class CoWinVaccineFinder extends BaseClient {
public static final String BBMP_DIST_CODE="294";
private boolean isSlotFound=false;
private static final int ONE_MINUTE=60000;
private static final int TWO_MINUTE=60000*2;
private static final int FIVE_SECS=1000*5;
private static final int TEN_SECS=1000*10;
private int waitTimeMs=TWO_MINUTE;//

  public static void main(String arg[]){
    System.out.println("Starting to Poll CoWin to find vaccination slots for age 18-44 slots ");
    CoWinVaccineFinder cWin=new CoWinVaccineFinder();

    String disrictCode =(arg.length==2)?arg[1]:BBMP_DIST_CODE;
    try {
      cWin.pollForStatus(disrictCode,arg[0]);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public void pollForStatus(String districtId, String mobileNumber) throws IOException {
    int attempt=0;
    while (true){
      System.out.println(simpleDateFormat.format(new Date())+"  Polling CoWin attempt "+(++attempt));
      check(districtId,mobileNumber);
      try {
        //Number of seconds polling - Set to 1 sec
        Thread.sleep(waitTimeMs);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
  }
  public void check(String districtId, String mobileNumber)  {

    Map<String,String> m=new HashMap<>();
    String[] dates={"08-05-2021","09-05-2021","10-05-2021","11-05-2021","12-05-2021","13-05-2021","14-05-2021"};
    m.put("district_id",districtId);//
    for(String date:dates){
      m.put("date",date);
      Call<SessionList> call = apiService.session("en_US",m);

      Response<SessionList> response= null;
      try {
        response = call.execute();
      } catch (SocketTimeoutException e) {
        System.out.println("Socket timeout exception");
        e.printStackTrace();
        try {
          call = apiService.session("en_US",m);
          response = call.execute();
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      SessionList sessionList = response.body();
      sessionList.getSessions().forEach(session ->{
      if(!"45".equals(session.getMin_age_limit())) {
        //if(true) {
      //  if(!"COVISHIELD".equals(session.getVaccine())){

          System.out.println("* Found Slot *  "+ session.getName() + " "+session.getPincode()+" "+session.getVaccine()+" "
            + session.getMin_age_limit() + " " + session.getAvailable_capacity()
            + " " + session.getDate() );
          System.out.println("model.Session from: "+session.getFrom() +" to: "+session.getTo()+" slots "+session.getSlots().toString());
          if(session.getAvailable_capacity()>1 &&!isSlotFound) {
            waitTimeMs=FIVE_SECS;
            isSlotFound=true;
            System.out.println(" *** Generating OTP ***");
            //generateCoWinOTP(mobileNumber);
          }else{
            isSlotFound=false;
            waitTimeMs=TWO_MINUTE;
          }
        }
      });


    }


  }

  private void generateCoWinOTP(String mobileNumber )  {
    Mobile m= new Mobile();
    m.setMobile(mobileNumber);
    Call<Txn> call = apiService.generateOTP("en_US",m);
    Response<Txn> txn= null;
    try {
      txn = call.execute();
    } catch (IOException e) {
      try {
        apiService.generateOTP("en_US",m).execute();
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
      e.printStackTrace();
    }

  }


}
