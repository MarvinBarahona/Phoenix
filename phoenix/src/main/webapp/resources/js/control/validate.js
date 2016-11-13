function validateEmail(email){
	//Valida email con @ y uno o más (.)
	var regex = /[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,4}$/; 
	return regex.test(email);
}

function validateLengthPassword(password){
	return password.length >= 8;
}

function validateFormatPassword(password){
	//Valida la contraseña con solo caracteres y al menos un número
	var regex = /(([A-Za-z]?[0-9]+[A-Za-z]+)+|([A-Za-z]+[0-9]+[A-Za-z]?)+)/;	
	return regex.test(password);
}

function validateName(name){
	//Valida que el nombre inicie el mayúscula.
	var regex = /^[A-Z][a-z]{1,}$/;
	return regex.test(name);
}