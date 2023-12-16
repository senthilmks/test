function isDate(d, m, y,arg) {

    

    if (!checkNumericText(d)){

        alert("Please give valid input to day field!");

        return false;

    }

    if (!checkNumericText(y)){

        alert("Please give valid input to year field!");

        return false;

    }



	var yy,mm,dd; 

	var im,id,iy;

	var present_date = new Date();

	yy = 1900 + present_date.getYear();

	if (yy > 3000)

	{

		yy = yy - 1900;

	}

	mm = present_date.getMonth();

	dd = present_date.getDate();

	var entered_month = eval(m)-parseInt(1);

	var invalid_month = eval(m)-parseInt(1);

	var entered_day = d; 

	var entered_year = y; 

        var msg = arg;

        if (msg == null){

            msg=""; 

        }



	if ( (d == 0) || (m == 0) || (y == 0) )

	{

		alert("Please enter correct date "+ msg);

		return false;

	}

	if ( is_greater_date(entered_year,entered_month,entered_day,yy,mm,dd,msg) && is_valid_day(invalid_month,entered_day,entered_year,msg) )

	{

		return true; 

	}

	return false;

}





function isDateNew(d, m, y,arg) {



	var yy,mm,dd;

	var im,id,iy;

	var present_date = new Date();

	yy = 1900 + present_date.getYear();

	if (yy > 3000)

	{

		yy = yy - 1900;

	}

	mm = present_date.getMonth();

	dd = present_date.getDate();

	var entered_month = eval(m)-parseInt(1);

	var invalid_month = eval(m)-parseInt(1);

	var entered_day = d; 

	var entered_year = y; 

        var msg = arg;

        if (msg == null){

            msg=""; 

        }



	if ( (d == 0) || (m == 0) || (y == 0) )

	{

		alert("Please enter correct date "+ msg);

		return false;

	}



        if (!checkNumericNew(y,msg)){

            return false;

        }

	return true;

}







function isGreaterDate(d, m, y,arg)

{

	var yy,mm,dd;

	var im,id,iy;

	var present_date = new Date();

	yy = 1900 + present_date.getYear();

	if (yy > 3000)

	{

		yy = yy - 1900;

	}

	mm = present_date.getMonth();

	dd = present_date.getDate();

	var entered_month = eval(m)-parseInt(1);

	var invalid_month = eval(m)-parseInt(1);

	var entered_day = d; 

	var entered_year = y; 

        //Check this variable as false make this function not compare for future date 

        //var fdcheck=false; 

        var msg = arg;

        if (msg == null){

            msg=""; 

        }



	if ( (d == 0) || (m == 0) || (y == 0) )

	{

		alert("Please enter correct date "+ msg);

		return false;

	}





        /* if (fdcheck){

           if ( is_greater_date(entered_year,entered_month,entered_day,yy,mm,dd,msg) && is_valid_day(invalid_month,entered_day,entered_year,msg) )

            {

                   return true; 

            }

          }else{

           return is_valid_day(invalid_month,entered_day,entered_year,msg);

          }  */



        if(is_valid_day(invalid_month,entered_day,entered_year,msg)){

              return true; 

        }    

	return false;

}



function is_greater_date(entered_year,entered_month,entered_day,yy,mm,dd,arg)

{       

       var msg = arg;

        if (msg == null){

            msg=""; 

        }  

	if (eval(entered_year) > eval(yy))

	{

		alert("The Year field is entered incorrectly."+msg);

		return false;

	}

	if (eval(entered_year) == eval(yy))

	{

		if (eval(entered_month) > eval(mm))

		{

			alert("The Month field is entered incorrectly."+msg);

			return false;

		}

		if (eval(entered_month) == eval(mm))

		{

			if (eval(entered_day) > eval(dd))

			{

				alert("The Date field is entered incorrectly."+msg);

				return false;

			}

		}

	}

	return true;

}

function isCheckFromToDate(frmYear,frmMonth,frmDay,toYear,toMonth,toDay){



    frmYear=parseFloat(frmYear);

    frmMonth=parseFloat(frmMonth);

    frmDay=parseFloat(frmDay);

    toYear=parseFloat(toYear);

    toMonth=parseFloat(toMonth);

    toDay=parseFloat(toDay);

    if (frmYear > toYear){            

            return false;

    }

    if (frmYear == toYear){

            if (frmMonth > toMonth){

                return false;

            }

            if (frmMonth == toMonth){

                if (frmDay>toDay){

                        return false;

                }

            }

    }

    return true;

}



function is_valid_day(entered_month,entered_day,entered_year,arg)

{       var msg = arg;

        if (msg == null){

            msg=""; 

        }

        //entered_month=strTrim(entered_month);

        //entered_day=strTrim(entered_day);

        //entered_year=strTrim(entered_year);

        if ((entered_year % 4) == 0) 

	{ 

		var days_in_month = "312931303130313130313031";

 	}

 	else 

	{ 

		var days_in_month = "312831303130313130313031";

 	} 

	var months = new Array("January","February","March","April","May","June","July","August","September","October","November","December");

	if (entered_month != -1)

	{  

		if (eval(entered_day) > eval(days_in_month.substring(2*entered_month,2*entered_month+2)))

		{                      

			alert ("The Date field is entered wrongly (the day field value exceeds the number of days for the month entered)."+msg);

			

			return false;

		}

	}

	return true;

}











//Function check_date is check the given date is valid one or not

// The format must be dd/MM/yyyy user can use any type of separator 

// If the date is current the function return true other wise return false

//  C.R.Senthil 

//Begin



function check_date(field){

            var checkstr = "0123456789";

            var DateField = field;

            var Datevalue = "";

            var DateTemp = "";

            var seperator = "/";

            var day;

            var month;

            var year;

            var leap = 0;

            var err = 0;

            var i;

               err = 0;

               //DateValue = DateField.value;

               DateValue = field;

               /* Delete all chars except 0..9 */

               for (i = 0; i < DateValue.length; i++) {

                      if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {

                         DateTemp = DateTemp + DateValue.substr(i,1);

                      }

               }

               DateValue = DateTemp;

               /* Always change date to 8 digits - string*/

               /* if year is entered as 2-digit / always assume 20xx */

               if (DateValue.length == 6) {

                  DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); }

               if (DateValue.length != 8) {

                  err = 19;}

               /* year is wrong if year = 0000 */

               /* What to check the year in the between two range, use the below if statement

               // startyear = 1940;

               // endyear = 2050; 

               // year = DateValue.substr(4,4);

               // if (year == 0 || year > endyear || year < startyear ) {

               //    err = 20;

               //  } 



               year = DateValue.substr(4,4);

               if (year == 0) {

                  err = 20;

               }

               /* Validation of month*/

               month = DateValue.substr(2,2);

               if ((month < 1) || (month > 12)) {

                  err = 21;

               }

               /* Validation of day*/

               day = DateValue.substr(0,2);

               if (day < 1 ) {

                 err = 22;

               }

               /* Validation leap-year / february / day */

               if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {

                  leap = 1;

               }

               if ((month == 2) && (leap == 1) && (day > 29)) {

                  err = 23;

               }

               if ((month == 2) && (leap != 1) && (day > 28)) {

                  err = 24;

               }



               /* Validation of other months */

               if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {

                  err = 25;

               }

               if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {

                  err = 26;

               }

               /* if 00 ist entered, no error, deleting the entry */

               if ((day == 0) && (month == 0) && (year == 00)) {

                  err = 0; day = ""; month = ""; year = ""; seperator = "";

               }

               /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */

               if (err == 0) {

                  DateField.value = day + seperator + month + seperator + year;

                  return true;

               }

               /* Error-message if err != 0 */

               else {

                  alert("Date is incorrect!");

                  //DateField.select();

                    //  DateField.focus();

                 return false; 

               }

}

