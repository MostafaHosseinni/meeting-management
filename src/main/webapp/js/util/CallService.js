'use strict';
app.factory('CallService', [
		'$http',
		'$q',
		'Loader',
		function($http, $q, Loader) {

			return {
				post : function(url, data) {
					Loader.showLoader();
					return $http.post(url, data).then(
							function(response) {
								Loader.hideLoader();
								return response.data;
							},
							function(errResponse) {
								alertError(convertError(errResponse));
								Loader.hideLoader();
								return $q.reject(errResponse);
							});
				},
				postHeader : function(url, data , header) {
					Loader.showLoader();
					return $http.post(url, data , header).then(
							function(response) {
								Loader.hideLoader();
								return response.data;
							},
							function(errResponse) {
								alertError(convertError(errResponse));
								Loader.hideLoader();
								return $q.reject(errResponse);
							});
				},
				put : function(url, data) {
					Loader.showLoader();
					return $http.put(url, data).then(
							function(response) {
								Loader.hideLoader();
								return response.data;
							},
							function(errResponse) {
								alertError(convertError(errResponse));
								Loader.hideLoader();
								return $q.reject(errResponse);
							});
				},
				get : function(url) {
					Loader.showLoader();
					return $http.get(url).then(
							function(response) {
								Loader.hideLoader();
								return response.data;
							},
							function(errResponse) {
								alertError(convertError(errResponse));
								Loader.hideLoader();
								return $q.reject(errResponse);
							});
				},
				getByResponse : function(url) {
					Loader.showLoader();
					return $http.get(url).then(
							function(response) {
								Loader.hideLoader()
								return response;
							},
							function(errResponse) {
								alertError(convertError(errResponse));
								Loader.hideLoader();
								return $q.reject(errResponse);
							});
				},
				postByResponse : function(url , data) {
					Loader.showLoader();
					return $http.post(url , data).then(
							function(response) {
								Loader.hideLoader()
								return response;
							},
							function(errResponse) {
								alertError(convertError(errResponse));
								Loader.hideLoader();
								return $q.reject(errResponse);
							});
				},
				remove : function(url) {
					Loader.showLoader();
					return $http.delete(url).then(
							function(response) {
								Loader.hideLoader();
								return response.data;
							},
							function(errResponse) {
								alertError(convertError(errResponse));
								Loader.hideLoader();
								return $q.reject(errResponse);
							});
				},
				
				getCachable : function(url) {
					Loader.showLoader();
					return $http.get(url, {
						cache : true
					}).then(
							function(response) {
								Loader.hideLoader();
								return response.data;
							},
							function(errResponse) {
								alertError(convertError(errResponse));
								Loader.hideLoader();
								return $q.reject(errResponse);
							});
				},

			};
		} ]);

function convertError(errResponse){
	
	
	errResponse.statusText = errResponse.headers('X-eApp-alert');
	errResponse.exceptionType = errResponse.headers('X-eApp-exceptionType');
	errResponse.erorrType = errResponse.headers('X-eApp-erorrType');
	
	if(errResponse.exceptionType == 'db'){
		if(errResponse.erorrType == 'notNull'){
			return "خطا در درج اطلاعات، امکان درج اطلاعات خالی میسر نمی باشد";
		}
		else if(errResponse.erorrType == 'uniq'){
			return "خطا در درج اطلاعات، امکان درج اطلاعات تکراری وجود ندارد";
		}
		else{
			return "خطا در درج اطلاعات، یک خطای ناشناخته در درج اطلاعات داخل پایگاه داده رخ داد";
		}
	}else if(errResponse.exceptionType == 'ac'){
		return "خطای دسترسی ، شما اجازه انجام این عملیات را ندارید";
	}else if(errResponse.exceptionType == 'un'){
		return "خطای ناشناخته سمت سرور رخ داد";
	}else if(errResponse.exceptionType == 'Validation failed'){
		return "زمان را در بازه ی زمانی 00 تا 24 تعریف کنید";
	}

	if(errResponse.statusText == "" || !errResponse.statusText){
			if(errResponse.status ==200) errResponse.statusText = 'موفقیت آمیز'
		    if(errResponse.status ==201) errResponse.statusText = 'ایجاد شد'
		    if(errResponse.status ==202) errResponse.statusText = 'پذیرفته شد'
		    if(errResponse.status ==203) errResponse.statusText = 'Non-Authoritative Information'
		    if(errResponse.status ==204) errResponse.statusText = 'بدون محتوا'
		    if(errResponse.status ==205) errResponse.statusText = 'باز راه اندازی محتوا'
		    if(errResponse.status ==206) errResponse.statusText = 'محتوای ناقص'
		    if(errResponse.status ==300) errResponse.statusText = 'Multiple Choices'
		    if(errResponse.status ==301) errResponse.statusText = 'Moved Permanently'
		    if(errResponse.status ==302) errResponse.statusText = 'Found'
		    if(errResponse.status ==303) errResponse.statusText = 'See Other'
		    if(errResponse.status ==304) errResponse.statusText = 'Not Modified'
		    if(errResponse.status ==305) errResponse.statusText = 'Use Proxy'
		    if(errResponse.status ==306) errResponse.statusText = 'Unused'
		    if(errResponse.status ==307) errResponse.statusText = 'Temporary Redirect'
		    if(errResponse.status ==400) errResponse.statusText = 'Bad Request'
		    if(errResponse.status ==401) errResponse.statusText = 'Unauthorized'
		    if(errResponse.status ==402) errResponse.statusText = 'Payment Required'
		    if(errResponse.status ==403) errResponse.statusText = 'عدم دسترسی'
		    if(errResponse.status ==404) errResponse.statusText = 'یافت نشد'
		    if(errResponse.status ==405) errResponse.statusText = 'Method Not Allowed'
		    if(errResponse.status ==406) errResponse.statusText = 'Not Acceptable'
		    if(errResponse.status ==407) errResponse.statusText = 'Proxy Authentication Required'
		    if(errResponse.status ==408) errResponse.statusText = 'Request Timeout'
		    if(errResponse.status ==409) errResponse.statusText = 'Conflict'
		    if(errResponse.status ==410) errResponse.statusText = 'Gone'
		    if(errResponse.status ==411) errResponse.statusText = 'Length Required'
		    if(errResponse.status ==412) errResponse.statusText = 'Precondition Required'
		    if(errResponse.status ==413) errResponse.statusText = 'Request Entry Too Large'
		    if(errResponse.status ==414) errResponse.statusText = 'Request-URI Too Long'
		    if(errResponse.status ==415) errResponse.statusText = 'Unsupported Media Type'
		    if(errResponse.status ==416) errResponse.statusText = 'Requested Range Not Satisfiable'
		    if(errResponse.status ==417) errResponse.statusText = 'Expectation Failed'
		    if(errResponse.status ==418) errResponse.statusText = 'I\'m a teapot'
		    if(errResponse.status ==500) errResponse.statusText = 'خطای ناشناخته سمت سرور رخ داد'
		    if(errResponse.status ==501) errResponse.statusText = 'Not Implemented'
		    if(errResponse.status ==502) errResponse.statusText = 'Bad Gateway'
		    if(errResponse.status ==503) errResponse.statusText = 'Service Unavailable'
		    if(errResponse.status ==504) errResponse.statusText = 'Gateway Timeout'
		    if(errResponse.status ==505) errResponse.statusText = 'HTTP Version Not Supported'
		
	}
	return errResponse.statusText + " - "
	+ errResponse.status;
	
}
