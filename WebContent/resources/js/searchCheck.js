function validate(event) {
	var myLength = document.getElementById("search-form:search").value.length;
	if (myLength < 3) {
		alert("Input must contain at least 3 characters")
		event.preventDefault();
		return false;

	} else {
		var string = document.getElementById('search-form:search').value;
		var re = new RegExp("^[a-zA-Z0-9.]*$");
		if (!re.test(string)) {
			alert("Input must contain only letters and digits.")
			event.preventDefault();
			return false;
		}
	}
}