//  End -->

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To find the Difference Between two dates

//% By     : Muralidharan G

//% Date   : 28-July-2003

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////



	function DateDiff(date1,date2){

                //alert("Paased  "+date1 + " " + date2);

		var d1=new Date(date1);

                var d2=new Date(date2);

                //alert("Convert "+d1 + " " + d2);

		var d3=(d2-d1)/86400000;

                //alert("d3"+d3);

		return d3;

                

	}





//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To Set the Day as double digit

//% By     : 

//% Date   : 8-Dec-2005

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////







function funSetDay(str){

    var val=document.getElementById(str).value;

    if(!(isNaN(val)) && val>0){

        if(val.length==1){

         val="0"+val;

         document.getElementById(str).value=val;

        }

    }

    else if(isNaN(val) || val<0 || parseInt(val)>31){

        alert("Day Entered is invalid");

        document.getElementById(str).value="";

        return false;

    }    

return true;

}

///---> End



//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To check whether the given field is empty or not

//% By     : Muralidharan G

//% Date   : 26-April-2003

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////



function checkEmpty(str){

	if (document.getElementById(str).value==""){

		alert('Field Empty, Value Required!');

		document.getElementById(str).focus();

		return false;

	}

return true;

}

function checkSingleSpace(str){

	if (document.getElementById(str).value==" "){

		alert('Field has Empty space, Value Required!');

		document.getElementById(str).focus();

		return false;

	}

return true;

}



//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To check whether the given field is empty or not

//% By     : Vinoth, S

//% Date   : 11-May-2004

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////



function checkEmpty(str, msg){

    if (document.getElementById(str).value==""){

        if (msg == null){

            alert('Field Empty, Value Required!');

        }else{

            alert('Value Required for ' + msg + '!');

        }

        document.getElementById(str).focus();

        return false;

    }

return true;

}



//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To check the single quotes and replace with Apostrophe

//% Date   : 26-April-2003

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

/*

function isValid(str){

	var val=document.getElementById(str).value;

	var ind=val.indexOf('##');

	if (ind != -1){

		alert('String ## is not allowed !');

		document.getElementById(str).value=val.substring(0,ind);

		return false;

	}

	val=document.getElementById(str).value;

	re=/'/g;

	var te=val.replace(re,'`');

	document.getElementById(str).value=te;

	return true;

}

*/



//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To check String

//% Date   : 26-April-2003

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

function checkStringName(str,arg){

	var list="abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	var val=document.getElementById(str).value;

	for(var i=0;i<val.length;i++){

		if(list.indexOf(val.charAt(i))== -1){

			alert("Not Valid TEXT... "+arg);

			document.getElementById(str).value="";

			document.getElementById(str).focus();

			return false;

		}

	}

return true;

}

function checkString(str,arg){

	var list="+- abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ.,";

	var val=document.getElementById(str).value;

	for(var i=0;i<val.length;i++){

		if(list.indexOf(val.charAt(i))== -1){

			alert("Not Valid TEXT... "+arg);

			//document.getElementById(str).value="";

			document.getElementById(str).focus();

			return false;

		}

	}

return true;

}



function checkBGString(str,arg){

	var list="+- abo ABO 12";

	var val=document.getElementById(str).value;

	for(var i=0;i<val.length;i++){

		if(list.indexOf(val.charAt(i))== -1){

			alert("Not Valid Blood Group... "+arg);

			//document.getElementById(str).value="";

			document.getElementById(str).focus();

			return false;

		}

	}

return true;

}

//This method is add by C.R.Senthil 

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To check String Alone Values

//% Date   : 08-November-2005 By Karthikeyan.D

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////





function checkStringAlone(str,arg){

	var list="+-()abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ(),& /.";

	var val=document.getElementById(str).value;

	for(var i=0;i<val.length;i++){

		if(list.indexOf(val.charAt(i))== -1){

			alert("Not Valid String value... "+arg);

			//document.getElementById(str).value="";

			document.getElementById(str).focus();

			return false;

		}

	}

return true;

}











function checkNameString(str,msg){

	var list="abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ .-";

	var val=document.getElementById(str).value;

	for(var i=0;i<val.length;i++){

		if(list.indexOf(val.charAt(i))== -1){

                        if (msg == null){

                             alert('Not A Valid Data!');

                         }else{

                              alert('Not a Valid Data in ' + msg + '!');

                         }			

			//document.getElementById(str).value="";

			document.getElementById(str).focus();

			return false;

		}

	}

return true;

}



function checkStringHypen(str,msg){

	var list="abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ -";

	var val=document.getElementById(str).value;

	for(var i=0;i<val.length;i++){

		if(list.indexOf(val.charAt(i))== -1){

                        if (msg == null){

                             alert('Not A Valid Data!');

                         }else{

                              alert('Not a Valid Data in ' + msg + '!');

                         }			

			//document.getElementById(str).value="";

			document.getElementById(str).focus();

			return false;

		}

	}

return true;

}



//Checking whetther single quotes in the given string 



function checkSingleQuote(str,msg){

         var list="'";

         var val=document.getElementById(str).value;

	 for(var i=0;i<val.length;i++){

		if(list.indexOf(val.charAt(i))>=0){

                        if (msg == null){

                             alert('Single Quotes Not Allowed!');

                         } else {

                              alert('Single Quotes Not Allowed ' + msg + '!');

                         }			

			//document.getElementById(str).value="";

			document.getElementById(str).focus();

			return false;

		}

	}

        return true;   

}

//Change the single quotes to Tiled Symbol

function replaceSingleQuote(str){

    var a = document.getElementById(str).value;

    var aa = "";

    var i = 0;

    while(a.length>i) {

        if (a.charAt(i)=="'")

            aa+="`";

        else

            aa+=a.charAt(i);

        i++;

    }

    document.getElementById(str).value=aa;

}

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To check whether the given field has Empty Spaces or not

//% By     : Karthikeyan.D

