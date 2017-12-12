package jfreerails.client.view;



public class BuildTrainController {

    private static String[] trainDesc; //Trains descriptions

    private static String[] trainImgs; //Trains image path
    
    //the call to the dialog
    
    public static Integer getBuildTrain( Object[] list ) {
        BuildTrainView  view = new BuildTrainView( list );
        view.show();
        Integer  selected = (Integer)view.getSelectedValue();
        view.dispose();
        return selected;
    }
    
    //initialize the string arrays for the trains
    
    public static void setTrains( String[] text, String[] images ) {
        trainDesc = text;
        trainImgs = images;
    }
    
    //methods for the View to get the data
    
    public static String getText( Object value, boolean isSelected ) {
        return "<html><body>" + ( isSelected ? "<strong>" : "" ) + trainDesc[ ( (Integer)value ).intValue() ] + ( isSelected ? "</strong>" : "" ) + "</body></html>";
    }
    
    public static String getImg( Object value, boolean isSelected ) {
        return trainImgs[ ( (Integer)value ).intValue() ];
    }
}
