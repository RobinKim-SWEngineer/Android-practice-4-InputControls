# Android-practice-4-InputControls
Controlling user inputs with spinner and radio button.

![Alt Text](https://github.com/RobinKim-SWEngineer/Images-for-document/blob/master/InputControls.gif)


With **Spinner** we can select one item from a set easily. For example, when we need users to provide contact information, we can put spinner so users can specify whether it's contact of home, mobile or work etc. 

## Related methods
 - Spinner needs an `Adapter` object to assign an array to the spinner. Like an adapter in the real world, it connects the desired data to the spinner. For spinner we use `ArrayAdapter` class.
   ```
   public class ArrayAdapter .. {
       public static ArrayAdapter createFromResource( .., int textArrayResId, .. ) { .. }
       public void setDropDownViewResource( .. ) { .. }
   }
   ```
   > In above methods, 2 layout resources must be provided for spinner's appearance. Android provides predefined layouts in **android.R.layout** class for them, unless we make our own customized layout. 
   
   After creation of an adapter with the content, set this adapter as the `Adapter` of spinner view throguh `setAdapter(Adapter T)` in AdapterView class. `Spinner` class inherits from `AbsSpinner` class which again, inherits from `AdapterView`.
   
 - To respond to user selection, use `onItemSelected(..)` method defined in `OnItemSelectedListener` interface. This interface is inside `Adapterview` class.
   ```
   public abstract class AdapterView .. {
       public interface OnItemSelectedListener {
           void onItemSelected(AdapterView parent, int position, ..); 
           void onNothingSelected( .. );
       }
       public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) { .. }
       public abstract void setAdapter(Adapter T);
       public Object getItemAtPosition(int position) { .. }
   }
   ```
   Implement `getItemAtPosition()` method inside OnItemSelected() upon system-passed AdapterView object with position to get the selected data.
   ```
   public void onItemSelected(AdapterView parent, int position, ..) {
       String spinnerMessage = parent.getItemAtPosition(position).toString();
   }
   ```
   > Above, the system-passed `AdapterView` parent parameter is the AdapterView where the user selection happened, which is in this case, the spinner object.
   
For more detail implementation of **Spinner** for each step please refer to the attached example.