//% Date   : 09-November-2005

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////



function checkEmptySpace(str,msg){

         var list=" ";

         var val=document.getElementById(str).value;

	 for(var i=0;i<val.length;i++){

		if(list.indexOf(val.charAt(i))>=0){

                        if (msg == null){

                             alert('Empty Spaces Not Allowed!');

                         } else {

                              alert('Empty Spaces Not Allowed In ' + msg + '!');

                         }			

			//document.getElementById(str).value="";

			document.getElementById(str).focus();

			return false;

		}

	}

        return true;   

}



function checkPfNumber(str,msg){

	var list="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ/0123456789";

	var val=document.getElementById(str).value;

	for(var i=0;i<val.length;i++){

		if(list.indexOf(val.charAt(i))== -1){

                        if (msg == null){

                             alert('Not A Valid Data!');

                         }else{

                              alert('Not a Valid Data in ' + msg + '!');

                         }			

			//document.getElementById(str).value="";

			document.getElementById(str).focus();

			return false;

		}

	}

return true;

}



function checkPfNumbers(str,msg){

	var list="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ/0123456789()";

	val=document.getElementById(str).value;

	for(var i=0;i<val.length;i++){

		if(list.indexOf(val.charAt(i))== -1){

                        if (msg == null){

                             alert('Not A Valid Data!');

                         }else{

                              alert('Not a Valid Data in ' + msg + '!');

                         }			

			//document.getElementById(str).value="";

			document.getElementById(str).focus();

			return false;

		}

	}

return true;

}





//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To check Numeric 

//% Date   : 26-April-2003

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////



function checkNumeric(str,msg){

	var list ="0123456789.";

    val = strTrim(document.getElementById(str).value);

	for(var i=0;i<val.length;i++){

		if(list.indexOf(val.charAt(i))== -1){

                       if (msg == null){

                             alert('Not A Valid Data!');

                         }else{

                              alert('Not a Valid Data in ' + msg + '!');

                         } 			

                            document.getElementById(str).value="";

                            document.getElementById(str).focus();

			return false;

		}

	}

return true;	

	

}//checkNumeric







function checkNumericNew(str,msg){

        if (IsEmpty(str,msg))

            return false;

            var list ="0123456789";

            val = document.getElementById(str).value;

            for(var i=0;i<val.length;i++){

                    if(list.indexOf(val.charAt(i))== -1){

                           if (msg == null){

                                 alert('Not A Valid Data!');

                             }else{

                                  alert('Not a Valid Data in ' + msg + '!');

                             } 

                              document.getElementById(str).value="";

                              document.getElementById(str).focus();

                            return false;

                    }

            }

            

return true;	

}//checkNumericNew



function checkNumericText(str){

    str=str+"";

    if (str.length > 0){

        var list ="0123456789";

        val = str;

        for(var i=0;i<val.length;i++){

            if(list.indexOf(val.charAt(i))== -1){

                return false;

            }

        }            

        return true;	

    }else{

        return false;

    }

}//checkNumericNew







function checkPerNumeric(str,msg){

	var list ="0123456789.";

	val = document.getElementById(str).value;

	for(var i=0;i<val.length;i++){

		if(list.indexOf(val.charAt(i))== -1){

                         if (msg == null){

                             alert('Not A Valid Data!');

                         }else{

                              alert('Not a Valid Data in ' + msg + '!');

                         } 		 

			//alert("Not a valid Number");

			// document.getElementById(str).value="";

			document.getElementById(str).focus();

			return false;

		}

	}

return true;		

}//checkPerNumeric 





function checkYearNumeric(str,msg){

	var list ="0123456789-";

	val = document.getElementById(str).value;

	for(var i=0;i<val.length;i++){

		if(list.indexOf(val.charAt(i))== -1){

                         if (msg == null){

                             alert('Not A Valid Data!');

                         }else{

                              alert('Not a Valid Data in ' + msg + '!');

                         } 		

			//alert("Not a valid Number");

			//document.getElementById(str).value="";

			document.getElementById(str).focus();

			return false;

		}

	}

return true;		

}//checkYearNumeric 



function checkYear(str,msg){

    if (document.getElementById(str).value==""){

            alert('Field Empty, Value Required!');

            document.getElementById(str).focus();

            return false;

    }

    else if (document.getElementById(str).value=="0000"){

            alert('Year Value Cannot be Zero!');

            document.getElementById(str).focus();

            return false;

    }

    else if(document.getElementById(str).value!=""){

        bol=true;

        var list ="0123456789";

        val = document.getElementById(str).value;

        for(var i=0;i<val.length;i++){

                if(list.indexOf(val.charAt(i))== -1){

                   if (msg == null){

                         alert('Not A Valid Data!'+msg);

                     }else{

                          alert('Not a Valid Data in ' + msg + '!');

                     } 			

                    //document.getElementById(str).value="";

                    document.getElementById(str).focus();

                    bol=false;

                    return false;

                }//end of outer if

        }//end of for

        if(bol){

            if(val.length!=4){

                alert("Please Enter the Full Year "+ msg +" !");

                return false;

            }

        }//end of if bol

    }//end of else if

return true;	

}//end of checkYear





function checkDay(str,msg){

    if (document.getElementById(str).value==""){

            alert('Field Empty, Value Required '+msg+' !');

            document.getElementById(str).focus();

            return false;

    }

    else if (document.getElementById(str).value=="00"){

            alert('Day Value Cannot be Zero '+msg+' !');

            document.getElementById(str).focus();

            return false;

    }

    else if(document.getElementById(str).value!=""){

        bol=true;

        var list ="0123456789";

        val = document.getElementById(str).value;

        for(var i=0;i<val.length;i++){

                if(list.indexOf(val.charAt(i))== -1){

                   if (msg == null){

                         alert('Not A Valid Data!');

                     }else{

                          alert('Not a Valid Data in ' + msg + '!');

                     } 			

                    //document.getElementById(str).value="";

                    document.getElementById(str).focus();

                    bol=false;

                    return false;

                }//end of outer if

        }//end of for

    }//end of else if

return true;	

}//end of checkDay

	



//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To check Alpha Numeric	values

//% Date   : 26-April-2003

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////



function checkAlpha(str, msg){

var list="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

val=document.getElementById(str).value;

	for(var i=0;i< val.length;i++){

		if(list.indexOf(val.charAt(i))== -1){

			 if (msg == null){

                             alert('Not A Valid Data!');

                         }else{

                              alert('Not a Valid Data in ' + msg + '!');

                         }

			//document.getElementById(str).value="";

			document.getElementById(str).focus();

			return false;

		}

	}

return true;

}



//Following Two Function is Added By C.R.Senthil 



