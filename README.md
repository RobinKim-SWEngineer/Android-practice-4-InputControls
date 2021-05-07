# Android-practice-4-InputControls
Controlling user inputs with radio buttons and spinner

![Alt Text](https://github.com/RobinKim-SWEngineer/Images-for-document/blob/master/InputControls.gif)


With **Spinner** we can select one item from a set easily. For example, when we need users to provide contact information, we can put spinner so users can specify whether it's contact of home, mobile or work etc. 

## Related methods
 - Spinner needs an `adapter` object to assign an array to the spinner. Like an adapter in the real world, it connects the desired data to the spinner. For spinner we use `ArrayAdapter` class.
   ```
   public class ArrayAdapter .. {
       public static ArrayAdapter createFromResource( .. ) { .. }
       public void setDropDownViewResource( .. ) { .. }
   }
   ```
   > In above methods, 2 layout resources must be provided for spinner's appearance. Android provides predefined layouts in **android.R.layout** class for them, unless we make our own customized layout. 
   
 - To respond to user selection, use `onItemSelected(..)` method defined in `OnItemSelectedListener` interface. This interface is inside `Adapterview` class from which *spinner inherits* through `AbsSpinner` class.
   ```
   public abstract class AdapterView .. {
       public interface OnItemSelectedListener {
           void onItemSelected(AdapterView parent, int position, ..); 
           void onNothingSelected( .. );
       }
       public Object getItemAtPosition(int position) { .. }
   }
   ```
   > Implement getItemAtPosition() method inside OnItemSelected() with system-passed position to get the selected data.
