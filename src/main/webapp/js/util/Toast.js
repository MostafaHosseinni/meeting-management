function alertSuccess(title, message, duration) {

	toastr.options = {
		closeButton : true,
		debug : false,
		newestOnTop : true,
		progressBar : true,
		positionClass : 'toast-top-right',
		preventDuplicates : false,
		onclick : null,
		timeOut : duration
	};

	toastr.success(message, title);
}

function alertSuccess(message, duration) {

	toastr.options = {
		closeButton : true,
		debug : false,
		newestOnTop : true,
		progressBar : true,
		positionClass : 'toast-top-right',
		preventDuplicates : false,
		onclick : null,
		timeOut : duration
	};

	toastr.success(message, "");
}

function alertSuccess(message) {
	
	toastr.options = {
			closeButton : true,
			debug : false,
			newestOnTop : true,
			progressBar : true,
			positionClass : 'toast-top-right',
			preventDuplicates : false,
			onclick : null,
			timeOut : 3000
	};
	
	toastr.success(message, "");
}

function alertInfo(title, message, duration) {
	
	toastr.options = {
			closeButton : true,
			debug : false,
			newestOnTop : true,
			progressBar : true,
			positionClass : 'toast-top-right',
			preventDuplicates : false,
			onclick : null,
			timeOut : duration
	};
	
	toastr.info(message, title);
}

function alertInfo(message, duration) {
	
	toastr.options = {
			closeButton : true,
			debug : false,
			newestOnTop : true,
			progressBar : true,
			positionClass : 'toast-top-right',
			preventDuplicates : false,
			onclick : null,
			timeOut : duration
	};
	
	toastr.info(message, "");
}

function alertInfo(message) {
	
	toastr.options = {
			closeButton : true,
			debug : false,
			newestOnTop : true,
			progressBar : true,
			positionClass : 'toast-top-right',
			preventDuplicates : false,
			onclick : null,
			timeOut : 3000
	};
	
	toastr.info(message, "");
}

function alertWarning(title, message, duration) {
	
	toastr.options = {
			closeButton : true,
			debug : false,
			newestOnTop : true,
			progressBar : true,
			positionClass : 'toast-top-right',
			preventDuplicates : false,
			onclick : null,
			timeOut : duration
	};
	
	toastr.warning(message, title);
}

function alertWarning(message, duration) {
	
	toastr.options = {
			closeButton : true,
			debug : false,
			newestOnTop : true,
			progressBar : true,
			positionClass : 'toast-top-right',
			preventDuplicates : false,
			onclick : null,
			timeOut : duration
	};
	
	toastr.warning(message, "");
}

function alertWarning(message) {
	
	toastr.options = {
			closeButton : true,
			debug : false,
			newestOnTop : true,
			progressBar : true,
			positionClass : 'toast-top-right',
			preventDuplicates : false,
			onclick : null,
			timeOut : 3000
	};
	
	toastr.warning(message, "");
}

function alertError(title, message, duration) {
	
	toastr.options = {
			closeButton : true,
			debug : false,
			newestOnTop : true,
			progressBar : true,
			positionClass : 'toast-top-right',
			preventDuplicates : false,
			onclick : null,
			timeOut : duration
	};
	
	toastr.error(message, title);
}

function alertError(message, duration) {
	
	toastr.options = {
			closeButton : true,
			debug : false,
			newestOnTop : true,
			progressBar : true,
			positionClass : 'toast-top-right',
			preventDuplicates : false,
			onclick : null,
			timeOut : duration
	};
	
	toastr.error(message, "");
}

function alertError(message) {
	
	toastr.options = {
			closeButton : true,
			debug : false,
			newestOnTop : true,
			progressBar : true,
			positionClass : 'toast-top-right',
			preventDuplicates : false,
			onclick : null,
			timeOut : 3000
	};
	
	toastr.error(message, "");
}