function checkAlphaNumeric(str, msg){

var list="abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789\n";

val=document.getElementById(str).value;

	for(var i=0;i< val.length;i++){

		if(list.indexOf(val.charAt(i))== -1){

			 if (msg == null){

                             alert('Not A Valid Data!');

                         }else{

                              alert('Not a Valid Data in ' + msg + '!');

                         }

			//document.getElementById(str).value="";

			document.getElementById(str).focus();

			return false;

		}

	}

return true;

}//checkAlphaNumeric





function checkAddressString(str, msg){

    var list="abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789\ / .,#-\n ";

    val=document.getElementById(str).value;

    for(var i=0;i< val.length;i++){

        if(list.indexOf(val.charAt(i))== -1){

            if (msg == null){

                alert('Not A Valid Data!');

            }else{

                alert('Not a Valid Data in ' + msg + '!');

            }

            document.getElementById(str).focus();

            return false;

        }

    }

    return true;

}//checkAddressString 



function checkHeadAlpha(str, msg){

var list="abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

val=document.getElementById(str).value;

	for(var i=0;i< val.length;i++){

		if(list.indexOf(val.charAt(i))== -1){

			 if (msg == null){

                             alert('Not A Valid Data!');

                         }else{

                              alert('Not a Valid Data in ' + msg + '!');

                         }

			//document.getElementById(str).value="";

			document.getElementById(str).focus();

			return false;

		}

	}

return true;

}// Head Alpha



// This function return whether given date is valid date or not //

// Months must be start from 0 - 11 (not use 1 - 12 ) C.R.Senthil //

 

function isValidDate(day,month,year){

	var dteDate;

	dteDate=new Date(year,month,day);

        return ((day==dteDate.getDate()) && (month==dteDate.getMonth()) && (year==dteDate.getFullYear()));

}









//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To check Phone No.

//% Date   : 21-Jul-2003  Modified on 01-Oct-2005 by D.Karthik

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////



function checkPhone(str,msg){

		val=document.getElementById(str).value;

		var strcheck="1234567890-+ ";

		for(i=0;i<val.length;i++){

			if(strcheck.indexOf(val.charAt(i))== -1){

				alert("Not a valid Phone No."+msg);

				//document.getElementById(str).value="";

				document.getElementById(str).focus();

				return false;

			}//end of if

		}//end of for

return true;

}



//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : Check for Maximum Length of a Text Area

//% Date   : 21-Jul-2003

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////



function checkTextAreaLen(str1,str2){

	txt=document.getElementById(str1).value;

	len=txt.length;

	if (len>str2) {

		alert('Length exceeds than the limit');

		document.getElementById(str1).value=txt.substring(0,str2);

	}//end of if

}//end of function



//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To check whether given field contains Integer or Not

//% Date   : 26-April-2003

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////



function checkInt(str){

		var val=document.getElementById(str).value;

		if (isNaN(val)){

 		   alert('Invalid Character');

  		   document.getElementById(str).value='';

		   document.getElementById(str).focus();

		   return false;

		  }

		if ((val >2147483647)||(val<-2147483648)){

				alert ('Length Exist than the limit');

  				document.getElementById(str).value='2147483647';

				document.getElementById(str).focus();

				return false;

		}

  		if (val.indexOf('.') != -1){

  			alert ('Invalid Character');

  			//document.getElementById(str).value='';

			document.getElementById(str).focus();

			return false;

  		}

  		var chk=val.indexOf('0',0);

  		while (chk == 0){

  			val=val.substring(1);

  			chk=val.indexOf('0',0);

  			document.getElementById(str).value=val;

  		}

return true;

}



//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To check whether given field contains Postive Integer or Not

//% Date   : 21-July-2003

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////



function checkPositiveInt(str){

	if (!checkInt(str)) return false;

	var txt=document.getElementById(str).value;

	var ind=txt.indexOf('-');

	if (ind == 0){

            document.getElementById(str).value=txt.substring(1);

            return true;

        }else if (ind > 0) {

            alert ('Please enter a positive value');

            document.getElementById(str).value="";

            return false;

        }

        



return true;

}



//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To check Decimal values with 2 precision

//% Date   : 26-April-2003

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////



function checkDecimal(str){

		val=document.getElementById(str).value;



	  if (val=="")  {

		  return false;

	  }

  		if (isNaN(val))

  		  {

  		   alert('Invalid Character');

                    document.getElementById(str).value="";

  		   document.getElementById(str).focus();

		   return false;

  		  }

  		 else{

  			ind=val.indexOf(".");

  			if(ind != -1){

  					len=val.length;

  					temp=val.substring(0,ind+3);

 					if (temp.length < ind+3){	temp=temp+'0';	}

  					document.getElementById(str).value=temp;

  				}

  			  else{

  				//document.getElementById(str).value=val+'0.00';

                                document.getElementById(str).value=val+'.00';

  			  }

  		 } 

        var chk=val.indexOf('0',0);

  		while (chk == 0 && val.length>1){

  			val=val.substring(1);

  			chk=val.indexOf('0',0);

  			document.getElementById(str).value=val;

  		}

  		 return true;

}//end of function checkDecimal



//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To check whether given field contains Postive Decimal or Not

//% Date   : 10-September-2003

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////



function checkPositiveDecimal(str)

{

	var blnResult;

	blnResult=checkDecimal(str);

	if (blnResult)

	{

		txt=document.getElementById(str).value;

		ind=txt.indexOf('-');

		if (ind == 0)

			document.getElementById(str).value=txt.substring(1);

	}

	else

	{

		return false;

	}

return true;

}





//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To trim the left and right blank spaces in a component (i.e. TextBox,TextArea)

//% Date   : 26-April-2003

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////



function trim(str){

	lTrim(str);

	rTrim(str);

}





//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To trim the left trailing blank spaces in a component

//% Date   : 26-April-2003

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////



function lTrim(str){

		var val=document.getElementById(str).value;

		var len=val.length;

		var temp='';

		for (i=0;i< len;i++){

		var chr=val.charAt(i);

		if (chr != ' ')	{temp=temp+val.substring(i);i=len;}

		}

		document.getElementById(str).value=temp;

		return true;

}



//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To trim the right trailing blank spaces in a component

//% Date   : 26-April-2003

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////



function rTrim(str){

	var val=document.getElementById(str).value;

	var len=val.length;

	var temp='';

	for (i=len-1;i>=0;i--){

		chr=val.charAt(i);

		if (chr != ' ')	{temp=temp+val.substring(0,i+1);i=-1;}

	}

	document.getElementById(str).value=temp;

}



//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To trim the left and right blank spaces of a string

//% Date   : 26-April-2003

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////



function strTrim(str){

	var str1=strLTrim(str);

	var str2=strRTrim(str1);

	return str2;

}



//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To trim the left trailing blank spaces of a string

//% Date   : 26-April-2003

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////



function strLTrim(str){

		var len=str.length;

		var temp='';

		for (i=0;i< len;i++){

		var chr=str.charAt(i);

		if (chr != ' ')	{temp=temp+str.substring(i);i=len;}

		}

		return temp;

}



