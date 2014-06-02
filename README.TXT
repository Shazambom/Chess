PROJECT TITLE: Chess
PURPOSE OF PROJECT: Its a game of Chess I mean.. What more could you ever want!?!?!
VERSION and DATE: v1.0, 6/1/14  

HOW TO CREATE THIS PROJECT: Well if you want to create this project from scratch... well I started with the 
abstract class Piece and then create the Board class and the Piece doublearray that went with it. 
Then I created all the extensions of Piece which are all the other pieces. I then overwrote getValidMove() 
and the constructor in each child class. At that point I set each getValidMove() to return true, then I set 
up the graphics and buttons and clickListener and the Main class so I could see the bugs as they happen. 
I also added the Location class cause I figured Location objects would come in handy and they really did, 
I would highly recomend using them, keeps a lot of things tidy and neat. 

I then started to implement all the rules I knew about chess and slowly started changing the 
getValidMove() methods to what they should be for each respective piece. It only takes a short while 
for a few pieces. 

Once I got all of the pieces moving properly I went to work on Castling, Ascending Pawns to Queens, and 
Enpassant.

Then I finally got to Check and Checkmate... Jesus I didn't think it would be THAT hard but I struggled 
with it. There are a LOT of things that can go wrong at this stage. Try and just think of it as simply as 
possible and remember what Check and Checkmate actually mean. Don't forget that you can actually block with
other pieces to get out of Check and Checkmate.
 
[BTW if you wanted the definitions of Check and Checkmate here you go 
CHECK: A state where the King is in danger of being taken by an opponent. (so if it is possible 
that any opposing piece getValidMove() is true at the King's position then the King is in Check) 
CHECKMATE: The state where the King is in check AND the King cannot move anywhere without still being in 
check and another piece cannot block the Piece that is currently endangering the King.]

I hope that helps with Check and Checkmate. Those states are pretty damn tricky.

After I figured all of that out I was pretty much done. Nothing much more to do. I don't think there is 
anyways. Look through the documentation to see what is what and see how everything works. There are short
explanations in there. The Piece class is a pleathora of knowledge. The Board class is a bit of a mess but
comments in there might explain some things.

COMMON BUGS:
The King doesn't check if its in check properly: 
    see above
    
The King can't tell if it is in Checkmate properly: 
    see above
    
You thought what you wanted to be the White player actually is Black: 
    ): yup turns out coding grids are weird. Also when setting and updating the images on the buttons 
    you have to set it up like.. opposite... like this:
    for(int i = 0; i < 8; i++){
        for(int j = 0; j < 8; j++){
            if(board[j][i] != null){
                Main.buttonGrid[j][i].setIcon(board[j][i].getImg());
            }
            else Main.buttonGrid[j][i].setIcon(null);
        }
    }
    yeah its weird. Instead of thinking like its [x][y] its [y][x] but only for images! for everything else 
    its [x][y] in the grid plane.
    
Castling doesn't work properly: 
    Yeah Look up how castling works and which spaces the king and rook move to. Make sure you are thinking 
    about how kingside castle and queenside castle work. Both can be easily differentiated by one if 
    statement and they work exactly the same for both Black and White with different Y coordinates.
    
ClickListener needs to remember which button is being clicked: 
    well no. It simply needs to remember the 
    x,y coordinates of the button being pressed. To do this simply modify the constructor of your 
    implemented ActionListener and make sure to put super(); before anything else in your new constructor
    and poof you can have your new ActionListener actually remember where it is in the button grid. Magic.
    
Bishops, Queens, Rooks, and Pawns(on their first double move) jump over things! Its messing with Check and 
Checkmate testing: 
    Yeah this bug ugg I had no idea what was going on for so long. So yeah the fix is pretty easy because
    all you have to do is fix Bishops and Rooks and a Queen is like a Bishop-Rook so you really don't have
    to do much. The pawn is pretty easy fix too. Basically all you have to do is check every position
    between the move you want to make and the position of the piece and if there is a piece there or a 
    position isn't = to null then the move isn't possible. Thats it :D
    
I implemented this method in a child Piece class and I get a compiler error when I call it!?!?:
    Just declare it in the abstract Piece class and if it is a boolean returning method make sure to have
    it return false in the Piece class and overwrite it in the child class you so desire but not in other
    classes. Be sure not to make it an abstract method like getValidMove() is or else you will have to 
    overwrite it in every child class. (Unless that is what you want)
    
The turn boolean isn't switching properly:
    Look at the location of each time the boolean needs to be switched. Is it in the correct place?
    Did you write something like this?:
    if(turn) turn = false;
    else turn = true;
    consider using a count with a modulus opperation (the % sign) to check to see if it is even and odd and 
    have it increase by one each time a move is made and the board is updated. Make sure you have the turn
    change from true to false or false to true before each time the count is incremented.
    
The King can't seem to take the piece attacking it to get out of check:
    Okay this one is a little weird and hard to grasp but it makes sense once it is explained.
    The King thinks that once it moves to that position that it will still be in check due to that piece.
    SO what you need to do is basically show the king that in the future you won't be in check. So what 
    you do is you move the king in the board and don't forget to use the move(Location a) method on the 
    King (this also applies for blocking to get out of check, both are very similar in nature). Once you 
    have moved the King to its new possible position you check and see if any piece can take it and if 
    one cannot take it then the move is valid. This also applies to protected pieces (basically kills 3
    bugs with one stone if you will). Also once you have shown that the King can move there you have to 
    move him BACK or whatever other piece you were planning on moving to block or take. If you don't 
    this could cause all sorts of bugs like spawning additional pieces from nothing or it may do nothing
    just be careful. 
    
StackOverflow Errors hawhat:
    You didn't return the boolean when you should have or fast enough OR some sneaky recursion is going on
    and you didn't intend for it to happen.
    
NullPointerException Error hawhat:
    Basically you just need to make sure a method isn't being called onto a null object (aka nothing). 
    So you need to put an if statement that checks that the object you are passing into the method is not 
    null and make adjustments if it is or just return false if it is. It really depends.
    
ArrayIndexOutOfBoundsException Error hawhat:
    Ya done did it. You tried to call something at the position array[-2][8] where the array length is 
    only 8 spaces for objects! So what do you need to do? If the number you are using to call into the 
    array is not within the array range (in this case it is probably from 0-7) then YOU SHALL NOT PASS.
    

    
I may come up with more bugs that I ran into later and update them here so check here if you are stuck.

AUTHORS: Ian Moreno

EFFECTIVE LINES OF CODE: 890 Lines

USER INSTRUCTIONS: Simply run the Main function's main method. If you are using the .jar then simply click
on a white piece and click on the square where you want it to move. Its that simple. Play from there 
accordingly against someone else or yourself. I will be working on playing against the computer too but
that may be a bit beyond my grasp and coding abilities. 


