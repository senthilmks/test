<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:useBean id="feedbackMsg" class="java.lang.String" scope="request"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>

<!--  <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/3.5.0/select2.min.css" rel="stylesheet"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/3.5.0/select2.min.js"></script>
 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>





<title>Hello World</title>
<style type="text/css">
<!--
.style1 {font-family: Calibri, Arial}

body{
  background-color: #455A64;
}

#multiWrapper {
  width: 300px;
  margin: 25px 0 0 25px;
}

.def-cursor {
  cursor: default;
}
-->
</style>

<script>
function formatResultMulti(data) {
  var city = $(data.element).data('city');
  var classAttr = $(data.element).attr('class');
  var hasClass = typeof classAttr != 'undefined';
  classAttr = hasClass ? ' ' + classAttr : '';

  var $result = $(
    '<div class="row">' +
    '<div class="col-md-6 col-xs-6' + classAttr + '">' + data.text + '</div>' +
    '<div class="col-md-6 col-xs-6' + classAttr + '">' + city + '</div>' +
    '</div>'
  );
  return $result;
}

$(function() {
  $('#multi').select2({
    width: '100%',
    templateResult: formatResultMulti
    //templateResult: myformatter
    
  });
  
  
  
  var deals= [{
	    "text": "JL - o yea - Testing this deal",
	    "id": 4,
	    "client_name": "JL",
	    "account_name": "o yea",
	    "deal_name": "Testing this deal"
	}, {
	    "text": "JL - test - fj;askld fj",
	    "id": 3,
	    "client_name": "JL",
	    "account_name": "test",
	    "deal_name": "fj;askld fj"
	}];
  
  $('#selectAccountDeal').select2({
	    data: deals,
	    width: '100%',
	    templateResult: formatSelect,
	    templateSelection: formatSelect,
	    escapeMarkup: function(m) { return m; },
	    matcher: matcher
	})
})

// Another select2




var firstEmptySelect = true;

function formatSelect(result) {
	console.log("result.id >> "+result.id);
    if (!result.id) {
        if (firstEmptySelect) {
            console.log('showing row');
            firstEmptySelect = false;
            return '<div class="row">' +
                    '<div class="col-xs-3"><b>Client</b></div>' +
                    '<div class="col-xs-3"><b>Account</b></div>' +
                    '<div class="col-xs-3"><b>Deal</b></div>' +
                    '</div>';
        } else {
            console.log('skipping row');
            return false;
        }
        console.log('result');
        console.log(result);
    }
    return '<div class="row">' +
                 '<div class="col-xs-3">' + result.client_name + '</div>' +
                 '<div class="col-xs-3">' + result.account_name + '</div>' +
                 '<div class="col-xs-3">' + result.deal_name + '</div>' +
                 '</div>';      
}

function matcher(query, option) {
    firstEmptySelect = true;
    if (!query.term) {
        return option;
    }
    var has = true;
    console.log("has >> "+has);
    var words = query.term.toUpperCase().split(" ");
    for (var i =0; i < words.length; i++){
        var word = words[i];
        has = has && (option.text.toUpperCase().indexOf(word) >= 0); 
    }
    if (has) return option;
    return false;
}


var fieldNames = [
    { fieldName: "JobNo", columnSize: 4 },
    { fieldName: "Description", columnSize: 8 }
];

//formatResult: function (item) {
	function myformatter (item) {
		
	console.log("item.length>> "+item.length);		

    var text = '<div class="row">';
    _.each(options.fieldNames, function (fieldName, index) {
        if (index === 0) {
            // If only an Id value was given, don't make it bold and give it the full width
            if (options.fieldNames.length === 1) {
                text = text + '<div class="col-md-12">' + item[fieldName.fieldName] + '</div>';
            } else {
                text = text + '<div class="col-md-' + fieldName.columnSize + '"><strong>' + item[fieldName.fieldName] + '</strong></div>';
            }
        } else {
            if (item[fieldName.fieldName] === undefined)
                item[fieldName.fieldName] = '';
            text = text + '<div class="col-md-' + fieldName.columnSize + '">' + item[fieldName.fieldName] + '</div>';
        }
    });
    return text + '</div>';
}

	
	// sample 3
	
	var headerIsAppend = false;
    $('#sample3').on('select2:open', function (e) {
        if (!headerIsAppend) {
            html = '<table class="table table-bordered" style="margin-top: 5px;margin-bottom: 0px;">'+
                '<tbody><tr><td width="20%"><b>Header1</b></td>'+
                    '<td width="10%"><b>header2</b></td>'+
                    '<td width="20%"><b>Header3</b></td>'+
                    '<td width="10%"><b>Header4</b></td>'+
                    '<td width="20%"><b>Header5</b></td>'+
                    '<td width="20%"><b>Header6</b></td>'+
                '</tr > </tbody></table>';
            $('.select2-search').append(html);
            $('.select2-results').addClass('stock');
            headerIsAppend = true;
        }
    });
    
    
  //  templateResult: function (repo) {
	    function sample3formatter (repo) {
        if (repo.medicine) {
            var html = '<table class="table table-bordered" style="margin-bottom: 0px;"> <tbody><tr> <td width="20%">' 
            + repo.medicine.name + '</td> <td width="10%">' 
            + repo.costPrice + '</td> <td width="20%">' 
            + repo.vendor + '</td><td width="10%">' 
            + repo.batchNum + '</td><td width="20%">' 
            + repo.expiredDate + '</td><td width="20%">' 
            + repo.quantity + '</td></tr > </tbody></table>';
            return $(html);
        }
    }

</script>
</head>

<body>
<div id='multiWrapper'>
  <select id="multi">
    <optgroup class='def-cursor' label='Country' data-city='City'>
      <option data-city="Athens" id="1" selected>Greece</option>
      <option data-city="Rome" "id="2 ">Italy</option>
  <option data-city="Paris " "id="3">France</option>
    </optgroup>
  </select>
  
  </br>
  </br>
  
  <select id="sample3"></select>
  
  <select id="selectAccountDeal" name="account_deal_id" placeholder="Select your Deal" required>
  <option></option>
</select>
</div>
</body>
</html>