//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To trim the right trailing blank spaces of a String

//% Date   : 26-April-2003

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////



function strRTrim(str){

	var len=str.length;

	var temp='';

	for (i=len-1;i>=0;i--){

		chr=str.charAt(i);

		if (chr != ' ')	{temp=temp+str.substring(0,i+1);i=-1;}

	}

	return temp;

}



//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To clear all the text,checkbox and select

//% Date   : 28-october-2003

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////



function clearAll(){

	var coll=document.getElementsByTagName("input");

	for (i=0; i<coll.length; i++){

		if (coll[i].type=="checkbox")	coll[i].checked=false;

		if (coll[i].type=="text")		coll[i].value="";

	}

	coll=document.getElementsByTagName("select");

	for(i=0; i<coll.length; i++) coll[i].selectedIndex=0;

}





//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To Enable all the textbox,select and checkbox in the document.

//% Author : KiranKumar

//% Date   : 28-April-2003

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////



function enableAll(){

	var coll=document.getElementsByTagName("input");

	for (i=0; i<coll.length; i++){

		if (coll[i].type=="checkbox")	coll[i].disabled=false;

		if (coll[i].type=="text"){

                    if (coll[i].disabled==true) {

                        coll[i].disabled=false;

                    } else {

                        coll[i].readOnly=false;

                    }

                }

		if (coll[i].type=="radio")		coll[i].disabled=false;

	}

	coll = document.getElementsByTagName("select");

	for (i=0; i<coll.length; i++) coll[i].disabled=false;

}



//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To Disable all the textbox,select and checkbox in the document.

//% Author : KiranKumar

//% Date   : 28-April-2003

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////



function disableAll(){

	var coll=document.getElementsByTagName("input");

	for (i=0; i<coll.length; i++){

		if (coll[i].type=="checkbox")	coll[i].disabled=true;

		if (coll[i].type=="text"){

                    if (coll[i].readOnly==true) {

                        coll[i].disabled=true;

                    } else {

                        coll[i].readOnly=true;

                    }

                }

		if (coll[i].type=="radio")		coll[i].disabled=true;

	}

	coll = document.getElementsByTagName("select");

	for (i=0; i<coll.length; i++) coll[i].disabled=true;

}



//%%% 

//%  This function is must for HTML ListView technology, SO B4 REMOVING THIS 

//%  TAKE CARE OF THAT

//%%%

function strltrim(strText) {

    while (strText.charCodeAt(0) == 160 || strText.charCodeAt(0) == 32)

        strText = strText.substring(1, strText.length)

    return strText;

}    

function strrtrim(strText){

    while (strText.charCodeAt(strText.length-1) == 160 || strText.charCodeAt(strText.length-1) == 32)

        strText = strText.substring(0, strText.length-1);

    return strText;

}

function strtrim(strText) {        

    return strltrim(strrtrim(strText));

}





/** Dont make any alter in above function  */





// Use this function if u need future Date -- By Praveen



function isFutureDate(d, m, y,arg)

{

	var yy,mm,dd;

	var im,id,iy;

	var present_date = new Date();

	yy = 1900 + present_date.getYear();

	if (yy > 3000)

	{

		yy = yy - 1900;

	}

	mm = present_date.getMonth();

	dd = present_date.getDate();

	var entered_month = eval(m)-parseInt(1);

	var invalid_month = eval(m)-parseInt(1);

	var entered_day = d; 

	var entered_year = y; 

        var msg = arg;

        if (msg == null){

            msg=""; 

        }



	if ( (d == 0) || (m == 0) || (y == 0) )

	{

		alert("Please enter correct date "+ msg);

		return false;

	}

	if ( is_Less_date(entered_year,entered_month,entered_day,yy,mm,dd,msg) && is_valid_day(invalid_month,entered_day,entered_year,msg) )

	{

		return true; 

	}

	return false;

}



function is_Less_date(entered_year,entered_month,entered_day,yy,mm,dd,arg)

{       

       var msg = arg;

        if (msg == null){

            msg=""; 

        }  

	if (entered_year < yy)

	{

		alert("The Year field is entered incorrectly. "+msg);

		return false;

	}

	if (entered_year == yy)

	{

		if (entered_month < mm)

		{

			alert("The Month field is entered incorrectly."+msg);

			return false;

		}

		if (entered_month == mm)

		{

			if (entered_day < dd)

			{

				alert("The Date field is entered incorrectly."+msg);

				return false;

			}

		}

	}

	return true;

}



function checkEmail(str,arg){

	var list="abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ.@1234567890_-";

	val=document.getElementById(str).value;

	for(var i=0;i<val.length;i++){

		if(list.indexOf(val.charAt(i))== -1){

			alert("Not a valid Email Id... "+arg);

			//document.getElementById(str).value="";

			document.getElementById(str).focus();

			return false;

		}

	}

/// Email Valid

    check=0;

    for(var i=0;i<val.length;i++){

		if(list.indexOf(val.charAt(i))== 54){

                    check=1;

                    break;

		}

	}

    if (check==1) {

    check=0;

        for(var i=0;i<val.length;i++){

                    if(list.indexOf(val.charAt(i))== 53){

                        check=1;

                        break;

                    }

            }

    }

    if (check==1)

        return true;

    else {

        alert("Not a valid Email Id... "+arg);

        return false;

    }

//// End

//return true;

}



//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////

//% call   : To check the validity of entered web address.

//% Author : Karthikeyan.D

//% Date   : 01-October-2005

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////



function checkWebAddress(str,arg)

{

	var list="abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ./:123456789";

	val=document.getElementById(str).value;

	for(var i=0;i<val.length;i++){

		if(list.indexOf(val.charAt(i))== -1){

			alert("Not a valid Web Address... "+arg);

			//document.getElementById(str).value="";

			document.getElementById(str).focus();

			return false;

		}

	}

// Web Address Validity check

    check=0;

    for(var i=0;i<val.length;i++){

		if(list.indexOf(val.charAt(i))== 53){

                    check=1;

                    break;

		}

	}

     if (check==1)

        return true;

    else {

        alert("Not a valid Web Address... "+arg);

        return false;

    }





}













// Is Extra Space 

function isExtraSpace(arg)  {

    str=document.getElementById(arg).value;

    var temp="";

    var len=str.length;

    for (i=0;i<len;i++) {

        if (str.charAt(i)==' ') {

            for (j=i;j<len;j++) {

                if (str.charAt(j+1)!=' '){

                    temp+=str.charAt(i);

                    i=j;

                    break;

                    }

                }

        } else {

            temp+=str.charAt(i);

        }

    }

    document.getElementById(arg).value=temp;

}

function loadCurrentDate(day,month,year)

