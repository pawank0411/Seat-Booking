package Models;

import android.content.Context;

import java.io.IOException;

/**
 * Created by pkumar on 5/7/18.
 */

public class AppStatus {
    Context context;

    public AppStatus(Context context){
        this.context=context;
    }

    public boolean isOnline(){
        Runtime runtime=Runtime.getRuntime();
        try{
            Process ipProcess=runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue=ipProcess.waitFor();
            return (exitValue==0);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
