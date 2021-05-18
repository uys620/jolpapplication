package com.example.myapplication;

public class searchpoint {
    double x[],y[];
    double min;
    double longitude;
    double latitude;
    int min_value;

    public searchpoint(double a,double b){
        x=new double[]{0,37.515367, 37.514174, 37.516985, 37.507232, 37.508645, 37.510245, 37.513024, 37.513552, 37.514216, 37.505629, 37.508370, 37.509340, 37.498976, 37.500755, 37.502559, 37.504457, 37.506789, 37.507708, 37.508891, 37.502950, 37.505376, 37.493988, 37.495793, 37.498423, 37.500466, 37.503002, 37.505181, 37.492339, 37.494181, 37.496258, 37.498846, 37.500938, 37.490910, 37.494477};
        y=new double[]{0,127.035321, 127.042374, 127.051236, 127.03379, 127.0385, 127.043875, 127.053253, 127.056364, 127.060301, 127.048463, 127.055802, 127.058591, 127.031088, 127.036957, 127.042751, 127.049006, 127.056699, 127.059424, 127.063233, 127.049749, 127.057302, 127.033473, 127.039288, 127.044738, 127.050832, 127.058632, 127.065101, 127.040908, 127.046754, 127.052789, 127.060912, 127.0674, 127.055401, 127.063255};
        min=Integer.MAX_VALUE;
        min_value=Integer.MAX_VALUE;
        longitude = 0;
        latitude = 0;
        for(int i=0;i<35;i++){
            if(min>(x[i]-a)*(x[i]-a)+(y[i]-b)*(y[i]-b)){
                min=(x[i]-a)*(x[i]-a)+(y[i]-b)*(y[i]-b);
                min_value=i;
            }
        }
    }

    public int getMin_Value(){
        min=Integer.MAX_VALUE;
        double a = this.latitude;
        double b = this.longitude;
        for(int i=0;i<35;i++){
            if(min>(x[i]-a)*(x[i]-a)+(y[i]-b)*(y[i]-b)){
                min=(x[i]-a)*(x[i]-a)+(y[i]-b)*(y[i]-b);
                this.min_value=i;
            }
        }
        return this.min_value;
    }


    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
