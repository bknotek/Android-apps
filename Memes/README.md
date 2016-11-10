#READ ME#

****************UPDATE*******************
-This section goes over the updates for assignment_3
-The details that remained from assignment_2 are under
 this section

Modifications:

-Now when a list item is clicked in MemeListFragment
 an Intent is called that shows a close up of the MEME in
 MemeActivity's MemeFragment
-Before, the app would remain in MemeListFragment
 and bring up a dialog that showed the MEME close up
-I had trouble saving the dialogbox's instancestate when
 the device flipped so moving the MEME's closeup to 
 MemeFragment resolved that issue, and also helped 
 with the editing process that we were required to 
 add in assignment_3
 
 
Additions:

(Persistance)
-For persistence, I went with the SQLite Database
 that the book details.
-I chose SQLite over shared preference because
 of its flexibiliy and ability to munipulate data easy
-When the app launced my pre-made MEMEs are
 automatically added to the database
-Then the user is able to add their own MEMEs by
 simply creating the MEME
-Because all data is saved in the database, when
 the user relaunches the app all of the MEMEs
 they have created are still available



(Editing)
-The app handles all the editing possiblities in the 
 MemeFrgament
-When the user is viewing the close up of the MEME,
 the options menu includes an EDIT button
-When the EDIT button is selected MemeFragment
 goes into editing mode:
    - All text becomed editable 
    - Enter image URL button is added
    - DONE EDITING button is added
    - CREATE button disappears 
-When the DONE EDITING button is clicked, the data
 that the user has changed for that particular MEME 
 is modified in the database 
-When the user goes back to the list, the list item will have
  been edited



(Alernatives)
-Before the app handled all editing in the MemeFragment,
 I had a seperate Activity that allowed the user to modify the
 MEME
-This worked but seemed a bit unnecessary when I realized 
 all the editing could be done in the same activity the user views
 the close up of the MEME
-Now without the extra activity, the app is smother because it 
 has less to process when the edit button is selected 






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

