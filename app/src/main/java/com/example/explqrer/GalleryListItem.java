package com.example.explqrer;

import android.graphics.Bitmap;

/**
 * Link: https://www.androidauthority.com/how-to-build-an-image-gallery-app-718976/
 * Author: Adam Sinicki
 * This class defines GalleryListItem objects they have images
 * imageId, is the ID of the actual image
 */
public class GalleryListItem {

    private Bitmap image;
    private String hashCode;
    private String codeDescription;
    private int codePts;

    /**
     * This gets the ID of the image stored in the ImageListItem object
     * @return imageId
     *      Which is the ID stored in the ImageListItem of an image
     */
    public Bitmap getImage(){
        return image;
    }

    /**
     * This sets the image ID into the ImageListItem object
     * @param bitmapImage
     *      Which is the URL of an image
     */
    public void setImage(Bitmap bitmapImage){
        this.image = bitmapImage;
    }

    public String getHashCode(){
       return this.hashCode;
    }

    public void setHashCode(String hashCode){
        this.hashCode = hashCode;
    }

    public String getCodeDescription() {
        return codeDescription;
    }

    public void setCodeDescription(String codeDescription) {
        this.codeDescription = codeDescription;
    }

    public int getCodePts() {
        return codePts;
    }

    public void setCodePts(int codePts) {
        this.codePts = codePts;
    }


}
