# durstExpressQA

Preconditions :
installed JAVA 11 , installed IDE(esclipse or intellij) , maven configured (with proxy details in settings.xml file).

Steps: 
1. Clone the code 
2 .Git Repo := https://github.com/Charmee2811/durstExpress_QA
3 .if download from zip then add chrome,geco and IE driver and selenium stanalone server.
4 .Open intellij >> import the project
5. Run testNG.xml file

NOTE :- Currently execution works with mozilla only, 


Logic:
in SRC folder >> test >>java 
testBase is main class having basic method
testCases has test scenerio 
Scenerio is to automate Search functionality,
Login>>handle any modal popup >> search for any drink>>check search auto complete is displayed >>select any drink
verify>> count displayed is same as number of drinks
verify all drink is as per search criteria

File structure details
1. config:- > folder having configuration files
2. OR :- object repository where details of all elements xpath are saved so that it is maintained seperate from code logic.
3 .testData :- for input values of test data (excel,JSON) can also be used
4. testNg.xml -> for execution


Technology Stack
JAVA 11, Maven , TestNG framework
IDE : intellij 

