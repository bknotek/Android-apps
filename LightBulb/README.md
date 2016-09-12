# README #

************************** LIGHTBULB APPLICATION****************************


**********************************CONTAINS***********************************

1 imageview for on/off lightbulb

1 imageview for on/off lightswitch(clicklistener)

2 layouts for vertical/horizaonal


*********************************PERFORMANCE********************************


The lightswitch imageview listens for the user to click, then the lightbulb imageview switches from on to off.


**********************************METHODS***********************************

-Uses getConfiguration, getResources, and setContentView to change layout when device is rotated.


-Uses onSavedInstanceState to save and restore the state of the two imageviews when the device is rotated:

- To save the state of the imageview,  I placed the lightbulb and l          lightswitch drawables into integer resource IDs so when the 
onSavedInstanceState method is called it collects which drawables are placed in the imageview at the time of the orientation change.  Then when orientation changes it uses the collected resource IDs and sets them back to their imageviews.