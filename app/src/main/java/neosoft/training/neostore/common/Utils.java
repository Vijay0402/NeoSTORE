package neosoft.training.neostore.common;

import android.graphics.SweepGradient;

/**
 * Created by webwerks1 on 2/11/17.
 */

public class Utils {

    public  String categoryFromId(int id){
        String category = "Category -";

        switch (id){
            case 1:return category+"Tables";
            case 2:return category+"Chairs";
            case 3:return category+"Sofas";
            case 4:return category+"Cupboards";
        }

        return category;
    }

}
