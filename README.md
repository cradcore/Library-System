# Library-System
CS430 - CSU
<span id="warning-container"></span>

<div id="header">![](./L5.php_files/glde_fr_med.gif) 

## <font color="white">CS 430 DL: Assignments</font>

</div>

<div id="content">

## <font color="darkgreen">Lab/Program Assignment 5</font>

<div class="importantText">

#### Version 1.0 - initial release

#### Version 1.1 - clarified selection by name behavior

* * *

<center><font color="DARKRED">

#### _Due Date: 11:59 PM, Sunday April 22<sup>nd</sup>  
Late Period : 11:59 PM, Tueday, April 24<sup>th</sup>_

</font></center>

* * *

### Lab Assignment 5: Creating a GUI interface to a SQL Database

<dl>

<dt>The purpose of this lab is for you:

*   To gain more experience with using a higher level language interface with SQL;
*   To create a user interface that connects to your Lab 4 database.

</dt>

</dl>

* * *

* * *

<dl>

### Description of the Task for Lab 5

<dt>The database used by this program is the same as the one used after running your Lab 4 Assignment. The purpose of this part of the assignment is to write a dynamic program based on responses of the student and input from the database.  

#### Activities

1.  Create GUI that will work with your Lab 4 database (i.e. your Lab3 data, plus the data input into Lab4, to bring the checked out books current.

    2.  It should first ask for the members id and verify that the member has a valid entry. If the id is not currently in the system, it should ask the questions to add the member. It should have a termination condition here as well.
    3.  It should then ask for the book they want to check out. This can be done one of three ways.
        1.  ISBN
        2.  Name - this can be a partial name, if more than one name matches, allow the user to select.
        3.  Author, then choosing from a list of books by that author
    4.  If the libraries have the book and there are copies available, the program should print a message telling the member what library and shelf the book is on (there may be more than one).
    5.  If either library has the book and all copies are checked out, the program should print a message to the member that all copies are currently checked out.
    6.  If neither library has the book, a message telling the member that the library does not currently have the book in stock.
    7.  Note: this system is providing info only, do not check the book out as a part of these actions.
    8.  Loop back to ask for the next member's id  

2.  You may use any windowing package you choose to implement your GUI. You MUST ensure this code will run on the state capital machines talking to faure so the TA can grade your code. [Here](http://www.cs.colostate.edu/~cs430dl/yr2018sp/more_assignments/LabData/Lab4B_ex.java) is a simple example of a program that does windowing. [Here](http://www.cs.colostate.edu/~cs430dl/yr2018sp/more_assignments/LabData/ssh_tunnel.pdf) is a writeup for doing port forwarding on Windows and [here](http://ubuntuguide.org/wiki/Using_SSH_to_Port_Forward) is one for Ubuntu.
3.  For this assignment, you will need to submit your **<tt>Lab5.java</tt>** file.  

    It is preferable that you include a **<tt>README</tt>** file. This file should explain how to run your program, especially if you have done something you expect may be different from other submissions or that may require special instructions for compilation and execution.

</dt>

</dl>

* * *

* * *

### Submitting Your Assignment

<dl>

<dd>Your database should be restored to initial state (end of L4) so your programs can be graded.</dd>

<dd>Submit

*   your **<tt>Lab5.java</tt>** file
*   any other required **<tt>*.java</tt>** files
*   your **<tt>README</tt>** file, if needed

**using the Canvas CS 430 DL Assignment Submission icon**  
shown on the Canvas CS 430 DL Assignments page.  

</dd>

<dd>**This is Lab Assignment 5**.</dd>

</dl>

* * *

### References

<dl>

<dd>[**<tt>http://www.kitebird.com/articles/jdbc.html#TOC_4</tt>**](http://www.kitebird.com/articles/jdbc.html#TOC_4).  

</dd>

<dd>[**<tt>http://www.developer.com/java/data/article.php/3417381</tt>**](http://www.developer.com/java/data/article.php/3417381).</dd>

</dl>

* * *

### Late Policy

<dl>

<dd>Late programs are subject to a 20% late penalty.</dd>

</dl>

* * *

### For more information on <tt>SQL</tt>

<dl>

<dd>Refer to [**http://dev.mysql.com/doc/mysql/en/**](http://dev.mysql.com/doc/mysql/en/) for **the online MySQL Reference Manual**, as well as information on downloading MySQL onto your own machine.</dd>

</dl>

* * *

<center>

### <font color="DARKRED">Please let us know if there are any obvious errors in this writeup.</font>

</center>

* * *

* * *

</div>

<div id="footer">Colorado State University, Fort Collins, CO 80523 USA  
©2016 Colorado State University</div>

</div>
