package zli.sb.slideshow;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class PictureService extends Service {
    

    public PictureService() {
    }

    // Binder given to clients
    private final IBinder binder = new CalcBinder();

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class CalcBinder extends Binder {
        PictureService getService() {
            // Return this instance of LocalService so clients can call public methods
            return PictureService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }



}
