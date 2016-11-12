function validateEmail(email){
	var regex = /[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,4}$/; 
	return regex.test(email);
}