function emptyInputCheck(event) {
	if (document.getElementById('dirID').value == '') {

		alert("You must type a directory first")
		event.preventDefault();
		return false;
	} else return true;

}

//$(document).on('click', '.loadBtn', function(event) {
//	event.preventDefault();
//
//	if ($('.directory').val() == '') {
//		alert("You must type a directory first")
//	}
//})

function validate(event) {
	if (document.getElementById('searchID').value.length < 3) {
		alert("Input must contain at least 3 characters")
		event.preventDefault();
		return false;

	} else {
		var string = document.getElementById('searchID').value;
		var re = new RegExp("^[a-zA-Z0-9.]*$");
		if (!re.test(string)) {
			alert("Input must contain only letters and digits.")
			event.preventDefault();
			return false;
		}
	}
	return true;
}