{

   var Present_Date=new Date();

   var Present_Day=Present_Date.getDate();

   var Present_Month=Present_Date.getMonth();

   Present_Month=parseInt(Present_Month)+parseInt(1);

   var Present_Year=1900+Present_Date.getYear();   

   if(parseInt(Present_Year)>3000)  {

	    Present_Year=Present_Year-1900;

	}

   if(parseInt(Present_Month)<10)  {

	     Present_Month="0"+Present_Month;

	}

   if (parseInt(Present_Day)<10)

        Present_Day="0"+Present_Day;

    document.getElementById(day).value=Present_Day;

    document.getElementById(month).value=Present_Month;

    document.getElementById(year).value=Present_Year;

}



function loadCurrentDateandTime(str,dateseperator){

   var Present_Date=new Date();

   var Present_Day=Present_Date.getDate();

   var Present_Month=Present_Date.getMonth();

   Present_Month=parseInt(Present_Month)+parseInt(1);

   var Present_Year=1900+Present_Date.getYear();   

   var result;



   if(parseInt(Present_Year)>3000)  {

	    Present_Year=Present_Year-1900;

	}

   if(parseInt(Present_Month)<10)  {

	     Present_Month="0"+Present_Month;

	}

   if (parseInt(Present_Day)<10)

        Present_Day="0"+Present_Day;



    document.getElementById(str).value=Present_Day+dateseperator+Present_Month+dateseperator+Present_Year+" "+Present_Date.getHours()+":"+Present_Date.getMinutes()+":"+Present_Date.getSeconds();

}



function checkISBN(str,msg) {

    var list="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-0987654321.";

    var act=document.getElementById(str).value;

    for (i=0;i<act.length;i++) {

        if (list.indexOf(act.charAt(i))==-1) {

            if (msg==null) {

                alert ("Not a valid data in ISBN");

                return false;

            } else  {

                alert ("Not a valid data in "+msg);

                return false;

            }

            document.getElementById(str).focus();

        }

    }

    return true;

}



function IsEmpty(str){

    trim(str);

    a=document.getElementById(str).value;

    if ((a.charAt(0)==" ") || (a.length==0))

        {

        alert("Please Enter Valid String Data");

           return true;

        }

    else

        return false;  

    } 



function IsEmpty(str,msg){

    trim(str);

    a=document.getElementById(str).value;

    if ((a.charAt(0)==" ") || (a.length==0))

        {

        alert("Please Enter Valid String Data For"+msg);

           return true;

        }

    else

        return false;  

    }

/*******************************

 *

 *  Here For the string Handling Functions

 *

 ******************************/



function funInitialCap(str) {

    var name=document.getElementById(str).value;

    document.getElementById(str).value=name.toUpperCase();

}

function funNameCap(str) {

    var name=document.getElementById(str).value;

    var temp="";

    var i=0;

    while(i < name.length) {

        if (i==0) 

            temp=name.charAt(0).toUpperCase();

        else if (name.charAt(i)==" ") { 

            temp+=name.charAt(i).toUpperCase();

            temp+=name.charAt(++i).toUpperCase();

        }

        else

            temp+=name.charAt(i)

        i++;

    }

    document.getElementById(str).value=temp;

}











/*function IsEmpty(str,msg){

    trim(str);

    a=document.getElementById(str).value;

    if ((a.charAt(0)==" ") || (a.length==0))

        {

         if (msg==null) {

        alert("Please Enter Valid String Data");

        return true;

                        }

        else{ 

         alert("Please Enter Valid String Data"+msg);

        return true;

             }

        }

    else

        return false;  

    }*/



function IsBlankSpace(str,msg){

   var strings="";

   strings=document.getElementById(str).value;

   if(strings==" " || strings==null){

       if(msg==null){

            alert ("Not a valid data !");

                return false;

            } else  {

                alert ("Not a valid data in "+msg);

                return false;

            }

            document.getElementById(str).focus();

        }

    

    return true;

  

}



 function checkEnterKey(str,msg){

    

    a=document.getElementById(str).value;

   if ((a.charCodeAt(0)==10) || (a.charCodeAt(0)==13) || (a.charCodeAt(0)==32))

        {

         if (msg==null) {

        alert("Please Enter Valid String Data");

        return false;

                        }

        else{ 

         alert("Please Enter Valid String Data"+msg);

        return false;

             }

             document.getElementById(str).focus();

        }

        

    else

        return true;  

    }



//this function accepts numbers only from 0 to 9 and not even . and allow back space.

//use this function in the keypress event like onkeypress= "return NumbersOnly(event)"

//arun.v

function NumbersOnly(e){

        var unicode=e.charCode? e.charCode : e.keyCode

        if (unicode!=8 && unicode!=9 && unicode!=46){ //if the key isn't the backspace key (which we should allow)

            if (unicode<48||unicode>57 ) //if not a number

                return false //disable key press

        }

}



function CoolNumbersOnly(e){

        var unicode=e.charCode? e.charCode : e.keyCode

        if (unicode != 8 && unicode!=9 && unicode!=46 ){ //if the key isn't the backspace key (which we should allow)

            if (unicode<48||unicode>57 ) //if not a number

                return false //disable key press

        }

}





function checkNumericOnly(str,arg){

    var list="0123456789";

    var val=document.getElementById(str).value;

    for(var i=0;i<val.length;i++){

    if(list.indexOf(val.charAt(i))== -1){

        alert("Not Valid Number .. "+arg);

        document.getElementById(str).value="";

        document.getElementById(str).focus();

        return false;

    }

}

return true;

}



function Age(OldMonth,DOBYear) {

    var d1=new Date();

    var CurrentYear=d1.getFullYear();

    var OldYear=CurrentYear-DOBYear;

    var CurrentMonth=1+d1.getMonth();



    if(CurrentMonth< OldMonth)

    {

    var month=OldMonth-CurrentMonth;

    str=OldYear-1+" Years "+(12-month)+" months Old";

    }

    else{

      str=OldYear+" Years Old";

    }



  return str;

}

function URLEncode(argString)

{

	// The Javascript escape and unescape functions do not correspond

	// with what browsers actually do...

	var SAFECHARS = "0123456789" +					// Numeric

					"ABCDEFGHIJKLMNOPQRSTUVWXYZ" +	// Alphabetic

					"abcdefghijklmnopqrstuvwxyz" +

					"-_.!~*'()";					// RFC2396 Mark characters

	var HEX = "0123456789ABCDEF";



	var plaintext = argString;

	var encoded = "";

	for (var i = 0; i < plaintext.length; i++ ) {

		var ch = plaintext.charAt(i);

	    if (ch == " ") {

		    encoded += "+";				// x-www-urlencoded, rather than %20

		} else if (SAFECHARS.indexOf(ch) != -1) {

		    encoded += ch;

		} else {

		    var charCode = ch.charCodeAt(0);

			if (charCode > 255) {

			    alert( "Unicode Character '" 

                        + ch 

                        + "' cannot be encoded using standard URL encoding.\n" +

				          "(URL encoding only supports 8-bit characters.)\n" +

						  "A space (+) will be substituted." );

				encoded += "+";

			} else {

				encoded += "%";

				encoded += HEX.charAt((charCode >> 4) & 0xF);

				encoded += HEX.charAt(charCode & 0xF);

			}

		}

	} // for



	return encoded;

	//return false;

}



