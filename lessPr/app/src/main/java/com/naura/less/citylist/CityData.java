package com.naura.less.citylist;

import android.graphics.Bitmap;
import android.media.Image;

import com.naura.less.R;
import com.naura.less.theatherdata.TheatherData;

import java.io.Serializable;
import java.util.List;

public class CityData implements Serializable {
    private String name;
  //  private List<TheatherData> theatherdays;
    private Bitmap verticalImage;
    private Bitmap horisontalImage;
    private Bitmap smallImage;

    public Bitmap getSmallImage() {
        return smallImage;
    }

    public CityData(String name, Bitmap verticalImage, Bitmap horisontalImage, Bitmap smallImage) {
        this.name = name;
        this.verticalImage = verticalImage;
        this.horisontalImage = horisontalImage;
       // this.theatherdays=theatherdays;
        this.smallImage=smallImage;
    }

    public String getName() {
        return name;
    }

 //   public List<TheatherData> getTheatherdays() {
     //   return theatherdays;
  //  }

    public Bitmap getVerticalImage() {
        return verticalImage;
    }

    public Bitmap getHorisontalImage() {
        return horisontalImage;
    }
}
