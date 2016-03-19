# pos-tagger

The basic function of this project is, given an URL as an argument, extract all the body text from its HTML and tag them using stanford-postagger.

####Out of memory issue
While tagging text from some URL's, I got Out of memory error.<br/>
To overcome this error, I tried to use JVM memory options and went up as high as 4GB as <br/>
-Xms2048m -Xmx4096m<br/>
But out of memroy error still persisted<br/><br/>
To avoid this, I started Tagging the string in batches, each batch containing 1000 words.
A 'Word' is defined as a sequence of character without space.

###Dependencies
1. stanford-postagger (http://nlp.stanford.edu/software/tagger.shtml#Download)
2. slf4j-api (Required stanford-postagger and can be found in the lib folder of the extracted stanford-postagger.zip)
3. slf4j-simple (Required stanford-postagger and can be found in the lib folder of the extracted stanford-postagger.zip)
4. jsoup (http://jsoup.org/download) - Used for extracting text from the HTML

####JSOUP
I have used JSOUP library to extract the body text from html.
I have also tried [Jericho Html Parser] (http://jericho.htmlparser.net/docs/index.html) but I found JSOUP was simple and easier to use and was suffice for this project.

###Eclipse support
Eclipse support has been provided through [.project](.project) and [.classpath](.classpath)
<BR/>To run in eclipse<br/>
1. Download the project from github as a zip file and extract it or clone the project<br/>
2. In eclipse, import the project as 'Import existing projects into workspace'.<br/>
3. Go to run configurations and in the Arguments tab, under Program arguments provide the url whose text you need to tag<br/>
4. Run the configuration.<br/>

####Excecute from the .jar
Form the command line, enter the following <br/>

java -jar pos-tagger.jar \<URL\>

####Sample output
Sample output of the 4 urls can be found at [TaggedOuput.txt](TaggedOutput.txt)

####Note
For URLs with lengthy body texts, the program will give the output but it might take several minutes before you see any output. So please be as patient as possible.

