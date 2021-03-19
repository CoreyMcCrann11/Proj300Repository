
package edu.paraicmcdonagh.discoverypage;

import android.content.Intent;
import android.view.View;

public class SMSLog extends MainActivity {

    public void doDiscovery(View view) {
        Intent DiscoveryPage = new Intent(view.getContext(), MainActivity.class);
        startActivity(DiscoveryPage);
    }
    public void doTimerPage(View view) {
        Intent StepTimer = new Intent( this, StepTimer.class);
        startActivity(StepTimer);
    }
    public void doBMIPage(View view) {
        Intent BMIMain = new Intent(view.getContext(), BMIMain.class);
        startActivity(BMIMain);
    }
    public void doProfilePage(View view) {
        //  Intent ProfilePage = new Intent(view.getContext(), ProfilePage.class);
        //startActivity(ProfilePage);
        
    }
}