function URLDecode(argString)

{

   // Replace + with ' '

   // Replace %xx with equivalent character

   // Put [ERROR] in output if %xx is invalid.

   var HEXCHARS = "0123456789ABCDEFabcdef"; 

   //var encoded = document.URLForm.F2.value;

   var encoded = argString;

   var plaintext = "";

   var i = 0;

   while (i < encoded.length) {

       var ch = encoded.charAt(i);

	   if (ch == "+") {

	       plaintext += " ";

		   i++;

	   } else if (ch == "%") {

			if (i < (encoded.length-2) 

					&& HEXCHARS.indexOf(encoded.charAt(i+1)) != -1 

					&& HEXCHARS.indexOf(encoded.charAt(i+2)) != -1 ) {

				plaintext += unescape( encoded.substr(i,3) );

				i += 3;

			} else {

				alert( 'Bad escape combination near ...' + encoded.substr(i) );

				plaintext += "%[ERROR]";

				i++;

			}

		} else {

		   plaintext += ch;

		   i++;

		}

	} // while

   //document.URLForm.F1.value = plaintext;

   //return false;

   return plaintext;

}

//*******************************************************************//

// function added by v.arun, to inlucde comma in the value.

// you have to give lower limit as well as inter level... 

// for eg: WithCommas("1234567.50",3,2) will produce 12,34,567.50

// for eg: WithCommas("1234567.50",3,3) will produce 1,234,567.50

// Date :14-03-2006

//*******************************************************************



function WithComma(argValue,argLowerLimit,argInterLevel){

    var strOmitted="00",lowerLimit=0,interLevel=0;

    var strNumber="",checkNumber=0,newStr="",sst="";

    var k="",len=0,res="",i=0,z=0;  

    var strOmitted="00";

    

    strNumber=argValue;

    lowerLimit=argLowerLimit;

    interLevel=argInterLevel;



    checkNumber=parseFloat(strNumber);    

    //check the number is it have minus sighn,if it has remove that sign..

    if(checkNumber<0){

        k="-";

        strNumber=String(checkNumber*-1);

    }

    else{

        k="";

    }

    //check the number , is it have decimal values, if it has separate it..

    sst=String(strNumber);

    z=sst.indexOf(".");

    if(z>0){

        strOmitted=sst.substring(z+1,sst.length);

        sst=sst.substring(0,z);            

    }

    if(parseInt(strOmitted)<10 && strOmitted.length==1 && strOmitted!="00")

        strOmitted=strOmitted+"0";

        

    strNumber=sst; //this is the decimal value removed, sign removed value



    len=strNumber.length;

    newStr=new String(strNumber);

    var firstQtr="",secondQtr="";



    //this loops add the , in the value..

    for(i=len-lowerLimit;i>0;i-=interLevel){

        firstQtr=newStr.substring(0,i);

        secondQtr=newStr.substring(i,len);

        newStr=(firstQtr+","+secondQtr);

        len=newStr.length;

    }

    //add minus sign, decimal value with the comma included value..

    res=k+(newStr+"."+strOmitted);

    //alert("With Comma "+res);

    //alert("With out Comma "+replaceComma(res));

    return res;

}

//to remove comma from the number...



function replaceComma(argValue){

    var data=argValue.split(",");

    var i=0;

    var len=data.length;

    var newVal="";

    for(i=0;i<len;i++){

        newVal=newVal+data[i];

    }  

    return newVal;

}





//*****************************For With comma********************************//







// Praveen.G.S

function funReplaceSingleQout(arg) {

    var a = document.getElementById(arg).value;

    var aa = "";

    var i = 0;

    while(a.length>i) {

        if (a.charAt(i)=="'")

            aa+="`";

        else if (a.charAt(i)=='"')

            aa+="`";

        else

            aa+=a.charAt(i);

        i++;

    }

    document.getElementById(arg).value=aa;

}



//Selvakumar K.P.

/**********************************************************************************

*                      Fiexed Decimal places                                      *                                 

***********************************************************************************/

function toFixDecimalPlaces(myNumber, NumberDec){

var cordec = Math.pow(10,NumberDec);

myNumber = Math.round(myNumber * cordec) / cordec;

return myNumber;

}



function replaceEnterKey(textarea,replaceWith){

    textarea.value=escape(textarea.value)

    for(i=0; i<textarea.value.length; i++){

            if(textarea.value.indexOf("%0D%0A") > -1){

            textarea.value=textarea.value.replace("%0D%0A",replaceWith)

            }

            else if(textarea.value.indexOf("%0A") > -1){

            textarea.value=textarea.value.replace("%0A",replaceWith)

            }

            else if(textarea.value.indexOf("%0D") > -1){

            textarea.value=textarea.value.replace("%0D",replaceWith)

            }

    }

    textarea.value=unescape(textarea.value)

}





function setEnterKeyToTextArea(argObject){

    argObject.value=escape(argObject.value)

    for(i=0; i<argObject.value.length; i++){

            if(argObject.value.indexOf("%24%23") > -1){

                argObject.value=argObject.value.replace("%24%23","\n")

            }

    }

    argObject.value=unescape(argObject.value)

}



function isNumberKey(evt){

             var charCode = (evt.which) ? evt.which : evt.keyCode;

             if ((charCode==46)&&(evt.which==0)){ //delete key

                 return true;

             }

             if ((charCode==37)&&(evt.which==0)){ //left arrow key

                 return true;

             }

             if ((charCode==39)&&(evt.which==0)){ //right arrow key

                 return true;

             }                          

             if ((charCode==8) || (charCode==9))   //backspace and tap key

                return true;



             if (charCode > 31 && (charCode < 48 || charCode > 57))

                return false;

             return true;

}



function isNumberKeyWithDecimal(evt){

             var charCode = (evt.which) ? evt.which : evt.keyCode;

             

             if ((charCode==8) || (charCode==9))   //backspace and tap key

                return true;



             if (charCode!=46){

                    if (charCode > 31 && (charCode < 48 || charCode > 57))

                        return false;

             }

             return true;

}



//With space

function isAlphaNumberKey(evt){

            var charCode = (evt.which) ? evt.which : evt.keyCode;

           

            if ((charCode==8) || (charCode==9) || (charCode==32))   //backspace and tap key

                return true;



            if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || (charCode > 47 && charCode < 58))

                return true;



           return false;  

}



//Without space

function isAlphaNumberKeyWithoutSpace(evt){

            var charCode = (evt.which) ? evt.which : evt.keyCode;

           

            if ((charCode==8) || (charCode==9))   //backspace and tap key

                return true;



            if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || (charCode > 47 && charCode < 58))

                return true;



           return false;

}



