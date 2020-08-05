'use strict';
app.factory('FileUploadUtil', [
		'CallService',
		'$cacheFactory',
		function(CallService, $cacheFactory) {

			var prefixUrl = './File';

			var header = {
				transformRequest : angular.identity,
				headers : {
					'Content-Type' : undefined
				}
			}

			return {
				uploadFile : function(file) {
					var url = prefixUrl + '/uploadFile';
					var request = new FormData();
					request.append("file", file);
					return CallService.postHeader(url, request, header);
				},
				uploadFileWithThumbnail : function(file) {
					var url = prefixUrl + '/uploadFileWithThumbnail';
					var request = new FormData();
					request.append("file", file);
					return CallService.postHeader(url, request, header);
				},
				uploadBatchFile : function(files) {
					var url = prefixUrl + '/uploadBatchFile';
					var request = new FormData();
					request.append("files", files);
					return CallService.postHeader(url, request, header);
				},
				uploadBatchFileWithThumbnail : function(files) {
					var url = prefixUrl + '/uploadBatchFileWithThumbnail';
					var request = new FormData();
					request.append("files", files);
					return CallService.postHeader(url, request, header);
				},
				getImage : function(fileName, mimeType) {
					return CallService.get(prefixUrl + "/getImage/" + fileName
							+ "/" + mimeType);
				},
				getImageThumbnail : function(fileName, mimeType) {
					return CallService.get(prefixUrl + "/getImageThumbnail/"
							+ fileName + "/" + mimeType);
				}

			};

		} ]);