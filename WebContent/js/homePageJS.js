$(document).on('click', '.submitBtn', function(event) {
	event.preventDefault();

	if ($('.directory').val() == '') {
		alert("You must type a directory first")
	} else {
		document["main-form"].action = "/WebDirCont/contentExtract?name=btn1";
		$('.submitForm').submit();
	}
})

$(document).on('click', '.DBLoadBtn', function(event) {
	event.preventDefault();

	if ($('.directory').val() == '') {
		alert("You must type a directory first")
	} else {
		document["main-form"].action = "/WebDirCont/contentExtract?name=btn2";
		$('.submitForm').submit();
	}
})

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