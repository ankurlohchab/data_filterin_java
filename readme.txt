The folder contain two files and two screenshots of the command prompt depicting the functionality of the code.

class2.java 	This file implemented the java code. It is based on the input as desired by the requirements. 
		
solution.jar 	The file has been generated form the class2.java file. It has all the necessary functionality.
		To run the file use following command on command prompt
			java -jar solution.jar -help  : will show all the possible options
			
		Usage
			java -jar solution.jar 
			 -d [dataset complete address] 
			 -m [	1 : removeing item with blank email,
				2: removing item with blank address,
				3: <both options 1 and 2>
                      		4: avg price of orders per year, 
				5 : total price of order per year, 
				6: top three customers with max no of orders, 
				7 : <option 4,5, and 6 combined>  ]
			 -o text [output file address]
	
Note : The functionality was required to be provided in either  xml, json or plain text.
	I have implemented it using plain text. 
___________________________________________________________________________________________________________________________________________________
Basis for future Developement
	The code has been designed to keep in mind the future scalability. 

		1.	Presently the code is producing output only in plain text format, same can be enhanced in 
			future easily to give other output forms.

		2.	All the possible combination of data manipulation has been tried in the code, however 
			fresh new provision can be applied on the data set using additional manipulation arguement values like 8,9,10 and so on..

		3. 	To ensure the scalability of the code for variable size data input, List is used to find the users having max no of orders, 
			that will enable code to give correct results for other data of large size too. 
____________________________________________________________________________________________________________________________________________________
Problems Faced:
	The main problem that i have faced is making jar file using mavens, i have never tried it earlier and normal jar file was havine issue without 
	the menifest. The issue was resolved using updating the "pom.xml" file with the class name. 


	
	