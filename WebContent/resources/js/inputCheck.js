//function checker(event) {
//	if (document.getElementById('dirID').value == '') {
//
//		alert("You must type a directory first")
//		event.preventDefault();
//		return false;
//	} else return true;
//
//}

$(document).on('click', '.loadBtn', function(event) {

	if ($('.directory').val() == '') {
		alert("You must type a directory first")
		event.preventDefault();
		return false;
	} else
		return true;

})

$(document).on('click', '.extractBtn', function(event) {

	if ($('.directory').val() == '') {
		alert("You must type a directory first")
		event.preventDefault();
		return false;
	} else
		return true;
})
