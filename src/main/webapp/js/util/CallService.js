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
								alertError(errResponse.statusText + " - "
										+ errResponse.status);
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
								alertError(errResponse.statusText + " - "
										+ errResponse.status);
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
								alertError(errResponse.statusText + " - "
										+ errResponse.status);
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
								alertError(errResponse.statusText + " - "
										+ errResponse.status);
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
								alertError(errResponse.statusText + " - "
										+ errResponse.status);
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
								alertError(errResponse.statusText + " - "
										+ errResponse.status);
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
								alertError(errResponse.statusText + " - "
										+ errResponse.status);
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
								alertError(errResponse.statusText + " - "
										+ errResponse.status);
								Loader.hideLoader();
								return $q.reject(errResponse);
							});
				},

			};
		} ]);
