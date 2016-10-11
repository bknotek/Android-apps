#READ ME#

****************DESIGN********************

-2 Activities that hold their own fragment

-Both activies extend the SingleFrameActiviy that holds
   the abstract method createFragment() and implements
   the FragmentManager when onCreate() is called. (This
   keeps me from having to instantiate FragmentManger
   in both of my activities )

-Fragments: MemeFragment and MemeListFragment

-MemeFragment

  -This is where the user cutomizes and creates and
  meme.

  Contains:

  *TextEdit to Enter Meme's title

  *ImageView that holds the Meme's Image

  *2 TextEdits for the user to enter the Meme's top
    and bottom text

  *2 Buttons

    - a Button  that brings up a dialogbox onClick() to enter
      image url.  It then uses Picasso to load the image and 
      send it to the ImageView target.

    -a Create Button that gathers all modifications, instatiates
     a meme object from the Meme class, and adds it 
     to the AllMemes list

-MemeListFragment

  -This is where all the Meme objects in the AllMemes 
    List are added to a RecyclerView

  -I went at the RecyclerView similar to the way the book
   does when it uses it in CriminalIntent since the book
   does a good job at explaining what everything does.

  Contains:

   *MemeHolder Class:

         -This holds Meme's image,text, and title widgets

         -Then it binds all the data that the user entered
           to each widget

         -I have also incleded an onClick method that brings
           up a DialogBox that contains a close up of the
           meme the user selected.

         -Used stackoverflow for help with AlertDialog.Builder

   *MemeAdapter Clsas:

          -This instantiates a new MemeHolder for
            each Meme object that has been cretead, and then
            adds it to the inflated list layout.

          -This is notified of all changes everytime the UI
            is updated.

   *onCreateView Method

           -Gets RecyclerViews references, cretates new 
             LinearLayout, then updatesUI(MemeAdapter)

   *onCreateOptionsMenu

           -Creates my custom option menu that has a plus
             sign item

           -When the item is pressed an Inent is instantiated
             that sends the user to the MemeActivity where
             they can create a meme

   
************ ALTERNATIVES****************

There are only a couple things that I planned on doing
 differently when I initially started this assignment.


-At first I didn't have a dialog box that came up to enter
 the image's URL

-It was just a TextEdit that the user clicked on and started
 typing 

-This turned out to be a design flaw becasue of the legth
 of most URLs

-It didn't give the user enough room to type the full URL

-It woud go to a new line and become hard to see


-The other differnce had to do with the dialog box that
 shows the close up of the meme when the user selects
 one from the list.

-When I began I had the program make a new intent and 
 go to a whole new Activity that would show the close up

-This became difficult because I needed to carry the meme's
 image over to the new activity with the putExtra method,
 and the only way I could do this was converting it to a 
 bitmap and including it with the intent

-Unfortunately the image would show up in the new Activity
 small with low resolution

-Bringing up a dialogbox with the close up solved my issue and
 is a bit faster than switching to a new Activity


**************PROBLEMS******************

I managed to meet all requirements in the rubric, but there
is one problem I could never solve:


I could never get the program to save the state of the closeup
dialog box, so when the orientaion changes the dialogbox
disappears and the user has to select the meme again.

The program throws a WindowLeaked error