function isCharacterKey(evt){

            var charCode = (evt.which) ? evt.which : evt.keyCode;

           

            if ((charCode==8) || (charCode==9) || (charCode==32))   //backspace and tap key

                return true;



            if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || (charCode==32))

                return true;



           return false;



}



function isCharacterWithDotKey(evt){

            var charCode = (evt.which) ? evt.which : evt.keyCode;

           

            if ((charCode==8) || (charCode==9)|| (charCode==46) || (charCode==32))   //backspace and tap key

                return true;



            if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123))

                return true;



           return false;



}



function isCharacterWithCommaKey(evt){

            var charCode = (evt.which) ? evt.which : evt.keyCode;

           

            if ((charCode==8) || (charCode==9)|| (charCode==46) || (charCode==44) || (charCode==32))   //backspace,tap key,dot and comma

                return true;



            if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123))

                return true;



           return false;

}



function isAddressKey(evt){

            var charCode = (evt.which) ? evt.which : evt.keyCode;

           

            if ((charCode==32) || (charCode==8) || (charCode==9)|| (charCode==26) || ((charCode>=44) && (charCode<=47)))   //backspace,tap key,dot,#,+,-)(

                return true;



            if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || (charCode > 47 && charCode < 58))

                return true;



           return false;

}



function isAddressKeyWithRestrictKey(evt){

            var charCode = (evt.which) ? evt.which : evt.keyCode;

           

            if ((charCode==34) || (charCode==39) || (charCode==96))   //single and double quote

                return false;



           return true;

}



//-- Allows Positive and Negative Integers

function IntegerOnly(evt,obj){        

        var unicode=evt.charCode? evt.charCode : evt.keyCode

        if ((unicode==45)&&(evt.keyCode==0)){ //minus key

            var objValue = obj.value;

            if (strTrim(objValue).length > 0 ) return false;

            return true;

        }        

        if ((unicode==39)&&(evt.keyCode==39 )){ //Right Arrow key

            return true;

        }

        if ((unicode==37)&&(evt.keyCode==37 )){ //Left Arrow key

            return true;

        }           

        if ((unicode==46)&&(evt.keyCode==46 )){ //Delete key

            return true;

        }           

        if (unicode!=8 && unicode!=9){ //if the key isn't the backspace key (which we should allow)

            if (unicode<48||unicode>57 ) //if not a number

                return false; //disable key press

        } 

}



//-- Allows Positive and Negative Decimals

function DecimalOnly(evt,obj){        

        var unicode=evt.charCode? evt.charCode : evt.keyCode

        if ((unicode==45)&&(evt.keyCode==0)){ //minus key

            var objValue = obj.value;

            if (strTrim(objValue).length > 0 ) return false;

            return true;

        }     

        if ((unicode==46)&&(evt.keyCode==0)){ //dot (.) key

            var objValue = obj.value;

            if (objValue.indexOf('.') != -1 ) {

                alert('Decimal point already found!')

                return false;

            }

            return true;

        }

        if ((unicode==39)&&(evt.keyCode==39 )){ //Right Arrow key

            return true;

        }

        if ((unicode==37)&&(evt.keyCode==37 )){ //Left Arrow key

            return true;

        }           

        if ((unicode==46)&&(evt.keyCode==46 )){ //Delete key

            return true;

        }            

        if (unicode!=8 && unicode!=9){ //if the key isn't the backspace key (which we should allow)

            if (unicode<48||unicode>57 ) //if not a number

                return false; //disable key press

        } 

}



//this function waits till the user completes their entry in text box 

// and calls the function passes as argument

// Muralidharan 09-01-2014



var t;



function waitForCompletion( argCallbackFunction, waitTime )

{

    

  if ( t )

  {

    clearTimeout( t );

    t = setTimeout( argCallbackFunction, waitTime );

  }

  else

  {

    t = setTimeout( argCallbackFunction, waitTime );

  }

}

 function SignedDecimalNumbersOnly(e){
            var unicode=e.charCode? e.charCode : e.keyCode;
            if (e.charCode == "0" ){ // allowing all non-characters
              return true;
            }else{
                if(unicode!=46 && unicode!=45 ){// dot (.) // minus (-)
                    if (unicode<48||unicode>57 ) //if not a number
                        return false; //disable key press
                }
                if ((unicode ==46) && (!funCheckDotDuplication(e.target.id))) return false;
                if ((unicode ==45) && (!funCheckSignDuplication(e.target.id))) return false;
            }
            return true;
    }

    function PositiveDecimalNumbersOnly(e){
            var unicode=e.charCode? e.charCode : e.keyCode;
            if (e.charCode == "0" ){ // allowing all non-characters
              return true;
            }else{
                if(unicode!=46){// dot (.) // minus (-)
                    if (unicode<48||unicode>57 ) //if not a number
                        return false; //disable key press
                }
                if ((unicode ==46) && (!funCheckDotDuplication(e.target.id))) return false;
            }
            return true;
    }

    function funCheckDotDuplication(fieldName){
        var currVal=document.getElementById(fieldName).value;
        var ind1=currVal.indexOf('.');
        if ( ind1 != -1){
            return false;
        }
        return true;
    }
    function funCheckSignDuplication(fieldName){
        var currVal=document.getElementById(fieldName).value;
        var ind1=currVal.indexOf('-');
        if ( ind1 != -1){
            return false;
        }
        return true;
    }

    function truncate(floatValue){
        return (parseInt( floatValue*100 ) / 100).toFixed(2);
    }
    function getReplaceEnterKeyValue(textarea,replaceWith){
    var textareavalue=escape(textarea.value)
    for(i=0; i<textareavalue.length; i++){
            if(textareavalue.indexOf("%0D%0A") > -1){
            textareavalue=textareavalue.replace("%0D%0A",replaceWith)
            }
            else if(textareavalue.indexOf("%0A") > -1){
            textareavalue=textareavalue.replace("%0A",replaceWith)
            }
            else if(textareavalue.indexOf("%0D") > -1){
            textareavalue=textareavalue.replace("%0D",replaceWith)
            }
    }
    textareavalue=unescape(textareavalue);
    return textareavalue;
}
function checkEmptyReturnZero(str){
	if (document.getElementById(str).value==""){
        document.getElementById(str).value="0"
	}
}
function PositiveDecimalNumbersOnly(e){
            var unicode=e.charCode? e.charCode : e.keyCode;
            if (e.charCode == "0" ){ // allowing all non-characters
              return true;
            }else{
                if(unicode!=46){// dot (.) // minus (-)
                    if (unicode<48||unicode>57 ) //if not a number
                        return false; //disable key press
                }
                if ((unicode ==46) && (!funCheckDotDuplication(e.target.id))) return false;
            }
            return true;
    }